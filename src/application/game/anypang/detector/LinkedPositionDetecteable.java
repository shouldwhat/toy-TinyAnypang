package application.game.anypang.detector;

import java.util.List;

import application.game.anypang.model.PositionInfo;
import application.game.anypang.model.StageInfo;

public interface LinkedPositionDetecteable {

	public List<PositionInfo> detectLinkedPosition(StageInfo stage);
	
	public boolean isLinkedPosition(PositionInfo targetPosition, PositionInfo tempPosition); 
}
