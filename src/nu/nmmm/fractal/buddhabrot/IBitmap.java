package nu.nmmm.fractal.buddhabrot;

import nu.nmmm.fractal.buddhabrot.color.RGB;

public interface IBitmap {
	void clear();

	int getWidth();
	int getHeight();

	RGB getPixel(int x, int y, RGB rgb);
	void hitPixel(int x, int y, int value);
}
