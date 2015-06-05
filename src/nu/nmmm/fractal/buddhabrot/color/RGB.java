package nu.nmmm.fractal.buddhabrot.color;

public class RGB {
	final public static int MAX_COLOR = 0xFF;

	private int _r;
	private int _g;
	private int _b;

	private int _max;

	public RGB(int max){
		setMax(max);
	}

	public RGB() {
		this(MAX_COLOR);
	}

	public void setMax(int max){
		if (max == 0)
			max = MAX_COLOR;

		this._max = max;

		setColorZero();
	}

	// =============================

	public RGB setColor(int r, int g, int b, int currentMax){
		if (currentMax == 0)
			currentMax = _max;

		this._r = _calcMaxIn(r, currentMax);
		this._g = _calcMaxIn(g, currentMax);
		this._b = _calcMaxIn(b, currentMax);

		return this;
	}

	public RGB setColor(int a, int currentMax){
		if (currentMax == 0)
			currentMax = MAX_COLOR;

		a = _calcMax(a, currentMax, _max);

		this._r = a;
		this._g = a;
		this._b = a;

		return this;
	}

	public RGB setColorZero(){
		this._r = 0;
		this._g = 0;
		this._b = 0;

		return this;
	}

	// =============================

	public int getR(int needMax){
		return _calcMaxOut(_r, needMax);
	}

	public int getG(int needMax){
		return _calcMaxOut(_g, needMax);
	}

	public int getB(int needMax){
		return _calcMaxOut(_b, needMax);
	}

	// =============================

	public int getR(){
		return getR(MAX_COLOR);
	}

	public int getG(){
		return getG(MAX_COLOR);
	}

	public int getB(){
		return getB(MAX_COLOR);
	}

	// =============================

	private int _calcMaxIn(int a, int currentMax){
		return _calcMax(a, currentMax, _max);
	}

	private int _calcMaxOut(int a, int needMax){
		return _calcMax(a, _max, needMax);
	}

	private static int _calcMax(int a, int currentMax, int needMax){
		double f = a / (double) currentMax * needMax;

		return (int) f;
	}
}
