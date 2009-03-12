package client.communication.tasks;

import org.testng.annotations.Test;

import client.communication.tasks.TaskCommFactory;
import client.communication.tasks.TaskCommunication;
import common.exceptions.MalformedMessageException;
import common.exceptions.UnsopportedMessageException;
import common.messages.MessageFactory;

import common.messages.MsgTypes;
import common.messages.notify.MsgChangePlayerState;
import common.messages.notify.MsgChangeWorld;


/**
 * Clase de Testeo utilizada para testear los métodos de la clase TaskCommunication  
 * @author fbertoni
 */


public class TestTaskCommunication {

	/**
	 * Crea una instancia de TaskCommunication con un tipo de mensaje dado y verifica que se haya
	 * creado correctamente 
	 * @throws UnsopportedMessageException
	 * @throws MalformedMessageException
	 */

	@Test
	public void createTaskCommunication() throws UnsopportedMessageException, MalformedMessageException {

		TaskCommFactory taskCommFactory = TaskCommFactory.getInstance();
		MsgChangePlayerState iMsg = (MsgChangePlayerState) MessageFactory.getInstance().createMessage(MsgTypes.MSG_CHANGE_PLAYER_STATE_SEND_TYPE);
		TaskCommunication taskCommunication = taskCommFactory.createComTask(iMsg);
		MsgChangePlayerState message = (MsgChangePlayerState) taskCommunication.getMessage();
			
		if (message.getType()!=MsgTypes.MSG_CHANGE_PLAYER_STATE_SEND_TYPE)
			assert(false): "La instancia de taskCommuniation no posee el tipo de mensaje almacenado";
	}

	
	/**
	 * Crea una instancia de TaskCommunication con un tipo de mensaje dado. Luego se le setea otro mensaje,
	 * y se comprueba a que el mensaje almacenado es el correcto.
	 * @throws UnsopportedMessageException
	 * @throws MalformedMessageException
	 */
	@Test
	public void setMessagetoTaskCommunication() throws UnsopportedMessageException, MalformedMessageException {

		TaskCommFactory taskCommFactory = TaskCommFactory.getInstance();
		MsgChangePlayerState iMsg = (MsgChangePlayerState) MessageFactory.getInstance().createMessage(MsgTypes.MSG_CHANGE_PLAYER_STATE_SEND_TYPE);
		TaskCommunication taskCommunication = taskCommFactory.createComTask(iMsg);
		MsgChangeWorld messageChangeWorld = (MsgChangeWorld) MessageFactory.getInstance().createMessage(MsgTypes.MSG_CHANGE_WORLD_TYPE);
		taskCommunication.setMessage(messageChangeWorld);
		
		if (taskCommunication.getMessage().getType()!=MsgTypes.MSG_CHANGE_WORLD_TYPE)
			assert(false): "La instancia de taskCommuniation no posee el tipo de mensaje almacenado";
	}

}
