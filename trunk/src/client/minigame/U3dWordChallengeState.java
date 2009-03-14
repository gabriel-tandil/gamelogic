package client.minigame;

import ar.edu.unicen.exa.game2d.wordchallenge.WordChallenge;
import client.manager.U3dHudManager;



public class U3dWordChallengeState extends MiniGameState {

	public U3dWordChallengeState(String name) {
		super(name);
	}

	@Override
	public void initialize() {
		this.initializeState();
		this.inicializaHUD();
	}

	@Override
	public void initializeState() {
		WordChallenge game = new WordChallenge();
		game.execute();
	}

	@Override
	public void updateState() {
		// TODO Auto-generated method stub
		
	}
	
	private void inicializaHUD() {
		U3dHudManager.getInstance().unSetCargando();
	}
	

}
