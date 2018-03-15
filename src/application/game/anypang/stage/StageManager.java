package application.game.anypang.stage;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import application.game.anypang.model.PositionInfo;
import application.game.anypang.model.StageInfo;

public class StageManager {

	private static StageManager SINGLETON;
	
	public synchronized static StageManager getInstance() {
		
		if(SINGLETON == null) {
			return new StageManager();
		}
		
		return SINGLETON;
	}
	
	public StageInfo makeNewStageByUserInput(int maxRowIndex, int maxColumnIndex, int setupMinValue, int setupMaxValue) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("*. Do you want to set using default values? [y/n] : ");
		String defaultSetYn = scanner.nextLine();
		
		if("y".equalsIgnoreCase(defaultSetYn)) {
			/*
			 * Make mock data stage.
			 */
			scanner.close();
			return this.makeNewStageByMockData();
		}
		
		System.out.println();
		System.out.println("*. Input Format : 2 4 1 2 1 (=seperated decimal value by blank space)");
		System.out.println("*. If you input wrong value, it will be setted to min value (=" + setupMinValue + ")");
		
		int[][] arrSource = new int[maxRowIndex][maxColumnIndex];
		for(int rowIndex = 0; rowIndex < maxRowIndex; rowIndex++) {
			
			System.out.print( "*. Input [" + (rowIndex + 1) + "]" + " Row Values (if your input is empty, it will be setted to random values) : ");
			
			String inputedRow = scanner.nextLine();
			
			if("".equals(inputedRow)) {
				/*
				 * Make random value row.
				 */
				inputedRow = this.makeRandomValueRow(maxColumnIndex, setupMinValue, setupMaxValue);
			}
			
			int[] arrInputRows = strValueToIntegerValues(inputedRow, maxColumnIndex, setupMinValue, setupMaxValue);
			arrSource[rowIndex] = arrInputRows;
		}
		
