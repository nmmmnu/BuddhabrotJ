package nu.nmmm.fractal.buddhabrot;

import nu.nmmm.fractal.buddhabrot.color.IColor;


public class ArrayFoldedDecorator implements IArray{
	private int _width;
	private int _height;

	private IArray _array;

	public ArrayFoldedDecorator(int width, int height, IArray array) {
		_width	= width;
		_height	= height;

		_array	= array;
	}

	public ArrayFoldedDecorator(int size, IArray array) {
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
	public int getMaxHitcount() {
		return _array.getMaxHitcount();
	}

	@Override
	public IColor getPixel(int x, int y){
		return _array.getPixel(_foldX(x), _foldY(y));
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

