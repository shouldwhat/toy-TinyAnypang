package application.game.anypang.stage.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import application.game.anypang.detector.HorizontalDetector;
import application.game.anypang.detector.LinkedPositionDetecteable;
import application.game.anypang.detector.VerticalDetector;
import application.game.anypang.model.PositionInfo;
import application.game.anypang.model.StageInfo;
import application.game.anypang.stage.StageManager;

public class TStageManager {

	public static void main(String[] args) {
		
		// [ Test makeRandomValue function ]
//		TestMakeRandomValue();
		
		// [ Test makeNewStage function ]
//		TestMakeNewStage();
		
		// [Test getRow function]
//		TestGetRow();
		
		// [Test getColumn function]
//		TestGetColumn();
		
		// [Test removeAndNextStage function]
//		TestMakeNextStage();
		
		// [Test makeNewUserInputStage function]
//		TestMakeUserInputStage();
	}

	@SuppressWarnings("unused")
	private static void TestMakeUserInputStage() {
		
		StageManager stageManager = StageManager.getInstance();
		
		StageInfo stage = stageManager.makeNewStageByUserInput(5, 5, 1, 4);
		stageManager.printStage(stage);
	}

	@SuppressWarnings("unused")
	private static void TestMakeNextStage() {
		
		StageManager stageManager = StageManager.getInstance();
		
//		StageInfo stageInfo = StageManager.getInstance().makeNewStage(5, 5, 1, 4);
		StageInfo stageInfo = mockupStage();
		
		List<LinkedPositionDetecteable> detectors = Arrays.asList(new HorizontalDetector(3), new VerticalDetector(3));
		
		List<PositionInfo> detected = new ArrayList<>();
		for(LinkedPositionDetecteable detector : detectors) {
			detected.addAll(detector.detectLinkedPosition(stageInfo));
		}
		
		stageManager.printStage(stageInfo);
		System.out.println();
		StageInfo removeAndNextStage = stageManager.refreshAndNextStage(stageInfo, detected);
		
		stageManager.printStage(removeAndNextStage);
		System.out.println();
	}

	@SuppressWarnings("unused")
	private static void TestGetColumn() {
		
		StageManager stageManager = StageManager.getInstance();
		StageInfo stage = stageManager.makeNewStageByRandom(3, 5, 1, 4);
		
		stageManager.printStage(stage);
		System.out.println(stage.getColumn(1));
	}

	@SuppressWarnings("unused")
	private static void TestGetRow() {
		
		StageManager stageManager = StageManager.getInstance();
		StageInfo stage = stageManager.makeNewStageByRandom(5, 5, 1, 4);
		
		stageManager.printStage(stage);
		System.out.println(stage.getRow(0));
	}

	@SuppressWarnings("unused")
	private static void TestMakeNewStage() {
		
		StageInfo stage = StageManager.getInstance().makeNewStageByRandom(5, 5, 1, 4);
		
		int[][] source = stage.getArrStage();
		for(int n=0; n<stage.getMaxRowIndex(); n++) {
			for(int i=0; i<stage.getMaxColumnIndex(); i++) {
				System.out.print("[" + source[n][i]  + "]");
			}
			System.out.println();
		}
		
		System.out.println(source[0][1]);
		System.out.println(source[0][2]);
		System.out.println(source[1][2]);
		System.out.println(source[1][3]);
	}

	@SuppressWarnings("unused")
	private static void TestMakeRandomValue() {
		
		for(int n=0; n<10; n++) {
			System.out.println(StageManager.getInstance().makeRandomValue(1, 4));
		}
	}
	
	@SuppressWarnings("unused")
	private static StageInfo mockupStage() {
		
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
