/**
 * RealTimeCommTask.java
 * 
 * @author lito
 */
package client.communication.tasks;

import common.messages.IMessage;

import client.game.task.IRealTimeTask;

/**
 * Clase que representa una tarea de comunicación de tiempo real. Esto significa
 * que las versiones viejas de la tarea pueden descartarse de forma segura si
 * hay una nueva versión de la misma tarea disponible.
 * 
 * @author lito
 */
public abstract class TaskRealTimeComm extends TaskCommunication implements
		IRealTimeTask {
	
	/** El time stamp de creación para esta <code>RealTimeTask</code>. */
	protected final long	timestamp;
	
	/**
	 * Constructor de <code>RealTimeTask</code>. Setea el mensaje interno con el
	 * parámetro y el tipo de mensaje de la tarea con el tipo del mensaje
	 * pasado. Adicionalmente se registrará el timestamp del momento de creación
	 * de la nueva instancia.
	 * 
	 * @param msg El mensaje de la instancia
	 */
	public TaskRealTimeComm(final IMessage msg) {
		super(msg);
		this.timestamp = System.currentTimeMillis();
	}
	
	/**
	 * Retorna el timestamp de creación de esta instancia.
	 * 
	 * @see client.game.task.IRealTimeTask#getTimestamp()
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
	 * @see client.game.task.IRealTimeTask
	 *      #isLaterThan(client.game.task.IRealTimeTask)
	 * @param task La <code>IRealTimeTask</code> con la cual se comparará.
	 * @return <code>true</code> si esta tarea fue construida después (o en el
	 *         mismo momento) que la pasada como parámetro.
	 */
	@Override
	public boolean isLaterThan(final IRealTimeTask task) {
		// Considera igual como posterior.
		return (this.timestamp >= task.getTimestamp());
	}
	
}
