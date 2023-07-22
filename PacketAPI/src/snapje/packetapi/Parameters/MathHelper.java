package snapje.packetapi.Parameters;

import java.util.Random;
import java.util.UUID;

public class MathHelper {
	
	/*
	 *  MathHelper class net.minecraft.server.v1_8_R3.MathHelper
	 */
	
	public static final float a = c(2.0F);
	private static final float b[];
	private static final int c[] = {0, 1, 28, 2, 29, 14, 24, 3, 30, 22, 20, 15, 25, 17, 4, 8, 31, 27, 13, 23, 21, 19,
			16, 7, 26, 12, 18, 6, 11, 5, 10, 9};
	private static final double d = Double.longBitsToDouble(4805340802404319232L);
	private static final double e[];
	private static final double f[];

	public static float sin(float f1) {
		return b[(int) (f1 * 10430.38F) & 65535];
	}

	public static float cos(float f1) {
		return b[(int) (f1 * 10430.38F + 16384F) & 65535];
	}

	public static float c(float f1) {
		return (float) Math.sqrt(f1);
	}

	public static float sqrt(double d1) {
		return (float) Math.sqrt(d1);
	}

	public static int d(float f1) {
		int j = (int) f1;
		return f1 >= (float) j ? j : j - 1;
	}

	public static int floor(double d1) {
		int j = (int) d1;
		return d1 >= (double) j ? j : j - 1;
	}

	public static long d(double d1) {
		long l = (long) d1;
		return d1 >= (double) l ? l : l - 1L;
	}

	public static float e(float f1) {
		return f1 < 0.0F ? -f1 : f1;
	}

	public static int a(int j) {
		return j < 0 ? -j : j;
	}

	public static int f(float f1) {
		int j = (int) f1;
		return f1 <= (float) j ? j : j + 1;
	}

	public static int f(double d1) {
		int j = (int) d1;
		return d1 <= (double) j ? j : j + 1;
	}

	public static int clamp(int j, int k, int l) {
		if (j < k)
			return k;
		if (j > l)
			return l;
		else
			return j;
	}

	public static float a(float f1, float f2, float f3) {
		if (f1 < f2)
			return f2;
		if (f1 > f3)
			return f3;
		else
			return f1;
	}

	public static double a(double d1, double d2, double d3) {
		if (d1 < d2)
			return d2;
		if (d1 > d3)
			return d3;
		else
			return d1;
	}

	public static double b(double d1, double d2, double d3) {
		if (d3 < 0.0D)
			return d1;
		if (d3 > 1.0D)
			return d2;
		else
			return d1 + (d2 - d1) * d3;
	}

	public static double a(double d1, double d2) {
		if (d1 < 0.0D)
			d1 = -d1;
		if (d2 < 0.0D)
			d2 = -d2;
		return d1 <= d2 ? d2 : d1;
	}

	public static int nextInt(Random random, int j, int k) {
		if (j >= k)
			return j;
		else
			return random.nextInt((k - j) + 1) + j;
	}

	public static float a(Random random, float f1, float f2) {
		if (f1 >= f2)
			return f1;
		else
			return random.nextFloat() * (f2 - f1) + f1;
	}

	public static double a(Random random, double d1, double d2) {
		if (d1 >= d2)
			return d1;
		else
			return random.nextDouble() * (d2 - d1) + d1;
	}

	public static double a(long al[]) {
		long l = 0L;
		long al1[] = al;
		int j = al1.length;
		for (int k = 0; k < j; k++) {
			long l1 = al1[k];
			l += l1;
		}

		return (double) l / (double) al.length;
	}

	public static float g(float f1) {
		f1 %= 360F;
		if (f1 >= 180F)
			f1 -= 360F;
		if (f1 < -180F)
			f1 += 360F;
		return f1;
	}

	public static double g(double d1) {
		d1 %= 360D;
		if (d1 >= 180D)
			d1 -= 360D;
		if (d1 < -180D)
			d1 += 360D;
		return d1;
	}

	public static int a(String s, int j) {
		try {
			return Integer.parseInt(s);
		} catch (Throwable throwable) {
			return j;
		}
	}

	public static int a(String s, int j, int k) {
		return Math.max(k, a(s, j));
	}

	public static double a(String s, double d1) {
		try {
			return Double.parseDouble(s);
		} catch (Throwable throwable) {
			return d1;
		}
	}

	public static double a(String s, double d1, double d2) {
		return Math.max(d2, a(s, d1));
	}

	public static int b(int j) {
		int k = j - 1;
		k |= k >> 1;
		k |= k >> 2;
		k |= k >> 4;
		k |= k >> 8;
		k |= k >> 16;
		return k + 1;
	}

	private static boolean d(int j) {
		return j != 0 && (j & j - 1) == 0;
	}

	private static int e(int j) {
		j = d(j) ? j : b(j);
		return c[(int) ((long) j * 125613361L >> 27) & 31];
	}

	public static int c(int j) {
		return e(j) - (d(j) ? 0 : 1);
	}

	public static int c(int j, int k) {
		if (k == 0)
			return 0;
		if (j == 0)
			return k;
		if (j < 0)
			k *= -1;
		int l = j % k;
		if (l == 0)
			return j;
		else
			return (j + k) - l;
	}

	public static UUID a(Random random) {
		long l = random.nextLong() & -61441L | 16384L;
		long l1 = random.nextLong() & 4611686018427387903L | -9223372036854775808L;
		return new UUID(l, l1);
	}

	public static double c(double d1, double d2, double d3) {
		return (d1 - d2) / (d3 - d2);
	}

	public static double b(double d1, double d2) {
		double d3 = d2 * d2 + d1 * d1;
		if (Double.isNaN(d3))
			return (0.0D / 0.0D);
		boolean flag = d1 < 0.0D;
		if (flag)
			d1 = -d1;
		boolean flag1 = d2 < 0.0D;
		if (flag1)
			d2 = -d2;
		boolean flag2 = d1 > d2;
		if (flag2) {
			double d4 = d2;
			d2 = d1;
			d1 = d4;
		}
		double d5 = i(d3);
		d2 *= d5;
		d1 *= d5;
		double d6 = d + d1;
		int j = (int) Double.doubleToRawLongBits(d6);
		double d7 = e[j];
		double d8 = f[j];
		double d9 = d6 - d;
		double d10 = d1 * d8 - d2 * d9;
		double d11 = (6D + d10 * d10) * d10 * 0.16666666666666666D;
		double d12 = d7 + d11;
		if (flag2)
			d12 = 1.5707963267948966D - d12;
		if (flag1)
			d12 = 3.1415926535897931D - d12;
		if (flag)
			d12 = -d12;
		return d12;
	}

	public static double i(double d1) {
		double d2 = 0.5D * d1;
		long l = Double.doubleToRawLongBits(d1);
		l = 6910469410427058090L - (l >> 1);
		d1 = Double.longBitsToDouble(l);
		d1 *= 1.5D - d2 * d1 * d1;
		return d1;
	}

	static {
		b = new float[65536];
		for (int j = 0; j < 65536; j++)
			b[j] = (float) Math.sin(((double) j * 3.1415926535897931D * 2D) / 65536D);

		e = new double[257];
		f = new double[257];
		for (int k = 0; k < 257; k++) {
			double d1 = (double) k / 256D;
			double d2 = Math.asin(d1);
			f[k] = Math.cos(d2);
			e[k] = d2;
		}

	}

}
