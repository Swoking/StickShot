package fr.dinnerwolph.stickshoot.utils;

public enum GameState {
	
	WAITING,
	TIMER,
	IN_GAME,
	END;
	
	private static GameState gameState;
	
	public static GameState getState(){
		return gameState;
	}
	
	public static void setState(GameState state){
		gameState = state;
	}
	

}
