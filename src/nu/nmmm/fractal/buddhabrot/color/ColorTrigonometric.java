package nu.nmmm.fractal.buddhabrot.color;

/*
 * This color scheme is based on work of
 * Alasdair Corbett <armcorbett @ gmail . com>
 *
 * https://github.com/withad/Mandelbrot-Maps-on-Android/blob/master/MandelbrotMapsAndroid/src/uk/ac/ed/inf/mandelbrotmaps/colouring/PsychadelicColouringScheme.java
 *
 * However the idea is bit changed.
 *
 * Here is how you draw Archimedes spiral:
 *
 * for i = 0 to 6.28 step 0.01
 *    r = start_r + i	// get this for RED
 *    x = sin(i)		// get this for BLUE
 *    y = sin(i)		// get this for GREEN
 *
 *    plot x * r, y * r
 *
 */

class ColorTrigonometric extends IColorLinear{
	final private static double	TAU			= 2 * Math.PI;
	final private static int	CYCLE_SIZE	= RGB.MAX_COLOR;

	@Override
	public IColor getClone() {
		return new ColorTrigonometric();
	}

	@Override
	public void convertColor(RGB rgb, int max){
		int color = getColor();

		if (color == max){
			rgb.setColorZero();
			return;
		}

		double ratio = color / (double) max;

		double alpha = ratio * TAU;

		double r = ratio * 2;
		double x = Math.cos(alpha) + 1;
		double y = Math.sin(alpha) + 1;

		int red   = _bound(r, max);
		int green = _bound(x, max);
		int blue  = _bound(y, max);

		rgb.setColor(red, green, blue, CYCLE_SIZE);
	}

	private int _bound(double color, int maxcolor){
		final double max = 2;

		return (int) (color / max * CYCLE_SIZE);
	}
}
