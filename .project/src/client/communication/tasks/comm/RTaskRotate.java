/**
 * RTaskRotate.java
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
public class RTaskRotate extends TaskCommunication {
	
	/**
	 * @param msg
	 */
	public RTaskRotate(IMessage msg) {
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
		return new RTaskRotate(msg);
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
