/**
 * RTaskGetRankingResponse.java
 * @author Castillo/Santos
 * Cada vez que se solicité el ranking de un juego 2D mediante el uso de mensajería<br>
 * que provee este framework se dará uso a dicha clase<br> 
 * Está clase se compone de tres metodos:<br>
 *     - <b>RTaskGetRankingResponse:</b> Constructor de la clase.<br>
 *     - <b>TaskCommunication:</b> Factory para crear una tarea de este tipo.<br>
 *     - <b>execute:</b> Funcionalidad principal de la clase.<br>
 */

package client.communication.tasks.comm;

import client.communication.tasks.TaskCommunication;
import client.minigame.MiniGameState;

import com.jmex.game.state.GameStateManager;

public class RTaskGetRankingResponse extends TaskCommunication {

	/**
	 * @param msg
	 */
	
	public RTaskGetRankingResponse(IMessage msg) {
		super(msg);
	}

	/**
	 * Crea una tarea de tipo <I>RTaskGetRankingResponse</I> y setea el mensaje.
	 * @see client.communication.tasks.TaskCommunication#factoryMethod(common.messages.IMessage)
	 * @param msg
	 * @return RTaskGetRankingResponse
	 */
	
	@Override
	public TaskCommunication factoryMethod(IMessage msg) {
		return new RTaskGetRankingResponse(msg);
	}

	/**
	 * Este método obtiene el ranking solicitado y se le hes seteado<br>
	 * al juego 2D en cuestión para que sea mostrado mediante las interfaces
	 * encargadas de realizar dicha operación. 
	 * @see Ranking
	 * @see MiniGameState
	 * @see client.game.task.ITask#execute() 01/11/2008
	 * @author Castillo/Santos
	 */
	
	@Override
	public void execute() {
		
		// el ranking que fue solicitado.
		Ranking ranking = ((MsgGetRankingResponse) this.getMessage())
				.getRanking();

		// Obtiene el estado actual, el cual esta identificado por el id del
		// minijuego para el cual se tiene el ranking
		MiniGameState miniGameState = (MiniGameState) GameStateManager
				.getInstance().getChild(ranking.getId2DGame());
		
		// FIXME se tienen que implementar el metodo que setea el ranking.
		miniGameState.setRanking(ranking);
	}

}
