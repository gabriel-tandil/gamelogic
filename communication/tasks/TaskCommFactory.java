/**
 * ComTaskFactory.java
 * 
 * @author lito
 */
package client.communication.tasks;

import java.util.Hashtable;

import client.communication.tasks.comm.RTaskArrived;
import client.communication.tasks.comm.RTaskChangeState;
import client.communication.tasks.comm.RTaskGet2DGamePriceResponse;
import client.communication.tasks.comm.RTaskGetAvailable2DGamesResponse;
import client.communication.tasks.comm.RTaskGetBuyable2DGamesResponse;
import client.communication.tasks.comm.RTaskGetDynamicEntityResponse;
import client.communication.tasks.comm.RTaskGetPlayerResponse;
import client.communication.tasks.comm.RTaskGetRankingResponse;
import client.communication.tasks.comm.RTaskGetTimesPlayedResponse;
import client.communication.tasks.comm.RTaskMove;
import client.communication.tasks.comm.RTaskRotate;
import client.communication.tasks.darkstarevents.TaskDisconnected;
import client.communication.tasks.darkstarevents.TaskLoggedIn;
import client.communication.tasks.darkstarevents.TaskLogginFailed;
import client.communication.tasks.darkstarevents.TaskReconnected;
import client.communication.tasks.darkstarevents.TaskReconnecting;

import common.exceptions.UnsopportedMessageException;
import common.messages.IMessage;
import common.messages.MessageFactory;
import common.messages.MsgTypes;

/**
 * Fábrica de tareas de comunicación. Para utilizarla, primero se debe obtener
 * su única instancia a travez del método {@link #getInstance()} (ya que sigue
 * el patrón <i>singleton</i>). Esta clase permite crear e inicializar una
 * instacia de la tarea apropiada para un mensaje dado. Las asociaciones entre
 * tipos de mensajes y tareas correspondientes se mantienen en un hash interno,
 * para el que se brindan métodos de manipulación.
 * 
 * @author Diego
 */
public class TaskCommFactory {
	
	/**
	 * Contiene el mapeo de los tipos de mensajes con la tarea encargada de
	 * realizar la acción correspondiente.
	 */
	private Hashtable<String, TaskCommunication>	comTaskForMsgType;
	
	/** La instancia <i>singleton</i> de esta clase. */
	private static final TaskCommFactory			INSTANCE	= new TaskCommFactory();
	
