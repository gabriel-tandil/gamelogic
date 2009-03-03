/**
 * RTaskGetRankingResponse.java
 * @author lito
 */
package client.communication.tasks.comm;

import com.jmex.game.state.GameStateManager;

import common.datatypes.Ranking;
import common.messages.IMessage;
import common.messages.responses.MsgGetRankingResponse;

import client.communication.tasks.TaskCommunication;
import client.minigame.MiniGameState;

/**
 * TODO hacer javaDoc
 * 
 * @author lito 01/11/2008
 */
public class RTaskGetRankingResponse extends TaskCommunication {

	/**
	 * @param msg
	 */
	public RTaskGetRankingResponse(IMessage msg) {
		super(msg);
	}

	/**
	 * TODO hacer javaDoc
	 * 
	 * @see client.communication.tasks.TaskCommunication#factoryMethod(common.messages.IMessage)
	 * @param msg
	 * @return
	 */
	@Override
	public TaskCommunication factoryMethod(IMessage msg) {
		return new RTaskGetRankingResponse(msg);
	}

	/**
	 * TODO hacer javaDoc
	 * 
	 * @see client.game.task.ITask#execute() 01/11/2008
	 * @author lito
	 */
	@Override
	public void execute() {
		// el ranking que fue solicitado.
		Ranking ranking = ((MsgGetRankingResponse) this.getMessage())
				.getRanking();

		// Obtiene el estado actual, el cual esta identificado por el id del
		// minijuego para el cual tse tiene el ranking
		MiniGameState miniGameState = (MiniGameState) GameStateManager
				.getInstance().getChild(ranking.getId2DGame());
		
		// FIXME se tienen que implementar el metodo que setea el ranking.
		miniGameState.setRanking(ranking);
	}

}
