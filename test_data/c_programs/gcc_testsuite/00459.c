



void foo(double *a, double *b, double *c, double *d)
{
  a[0] = b[0] + c[0] * d[0];
  a[1] = b[1] - c[1] * d[1];
  a[2] = -b[2] + c[2] * d[2];
  a[3] = -b[3] - c[3] * d[3];
  a[4] = -( b[4] + c[4] * d[4]);
  a[5] = -( b[5] - c[5] * d[5]);
  a[6] = -(-b[6] + c[6] * d[6]);
  a[7] = -(-b[7] - c[7] * d[7]);
  a[10] = b[10] - c[10] * -d[10];
  a[11] = b[11] + c[11] * -d[11];
  a[12] = -b[12] - c[12] * -d[12];
  a[13] = -b[13] + c[13] * -d[13];
  a[14] = -( b[14] - c[14] * -d[14]);
  a[15] = -( b[15] + c[15] * -d[15]);
  a[16] = -(-b[16] - c[16] * -d[16]);
  a[17] = -(-b[17] + c[17] * -d[17]);
}

void foos(float *a, float *b, float *c, float *d)
{
  a[0] = b[0] + c[0] * d[0];
  a[1] = b[1] - c[1] * d[1];
  a[2] = -b[2] + c[2] * d[2];
  a[3] = -b[3] - c[3] * d[3];
  a[4] = -( b[4] + c[4] * d[4]);
  a[5] = -( b[5] - c[5] * d[5]);
  a[6] = -(-b[6] + c[6] * d[6]);
  a[7] = -(-b[7] - c[7] * d[7]);
  a[10] = b[10] - c[10] * -d[10];
  a[11] = b[11] + c[11] * -d[11];
  a[12] = -b[12] - c[12] * -d[12];
  a[13] = -b[13] + c[13] * -d[13];
  a[14] = -( b[14] - c[14] * -d[14]);
  a[15] = -( b[15] + c[15] * -d[15]);
  a[16] = -(-b[16] - c[16] * -d[16]);
  a[17] = -(-b[17] + c[17] * -d[17]);
}