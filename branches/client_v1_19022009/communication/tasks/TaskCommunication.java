/**
 * CommunicationTask.java
 * 
 * @author lito
 */
package client.communication.tasks;

import common.messages.IMessage;

import client.game.task.ITask;

/**
 * La clase est� pensada para abstraer el comportamiento de las tareas que
 * realizan actividades de comunicaci�n, ya sea que se especialicen en el env�o
 * de mensajes o en la ejecuci�n relacionada a la recepci�n de los mismos.<BR/>
 * Sigue el patr�n FactoryMethod, en el que cada subclase podr� especializar la
 * construcci�n de sus instancias a partir de un {@code IMensaje} asociado al
 * {@code msgType} de la tarea en cuesti�n.
 * 
 * @author lito
 */
public abstract class TaskCommunication implements ITask {
	
	/** El mensaje que se enviar�. */
	private IMessage	message;
	
	/** El time stamp de creaci�n para esta <code>RealTimeTask</code>. */
	protected final long	timestamp;
	
	/**
	 * Constructor que inicializa el estado interno con el par�metro. Setea el
	 * mensaje interno con el par�metro y el tipo de mensaje de la tarea con el
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
	 * Sigue el patr�n de FactoryMethod, su implementaci�n debe crear y devolver
	 * una instancia de la clase implementadora.
	 * 
	 * @param msg El mensaje con el que trabajar� la tarea internamente.
	 * 
	 * @return Una instancia de la clase implementadora.
	 */
	public abstract TaskCommunication factoryMethod(IMessage msg);

	/**
	 * Retorna el timestamp de creaci�n de esta instancia.	 * 
	 * @return El timestamp de creaci�n.
	 */
	@Override
	public long getTimestamp() {
		return this.timestamp;
	}
	
	/**
	 * Compara el tiempo de construcci�n de esta tarea con la
	 * <code>IRealTimeTask</code> pasada como par�metro.
	 * 
	 * @param task La <code>ITask</code> con la cual se comparar�.
	 * @return <code>true</code> si esta tarea fue construida despu�s (o en el
	 *         mismo momento) que la pasada como par�metro.
	 */
	@Override
	public boolean isLaterThan(final ITask task) {
		// Considera igual como posterior.
		return (this.timestamp >= task.getTimestamp());
	}
	
}
