package nu.nmmm.fractal.buddhabrot.color;

public class ColorNebula implements IColor{
	final public static int SCHEME_RB = 0;
	final public static int SCHEME_RG = 1;
	final public static int SCHEME_BR = 2;
	final public static int SCHEME_BG = 3;

	final private static double SH_F_31 = 0.0;
	final private static double SH_F_12 = 0.1;
	final private static double SH_F_23 = 0.7;

	final private static int CHANNEL_R = 0;
	final private static int CHANNEL_G = 1;
	final private static int CHANNEL_B = 2;

	private int _hitcount = 0;

	private int _startR;
	private int _startG;
	private int _startB;

	private int _endR;
	private int _endG;
	private int _endB;

	public ColorNebula(int startR, int endR, int startG, int endG, int startB, int endB, int _ignore_me){
		this._startR = startR;
		this._startG = startG;
		this._startB = startB;

		this._endR = endR;
		this._endG = endG;
		this._endB = endB;
	}

	public static ColorNebula getInstance(int scheme, int iterations){
		int a1 = (int) (iterations * SH_F_31);
		int a2 = (int) (iterations * SH_F_12);
		int a3 = (int) (iterations * SH_F_23);

		int a4 = a1;

		switch(scheme){
		case SCHEME_RG: return new ColorNebula(a1, a2, a2, a3, a3, a4, 0 );
		case SCHEME_RB:	return new ColorNebula(a1, a2, a3, a4, a2, a3, 0 );
		case SCHEME_BG: return new ColorNebula(a3, a4, a2, a3, a1, a2, 0 );
		case SCHEME_BR: return new ColorNebula(a2, a3, a3, a4, a1, a2, 0 );
		}

		return getInstance(SCHEME_RB, iterations);
	}

	@Override
	public boolean isRGBOut() {
		return true;
	}

	@Override
	public int getInputChannelsCount() {
		return 4;
	}

	@Override
	public void clear() {
		_hitcount = 0;
	}

	@Override
	public int getMaxHitcount() {
		return _hitcount;
	}

	@Override
	public int hitColor(int channel, int color, int count) {
		int mychannel = _calcNumberChannel(count);

		// this may return -1,
		// but it will not be equal to the mychannel.

		if (channel == mychannel){
			++color;

			if (color > _hitcount)
				_hitcount = color;
		}

		return color;
	}

	private int _calcNumberChannel(int a){
		if (_calcNumberChannelIn(a, _startR, _endR))
			return CHANNEL_R;

		if (_calcNumberChannelIn(a, _startG, _endG))
			return CHANNEL_G;

		if (_calcNumberChannelIn(a, _startB, _endB))
			return CHANNEL_B;

		return -1;
	}

	private boolean _calcNumberChannelIn(int a, int start, int end){
		if (start < end)
			return a >= start && a < end;

		return a >= start || a < end;
	}

	@Override
	public int convertColor(int channel, int color) {
		return color;
	}

}
