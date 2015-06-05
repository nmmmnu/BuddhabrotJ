package nu.nmmm.fractal.buddhabrot;

import nu.nmmm.fractal.buddhabrot.color.IColor;

public interface IArray {
	void clear();

	int getWidth();
	int getHeight();

	IColor getPixel(int x, int y);
	void hitPixel(int x, int y, int value);

	int getMaxHitcount();
}
