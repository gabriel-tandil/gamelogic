/**
 * RealTimeCommTask.java
 * 
 * @author lito
 */
package client.communication.tasks;

import common.messages.IMessage;

import client.game.task.IRealTimeTask;

/**
 * Clase que representa una tarea de comunicaci�n de tiempo real. Esto significa
 * que las versiones viejas de la tarea pueden descartarse de forma segura si
 * hay una nueva versi�n de la misma tarea disponible.
 * 
 * @author lito
 */
public abstract class TaskRealTimeComm extends TaskCommunication implements
		IRealTimeTask {
	
	/** El time stamp de creaci�n para esta <code>RealTimeTask</code>. */
	protected final long	timestamp;
	
	/**
	 * Constructor de <code>RealTimeTask</code>. Setea el mensaje interno con el
	 * par�metro y el tipo de mensaje de la tarea con el tipo del mensaje
	 * pasado. Adicionalmente se registrar� el timestamp del momento de creaci�n
	 * de la nueva instancia.
	 * 
	 * @param msg El mensaje de la instancia
	 */
	public TaskRealTimeComm(final IMessage msg) {
		super(msg);
		this.timestamp = System.currentTimeMillis();
	}
	
	/**
	 * Retorna el timestamp de creaci�n de esta instancia.
	 * 
	 * @see client.game.task.IRealTimeTask#getTimestamp()
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
	 * @see client.game.task.IRealTimeTask
	 *      #isLaterThan(client.game.task.IRealTimeTask)
	 * @param task La <code>IRealTimeTask</code> con la cual se comparar�.
	 * @return <code>true</code> si esta tarea fue construida despu�s (o en el
	 *         mismo momento) que la pasada como par�metro.
	 */
	@Override
	public boolean isLaterThan(final IRealTimeTask task) {
		// Considera igual como posterior.
		return (this.timestamp >= task.getTimestamp());
	}
	
}
