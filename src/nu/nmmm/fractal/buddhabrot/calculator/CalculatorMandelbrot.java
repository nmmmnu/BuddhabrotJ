package nu.nmmm.fractal.buddhabrot.calculator;

import java.util.List;

import nu.nmmm.fractal.buddhabrot.FPoint;

public class CalculatorMandelbrot implements ICalculator{
	private final static float M_ESCAPE2 = 2 * 2;

	private boolean _optimizeCardioid;

	public CalculatorMandelbrot(boolean optimizeCardioid){
		this._optimizeCardioid = optimizeCardioid;
	}

	public CalculatorMandelbrot(){
		this(true);
	}

	@Override
	public int Z(double x, double y, int iterations, List<FPoint> points) {
		if (_optimizeCardioid)
			if (_optimizedCheckZ(x, y))
				return iterations;

		if (points != null)
			points.clear();

		double zr = 0;
		double zi = 0;

		int i;
		for(i = 0; i < iterations; ++i){
			double zr2 = zr * zr;
			double zi2 = zi * zi;

			if (zr2 + zi2 > M_ESCAPE2)
				return i;

			// save current points for later use...
			if (points != null){
				FPoint p = new FPoint(zr, zi);
				points.add(p);
			}

			// calculate next Z

			// z = z*z + c

			zi = 2 * zr * zi + y;
			zr = zr2 - zi2 + x;
		}

		return i;
	}

	private boolean _optimizedCheckZ(double x, double y){
		/*
		 * These are for C project
		 *
		 * Bulb + Cardioid	= 10 sec
		 * Cardioid only	= 18 sec
		 * Bulb only		= 60 sec
		 * no optimizations	= 66 sec
		 */

		// Bulb test
		double y2 = y * y;

		double bt = (x + 1) * (x + 1) + y2;

		if (bt < 0.0625)	// 0.0625 = 1/16
			return true;


		// Cardioid test, without SQRT
		double x4 = x - 0.25;	// 0.25 = 1/4

		//double y2 = y * y;

		double q = x4 * x4 + y2;

		if (q * (q + x4) < 0.25 * y2)
			return true;


		// Ok...
		return false;
	}

}
