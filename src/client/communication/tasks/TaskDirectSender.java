/**
 * DirectSenderTask.java
 * 
 * @author lito
 */
package client.communication.tasks;

import java.util.logging.Level;
import java.util.logging.Logger;

import common.messages.IMessage;

import client.communication.ClientCommunication;
import client.communication.GameContext;
import client.communication.exceptions.SendMessageException;

/**
 * Esta clase representa una tarea que ejecuta el envío de un mensaje directo al
 * servidor. Se entiende mensaje directo en el contexto del framework Darkstar,
 * donde en este caso el cliente envía un mensaje privado al servidor.
 * 
 * @author Diego
 */
public class TaskDirectSender extends TaskCommunication {
	
	/** El {@link Logger} para esta clase. */
	private static final Logger	LOGGER	= Logger
												.getLogger(TaskDirectSender.class
														.getName());
	
	/**
	 * Constructor que inicializa el estado interno con el mensaje pasado como
	 * parámetro.
	 * 
	 * @param msgToSend el mensaje a enviar
	 * 
	 * @author Diego
	 */
	public TaskDirectSender(final IMessage msgToSend) {
		super(msgToSend);
	}
	
	/**
	 * Ejecuta la tarea. Para esta clase eso significa enviar el mensaje.
	 * 
	 * @see client.game.task.ITask#execute()
	 * @author Diego
	 */
	@Override
	public void execute() {
		ClientCommunication client = GameContext.getClientCommunication();
		
		try {
			client.send(this.getMessage());
		} catch (SendMessageException e) {
			LOGGER.log(Level.WARNING, "No se pudo enviar el mensaje: {0}", e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Sigue el patrón FactoryMethod. Este método se utiliza para obtener las
	 * nuevas instancias de {@code DirectSenderTask}.
	 * 
	 * @see client.communication.tasks.TaskCommunication
	 *      #factoryMethod(common.messages.IMessage)
	 * @param msg el mensaje a enviar
	 * @return Una nueva instancia de esta clase, configurada con el mensaje
	 *         pasado como parámetro.
	 * @author Diego
	 */
	@Override
	public TaskCommunication factoryMethod(final IMessage msg) {
		return new TaskDirectSender(msg);
	}
	
}
