package client.communication.tasks;

import org.testng.annotations.Test;

import common.exceptions.MalformedMessageException;
import common.exceptions.UnsopportedMessageException;
import common.messages.MessageFactory;
import common.messages.MsgTypes;
import common.messages.notify.MsgChangePlayerState;


/**
 * Clase de Testeo utilizada para testear los métodos de la clase TestTaskComFactory  
 * @author fbertoni
 */

public class TestTaskComFactory {

	/**
	 * Crea una instancia de TaskCommunication y la agrega al taskComFactory para luego verificar
	 * si esa asociacion existe
	 * @throws UnsopportedMessageException
	 * @throws MalformedMessageException
	 */
	@Test
	public void testAssociateMessage() throws UnsopportedMessageException, MalformedMessageException {
		

		TaskCommFactory taskCommFactory = TaskCommFactory.getInstance();
		MsgChangePlayerState iMsg = (MsgChangePlayerState) MessageFactory.getInstance().createMessage(MsgTypes.MSG_CHANGE_PLAYER_STATE_SEND_TYPE);
		TaskCommunication taskCommunication = taskCommFactory.createComTask(iMsg);
	
		taskCommFactory.addComTask(taskCommunication);
		
		boolean contiene = taskCommFactory.containsMsgType(MsgTypes.MSG_CHANGE_PLAYER_STATE_SEND_TYPE);
		
		assert(contiene): "El mesaje creado no es del tipo correcto";
	}
	
	/**
	 * Crea una instancia de TaskCommunication y la agrega al taskComFactory para luego verificar
	 * si esa asociacion existe
	 * @throws UnsopportedMessageException
	 * @throws MalformedMessageException
	 */
	@Test
	public void RemoveAssociateMessage() throws UnsopportedMessageException, MalformedMessageException {
		

		TaskCommFactory taskCommFactory = TaskCommFactory.getInstance();
		MsgChangePlayerState iMsg = (MsgChangePlayerState) MessageFactory.getInstance().createMessage(MsgTypes.MSG_CHANGE_PLAYER_STATE_SEND_TYPE);
		TaskCommunication taskCommunication = taskCommFactory.createComTask(iMsg);
	
		taskCommFactory.addComTask(taskCommunication);
		taskCommFactory.removeComTask(MsgTypes.MSG_CHANGE_PLAYER_STATE_SEND_TYPE);
		
		boolean contiene = taskCommFactory.containsMsgType(MsgTypes.MSG_CHANGE_PLAYER_STATE_SEND_TYPE);
		
		assert(!contiene): "La instancia de taskCommunitacion no ha sido removida exitosamente";
	}

}
