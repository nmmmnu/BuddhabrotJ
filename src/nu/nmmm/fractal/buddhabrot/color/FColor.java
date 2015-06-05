package nu.nmmm.fractal.buddhabrot.color;

public class FColor {
	public final static int LINEAR			= 0;
	public final static int LINEAR_NEGATIVE	= 1;
	public final static int TRIGONOMETRIC	= 2;

	public static IColor getInstance(int color){
		switch(color){
		case LINEAR:			return new ColorLinear();
		case LINEAR_NEGATIVE:	return new ColorLinear(ColorLinear.NEGATIVE);
		case TRIGONOMETRIC:		return new ColorTrigonometric();
		}

		return new ColorLinear();
	}
}
