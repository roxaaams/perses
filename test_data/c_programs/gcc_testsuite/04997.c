extern int foo (void);

int
fn1 (int n)
{
  if (n == 1)
    return -1;
  else if (n == 2)
    return 0;
  else if (n == 1)
    return 1;
  return 0;
}

int
fn2 (void)
{
  if (4)
    return 1;
  else if (4)
    return 2;


  if (10)
    return 3;
  else if (10)
    return 4;
}

int
fn3 (int n)
{
  if (n == 42)
    return 1;
  if (n == 42)
    return 2;

  if (n)
    if (n)
      if (n)
 if (n)
   return 42;

  if (!n)
    return 10;
  else
    return 11;
}

int
fn4 (int n)
{
  if (n > 0)
    {
      if (n == 1)
 return 1;
      else if (n == 1)
 return 2;
    }
  else if (n < 0)
    {
      if (n < -1)
 return 6;
      else if (n < -2)
 {
   if (n == -10)
     return 3;
   else if (n == -10)
     return 4;
 }
    }
  else
    return 7;
  return 0;
}

struct S { long p, q; };

int
fn5 (struct S *s)
{
  if (!s->p)
    return 12345;
  else if (!s->p)
    return 1234;
  return 0;
}

int
fn6 (int n)
{
  if (n)
    return n;
  else if (n)
    return n;
  else if (n)
    return n;
  else if (n)
    return n;
  else if (n)
    return n;
  else if (n)
    return n;
  else if (n)
    return n;
  else if (n)
    return n;
  return 0;
}

int
fn7 (int n)
{
  if (n == 0)
    return 10;
  else if (n == 1)
    return 11;
  else if (n == 2)
    return 12;
  else if (n == 3)
    return 13;
  else if (n == 4)
    return 14;
  else if (n == 5)
    return 15;
  else if (n == 6)
    return 16;
  else if (n == 7)
    return 17;
  else if (n == 0)
    return 100;
  else if (n == 1)
    return 101;
  else if (n == 2)
    return 102;
  else if (n == 3)
    return 103;
  else if (n == 4)
    return 104;
  else if (n == 5)
    return 105;
  else if (n == 6)
    return 106;
  else if (n == 7)
    return 107;
  return 0;
}

int
fn8 (_Bool b)
{
  if (!b)
    return 16;
  else if (!b)
    return 27;
  else
    return 64;
}

int
fn9 (int i, int j, int k)
{
  if ((i > 0 && j > 0 && k > 0)
      && ((i > 11 && j == 76 && k < 10)
   || (i < 0 && j == 99 && k > 103)))
    return -999;
  else
  if ((i > 0 && j > 0 && k > 0)
      && ((i > 11 && j == 76 && k < 10)
   || (i < 0 && j == 99 && k > 103)))
    return 999;
  else
    return 0;
}

int
fn10 (void)
{
  if (foo ())
    return 1732984;
  else if (foo ())
    return 18409;
  return 0;
}

int
fn11 (int n)
{
  if (++n == 10)
    return 666;
  else if (++n == 10)
    return 9;
  return 0;
}