package application.game.anypang.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StageInfo {

	public static final int BLANK = 0;
	
	int[][] arrStage;
	
	int availMinValue;
	int availMaxValue;
	
	int maxRowIndex;
	int maxColumnIndex;
	
	public StageInfo(int[][] arrStage, int availMinValue, int availMaxValue, int maxRowIndex, int maxColumnIndex) {
		this.arrStage = arrStage;
		this.availMinValue = availMinValue;
		this.availMaxValue = availMaxValue;
		this.maxRowIndex = maxRowIndex;
		this.maxColumnIndex = maxColumnIndex;
	}

	@Override
	public String toString() {
		return "StageInfo [arrCurrStage=" + Arrays.toString(arrStage) + ", availMinValue=" + availMinValue
				+ ", availMaxValue=" + availMaxValue + ", maxRowIndex=" + maxRowIndex + ", maxColumnIndex="
				+ maxColumnIndex + "]";
	}
	
	public List<PositionInfo> getRow(int rowIndex) {
		
		List<PositionInfo> row = new ArrayList<>();

		for(int columnIndex = 0; columnIndex < maxColumnIndex; columnIndex++) {
			PositionInfo positionInfo = new PositionInfo(rowIndex, columnIndex, arrStage[rowIndex][columnIndex]);
			row.add(positionInfo);
		}
		
		return row;
	}
	
	public List<PositionInfo> getColumn(int columnIndex) {
		
		List<PositionInfo> column = new ArrayList<>();
		
		for(int rowIndex = 0; rowIndex < maxRowIndex; rowIndex++) {
			PositionInfo positionInfo = new PositionInfo(rowIndex, columnIndex, arrStage[rowIndex][columnIndex]);
			column.add(positionInfo);
		}
		
		return column;
	}
	
	public void setPositionValue(int rowIndex, int columnIndex, int value) {
		this.arrStage[rowIndex][columnIndex] = value;
	}
	
	public int getPositionValue(int rowIndex, int columnIndex) {
		return this.arrStage[rowIndex][columnIndex];
	}

	public int[][] getArrStage() {
		return arrStage;
	}

	public void setArrStage(int[][] arrStage) {
		this.arrStage = arrStage;
	}

	public int getAvailMinValue() {
		return availMinValue;
	}

	public void setAvailMinValue(int availMinValue) {
		this.availMinValue = availMinValue;
	}

	public int getAvailMaxValue() {
		return availMaxValue;
	}

	public void setAvailMaxValue(int availMaxValue) {
		this.availMaxValue = availMaxValue;
	}

	public int getMaxRowIndex() {
		return maxRowIndex;
	}

	public void setMaxRowIndex(int maxRowIndex) {
		this.maxRowIndex = maxRowIndex;
	}

	public int getMaxColumnIndex() {
		return maxColumnIndex;
	}

	public void setMaxColumnIndex(int maxColumnIndex) {
		this.maxColumnIndex = maxColumnIndex;
	}
}
