package nu.nmmm.fractal.buddhabrot.calculator;

import java.util.List;

import nu.nmmm.fractal.buddhabrot.FPoint;

public interface ICalculator {
	public int Z(double x, double y, int iterations, List<FPoint> points);
}
