package client.game.task;

import client.minigame.U3dWordChallengeState;

import com.jmex.game.state.BasicGameState;
import com.jmex.game.state.GameStateManager;

public class U3dChangeToWordChallengeGame extends ChangeStateTask {

	public U3dChangeToWordChallengeGame(BasicGameState state) {
		super(state);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		((U3dWordChallengeState) GameStateManager.getInstance().getChild("WordChallenge")).initialize();
		super.execute();
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public void initTask() {

	}

}
