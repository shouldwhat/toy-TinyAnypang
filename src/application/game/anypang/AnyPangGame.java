package application.game.anypang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import application.game.Game;
import application.game.anypang.detector.HorizontalDetector;
import application.game.anypang.detector.LinkedPositionDetecteable;
import application.game.anypang.detector.VerticalDetector;
import application.game.anypang.model.PositionInfo;
import application.game.anypang.model.StageInfo;
import application.game.anypang.stage.StageManager;

public class AnyPangGame extends Game {

	private StageInfo originStage;
	private StageInfo currentStage;

	private StageManager stageManager;
	
	private List<LinkedPositionDetecteable> linkdedPositionDetectors;
	
	@Override
	protected void setup() {
		
		int minCountForDetectSuccess = 0;
		int maxRowIndex = 0;
		int maxColumnIndex = 0;
		int setupMinValue = 0;
		int setupMaxValue = 0;
		{	/* [Step.1] Setup 'System Operation Value'. */
			minCountForDetectSuccess = 3;
			maxRowIndex = 5;
			maxColumnIndex = 5;
			setupMinValue = 1;
			setupMaxValue = 4;
		}
		
		{	/* [Step.2] Print 'Setup Value' */
			System.out.println("\n\n=============================================== [SETUP]");
			System.out.println("[ Max Row Count ]    = " + maxRowIndex);
			System.out.println("[ Max Column Count ] = " + maxColumnIndex);
			System.out.println("[ Setup Value Range ] = " + setupMinValue + " ~ " + setupMaxValue);
			System.out.println("[ Min Hit Count For Detection ] = " + minCountForDetectSuccess);
			System.out.println("=======================================================");
			System.out.println();
		}
		
		{	/* [Step.3] Setup 'Processing Manager Object'. */
			this.stageManager = StageManager.getInstance();
			this.linkdedPositionDetectors = Arrays.asList( new HorizontalDetector(minCountForDetectSuccess), new VerticalDetector(minCountForDetectSuccess) );
		}
		
		{	/* [Step.4] Setup 'First Stage' */
//			this.originStage = this.currentStage = stageManager.makeNewStage(maxRowIndex, maxColumnIndex, availMinValue, availMaxValue);
//			this.originStage = this.currentStage = stageManager.makeMockStage();
			this.originStage = this.currentStage = stageManager.makeNewStageByUserInput(maxRowIndex, maxColumnIndex, setupMinValue, setupMaxValue);
		}
		
		{	/* [Step.5] Print 'First Stage' */
			System.out.println();
			System.out.println("======================================= [ START-STAGE ]");
			stageManager.printStage(originStage);
			System.out.println("=======================================================");
			System.out.println();
		}
	}

	@Override
	protected void start() {
		
		/*
		 * ------------------------- [ Processing Routine ]------------------------
		 * [Step.1] 
		 * detect linked position
		 * 
		 * [Step.2],[Step.3]
		 * check next stage
		 * 		 	if there is next stage(= exist 'detected positions') -> go to next stage
		 * 			else -> game over
		 * -----------------------------------------------------------------------------
		 */
		while(true) {
			
			/* [Step.1] Detect 'linked positions' by detectors. */
			List<PositionInfo> detectedPositions = this.detectLinkedPositions(this.currentStage);

			/* [Step.2] Check exist 'next stage'. */
			if(this.inputNextStageYn(detectedPositions)) {
				break;
			}
			
			/* [Step.3] Go to 'next stage' after removing 'detected positions' */
			currentStage = stageManager.refreshAndNextStage(currentStage, detectedPositions);
			
			/* [Step.4] Print 'current stage' */
			{
				System.out.println("=================================== [ REFRESHED-STAGE ]");
				stageManager.printStage(currentStage);
				System.out.println("=======================================================\n");
			}
			
		}
	}

	private List<PositionInfo> detectLinkedPositions(StageInfo currentStage) {
		/*
		 *  linkedValueDetectors = [HorizonDetector, VerticalDetector] 
		 */
		List<PositionInfo> detectedPositions = new ArrayList<>();
		
		for(LinkedPositionDetecteable detector : this.linkdedPositionDetectors) {
			detectedPositions.addAll(detector.detectLinkedPosition(this.currentStage));
		}
		
		return detectedPositions;
	}
	
	private boolean inputNextStageYn(List<PositionInfo> detectedPositions) {

		boolean existNextStage = !(detectedPositions.size() == 0);
		return existNextStage == false;
	}

	@Override
	protected void finish() {
		
		/* [Step.1] Print 'Result Stage' */
		System.out.println("========================================= [ END-STAGE ]");
		stageManager.printStage(currentStage);
		System.out.println("=======================================================\n");
	}
}
