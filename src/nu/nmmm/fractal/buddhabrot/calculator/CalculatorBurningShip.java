package nu.nmmm.fractal.buddhabrot.calculator;

import java.util.List;

import nu.nmmm.fractal.buddhabrot.FPoint;

public class CalculatorBurningShip implements ICalculator {
	private final static float M_ESCAPE2 = 2 * 2;

	@Override
	public int Z(double x, double y, int iterations, List<FPoint> points) {
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

			if (zr > 0)
				zr = - zr;

//			if (zi < 0)
//				zi = - zi;

			// calculate next Z

			// z = z*z + c

			zi = 2 * zr * zi + y;
			zr = zr2 - zi2 + x;
		}

		return i;
	}
}
