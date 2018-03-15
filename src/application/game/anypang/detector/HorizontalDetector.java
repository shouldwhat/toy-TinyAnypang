package application.game.anypang.detector;

import java.util.ArrayList;
import java.util.List;

import application.game.anypang.model.PositionInfo;
import application.game.anypang.model.StageInfo;

public class HorizontalDetector extends Detector implements LinkedPositionDetecteable {

	public HorizontalDetector(int minHitCountForDetectSuccess) {
		super(minHitCountForDetectSuccess);
	}
	
	@Override
	public List<PositionInfo> detectLinkedPosition(StageInfo stageInfo) {
		
		List<PositionInfo> detectedPositions = new ArrayList<>();
		
		/* [Step.1] For each stage row */
		for(int targetRowIndex = 0; targetRowIndex < stageInfo.getMaxRowIndex(); targetRowIndex++) {

			/* [Step.2] get row datas at 'stageInfo' */
			List<PositionInfo> rowData = stageInfo.getRow(targetRowIndex);

			/* [Step.3] detect linked values at row datas. and save in 'detectedPositionInfos'.*/
			List<PositionInfo> detectedPositionsAtRow = this.detectAtRow(rowData);
			detectedPositions.addAll(detectedPositionsAtRow);
		}
		
		return detectedPositions;
	}

	private List<PositionInfo> detectAtRow(List<PositionInfo> rowData) {
		
		/* save detected position info (=output result) */
		List<PositionInfo> detectedPositions = new ArrayList<>();
		
		PositionInfo targetPosition = rowData.get(0);
		/* [Step.1] For each column */
		for(int columnIndex = 1; columnIndex < rowData.size(); columnIndex++) {

			PositionInfo tempPosition = rowData.get(columnIndex);

			/* [Step.2] Check linked position */
			if(this.isLinkedPosition(targetPosition, tempPosition)) {
				/* found linked position */
				
				if(super.isFirstCacheHit()) {
					/* is first saved position */
					super.saveCache(targetPosition);
				}
			
				/* save position to cache */
				super.saveCache(tempPosition);
				
				if(this.isLastIndex(columnIndex, rowData)) {
					/* is last column index -> flush cache */
					detectedPositions.addAll(super.flushCache());
				}
			}
			else {	
				detectedPositions.addAll(super.flushCache());
			}
			/* [Step.3] Set next target position */
			targetPosition = tempPosition;
		}
		
		return detectedPositions;
	}
	
	@Override
	public boolean isLinkedPosition(PositionInfo targetPosition, PositionInfo tempPosition) {
		boolean isBlank = targetPosition.getValue() == StageInfo.BLANK;
//		boolean isEqualValue = targetPosition.getValue() == tempPosition.getValue();
		return (isBlank == false) && (targetPosition.getValue() == tempPosition.getValue());
	}
	
	private boolean isLastIndex(int columnIndex, List<PositionInfo> rowData) {
		return (rowData.size() - 1) == columnIndex;
	}
}
