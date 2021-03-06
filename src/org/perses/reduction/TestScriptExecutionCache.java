/*
 * Copyright (C) 2018-2020 University of Waterloo.
 *
 * This file is part of Perses.
 *
 * Perses is free software; you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 3, or (at your option) any later version.
 *
 * Perses is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Perses; see the file LICENSE.  If not see <http://www.gnu.org/licenses/>.
 */
package org.perses.reduction;

import com.google.common.collect.ImmutableList;
import org.perses.program.PersesToken;
import org.perses.program.TokenizedProgram;
import org.perses.reduction.TestScript.TestResult;
import org.perses.reduction.TokenizedProgramEncoder.CompactProgramEncoding;
import org.perses.util.Fraction;

import java.util.*;

import static org.perses.reduction.AbstractTestScriptExecutionCacheProfiler.now;

public final class TestScriptExecutionCache extends AbstractTestScriptExecutionCache {

  private final HashMap<CompactProgramEncoding, TestResult> cache = new HashMap<>();
  private TokenizedProgramEncoder encoder;
  private final AbstractTestScriptExecutionCacheProfiler profiler;
  private final int refreshStep;

  public TestScriptExecutionCache(
      TokenizedProgram currentBestProgram,
      AbstractTestScriptExecutionCacheProfiler profiler,
      Fraction refreshStepFraction) {
    // Record the time to create an encoder, to
    encoder = new TokenizedProgramEncoder(currentBestProgram.getTokens(), profiler);
    this.profiler = profiler;
    this.refreshStep = refreshStepFraction.multiply(currentBestProgram.tokenCount());
  }

  @Override
  public Optional<TestResult> getCachedResult(TokenizedProgram program) {
    final CompactProgramEncoding key = encoder.encode(program).get();
    return Optional.ofNullable(cache.get(key));
  }

  @Override
  void addResult(TokenizedProgram program, TestResult result) {
    final CompactProgramEncoding key = encoder.encode(program).get();
    assert !cache.containsKey(key);
    cache.put(key, result);
  }

  @Override
  public int size() {
    return cache.size();
  }

  @Override
  public int memoryFootprintInBytes() {
    int result = 0;
    for (Map.Entry<CompactProgramEncoding, TestResult> entry : cache.entrySet()) {
      result += entry.getKey().memoryFootprintInBytes() + 4;
    }
    return result;
  }

  private final boolean needsHeavyWeightCleanup(
      ImmutableList<PersesToken> programInEncoder, TokenizedProgram currentBestProgram) {
    final int oldSize = programInEncoder.size();
    final int newSize = currentBestProgram.tokenCount();
    return oldSize - newSize >= refreshStep;
  }

  @Override
  public void evictEntriesLargerThan(TokenizedProgram best) {
    if (needsHeavyWeightCleanup(encoder.getTokensInOrigin(), best)) {
      heavyweightCleanup(best);
    } else {
      lightweightCleanup(best);
    }
  }

  void heavyweightCleanup(TokenizedProgram best) {
    final long startTime = now();
    final ArrayList<CompactProgramEncoding> newKeys = new ArrayList<>();
    final ArrayList<TestResult> newValues = new ArrayList<>();
    final int bestTokenCount = best.tokenCount();
    final TokenizedProgramEncoder oldEncoder = encoder;
    final TokenizedProgramEncoder newEncoder =
        new TokenizedProgramEncoder(best.getTokens(), profiler);
    encoder = newEncoder;
    final int cacheSizeBefore = cache.size();

    final Iterator<Map.Entry<CompactProgramEncoding, TestResult>> iter =
        cache.entrySet().iterator();
    while (iter.hasNext()) {
      Map.Entry<CompactProgramEncoding, TestResult> entry = iter.next();
      final CompactProgramEncoding oldEncoding = entry.getKey();
      if (oldEncoding.getTokenCount() >= bestTokenCount) {
        // The cache program is ge the best program, and will not be used.
        iter.remove();
        continue;
      }
      final TokenizedProgram decodedProgram = oldEncoder.decode(oldEncoding);
      final Optional<CompactProgramEncoding> optionalNewEncoding =
          newEncoder.encode(decodedProgram);
      if (!optionalNewEncoding.isPresent()) {
        // This program will no longer be generated in the future.
        iter.remove();
        continue;
      }
      final CompactProgramEncoding newEncoding = optionalNewEncoding.get();
      if (newEncoder.equals(oldEncoding)) {
        // Keep the old encoding, as it is the same as the new one.
        continue;
      } else {
        // The old encoding becomes stale. Remove it, and the new one will be added.
        iter.remove();
        newKeys.add(newEncoding);
        newValues.add(entry.getValue());
      }
    }
    assert newKeys.size() == newValues.size();
    final int newKeySize = newKeys.size();
    for (int i = 0; i < newKeySize; ++i) {
      cache.put(newKeys.get(i), newValues.get(i));
    }
    final long endTime = now();
    profiler.onHeavyweightCacheRefreshing(
        oldEncoder.getTokensInOrigin(),
        best.getTokens(),
        cacheSizeBefore,
        cache.size(),
        startTime,
        endTime);
  }

  void lightweightCleanup(TokenizedProgram best) {
    final Iterator<Map.Entry<CompactProgramEncoding, TestResult>> iter =
        cache.entrySet().iterator();
    final int tokenCount = best.tokenCount();
    while (iter.hasNext()) {
      Map.Entry<CompactProgramEncoding, TestResult> entry = iter.next();
      if (entry.getKey().getTokenCount() >= tokenCount) {
        iter.remove();
      }
    }
  }
}
