package client.game.state;

import ar.edu.unicen.exa.game2d.wordchallenge.WordChallenge;
import client.manager.HudManager;



public class U3dWordChallengeState extends U3dMiniGameState {

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
	public void updateState(float interpolation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public WorldGameState getWorld() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void inicializaHUD() {
		HudManager.getInstance().unSetCargando();
	}
	

}
