package client.game.task;

import client.game.state.U3dExteriorState;
import client.game.view.IDynamicView;
import client.game.view.U3dBuildingView;
import client.game.view.View;
import client.manager.ViewManager;

import com.jmex.game.state.BasicGameState;
import com.jmex.game.state.GameStateManager;

public class U3dChangeToExterior extends ChangeStateTask {

	public U3dChangeToExterior(BasicGameState state) {
		super(state);
		// TODO Auto-generated constructor stub
	}

	@Override
public void execute() {
GameStateManager.getInstance().deactivateAllChildren();
GameStateManager.getInstance().activateChildNamed("U3dExteriorState");
//ViewManager.getInstance().getInstance().addDirtyView((IDynamicView) (((U3dExteriorState)GameStateManager.getInstance().getChild("U3dExteriorState")).getRootNode()));
super.execute();
}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	public void initTask(){
		((U3dExteriorState)GameStateManager.getInstance().getChild("U3dExteriorState")).initialize();
	}


}
