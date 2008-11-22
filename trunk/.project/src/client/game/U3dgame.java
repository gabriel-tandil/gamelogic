package client.game;

import client.game.state.U3dExteriorState;
import client.game.state.WorldGameState;
import client.gameEngine.InputManager;
import client.gameEngine.PhysicsManager;
import client.manager.EntityManager;
import client.manager.HudManager;
import client.manager.TaskManager;
import client.manager.ViewManager;

import com.jme.input.KeyBindingManager;
import com.jme.math.Vector3f;
import com.jme.renderer.Camera;
import com.jme.renderer.ColorRGBA;
import com.jme.system.DisplaySystem;
import com.jme.util.GameTaskQueue;
import com.jme.util.GameTaskQueueManager;
import com.jme.util.NanoTimer;
import com.jmex.game.state.GameStateManager;

public class U3dgame extends Game {
	
	private int numPlayers;
    private int realPlayers = 0;
    private int readyPlayers = 0;
    private int nextPlayerId = 1;
    private String gameName;

	@Override
	protected void initCamera() {
		// Create the camera.
		Camera camera = this.display.getRenderer().createCamera(this.display.getWidth(), this.display.getHeight());
		camera.setFrustumPerspective(45.0f, this.display.getWidth()/this.display.getHeight(), .1f, 500);
		Vector3f location = new Vector3f(0.0f, 0.0f, 0.0f);
		Vector3f left = new Vector3f(-1.0f, 0.0f, 0.0f);
		Vector3f up = new Vector3f(0.0f, 1.0f, 0.0f);
		Vector3f direction = new Vector3f(0.0f, 0.0f, -1.0f);
		camera.setFrame(location, left, up, direction);
		camera.setParallelProjection(false);
		camera.update();
		// Assign Camera.
		this.display.getRenderer().setCamera(camera);
	}

	@Override
	protected void initHotKeys() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initManagers() {
		setEntityManager(EntityManager.getInstance());
		setGameStateManager(GameStateManager.getInstance());
		setInputManager(InputManager.getInstance());
		setHudManager(HudManager.getInstance());
		setPhysicsManager(PhysicsManager.getInstance());
		setTaskManager(TaskManager.getInstance());
		setViewManager(ViewManager.getInstance());
	}

	@Override
	protected void initWindow() {
		this.display.createWindow(this.settings.getWidth(), this.settings.getHeight(), this.settings.getDepth(),
				this.settings.getFrequency(), this.settings.isFullscreen());
		this.display.getRenderer().setBackgroundColor(ColorRGBA.black);

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
        this.getGameStateManager().attachChild(campus);
        campus.setActive(true);
        campus.initialize();
        
        U3dEndState end = new U3dEndState();
        end.setActive(false);
        this.getGameStateManager().attachChild(end);

        // login immediately with the current system time as the login name
       // TaskManager.getInstance().createTask("U3dSetupPlayerTask.class");
	}

	@Override
	protected void initSystem() {
		this.display = DisplaySystem.getDisplaySystem(this.settings.getRenderer());
		this.display.setTitle("Snowman");
		this.timer = new NanoTimer();
		
		this.initWindow();
		this.initCamera();
		this.initManagers();

	}

	@Override
	protected void reinit() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void render(float arg0) {
		

	this.display.getRenderer().clearBuffers();
	GameTaskQueueManager.getManager().getQueue(GameTaskQueue.RENDER).execute();

	display.getRenderer().draw(((WorldGameState)this.gameStateManager.getChild("U3dExteriorState")).getRootNode());
    
	}
	
	@Override
	protected void update(float arg0) {
		this.intervalo = this.timer.getTimePerFrame();
		GameTaskQueueManager.getManager().getQueue(GameTaskQueue.UPDATE).execute();
		// Update input manager.
		this.inputManager.update(this.intervalo);
		// Execute tasks.
		this.taskManager.update();
		// Update physics.
		this.physicsManager.update();
		// Update the game states.
		this.gameStateManager.update(this.intervalo);
		if(KeyBindingManager.getKeyBindingManager().isValidCommand("exit", false)) 
			this.finish();
		
	}

}
