



void cf (void *);

void *
af (void *a)
{
  return a;
}
void
bf (void)
{
  void *p;
  {
    int i = 1;
    char v[i];
    p = af (v);
  }
  cf (p);
}
