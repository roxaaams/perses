


struct B {
    unsigned bit0 : 1;
    unsigned bit1 : 1;
};

void
foo (struct B *b)
{
  b->bit0 = b->bit0 | b->bit1;
}