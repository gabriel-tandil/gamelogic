/**
 * CommunicationTask.java
 * 
 * @author lito
 */
package client.communication.tasks;

import common.messages.IMessage;

import client.game.task.ITask;

/**
 * La clase está pensada para abstraer el comportamiento de las tareas que
 * realizan actividades de comunicación, ya sea que se especialicen en el envío
 * de mensajes o en la ejecución relacionada a la recepción de los mismos.<BR/>
 * Sigue el patrón FactoryMethod, en el que cada subclase podrá especializar la
 * construcción de sus instancias a partir de un {@code IMensaje} asociado al
 * {@code msgType} de la tarea en cuestión.
 * 
 * @author lito
 */
public abstract class TaskCommunication implements ITask {
	
	/** El mensaje que se enviará. */
	private IMessage	message;
	
	/** El time stamp de creación para esta <code>RealTimeTask</code>. */
	protected final long	timestamp;
	
	/**
	 * Constructor que inicializa el estado interno con el parámetro. Setea el
	 * mensaje interno con el parámetro y el tipo de mensaje de la tarea con el
	 * tipo del mensaje pasado.
	 * 
	 * @param msg El mensaje de la instancia
	 */
	public TaskCommunication(final IMessage msg) {
		this.message = msg;
		this.timestamp = System.currentTimeMillis();
	}
	
	/**
	 * Retorna el mensaje de esta tarea.
	 * 
	 * @return el mensaje.
	 */
	public final IMessage getMessage() {
		return message;
	}
	
	/**
	 * Setea el mensaje de esta tarea.
	 * 
	 * @param aMessage el mensaje a setear.
	 */
	public final void setMessage(final IMessage aMessage) {
		this.message = aMessage;
	}
	
	/**
	 * Retorna el tipo de mensaje de esta tarea.
	 * 
	 * @return El tipo de mensaje del mensaje interno de la tarea.
	 */
	public String getMsgType() {
		return this.message.getType();
	}
	
	/**
	 * Sigue el patrón de FactoryMethod, su implementación debe crear y devolver
	 * una instancia de la clase implementadora.
	 * 
	 * @param msg El mensaje con el que trabajará la tarea internamente.
	 * 
	 * @return Una instancia de la clase implementadora.
	 */
	public abstract TaskCommunication factoryMethod(IMessage msg);

	/**
	 * Retorna el timestamp de creación de esta instancia.	 * 
	 * @return El timestamp de creación.
	 */
	@Override
	public long getTimestamp() {
		return this.timestamp;
	}
	
	/**
	 * Compara el tiempo de construcción de esta tarea con la
	 * <code>IRealTimeTask</code> pasada como parámetro.
	 * 
	 * @param task La <code>ITask</code> con la cual se comparará.
	 * @return <code>true</code> si esta tarea fue construida después (o en el
	 *         mismo momento) que la pasada como parámetro.
	 */
	@Override
	public boolean isLaterThan(final ITask task) {
		// Considera igual como posterior.
		return (this.timestamp >= task.getTimestamp());
	}
	
}
