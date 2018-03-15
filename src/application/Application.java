package application;

import application.game.Game;
import application.game.anypang.AnyPangGame;

public class Application {

	public static void main(String[] args) {
		
		Game game = new AnyPangGame();
		game.run();
	}
}
