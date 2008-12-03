/**
 * 
 */
package client.communication.tasks.comm;

import common.messages.IMessage;

import client.communication.tasks.TaskCommunication;

/**
 * @author lito
 *
 */
public class RTaskGetDynamicEntityResponse extends TaskCommunication {

	/**
	 * @param msg
	 */
	public RTaskGetDynamicEntityResponse(IMessage msg) {
		super(msg);
	}
	
	/**
	 *  (non-Javadoc)
	 * @see client.communication.tasks.TaskCommunication#factoryMethod(common.messages.IMessage)
	 */
	@Override
	public TaskCommunication factoryMethod(IMessage msg) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 *  (non-Javadoc)
	 * @see client.game.task.ITask#execute()
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	
}
