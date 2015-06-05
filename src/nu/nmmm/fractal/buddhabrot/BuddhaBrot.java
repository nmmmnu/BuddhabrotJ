package nu.nmmm.fractal.buddhabrot;

import java.util.ArrayList;
import java.util.List;

import nu.nmmm.fractal.buddhabrot.calculator.ICalculator;
import nu.nmmm.fractal.buddhabrot.color.IColor;
import nu.nmmm.fractal.buddhabrot.color.RGB;

public class BuddhaBrot {
	private static final double M_WIDTH			= 4;
	private static final double M_WIDTH2		= M_WIDTH / 2;
	private static final double M_CORRECTION	= - M_WIDTH2;

	private static String PROGRESS_BAR_MASK		= "Progressing: %8.4f of %8.4f %n";

	private ICalculator _calc;
	private IArray _arr;

	private int	_iterations;
	private double _step;

	@SuppressWarnings("unused")
	private double _res;
	private double _resBack;
	private boolean _folded;

	private List<FPoint> _points;

	BuddhaBrot(ICalculator calc, IArray arr, int iterations, double step, boolean folded){
		this._calc = calc;
		this._arr = arr;

		this._iterations = iterations;
		this._step = step;

		this._folded = folded;

		this._res		= M_WIDTH / arr.getWidth();
		this._resBack	= arr.getWidth() / M_WIDTH;

		this._points = new ArrayList<FPoint>();
	}

	BuddhaBrot(ICalculator calc, IArray arr, int iterations, double step){
		this(calc, arr, iterations, step, false);
	}

	public void generate(){
		_arr.clear();

		final double m_width2 = _folded ? 0 : M_WIDTH2;

		double xf, yf;
		for(yf = - M_WIDTH2; yf <= m_width2; yf += _step){
			for(xf = - M_WIDTH2; xf <= M_WIDTH2; xf += _step)
				_calculateEscape(xf, yf);

			_showProgressBar(yf, m_width2);
		}

		_showProgressBar(100.0, 100.0);
	}

	public void output(){
		RGB rgb = new RGB();

		int size = _arr.getWidth();

		System.out.format("P3%n");
		System.out.format("%d %d%n", size, size);
		System.out.format("%d%n", RGB.MAX_COLOR);

		for(int x = 0; x < size; ++x){
			for(int y = 0; y < size; ++y){
				IColor color = _arr.getPixel(x, y);
				if (color == null)
					rgb.setColorZero();
				else
					color.convertColor(rgb, _arr.getMaxHitcount() );

				System.out.format("%d %d %d ", rgb.getR(), rgb.getG(), rgb.getB());
			}

			System.out.println();
		}
	}

	boolean _calculateEscape(double xf, double yf){
		int it = _calc.Z(xf, yf, _iterations, _points);

		// if it escapes equal to iterations,
		// then point is bounded
		if (it == _iterations)
			return false;

		// if it escapes on 0 iteration,
		// then point is escaped outside the circle
		if (it == 0)
			return true;

		int iterations = 0;
		for(final FPoint p : _points){
			++iterations;

			if (iterations == 1)
				continue;

			// skip points around point (0, 0)
			if (p.x > - _step && p.x < + _step && p.y > - _step && p.y < + _step)
				continue;


			int x = _convertVirtualX(p.x);
			int y = _convertVirtualY(p.y);

			_arr.hitPixel(x, y, it);
		}

		return true;
	}

	private int _convertVirtualX(double a){
		double f = (a - M_CORRECTION) * _resBack;
		return (int) f;
	}

	private int _convertVirtualY(double a){
		double f = (a - M_CORRECTION) * _resBack;
		return (int) f;
	}

	private static void _showProgressBar(double p, double total) {
		System.err.format(PROGRESS_BAR_MASK, p, total);
	}
}
