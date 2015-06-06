package nu.nmmm.fractal.buddhabrot;

import nu.nmmm.fractal.buddhabrot.calculator.CalculatorMandelbrot;
import nu.nmmm.fractal.buddhabrot.calculator.ICalculator;
import nu.nmmm.fractal.buddhabrot.color.ColorLinear;
import nu.nmmm.fractal.buddhabrot.color.ColorNebula;
import nu.nmmm.fractal.buddhabrot.color.ColorTrigonometric;
import nu.nmmm.fractal.buddhabrot.color.FColor;
import nu.nmmm.fractal.buddhabrot.color.IColor;

public class start {
	public static int		SCREEN_SIZE			= 4000;
	public static int		ITERATIONS			= 10 * 1000;
	public static double	POINT_STEP			= (double) 2 / SCREEN_SIZE / 10;



	public static boolean	M_OPTIMIZE_CARDIOID	= true;
	public static boolean	FOLDED_ARRAY		= true;

//	public static int		COLOR_TYPE			= FColor.LINEAR;
//	public static int		COLOR_TYPE			= FColor.LINEAR_NEGATIVE;
//	public static int		COLOR_TYPE			= FColor.TRIGONOMETRIC;
	public static int		COLOR_TYPE			= FColor.NEBULA;

	public static void main(String args[]){
		BuddhaBrot bb = __FBuddhaBrot();

		bb.generate();
		bb.output();

	}

	// 5000, 500, 50

	private static IColor FColor(int type){
		switch(type){
		default:
		case FColor.LINEAR:				return new ColorLinear();
		case FColor.LINEAR_NEGATIVE:	return new ColorLinear(ColorLinear.NEGATIVE);
		case FColor.TRIGONOMETRIC:		return new ColorTrigonometric();
		case FColor.NEBULA:				return new ColorNebula(5000, 500, 50);
		}
	}

	private static IBitmap __FArray(){
		IColor color  = FColor(COLOR_TYPE);

		if (FOLDED_ARRAY){
			IBitmap array = new Bitmap(SCREEN_SIZE, SCREEN_SIZE / 2 + 1, color);

			return new BitmapFoldDecorator(SCREEN_SIZE, array);
		}

		return new Bitmap(SCREEN_SIZE, color);
	}

	private static BuddhaBrot __FBuddhaBrot(){
		ICalculator calc	= new CalculatorMandelbrot(M_OPTIMIZE_CARDIOID);
	//	ICalculator calc	= new CalculatorBurningShip();

		IBitmap arr			= __FArray();

		return new BuddhaBrot(calc, arr, ITERATIONS, POINT_STEP, FOLDED_ARRAY);
	}
}
