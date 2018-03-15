package application.game.anypang.detector;

import java.util.ArrayList;
import java.util.List;

import application.game.anypang.model.PositionInfo;

public abstract class Detector {

	private int minHitCountForDetectSuccess;
	
	private List<PositionInfo> caches; 
	
	protected Detector(int minHitCountForDetectSuccess) {
		this.minHitCountForDetectSuccess = minHitCountForDetectSuccess;
		this.caches = new ArrayList<>();
	}
	protected void saveCache(PositionInfo positionInfo) {
		caches.add(positionInfo);
	}
	
	protected void clearCache() {
		caches.clear();
	}
	protected List<PositionInfo> flushCache() {
		List<PositionInfo> output = new ArrayList<>();
		if(this.isDetectSuccess()) {
			output.addAll(this.getCaches());
		}
		this.clearCache();
		return output;
	}
	
	protected boolean isFirstCacheHit() {
		return caches.size() == 0;
	}
	
	protected boolean isDetectSuccess() {
		return caches.size() >= minHitCountForDetectSuccess;
	}

	public List<PositionInfo> getCaches() {
		return caches;
	}
}
