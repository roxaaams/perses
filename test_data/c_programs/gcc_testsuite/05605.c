


struct {
 int i;
 struct {
  struct {
   int i;
  } x[2];
 } b;
} a;

int foo(void)
{
 a.i = 1;
 a.b.x[0].i = 0;
 a.b.x[1].i = 1;
 return a.i + a.b.x[0].i;
}