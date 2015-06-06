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

public class ColorTrigonometric extends IColorLinear{
	final private static double	TAU					= 2 * Math.PI;
	final private static double TRIGONOMETRIC_MAX	= 2;

	@Override
	public boolean isRGBOut(){
		return true;
	}

	@Override
	public int convertColor(int channel, int color){
		if (color == getMaxHitcount())
			return 0;

		double ratio = color / (double) getMaxHitcount();

		double alpha = ratio * TAU;

		switch(channel){
		case 0:
			double r = ratio * 2;
			return _bound(r);

		case 1:
			double x = Math.cos(alpha) + 1;
			return _bound(x);

		case 2:
			double y = Math.sin(alpha) + 1;
			return _bound(y);
		}

		// this will never happen
		return 0;
	}

	private int _bound(double color){
		return (int) (color / TRIGONOMETRIC_MAX * getMaxHitcount());
	}
}
