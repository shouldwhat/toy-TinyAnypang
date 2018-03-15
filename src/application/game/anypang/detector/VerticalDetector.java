package application.game.anypang.detector;

import java.util.ArrayList;
import java.util.List;

import application.game.anypang.model.PositionInfo;
import application.game.anypang.model.StageInfo;

public class VerticalDetector extends Detector implements LinkedPositionDetecteable {

	public VerticalDetector(int minHitCountForDetectSuccess) {
		super(minHitCountForDetectSuccess);
	}
	
	@Override
	public List<PositionInfo> detectLinkedPosition(StageInfo stageInfo) {

		List<PositionInfo> detectedPositions = new ArrayList<>();
		
		/* [Step.1] For each stage column */
		for(int targetColumnIndex = 0; targetColumnIndex < stageInfo.getMaxColumnIndex(); targetColumnIndex++) {
			
			/* [Step.2] get column data at 'stageInfo' */
			List<PositionInfo> columnData = stageInfo.getColumn(targetColumnIndex);
			
			/* [Step.3] detect linked values at column datas. and save in 'detectedPositionInfosAtColumnData' */
			List<PositionInfo> detectedPositionsAtColumn = this.detectAtColumn(columnData);
			detectedPositions.addAll(detectedPositionsAtColumn);
		}
		
		return detectedPositions;
	}

	private List<PositionInfo> detectAtColumn(List<PositionInfo> columnData) {
		
		/* this variable is to save detected position info (=output result) */
		List<PositionInfo> detectedPositions = new ArrayList<>();

		PositionInfo targetPosition = columnData.get(0);
		/* [Step.1] for each row */
		for(int rowIndex = 1; rowIndex < columnData.size(); rowIndex++) {

			PositionInfo tempPosition = columnData.get(rowIndex);
			
			/* [Step.2] Check linked position */
			if(this.isLinkedPosition(targetPosition, tempPosition)) {
				/* found linked position */
				
				if(super.isFirstCacheHit()) {
					/* is first saved position */
					super.saveCache(targetPosition);
				}
				
				/* save position to cache */
				super.saveCache(tempPosition);
				
				if(this.isLastIndex(rowIndex, columnData)) {
					/* is last column index -> flush cache */
					detectedPositions.addAll(super.flushCache());
				}
			}
			else {	
				detectedPositions.addAll(super.flushCache());
			}
			/* [Step.3] Set next target position. */
			targetPosition = tempPosition;
		}
		
		return detectedPositions;
	}
	
	@Override
	public boolean isLinkedPosition(PositionInfo targetPosition, PositionInfo tempPosition) {
		boolean isBlank = targetPosition.getValue() == StageInfo.BLANK;
		return (isBlank == false) && (targetPosition.getValue() == tempPosition.getValue());
	}

	private boolean isLastIndex(int rowIndex, List<PositionInfo> columnData) {
		return (columnData.size() - 1) == rowIndex;
	}
}
