package client.game.task;

import java.util.Iterator;

import client.communication.DynamicEntitysSolicitations;
import client.communication.GameContext;
import client.communication.PositionsTranslator;
import client.communication.WorldsMaper;
import client.communication.tasks.TaskCommFactory;
import client.game.entity.IEntity;
import client.game.entity.Player;
import client.game.state.U3dState;
import client.game.view.U3dPlayerView;
import client.gameEngine.InputManager;
import client.manager.EntityManager;
import client.manager.TaskManager;
import client.manager.ViewManager;

import com.jme.input.KeyInput;
import com.jme.math.Vector3f;
import com.jmex.game.state.GameStateManager;
import com.sun.xml.internal.bind.CycleRecoverable.Context;

import common.exceptions.UnsopportedMessageException;
import common.messages.MessageFactory;
import common.messages.MsgPlainText;
import common.messages.MsgTypes;
import common.messages.notify.MsgChangeWorld;

/**
 * <p>Title: ChangeToPlace</p>
 * <p>Description: clase que representa el cambio de estado generado al querer
 * ingresar a un nuevo mundo en el juego.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * @author L. Rudenick
 * @version 1.0
 */
public class ChangeToPlace extends ChangeStateTask {
	private String proxEstado;
	
	private Vector3f newPosition;

	/**
	 * Constructora principal de la clase. Inicializa los componentes a
	 * utilizar en el objeto.
	 * @param proxEstado nuevo estado al que se va a cambiar
	 * @param theNewPosition posición en donde va a aparecer el player en el
	 * mundo al que acaba de ingresar.
	 */
	public ChangeToPlace(String proxEstado, Vector3f theNewPosition) {
		this.proxEstado = proxEstado;
		newPosition = theNewPosition;
	}

	/**
	 * Se elimina todo estado anterior, y se genera el nuevo estado al que se 
	 * va a cambiar, con su respectivo player, la vista asociada al mismo, la posición
	 * en la que este va a aparecer. Se setea este estado como próximo, y luego 
	 * se envía un mensaje al servidor, para notificar el cambio de mundo en el 
	 * juego.
	 */
	@Override
	public void execute() {
		try {
			Player player = (Player) EntityManager.getInstance().getEntity(
					GameContext.getUserName());

			U3dState actualEstado = ((U3dState) GameStateManager.getInstance()
					.getChild(U3dState.actualState));

			U3dPlayerView playerView = (U3dPlayerView) actualEstado
					.getRootNode()
					.getChild(GameContext.getUserName() + "_View");
			actualEstado.getRootNode().detachChild(playerView);

			IEntity entity = EntityManager.getInstance().getEntity(actualEstado.getName());
			ViewManager.getInstance().removeView(entity);
			
			/**/EntityManager.getInstance().removeAll();
			
			//EntityManager.getInstance().removeEntity(actualEstado.getName());
			
			this.endState(proxEstado);

			/**/EntityManager.getInstance().registerEntity(player);
			
			
			GameStateManager.getInstance().deactivateAllChildren();
			GameStateManager.getInstance().activateChildNamed(proxEstado);
			
			U3dState proximoEstado = ((U3dState) GameStateManager.getInstance()
					.getChild(proxEstado));
			proximoEstado.getRootNode().attachChild(playerView);

			proximoEstado.initialize();

			player.setPosition(newPosition);

			proximoEstado.initializeCamera(playerView);
			
			player.setActualWorld(this.proxEstado);
			
			// se crea msg de a enviar
			MsgChangeWorld msg = (MsgChangeWorld) MessageFactory.getInstance()
					.createMessage(MsgTypes.MSG_CHANGE_WORLD_TYPE);

			// se seteo el id del proximo mundo y la posicion del proximo mundo.
			msg.setIdNewWorld(WorldsMaper.CLIENT_TO_SERVER.get(proxEstado));

			msg.setSpownPosition(PositionsTranslator.serverPosition(proxEstado,
					player.getPosition()));
			// se crea una TaskComm con el msg anterior
			ITask task = TaskCommFactory.getInstance().createComTask(msg);
			TaskManager.getInstance().submit(task);

			DynamicEntitysSolicitations.DYNAMIC_ENTITYS_STATES. clear();
			
		} catch (UnsopportedMessageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

}
