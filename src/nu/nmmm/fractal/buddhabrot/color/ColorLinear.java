package nu.nmmm.fractal.buddhabrot.color;

public class ColorLinear extends IColorLinear{
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
	public boolean isRGBOut(){
		return false;
	}

	@Override
	public int convertColor(int channel_ignored, int color){
		switch(_type){
		case NEGATIVE:
			color = getMaxHitcount() - color;
			break;

		case NORMAL:
		}

		return color;
	}
}
