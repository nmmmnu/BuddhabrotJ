package nu.nmmm.fractal.buddhabrot;

import nu.nmmm.fractal.buddhabrot.calculator.CalculatorPerpendicularBurningShip;
import nu.nmmm.fractal.buddhabrot.calculator.ICalculator;
import nu.nmmm.fractal.buddhabrot.color.ColorLinear;
import nu.nmmm.fractal.buddhabrot.color.ColorNebula;
import nu.nmmm.fractal.buddhabrot.color.ColorTrigonometric;
import nu.nmmm.fractal.buddhabrot.color.IColor;

public class start {
	private static final int	LINEAR				= 0;
	private static final int	TRIGONOMETRIC		= 1;
	private static final int	NEBULA				= 2;

	public static final int		SCREEN_SIZE			= 4000;
	public static final int		ITERATIONS			= 5000;
	public static final double	POINT_STEP			= (double) 2 / SCREEN_SIZE / 5;

	public static final boolean	M_OPTIMIZE_CARDIOID	= true;

//	public static int		COLOR_TYPE			= LINEAR;
	public static int		COLOR_TYPE			= TRIGONOMETRIC;
//	public static int		COLOR_TYPE			= NEBULA;

	public static void main(String args[]){
		BuddhaBrot bb = __FBuddhaBrot();

		bb.generate();
		bb.output();

	}

	private static IColor FColor(int type){
		switch(type){
		default:
		case LINEAR:			return new ColorLinear();
		case TRIGONOMETRIC:		return new ColorTrigonometric();
		case NEBULA:			return ColorNebula.getInstance(ColorNebula.SCHEME_RG, ITERATIONS);
		}
	}

	private static IBitmap __FArray(boolean hasSymetry){
		IColor color  = FColor(COLOR_TYPE);

		if (hasSymetry){
			IBitmap array = new Bitmap(SCREEN_SIZE, SCREEN_SIZE / 2 + 1, color);

			return new BitmapFoldDecorator(SCREEN_SIZE, array);
		}

		return new Bitmap(SCREEN_SIZE, color);
	}

	private static BuddhaBrot __FBuddhaBrot(){
	//	ICalculator calc	= new CalculatorMandelbrot(M_OPTIMIZE_CARDIOID);
	//	ICalculator calc	= new CalculatorBurningShip();
		ICalculator calc	= new CalculatorPerpendicularBurningShip();
	//	ICalculator calc	= new CalculatorPerpendicularMandelbrot();

		boolean hasSymmetryY = calc.hasSymmetryY();

		IBitmap arr			= __FArray(hasSymmetryY);

		return new BuddhaBrot(calc, arr, ITERATIONS, POINT_STEP, hasSymmetryY);
	}
}
