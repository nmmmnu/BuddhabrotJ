package nu.nmmm.fractal.buddhabrot.color;

public abstract class IColorLinear implements IColor{
	private int _hitcount = 0;

	@Override
	public void clear(){
		_hitcount = 0;
	}

	@Override
	public int getInputChannelsCount(){
		return 1;
	}

	@Override
	public int hitColor(int channel, int color, int count){
		++color;

		if (color > _hitcount)
			_hitcount = color;

		return color;
	}

	@Override
	public int getMaxHitcount() {
		return _hitcount;
	}

}
