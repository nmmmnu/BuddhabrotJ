package nu.nmmm.fractal.buddhabrot.color;


public interface IColor{
	IColor getClone();

	void clear();

	int hit(int value);

	void convertColor(RGB rgb, int max);
}
