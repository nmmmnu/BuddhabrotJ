package nu.nmmm.fractal.buddhabrot.calculator;

import java.util.List;

public class CalculatorPerpendicularBurningShip implements ICalculator {
	private final static float M_ESCAPE2 = 2 * 2;

	@Override
	public boolean hasSymmetryY() {
		return false;
	}

	@Override
	public int Z(double x, double y, int iterations, List<Coordinate> coordinates) {
		if (coordinates != null)
			coordinates.clear();

		double zr = 0;
		double zi = 0;

		int i;
		for(i = 0; i < iterations; ++i){
			double zr2 = zr * zr;
			double zi2 = zi * zi;

			if (zr2 + zi2 > M_ESCAPE2)
				return i;

			// save current points for later use...
			if (coordinates != null){
				Coordinate p = new Coordinate(zr, zi);
				coordinates.add(p);
			}

			if (zi < 0)
				zr = - zr;

			// calculate next Z

			// z = z*z + c

			zi = 2 * zr * zi + y;
			zr = zr2 - zi2 + x;
		}

		return i;
	}
}
