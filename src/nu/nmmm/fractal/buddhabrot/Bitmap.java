package nu.nmmm.fractal.buddhabrot;

import nu.nmmm.fractal.buddhabrot.color.IColor;
import nu.nmmm.fractal.buddhabrot.color.RGB;

public class Bitmap implements IBitmap{
	private int _width;
	private int _height;

	private IColor _color;

	private int[] _data;

	public Bitmap(int width, int height, IColor color){
		this._width = width;
		this._height = height;

		this._color = color;

		this._data = new int[ getSize() ];
	}

	public Bitmap(int size, IColor color){
		this(size, size, color);
	}

	private int getSize(){
		return _width * _height * _color.getInputChannelsCount();
	}

	@Override
	public void clear(){
		_color.clear();

		for(int i = 0; i < getSize(); ++i)
			_data[i] = 0;
	}

	// ========================

	@Override
	public RGB getPixel(int x, int y, RGB rgb){
		if (x >= _width || y >= _height)
			return null;

		if (_color.isRGBOut() == false){
			int val = _data[ _pos(x, y, 0) ];

			rgb.setColor(
					_color.convertColor(0, val),
					_color.getMaxHitcount()
			);
		}else{
			boolean ch = _color.getInputChannelsCount() == 1;

			int val0 = _data[ _pos(x, y, ch ? 0 : 0) ];
			int val1 = _data[ _pos(x, y, ch ? 0 : 1) ];
			int val2 = _data[ _pos(x, y, ch ? 0 : 2) ];

			rgb.setColor(
					_color.convertColor(0, val0),
					_color.convertColor(1, val1),
					_color.convertColor(2, val2),
					_color.getMaxHitcount()
			);
		}

		return rgb;
	}

	@Override
	public void hitPixel(int x, int y, int count){
		if (x >= _width || y >= _height)
			return;

		for(int ch = 0; ch < _color.getInputChannelsCount(); ++ch){
			int pos = _pos(x, y, ch);

			_data[pos] = _color.hitColor(ch, _data[pos], count);
		}
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

	private int _pos(int x, int y, int ch){
		return x + y * _width + ch * _width * _height;
	}
}