	/**
	 * Constructor por defecto. Privado, por tratarse de un <i>singleton</i>.<BR/>
	 * Inicializa la estructura interna, y setea las tareas relacionadas a los
	 * mensajes probisos por el framework.
	 */
	private TaskCommFactory() {
		comTaskForMsgType = new Hashtable<String, TaskCommunication>();
		try {
			
			IMessage getDynamicEntityResponse = MessageFactory.getInstance()
					.createMessage(
							MsgTypes.MSG_GET_DYNAMIC_ENTITY_RESPONSE_TYPE);
			RTaskGetDynamicEntityResponse rtGetDynamicEntityResponse = new RTaskGetDynamicEntityResponse(
					getDynamicEntityResponse);
			addComTask(rtGetDynamicEntityResponse);
			
			IMessage getPlayerResponse = MessageFactory.getInstance()
					.createMessage(MsgTypes.MSG_GET_PLAYER_RESPONSE_TYPE);
			RTaskGetPlayerResponse rtGetPlayerResponse = new RTaskGetPlayerResponse(
					getPlayerResponse);
			addComTask(rtGetPlayerResponse);
			
			IMessage getRankingResponse = MessageFactory.getInstance()
					.createMessage(MsgTypes.MSG_GET_RANKING_RESPONSE_TYPE);
			RTaskGetRankingResponse rtGetRankingResponse = new RTaskGetRankingResponse(
					getRankingResponse);
			addComTask(rtGetRankingResponse);
			
			IMessage get2DGamePriceResponse = MessageFactory
					.getInstance()
					.createMessage(MsgTypes.MSG_GET_2DGAMES_PRICE_RESPONSE_TYPE);
			RTaskGet2DGamePriceResponse rtGet2DGamePriceResponse = new RTaskGet2DGamePriceResponse(
					get2DGamePriceResponse);
			addComTask(rtGet2DGamePriceResponse);
			
			IMessage getAvailable2DGamesResponse = MessageFactory.getInstance()
					.createMessage(
							MsgTypes.MSG_GET_AVAILABLE_2DGAMES_RESPONSE_TYPE);
			RTaskGetAvailable2DGamesResponse rtGetAvailable2DGamesResponse = new RTaskGetAvailable2DGamesResponse(
					getAvailable2DGamesResponse);
			addComTask(rtGetAvailable2DGamesResponse);
			
			IMessage getBuyable2DGamesResponse = MessageFactory.getInstance()
					.createMessage(
							MsgTypes.MSG_GET_BUYABLE_2DGAMES_RESPONSE_TYPE);
			RTaskGetBuyable2DGamesResponse rtGetBuyable2DGamesResponse = new RTaskGetBuyable2DGamesResponse(
					getBuyable2DGamesResponse);
			addComTask(rtGetBuyable2DGamesResponse);
			
			IMessage getTimesPlayedResponse = MessageFactory.getInstance()
					.createMessage(MsgTypes.MSG_GET_TIMES_PLAYED_RESPONSE_TYPE);
			RTaskGetTimesPlayedResponse rtGetTimesPlayedResponse = new RTaskGetTimesPlayedResponse(
					getTimesPlayedResponse);
			addComTask(rtGetTimesPlayedResponse);
			
			IMessage add2DGameScore = MessageFactory.getInstance()
					.createMessage(MsgTypes.MSG_ADD_2D_GAME_SCORE_TYPE);
			TaskDirectSender tdsAdd2DGameScore = new TaskDirectSender(
					add2DGameScore);
			addComTask(tdsAdd2DGameScore);
			
			IMessage setPlayerProperty = MessageFactory.getInstance()
					.createMessage(MsgTypes.MSG_SET_PLAYER_PROPERTY_TYPE);
			TaskDirectSender tdsSetPlayerProperty = new TaskDirectSender(
					setPlayerProperty);
			addComTask(tdsSetPlayerProperty);
			
			IMessage abortQuest = MessageFactory.getInstance().createMessage(
					MsgTypes.MSG_ABORT_QUEST_TYPE);
			TaskDirectSender tdsAbortQuest = new TaskDirectSender(abortQuest);
			addComTask(tdsAbortQuest);
			
			IMessage buyGame = MessageFactory.getInstance().createMessage(
					MsgTypes.MSG_BUY_GAME_TYPE);
			TaskDirectSender tdsBuyGame = new TaskDirectSender(buyGame);
			addComTask(tdsBuyGame);
			
			IMessage finishQuest = MessageFactory.getInstance().createMessage(
					MsgTypes.MSG_FINISH_QUEST_TYPE);
			TaskDirectSender tdsFinishQuest = new TaskDirectSender(finishQuest);
			addComTask(tdsFinishQuest);
			
			IMessage get2DGamePrice = MessageFactory.getInstance()
					.createMessage(MsgTypes.MSG_GET_2DGAME_PRICE_TYPE);
			TaskDirectSender tdsGet2DGamePrice = new TaskDirectSender(
					get2DGamePrice);
			addComTask(tdsGet2DGamePrice);
			
			IMessage getDynamicEntity = MessageFactory.getInstance()
					.createMessage(MsgTypes.MSG_GET_DYNAMIC_ENTITY_TYPE);
			TaskDirectSender tdsGetDynamicEntity = new TaskDirectSender(
					getDynamicEntity);
			addComTask(tdsGetDynamicEntity);
			
			IMessage getRanking = MessageFactory.getInstance().createMessage(
					MsgTypes.MSG_GET_RANKING_TYPE);
			TaskDirectSender tdsGetRanking = new TaskDirectSender(getRanking);
			addComTask(tdsGetRanking);
			
			IMessage getTimesPlayed = MessageFactory.getInstance()
					.createMessage(MsgTypes.MSG_GET_TIMES_PLAYED_TYPE);
			TaskDirectSender tdsGetTimesPlayed = new TaskDirectSender(
					getTimesPlayed);
			addComTask(tdsGetTimesPlayed);
			
			IMessage nextQuestState = MessageFactory.getInstance()
					.createMessage(MsgTypes.MSG_NEXT_QUEST_STATE_TYPE);
			TaskDirectSender tdsNextQuestState = new TaskDirectSender(
					nextQuestState);
			addComTask(tdsNextQuestState);
			
			IMessage startQuest = MessageFactory.getInstance().createMessage(
					MsgTypes.MSG_START_QUEST_TYPE);
			TaskDirectSender tdsStartQuest = new TaskDirectSender(startQuest);
			addComTask(tdsStartQuest);
			
			IMessage arrived = MessageFactory.getInstance().createMessage(
					MsgTypes.MSG_ARRIVED_TYPE);
			RTaskArrived tdsArrived = new RTaskArrived(arrived);
			addComTask(tdsArrived);
			
			IMessage enterWorld = MessageFactory.getInstance().createMessage(
					MsgTypes.MSG_ENTER_WORLD_TYPE);
			TaskDirectSender tdsEnterWorld = new TaskDirectSender(enterWorld);
			addComTask(tdsEnterWorld);
			
			IMessage left = MessageFactory.getInstance().createMessage(
					MsgTypes.MSG_LEFT_TYPE);
			TaskDirectSender tdsLeft = new TaskDirectSender(left);
			addComTask(tdsLeft);
			
			IMessage getPlayer = MessageFactory.getInstance().createMessage(
					MsgTypes.MSG_GET_PLAYER_TYPE);
			TaskDirectSender tdsGetPlayer = new TaskDirectSender(getPlayer);
			addComTask(tdsGetPlayer);
			
			IMessage getAvailable2DGames = MessageFactory.getInstance()
					.createMessage(MsgTypes.MSG_GET_AVAILABLE_2DGAMES_TYPE);
			TaskDirectSender tdsGetAvailable2DGames = new TaskDirectSender(
					getAvailable2DGames);
			addComTask(tdsGetAvailable2DGames);
			
			IMessage getBuyalable2DGames = MessageFactory.getInstance()
					.createMessage(MsgTypes.MSG_GET_BUYABLE_2DGAMES_TYPE);
			TaskDirectSender tdsGetBuyalable2DGames = new TaskDirectSender(
					getBuyalable2DGames);
			addComTask(tdsGetBuyalable2DGames);
			
			IMessage moveSend = MessageFactory.getInstance().createMessage(
					MsgTypes.MSG_MOVE_SEND_TYPE);
			TaskChannelSender tdsMoveSend = new TaskChannelSender(moveSend);
			addComTask(tdsMoveSend);
			
			IMessage moveNotify = MessageFactory.getInstance().createMessage(
					MsgTypes.MSG_MOVE_NOTIFY_TYPE);
			RTaskMove tdsMoveNotify = new RTaskMove(moveNotify);
			addComTask(tdsMoveNotify);
			
			IMessage rotateSend = MessageFactory.getInstance().createMessage(
					MsgTypes.MSG_ROTATE_SEND_TYPE);
			TaskChannelSender tdsRotateSend = new TaskChannelSender(rotateSend);
			addComTask(tdsRotateSend);
			
			IMessage rotateNotify = MessageFactory.getInstance().createMessage(
					MsgTypes.MSG_ROTATE_NOTIFY_TYPE);
			RTaskRotate tdsRotateNotify = new RTaskRotate(
					rotateNotify);
			addComTask(tdsRotateNotify);
			
			IMessage changePlayerStateSend = MessageFactory.getInstance()
					.createMessage(MsgTypes.MSG_CHANGE_PLAYER_STATE_SEND_TYPE);
			TaskChannelSender tdsChangePlayerStateSend = new TaskChannelSender(
					changePlayerStateSend);
			addComTask(tdsChangePlayerStateSend);
			
			IMessage changePlayerStateNotify = MessageFactory
					.getInstance()
					.createMessage(MsgTypes.MSG_CHANGE_PLAYER_STATE_NOTIFY_TYPE);
			RTaskChangeState tdsChangePlayerStateNotify = new RTaskChangeState(
					changePlayerStateNotify);
			addComTask(tdsChangePlayerStateNotify);
			
			TaskDisconnected tDisconnected = new TaskDisconnected(null);
			addComTask(tDisconnected);
			
			TaskLoggedIn tLoggedIn = new TaskLoggedIn();
			addComTask(tLoggedIn);
			
			TaskLogginFailed tLogginFailed = new TaskLogginFailed();
			addComTask(tLogginFailed);
			
			TaskReconnected tReconnected = new TaskReconnected();
			addComTask(tReconnected);
			
			TaskReconnecting tReconnecting = new TaskReconnecting();
			addComTask(tReconnecting);
			
		} catch (UnsopportedMessageException e) {
			// No deberia suceder
			e.printStackTrace();
		}
	}
	
