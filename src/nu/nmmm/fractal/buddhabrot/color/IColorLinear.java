package nu.nmmm.fractal.buddhabrot.color;

public abstract class IColorLinear implements IColor{
	private int _color = 0;

	protected int getColor(){
		return _color;
	}

	@Override
	public void clear() {
		_color = 0;
	}

	@Override
	public int hit(int value) {
		return ++_color;
	}

}
