package application.game;

public abstract class Game {
	
	protected abstract void setup();
	protected abstract void start();
	protected abstract void finish();
	
	public void run() {
		/* [Step.1] Setup environment to start. */
		setup();
		
		/* [Step.2] Start game*/
		start();
		
		/* [Step.3] Game Over */
		finish();
	}
}
