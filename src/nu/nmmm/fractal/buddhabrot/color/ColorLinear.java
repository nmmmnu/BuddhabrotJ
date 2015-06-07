package nu.nmmm.fractal.buddhabrot.color;

public class ColorLinear extends IColorLinear{
	@Override
	public boolean isRGBOut(){
		return false;
	}

	@Override
	public int convertColor(int channel_ignored, int color){
		return color;
	}
}
