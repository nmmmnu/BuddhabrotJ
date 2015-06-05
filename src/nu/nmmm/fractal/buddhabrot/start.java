package nu.nmmm.fractal.buddhabrot;

import nu.nmmm.fractal.buddhabrot.calculator.CalculatorMandelbrot;
import nu.nmmm.fractal.buddhabrot.calculator.ICalculator;
import nu.nmmm.fractal.buddhabrot.color.FColor;
import nu.nmmm.fractal.buddhabrot.color.IColor;

public class start {
	public static int		SCREEN_SIZE			= 4000;
	public static int		ITERATIONS			= 2000;
	public static double	POINT_STEP			= 0.001;

	public static boolean	M_OPTIMIZE_CARDIOID	= false;
	public static boolean	FOLDED_ARRAY		= true;

//	public static int		COLOR_TYPE			= FColor.LINEAR;
//	public static int		COLOR_TYPE			= FColor.LINEAR_NEGATIVE;
	public static int		COLOR_TYPE			= FColor.TRIGONOMETRIC;

	public static void main(String args[]){
		BuddhaBrot bb = __FBuddhaBrot();

		bb.generate();
		bb.output();

	}

	private static IArray __FArray(){
		IColor color = FColor.getInstance(COLOR_TYPE);

		if (FOLDED_ARRAY){
			IArray array = new Array(SCREEN_SIZE, SCREEN_SIZE / 2 + 1, color);

			return new ArrayFoldedDecorator(SCREEN_SIZE, array);
		}

		return new Array(SCREEN_SIZE, color);
	}

	private static BuddhaBrot __FBuddhaBrot(){
		ICalculator calc	= new CalculatorMandelbrot(M_OPTIMIZE_CARDIOID);
	//	ICalculator calc	= new CalculatorBurningShip();

		IArray arr			= __FArray();

		return new BuddhaBrot(calc, arr, ITERATIONS, POINT_STEP, FOLDED_ARRAY);
	}
}
