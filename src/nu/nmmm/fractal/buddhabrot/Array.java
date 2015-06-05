package nu.nmmm.fractal.buddhabrot;

import nu.nmmm.fractal.buddhabrot.color.IColor;

public class Array implements IArray{
	private int _width;
	private int _height;
	private int _hitcount;

	private IColor[] _data;

	public Array(int width, int height, IColor color){
		this._width = width;
		this._height = height;

		int size = _width * _height;

		this._data = _initData(size, color);
	}

	public Array(int size, IColor color){
		this(size, size, color);
	}

	private IColor[] _initData(int size, IColor colorPrototype){
		IColor[] data = new IColor[size];

		data[0] = colorPrototype.getClone();

		if (size > 1)
			for(int i = 1; i < size; ++i)
				data[i] = colorPrototype.getClone();

		return data;
	}

	@Override
	public void clear(){
		int size = _width * _height;

		for(int i = 0; i < size; ++i)
			_data[i].clear();

		_hitcount = 0;
	}

	// ========================

	@Override
	public IColor getPixel(int x, int y){
		if (x >= _width || y >= _height)
			return null;

		int pos = _pos(x, y);

		return _data[pos];
	}

	@Override
	public void hitPixel(int x, int y, int value){
		if (x >= _width || y >= _height)
			return;

		int pos = _pos(x, y);
		int val = _data[pos].hit(value);

		if (val > _hitcount)
			_hitcount = val;
	}

	// ========================

	@Override
	public int getWidth(){
		return _width;
	}

	@Override
	public int getHeight() {
		return _height;
	}

	// ========================

	private int _pos(int x, int y){
		return x + y * _width;
	}

	@Override
	public int getMaxHitcount() {
		return _hitcount;
	}
}
