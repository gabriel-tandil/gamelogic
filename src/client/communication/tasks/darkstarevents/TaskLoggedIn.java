package client.communication.tasks.darkstarevents;

import common.messages.IMessage;

import client.communication.tasks.TaskCommunication;

/**
 * @author lito
 * 
 */
public class TaskLoggedIn extends TaskCommunication {
	
	/**
	 * El tipo de mensaje de la tarea.
	 */
	public static final String	LOGGEDIN_TASK_TYPE	= "loggedIn";
	
	/**
	 * 
	 */
	public TaskLoggedIn() {
		super(null);
	}
	
	/**
	 * 
	 * @see client.communication.tasks.TaskCommunication#
	 *      factoryMethod(common.messages.IMessage)
	 */
	@Override
	public TaskCommunication factoryMethod(final IMessage msg) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 
	 * @see client.game.task.ITask#execute()
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @return {@link #LOGGEDIN_TASK_TYPE}
	 */
	public final String getMsgType() {
		return LOGGEDIN_TASK_TYPE;
	}
	
}