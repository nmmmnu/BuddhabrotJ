package nu.nmmm.fractal.buddhabrot.color;

public class ColorNebula implements IColor{
	private int _hitcount = 0;

	private int _startR;
	private int _startG;
	private int _startB;

	public ColorNebula(int startR, int startG, int startB){
		this._startR = startR;
		this._startG = startG;
		this._startB = startB;
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

		if (channel == mychannel)
			++color;

		if (color > _hitcount)
			_hitcount = color;

		return color;
	}

	private int _calcNumberChannel(int a){
		int result = 0;

		if (a >= _startR)
			++result;

		if (a >= _startG)
			++result;

		if (a >= _startB)
			++result;

		return result > 0 ? result - 1 : 3 - 1;
	}

	@Override
	public int convertColor(int channel, int color) {
		return color;
	}

}
