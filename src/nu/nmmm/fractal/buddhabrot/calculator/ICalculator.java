package nu.nmmm.fractal.buddhabrot.calculator;

import java.util.List;

public interface ICalculator {
	public boolean hasSymmetryY();
	public int Z(double x, double y, int iterations, List<Coordinate> coordinates);
}
