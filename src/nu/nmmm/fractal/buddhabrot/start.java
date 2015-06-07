package nu.nmmm.fractal.buddhabrot;

import nu.nmmm.fractal.buddhabrot.calculator.CalculatorBurningShip;
import nu.nmmm.fractal.buddhabrot.calculator.ICalculator;
import nu.nmmm.fractal.buddhabrot.color.ColorLinear;
import nu.nmmm.fractal.buddhabrot.color.ColorNebula;
import nu.nmmm.fractal.buddhabrot.color.ColorTrigonometric;
import nu.nmmm.fractal.buddhabrot.color.IColor;

public class start {
	private static final int	LINEAR				= 0;
	private static final int	TRIGONOMETRIC		= 1;
	private static final int	NEBULA				= 2;

	public static final int		SCREEN_SIZE			= 1000;
	public static final int		ITERATIONS			= 5000;
	public static final double	POINT_STEP			= (double) 2 / SCREEN_SIZE / 5;

	public static final boolean	M_OPTIMIZE_CARDIOID	= !true;
	public static final boolean	FOLDED_ARRAY		= !true;

//	public static int		COLOR_TYPE			= FColor.LINEAR;
//	public static int		COLOR_TYPE			= FColor.LINEAR_NEGATIVE;
//	public static int		COLOR_TYPE			= FColor.TRIGONOMETRIC;
	public static int		COLOR_TYPE			= NEBULA;

	public static void main(String args[]){
		BuddhaBrot bb = __FBuddhaBrot();

		bb.generate();
		bb.output();

	}

	private static IColor FColorNebula(int i){
		int a1 = 0;
		int a2 = (int) (ITERATIONS * 0.1);
		int a3 = (int) (ITERATIONS * 0.7);

		int a4 = a1;

		/*
		red - blue
		IColor nebula = new ColorNebula(
					a1, a2,
					a3, a4,
					a2, a3,
					0 );

		red - green
		IColor nebula = new ColorNebula(
					a1, a2,
					a2, a3,
					a3, a4,
					0 );

		blue - red
		IColor nebula = new ColorNebula(
					a2, a3,
					a3, a4,
					a1, a2,
					0 );

		blue - green
		IColor nebula = new ColorNebula(
					a3, a4,
					a2, a3,
					a1, a2,
					0 );
		*/

		IColor nebula = new ColorNebula(
				a2, a3,
				a3, a4,
				a1, a2,
				0 );

		//nebula = new ColorNegativeDecorator(nebula);

		return nebula;
	}

	private static IColor FColor(int type){
		switch(type){
		default:
		case LINEAR:			return new ColorLinear();
		case TRIGONOMETRIC:		return new ColorTrigonometric();
		case NEBULA:			return FColorNebula(ITERATIONS);
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
	//	ICalculator calc	= new CalculatorMandelbrot(M_OPTIMIZE_CARDIOID);
		ICalculator calc	= new CalculatorBurningShip();

		IBitmap arr			= __FArray();

		return new BuddhaBrot(calc, arr, ITERATIONS, POINT_STEP, FOLDED_ARRAY);
	}
}
