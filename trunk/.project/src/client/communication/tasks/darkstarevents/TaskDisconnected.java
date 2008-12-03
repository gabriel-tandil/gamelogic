package client.communication.tasks.darkstarevents;

import common.messages.IMessage;
import client.communication.tasks.TaskCommunication;

/**
 * 
 */
public class TaskDisconnected extends TaskCommunication {
	
	/**
	 * El tipo de mensaje de la tarea.
	 */
	public static final String	DISCONNECTED_TASK_TYPE	= "disconnected";
	
	/** La razon de la desconeccion. */
	private String				razonDesconeccion;
	
	/**
	 * @param razonDesconeccion
	 */
	public TaskDisconnected(final String razonDesconeccion) {
		super(null);
		this.razonDesconeccion = razonDesconeccion;
	}
	
	/**
	 * 
	 * @see client.communication.tasks.TaskCommunication#factoryMethod(common.messages
	 *      .IMessage)
	 */
	@Override
	public TaskCommunication factoryMethod(final IMessage msg) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 
	 * 
	 * @see client.game.task.ITask#execute()
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @return {@link #DISCONNECTED_TASK_TYPE}
	 */
	public final String getMsgType() {
		return DISCONNECTED_TASK_TYPE;
	}
	
	/**
	 * @return the razonDesconeccion
	 */
	public final String getRazonDesconeccion() {
		return razonDesconeccion;
	}
	
	/**
	 * @param razonDesconeccion the razonDesconeccion to set
	 */
	public final void setRazonDesconeccion(final String razonDesconeccion) {
		this.razonDesconeccion = razonDesconeccion;
	}
	
}