	/**
	 * Retorna el hash de tipos de mensaje y tarea correspondiente.
	 * 
	 * @return el hash
	 */
	public Hashtable<String, TaskCommunication> getComTaskForType() {
		return comTaskForMsgType;
	}
	
	/**
	 * Especifica directamente el hash completo que mapea tipos de mensaje y
	 * tareas encargadas de tratarlos. Se perderá cualquier otra asociación
	 * previa.
	 * 
	 * @param comTaskForMsgTypeHash el hash a establecer
	 */
	public void setComTaskForType(
			final Hashtable<String, TaskCommunication> comTaskForMsgTypeHash) {
		
		this.comTaskForMsgType = comTaskForMsgTypeHash;
	}
	
	/**
	 * Devuelve la única instancia de esta clase.
	 * 
	 * @return La instancia.
	 */
	public static TaskCommFactory getInstance() {
		return INSTANCE;
	}
	
	/**
	 * Método para fabricar una instancia de la clase de tarea apropiada según
	 * el tipo del mensaje pasado como parámetro. La tarea de comunicación en
	 * cuestión estará inicializada con el mensaje dado.
	 * 
	 * @param msg el mensaje a usar
	 * @return la nueva tarea
	 */
	public final TaskCommunication createComTask(final IMessage msg) {
		return this.comTaskForMsgType.get(msg.getType()).factoryMethod(msg);
	}
	
