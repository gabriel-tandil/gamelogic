/**
 * RTaskChangeState.java
 * @author lito
 */
package client.communication.tasks.comm;

import common.messages.IMessage;

import client.communication.tasks.TaskCommunication;

/**
 * TODO hacer javaDoc
 *
 * @author lito
 * 01/11/2008
 */
public class RTaskChangeState extends TaskCommunication {
	
	/**
	 * @param msg
	 */
	public RTaskChangeState(IMessage msg) {
		super(msg);
	}
	
	/**
	 * TODO hacer javaDoc
	 * @see client.communication.tasks.TaskCommunication#factoryMethod(common.messages.IMessage)
	 * @param msg
	 * @return
	 */
	@Override
	public TaskCommunication factoryMethod(IMessage msg) {
		return new RTaskChangeState(msg);
	}
	
	/**
	 * TODO hacer javaDoc
	 * @see client.game.task.ITask#execute()
	 * 01/11/2008
	 * @author lito
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	
}
