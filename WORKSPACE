load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

RULES_JVM_EXTERNAL_TAG = "2.6"

RULES_JVM_EXTERNAL_SHA = "064b9085b21c349c8bd8be015a73efd6226dd2ff7d474797b3507ceca29544bb"

http_archive(
    name = "rules_jvm_external",
    sha256 = RULES_JVM_EXTERNAL_SHA,
    strip_prefix = "rules_jvm_external-%s" % RULES_JVM_EXTERNAL_TAG,
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/%s.zip" % RULES_JVM_EXTERNAL_TAG,
)

load("@rules_jvm_external//:defs.bzl", "maven_install")

maven_install(
    name = "maven",
    artifacts = [
        "com.beust:jcommander:1.78",
        "com.google.errorprone:error_prone_annotations:2.3.4",
        "com.google.flogger:flogger-system-backend:0.4",
        "com.google.flogger:flogger:0.4",
        "com.google.guava:guava:28.1-jre",
        "com.google.truth:truth:1.0.1",
        "com.googlecode.java-diff-utils:diffutils:1.3.0",
        "it.unimi.dsi:fastutil:8.3.0",
        "me.lemire.integercompression:JavaFastPFOR:0.1.9",
        "org.antlr:antlr4-runtime:4.8-1",
        "org.antlr:antlr4:4.8-1",
        "org.apache.commons:commons-exec:1.3",
        "org.apache.commons:commons-lang3:3.9",
        "org.checkerframework:checker-qual:2.11.0",
        "org.jfree:jfreechart:1.5.0",
        "org.jgrapht:jgrapht-core:1.3.0",
        # Do not include org.jgrapht:jgrapht-io. It depends on antlr4-runtime:4.7.1
    ],
    fetch_sources = True,
    repositories = [
        "https://jcenter.bintray.com",
        "https://maven.google.com",
        "https://repo1.maven.org/maven2",
    ],
)

rules_kotlin_version = "legacy-1.3.0"

rules_kotlin_sha = "4fd769fb0db5d3c6240df8a9500515775101964eebdf85a3f9f0511130885fde"

http_archive(
    name = "io_bazel_rules_kotlin",
    sha256 = rules_kotlin_sha,
    strip_prefix = "rules_kotlin-%s" % rules_kotlin_version,
    type = "zip",
    urls = ["https://github.com/bazelbuild/rules_kotlin/archive/%s.zip" % rules_kotlin_version],
)

load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kotlin_repositories", "kt_register_toolchains")

kotlin_repositories()  # if you want the default. Otherwise see custom kotlinc distribution below

kt_register_toolchains()  # to use the default toolchain, otherwise see toolchains below