		scanner.close();
		return new StageInfo(arrSource, setupMinValue, setupMaxValue, maxRowIndex, maxColumnIndex);
	}
	
	private int[] strValueToIntegerValues(String inputedRow, int maxColumnCount, int minValue, int maxValue) {
		
		int[] arrInputRows = new int[maxColumnCount];
		
		String[] splitedInputRow = inputedRow.split(" ");
		
		for(int columnIndex = 0 ; columnIndex < maxColumnCount; columnIndex++) {
			
			int iInputedColumnValue;
			try {
				
				String strInputedColumnValue = splitedInputRow[columnIndex];
				iInputedColumnValue = Integer.parseInt(strInputedColumnValue);
				if( iInputedColumnValue < minValue ) {
					iInputedColumnValue = minValue;
				}
				else if ( (iInputedColumnValue > maxValue) ) {
					iInputedColumnValue = minValue;
				}
			}
			catch(Exception e) {
				iInputedColumnValue = minValue;
			}
			arrInputRows[columnIndex] = iInputedColumnValue;
		}
		
		return arrInputRows;
	}

	private String makeRandomValueRow(int maxColumnIndex, int setupMinValue, int setupMaxValue) {
		
		String randomValueRow = "";
		
		for(int index = 0; index < maxColumnIndex; index++) {
			int randomValue = this.makeRandomValue(setupMinValue, setupMaxValue);
			randomValueRow += (randomValue + " ");
		}
		
		return randomValueRow.trim();
	}

	public StageInfo makeNewStageByRandom(int maxRowIndex, int maxColumnIndex, int availMinValue, int availMaxValue) {
		
		int[][] arrSource = new int[maxRowIndex][maxColumnIndex];
		
		for(int rowIndex = 0; rowIndex < maxRowIndex; rowIndex++) {
			for(int columnIndex = 0; columnIndex < maxColumnIndex; columnIndex++) {
				arrSource[rowIndex][columnIndex] = this.makeRandomValue(availMinValue, availMaxValue);
			}
		}
		
		return new StageInfo(arrSource, availMinValue, availMaxValue, maxRowIndex, maxColumnIndex);
	}
	
	public int makeRandomValue(int availMinValue, int availMaxValue) {
		
		/* [Step.1] make random value from '0' to 'max(=availMaxValue - availMinValue + 1)' */ 
		int max = availMaxValue - availMinValue + 1;
		int randomValue = new Random().nextInt(max);
		
		/* [Step.2] convert random value from 'availMinValue' to 'availMaxValue' */
		randomValue = randomValue + availMinValue;
		
		return randomValue;
	}

	public StageInfo refreshAndNextStage(StageInfo stageInfo, List<PositionInfo> beRemoved) {
		
		/* [Step.1] fill the 'blank value(=0)' for be remove positions(=beRemoved). */
		stageInfo = this.fillBlankValue(stageInfo, beRemoved);

		int maxColumnIndex = stageInfo.getMaxColumnIndex();
		/* [Step.2] for each stage column */
		for(int columnIndex = 0; columnIndex < maxColumnIndex; columnIndex++) {
			
			/* [Step.3] do refresh column position. */
			List<PositionInfo> columnData = stageInfo.getColumn(columnIndex);
			stageInfo = this.refreshStageColumn(stageInfo, columnData);
		}
		
		return stageInfo;
	}
	
	private StageInfo fillBlankValue(StageInfo stageInfo, List<PositionInfo> beRemoved) {
		
		beRemoved.forEach(removePosition -> { stageInfo.setPositionValue(removePosition.getxIndex(), removePosition.getyIndex(), StageInfo.BLANK); });
		return stageInfo;
	}
	
	private StageInfo refreshStageColumn(StageInfo stageInfo, List<PositionInfo> columnData) {
		
		int maxRowIndex = stageInfo.getMaxRowIndex();
		
		/* [Step.1] for each "columnData's row" */
		for(int rowIndex = 1; rowIndex < maxRowIndex; rowIndex++) {
			
			PositionInfo currentPosition = columnData.get(rowIndex);
			if(StageInfo.BLANK == currentPosition.getValue()) {
				/* current position is 'blank(=0)' */
				this.shiftDownColumnPositions(stageInfo, currentPosition);
			}
		}
		
		return stageInfo;
	}

	private void shiftDownColumnPositions(StageInfo stageInfo, PositionInfo blankPosition) {
		
		int startRowIndex = blankPosition.getxIndex();
		int columnIndex = blankPosition.getyIndex();
		
		for(int rowIndex = startRowIndex; rowIndex > 0; rowIndex--) {
			int beforePositionValue = stageInfo.getPositionValue(rowIndex - 1, columnIndex);
			stageInfo.setPositionValue(rowIndex, columnIndex, beforePositionValue);
		}
		
		stageInfo.setPositionValue(0, columnIndex, StageInfo.BLANK);
	}

	public void printStage(StageInfo stageInfo) {
		
		int[][] source = stageInfo.getArrStage();
		for(int n=0; n<stageInfo.getMaxRowIndex(); n++) {
			for(int i=0; i<stageInfo.getMaxColumnIndex(); i++) {
				System.out.print("[" + source[n][i]  + "]");
			}
			System.out.println();
		}
	}
	
	public StageInfo makeNewStageByMockData() {
		
		int[][] arr = new int[5][5];
		
		arr[0][0]=2;
		arr[0][1]=4;
		arr[0][2]=1;
		arr[0][3]=2;
		arr[0][4]=1;

		arr[1][0]=3;
		arr[1][1]=4;
		arr[1][2]=2;
		arr[1][3]=3;
		arr[1][4]=3;

		arr[2][0]=2;
		arr[2][1]=4;
		arr[2][2]=1;
		arr[2][3]=2;
		arr[2][4]=2;

		arr[3][0]=4;
		arr[3][1]=4;
		arr[3][2]=4;
		arr[3][3]=1;
		arr[3][4]=2;

		arr[4][0]=4;
		arr[4][1]=2;
		arr[4][2]=3;
		arr[4][3]=3;
		arr[4][4]=2;
		
		return new StageInfo(arr, 1, 4, 5, 5);
	}
}