	/**
	 * Añade una asociación, mapeando el tipo de mensaje de la tarea {@code
	 * comTask} con ella misma.<BR/>
	 * NOTA: si comTask no tiene seteado un mensaje, y este no tiene seteado un
	 * tipo, se arrojara {@link NullPointerException}.<BR/>
	 * Si internamente ya existe una tarea asociada al tipo de mensaje que se
	 * agregara, se "pisara la referencia".
	 * 
	 * @param comTask tarea de comunicación correspondiente asociado al tipo de
	 *        mensaje de su mensaje interno.
	 */
	public final void addComTask(final TaskCommunication comTask) {
		this.comTaskForMsgType.put(comTask.getMsgType(), comTask);
	}
	
	/**
	 * Elimina una asociación del hash para un tipo de mensaje dado. Si ese tipo
	 * no se encuentra como clave del hash, no se hace nada.
	 * 
	 * @param msgType tipo de mensaje al que se quitará su asociación
	 * 
	 * @return la tarea correspondiente o <code>null</code> si no había alguna
	 *         mapeada para el tipo de mensaje dado
	 */
	public final TaskCommunication removeComTask(final String msgType) {
		return this.comTaskForMsgType.remove(msgType);
	}
	
	/**
	 * Verifica si para el tipo de mensaje especificado se tiene una tarea
	 * asociada.
	 * 
	 * @param msgType tipo de mensaje
	 * 
	 * @return <code>true</code> si y solo si el tipo de mensaje está mapeado.
	 */
	public final boolean containsMsgType(final String msgType) {
		return this.comTaskForMsgType.containsKey(msgType);
	}
	
}
