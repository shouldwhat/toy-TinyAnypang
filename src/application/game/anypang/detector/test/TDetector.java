package application.game.anypang.detector.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import application.game.anypang.detector.HorizontalDetector;
import application.game.anypang.detector.LinkedPositionDetecteable;
import application.game.anypang.detector.VerticalDetector;
import application.game.anypang.model.PositionInfo;
import application.game.anypang.model.StageInfo;
import application.game.anypang.stage.StageManager;

public class TDetector {

	public static void main(String[] args) {
		
		// [ Test HorizonDetector detectLinkedValue function ]
//		TestHorizonDetector();
		
		// [ Test VerticalDetector detectLinkedValue function ]
//		TestVertialDetector();
		
		// [ Test Horizon & Vertical Detector]
//		TestDualDetector();
	}

	@SuppressWarnings("unused")
	private static void TestDualDetector() {
		
		StageInfo stageInfo = StageManager.getInstance().makeNewStageByRandom(5, 5, 1, 4);
		StageManager.getInstance().printStage(stageInfo);
		
		List<LinkedPositionDetecteable> detectors = Arrays.asList(new HorizontalDetector(3), new VerticalDetector(3));
		
		List<PositionInfo> detected = new ArrayList<>();
		for(LinkedPositionDetecteable detector : detectors) {
			detected.addAll(detector.detectLinkedPosition(stageInfo));
		}
		
		System.out.println(detected);
		
	}

	@SuppressWarnings("unused")
	private static void TestVertialDetector() {
		
		StageInfo stageInfo = StageManager.getInstance().makeNewStageByRandom(5, 5, 1, 4);
//		StageInfo stageInfo = makeStage();
		
		VerticalDetector detector = new VerticalDetector(3);
		
		StageManager.getInstance().printStage(stageInfo);
		System.out.println(detector.detectLinkedPosition(stageInfo));
	}

	@SuppressWarnings("unused")
	private static void TestHorizonDetector() {
		
		StageInfo stageInfo = StageManager.getInstance().makeNewStageByRandom(5, 5, 1, 4);
//		StageInfo stageInfo = makeStage();
		
		HorizontalDetector detector = new HorizontalDetector(3);
		
		StageManager.getInstance().printStage(stageInfo);
		System.out.println(detector.detectLinkedPosition(stageInfo));
	}

	@SuppressWarnings("unused")
	private static StageInfo mockupStage() {
		
		int[][] arr = new int[5][5];
		
		arr[0][0]=1;
		arr[0][1]=2;
		arr[0][2]=4;
		arr[0][3]=3;
		arr[0][4]=1;

		arr[1][0]=4;
		arr[1][1]=2;
		arr[1][2]=2;
		arr[1][3]=2;
		arr[1][4]=2;

		arr[2][0]=3;
		arr[2][1]=3;
		arr[2][2]=1;
		arr[2][3]=4;
		arr[2][4]=4;

		arr[3][0]=1;
		arr[3][1]=1;
		arr[3][2]=1;
		arr[3][3]=2;
		arr[3][4]=3;

		arr[4][0]=1;
		arr[4][1]=4;
		arr[4][2]=1;
		arr[4][3]=4;
		arr[4][4]=3;
		
		return new StageInfo(arr, 1, 4, 5, 5);
	}
}
