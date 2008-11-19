package client.game;

import client.game.state.U3dExteriorState;
import client.game.state.U3dLoginState;
import client.game.task.ETask;
import client.game.task.TaskManagerFactory;
import client.gameEngine.InputManager;
import client.gameEngine.PhysicsManager;
import client.manager.EntityManager;
import client.manager.HudManager;
import client.manager.TaskManager;
import client.manager.ViewManager;

import com.jmex.game.state.GameStateManager;

public class U3dgame extends Game {
	
	private int numPlayers;
    private int realPlayers = 0;
    private int readyPlayers = 0;
    private int nextPlayerId = 1;
    private String gameName;

	@Override
	protected void initCamera() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initHotKeys() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initManagers() {
		setEntitymanager(EntityManager.getInstance());
		setGamestatemanager(GameStateManager.getInstance());
		setInputmanager(InputManager.getInstance());
		setIhudmanager(HudManager.getInstance());
		setPhysicsmanager(PhysicsManager.getInstance());
		setTaskmanager(TaskManager.getInstance());
		setViewmanager(ViewManager.getInstance());
	}

	@Override
	protected void initWindow() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateHuds() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void cleanup() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initGame() {
		//U3dLoginState login = new U3dLoginState();
		//this.getGamestatemanager().attachChild(login);
        //login.setActive(true);
        //login.initialize();
		
        U3dExteriorState campus = new U3dExteriorState("U3dExteriorState");
        this.getGamestatemanager().attachChild(campus);
        campus.setActive(true);
        campus.initialize();
        
        U3dEndState end = new U3dEndState();
        end.setActive(false);
        this.getGamestatemanager().attachChild(end);

        // login immediately with the current system time as the login name
        TaskManager.getInstance().createTask(U3dSetupPlayerTask.class);
	}

	@Override
	protected void initSystem() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void reinit() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void render(float arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void update(float arg0) {
		// TODO Auto-generated method stub

	}

}
