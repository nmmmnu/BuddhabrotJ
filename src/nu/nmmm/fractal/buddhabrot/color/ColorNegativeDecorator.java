package nu.nmmm.fractal.buddhabrot.color;

public class ColorNegativeDecorator implements IColor{
	private IColor _color;

	public ColorNegativeDecorator(IColor color){
		this._color = color;
	}

	@Override
	public int convertColor(int channel, int color) {
		color = getMaxHitcount() - color;
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
