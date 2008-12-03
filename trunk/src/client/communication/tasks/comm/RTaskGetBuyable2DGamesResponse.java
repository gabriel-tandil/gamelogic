/**
 * 
 */
package client.communication.tasks.comm;

import client.communication.tasks.TaskCommunication;

import common.messages.IMessage;

/**
 * @author lito
 *
 */
public class RTaskGetBuyable2DGamesResponse extends TaskCommunication {
	
	/**
	 * @param msg
	 */
	public RTaskGetBuyable2DGamesResponse(IMessage msg) {
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
		return new RTaskArrived(msg);
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
