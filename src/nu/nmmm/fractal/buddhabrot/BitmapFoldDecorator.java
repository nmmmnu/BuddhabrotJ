package nu.nmmm.fractal.buddhabrot;

import nu.nmmm.fractal.buddhabrot.color.RGB;


public class BitmapFoldDecorator implements IBitmap{
	private int _width;
	private int _height;

	private IBitmap _array;

	public BitmapFoldDecorator(int width, int height, IBitmap array) {
		_width	= width;
		_height	= height;

		_array	= array;
	}

	public BitmapFoldDecorator(int size, IBitmap array) {
		this(size, size, array);
	}

	@Override
	public int getWidth() {
		return _width;
	}

	@Override
	public int getHeight() {
		return _height;
	}

	@Override
	public void clear() {
		_array.clear();
	}

	@Override
	public RGB getPixel(int x, int y, RGB rgb){
		return _array.getPixel(_foldX(x), _foldY(y), rgb);
	}

	@Override
	public void hitPixel(int x, int y, int value){
		_array.hitPixel(_foldX(x), _foldY(y), value);
	}

	private int _foldX(int a){
		return __fold(a, _array.getWidth(), this.getWidth() );
	}

	private int _foldY(int a){
		return __fold(a, _array.getHeight(), this.getHeight() );
	}

	private static int __fold(int a, int smallSize, int fullSize){
		if (a < smallSize || a > fullSize)
			return a;

		return fullSize - a;
	}
}

