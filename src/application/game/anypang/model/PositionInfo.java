package application.game.anypang.model;

public class PositionInfo {

	private int xIndex;
	private int yIndex;
	
	private int value;
	
	@Override
	public String toString() {
		return "PositionInfo [xIndex=" + xIndex + ", yIndex=" + yIndex + ", value=" + value + "]\n";
	}

	public PositionInfo(int x, int y, int value) {
		this.xIndex = x;
		this.yIndex = y;
		this.value = value;
	}

	public int getxIndex() {
		return xIndex;
	}

	public void setxIndex(int xIndex) {
		this.xIndex = xIndex;
	}

	public int getyIndex() {
		return yIndex;
	}

	public void setyIndex(int yIndex) {
		this.yIndex = yIndex;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
