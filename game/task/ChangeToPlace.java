package client.game.task;

import client.communication.GameContext;
import client.game.entity.IEntity;
import client.game.entity.Player;
import client.game.state.U3dState;
import client.game.view.U3dPlayerView;
import client.gameEngine.InputManager;
import client.manager.EntityManager;
import client.manager.ViewManager;

import com.jme.input.KeyInput;
import com.jmex.game.state.GameStateManager;
import com.sun.xml.internal.bind.CycleRecoverable.Context;

public class ChangeToPlace extends ChangeStateTask {
	private String proxEstado;

	public ChangeToPlace(String proxEstado) {
		this.proxEstado = proxEstado;
	}

	public void execute() {
		Player player = (Player)EntityManager.getInstance().getEntity(GameContext.getUserName());
		
		U3dState actualEstado = ((U3dState)GameStateManager.getInstance().getChild(U3dState.actualState));
		
		U3dPlayerView playerView = (U3dPlayerView)actualEstado.getRootNode().getChild(GameContext.getUserName()+"_View");
		actualEstado.getRootNode().detachChild(playerView);
		
		IEntity entity = EntityManager.getInstance().getEntity("World");
		ViewManager.getInstance().removeView(entity);
		EntityManager.getInstance().removeEntity("World");		
		
		this.endState(proxEstado);
				
		GameStateManager.getInstance().deactivateAllChildren();
		GameStateManager.getInstance().activateChildNamed(proxEstado);
		
		U3dState proximoEstado = ((U3dState)GameStateManager.getInstance().getChild(proxEstado));
		proximoEstado.getRootNode().attachChild(playerView);
		
		
		proximoEstado.initialize();
		proximoEstado.initializeCamera(playerView);
		
	}
	
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

}
