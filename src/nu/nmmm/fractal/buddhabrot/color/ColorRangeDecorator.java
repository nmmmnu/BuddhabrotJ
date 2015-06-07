package nu.nmmm.fractal.buddhabrot.color;

public class ColorRangeDecorator implements IColor{
	private IColor _color;

	private double _min;
	private double _max;

	public ColorRangeDecorator(IColor color, double min, double max){
		this._color = color;

		this._min = _check(min);
		this._max = _check(max);
	}

	private static double _check(double a){
		if (a < 0)
			return 0;

		if (a > 1)
			return 1;

		return a;
	}

	@Override
	public int convertColor(int channel, int color) {
		if (_min > 0){
			int min = (int) (getMaxHitcount() * _min);

			if (color < min)
				return _color.convertColor(channel, min);
		}

		if (_max < 1){
			int max = (int) (getMaxHitcount() * _max);

			if (color > max)
				return _color.convertColor(channel, max);
		}

		return _color.convertColor(channel, color);
	}

	@Override
	public boolean isRGBOut() {
		return _color.isRGBOut();
	}

	@Override
	public int getInputChannelsCount() {
		return _color.getInputChannelsCount();
	}

	@Override
	public void clear() {
		_color.clear();
	}

	@Override
	public int hitColor(int channel, int color, int count) {
		return _color.hitColor(channel, color, count);
	}

	@Override
	public int getMaxHitcount() {
		return _color.getMaxHitcount();
	}
}
