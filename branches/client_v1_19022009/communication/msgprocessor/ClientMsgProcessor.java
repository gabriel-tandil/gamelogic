/**
 * ClientMsgProcessor.java
 * 
 * @author Javier
 */
package client.communication.msgprocessor;

import client.communication.tasks.TaskCommFactory;
import client.communication.tasks.TaskCommunication;
import client.communication.tasks.darkstarevents.TaskDisconnected;
import client.communication.tasks.darkstarevents.TaskLoggedIn;
import client.communication.tasks.darkstarevents.TaskLogginFailed;
import client.communication.tasks.darkstarevents.TaskReconnected;
import client.communication.tasks.darkstarevents.TaskReconnecting;
import client.manager.TaskManager;
import common.messages.IMessage;
import common.messages.MsgTypes;
import common.processors.IProcessor;
import common.processors.MsgProcessorFactory;

/**
 * Este procesador, realiza para todos los mensajes asociados la misma accion,
 * crear la {@link TaskCommunication} asociada, y solicitar al manager de tareas
 * que la ejecute.<BR/> Sigue el patron singleton, para evita el overhead de
 * crear un procesador nuevo para cada mensaje.<BR/> A su vez posee un metodo
 * estatico para configurar la instancia singleton de la clase como rocesador
 * para todos los tipos de mensajes provistos por el framework que llegan al
 * cliente.
 * 
 * @author Javier
 */
public class ClientMsgProcessor implements IProcessor {

	/**
	 * Instancia del singleton.
	 * 
	 * @author Javier
	 */
	private static ClientMsgProcessor INSTANCE = new ClientMsgProcessor();

	/**
	 * Constructor por defecto.
	 * 
	 * @author Javier
	 */
	private ClientMsgProcessor() {
	}

	/**
	 * Crea la {@link TaskCommunication} correspondiente al mensaje pasado como
	 * parametro a travez de la {@link TaskCommFactory}, y la "submitea" al
	 * {@link TaskManager} para ser ejecutada.
	 * 
	 * @see common.processors.IProcessor#process(common.messages.IMessage)
	 * @param msg
	 *            El mensaje que se procesara.
	 * @author Javier
	 */
	@Override
	public void process(final IMessage msg) {
		TaskCommunication comT = TaskCommFactory.getInstance().createComTask(
				msg);
		TaskManager.getInstance().submit(comT);
	}

	/**
	 * Metodo para devolver la instancia del procesador del cliente.
	 * 
	 * @return INSTANCE Instancia del singleton, es el procesador del cliente.
	 * @author Javier
	 */
	public static final ClientMsgProcessor getInstance() {
		return INSTANCE;
	}

	/**
	 * Hace uso del metodo getInstance para obtener el procesador.
	 * 
	 * @return Devuelve al objeto de tipo {@link IProcessor} a traves de una
	 *         instancia del mismo.
	 * @author Javier
	 */
	@Override
	public IProcessor factoryMethod() {
		return ClientMsgProcessor.getInstance();
	}

	/**
	 * Dado que es sigue el patron singletton, este procesador podra estar
	 * asociado a mas de un tipo de mensaje, por esa razon, se devuelve un
	 * String constante.
	 * 
	 * @return Se retorna el String {@code "N/A"}.
	 * @author Javier
	 */
	@Override
	public String getMsgType() {
		return "N/A";
	}

	/**
	 * Este metodo no realiza ninguna accion.
	 * 
	 * @param msgType
	 *            no utilizado.
	 */
	@Override
	public void setMsgType(String msgType) {
		return;
	}

	/**
	 * Con figura la fabrica de procesadores {@link MsgProcessorFactory}
	 * asignado para cada tipo de mensaje del framework que es recibido en el
	 * cliente, la instancia singleton de la clase.
	 */
	public static void configureMsgProcessorFactory() {

		MsgProcessorFactory.getInstance().addProcessor(
				MsgTypes.MSG_GET_RANKING_RESPONSE_TYPE, INSTANCE);

		MsgProcessorFactory.getInstance().addProcessor(
				MsgTypes.MSG_GET_PLAYER_RESPONSE_TYPE, INSTANCE);

		MsgProcessorFactory.getInstance().addProcessor(
				MsgTypes.MSG_GET_DYNAMIC_ENTITY_RESPONSE_TYPE, INSTANCE);

		MsgProcessorFactory.getInstance().addProcessor(
				MsgTypes.MSG_GET_2DGAMES_PRICE_RESPONSE_TYPE, INSTANCE);

		MsgProcessorFactory.getInstance().addProcessor(
				MsgTypes.MSG_GET_AVAILABLE_2DGAMES_RESPONSE_TYPE, INSTANCE);

		MsgProcessorFactory.getInstance().addProcessor(
				MsgTypes.MSG_GET_TIMES_PLAYED_RESPONSE_TYPE, INSTANCE);

		MsgProcessorFactory.getInstance().addProcessor(
				MsgTypes.MSG_GET_BUYABLE_2DGAMES_RESPONSE_TYPE, INSTANCE);

		MsgProcessorFactory.getInstance().addProcessor(
				MsgTypes.MSG_MOVE_NOTIFY_TYPE, INSTANCE);

		MsgProcessorFactory.getInstance().addProcessor(
				MsgTypes.MSG_ROTATE_NOTIFY_TYPE, INSTANCE);

		MsgProcessorFactory.getInstance().addProcessor(
				MsgTypes.MSG_CHANGE_PLAYER_STATE_NOTIFY_TYPE, INSTANCE);

		MsgProcessorFactory.getInstance().addProcessor(
				TaskLoggedIn.LOGGEDIN_TASK_TYPE, INSTANCE);

		MsgProcessorFactory.getInstance().addProcessor(
				TaskDisconnected.DISCONNECTED_TASK_TYPE, INSTANCE);

		MsgProcessorFactory.getInstance().addProcessor(
				TaskReconnecting.RECONNECTING_TASK_TYPE, INSTANCE);

		MsgProcessorFactory.getInstance().addProcessor(
				TaskReconnected.RECONNECTED_TASK_TYPE, INSTANCE);

		MsgProcessorFactory.getInstance().addProcessor(
				TaskDisconnected.DISCONNECTED_TASK_TYPE, INSTANCE);

		MsgProcessorFactory.getInstance().addProcessor(
				TaskLogginFailed.LOGGIN_FAILED_TASK_TYPE, INSTANCE);

		MsgProcessorFactory.getInstance().addProcessor(
				MsgTypes.MSG_ARRIVED_TYPE, INSTANCE);

		MsgProcessorFactory.getInstance().addProcessor(MsgTypes.MSG_LEFT_TYPE,
				INSTANCE);
	}

}
