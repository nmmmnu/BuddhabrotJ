package nu.nmmm.fractal.buddhabrot.color;

class ColorLinear extends IColorLinear{
	public final static int NORMAL		= 0;
	public final static int NEGATIVE	= 1;

	private int _type;

	public ColorLinear(int type){
		this._type = type;
	}

	public ColorLinear(){
		this(NORMAL);
	}

	@Override
	public IColor getClone() {
		return new ColorLinear(_type);
	}

	@Override
	public void convertColor(RGB rgb, int max){
		int color = getColor();

		switch(_type){
		case NEGATIVE:
			color = max - color;
			break;

		case NORMAL:
		}

		rgb.setColor(color, max);
	}
}
