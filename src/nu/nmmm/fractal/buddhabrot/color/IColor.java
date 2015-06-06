package nu.nmmm.fractal.buddhabrot.color;


public interface IColor{
	boolean isRGBOut();
	int getInputChannelsCount();

	void clear();
	int getMaxHitcount();

	int hitColor(int channel, int color, int count);
	int convertColor(int channel, int color);

}
