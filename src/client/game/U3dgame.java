package client.game;


import client.game.state.U3dExteriorState;
import client.game.state.U3dIntACIState;
import client.game.state.U3dIntBuffetState;
import client.game.state.U3dIntEcoState;
import client.game.state.U3dIntExaState;
import client.game.state.U3dIntIsistanState;
import client.game.state.U3dLoginState;
import client.game.state.U3dState;
import client.gameEngine.InputManager;
import client.gameEngine.PhysicsManager;
import client.manager.CollisionManager;
import client.manager.EntityManager;
import client.manager.U3dHudManager;
import client.manager.TaskManager;
import client.manager.ViewManager;
import client.minigame.U3dWordChallengeState;

import com.jme.input.KeyBindingManager;
import com.jme.input.KeyInput;
import com.jme.math.Vector3f;
import com.jme.renderer.Camera;
import com.jme.renderer.ColorRGBA;
import com.jme.renderer.Renderer;
import com.jme.system.DisplaySystem;
import com.jme.util.GameTaskQueue;
import com.jme.util.GameTaskQueueManager;
import com.jme.util.NanoTimer;
import com.jme.util.geom.Debugger;
import com.jmex.game.state.GameStateManager;
/**
 * en esta clase se inicializa el sistema
 * 
 *
 */
public class U3dgame extends Game {
	
	
	
	private int numPlayers;
    private int realPlayers = 0;
    private int readyPlayers = 0;
    private int nextPlayerId = 1;
    private String gameName;
	private boolean dibujaBounds;
    
	public U3dgame(){
		super();
	}
	
	protected void initSystem() {
		this.display = DisplaySystem.getDisplaySystem(this.settings.getRenderer());
		this.display.setTitle("U3d");
		this.timer = new NanoTimer();
		dibujaBounds=false;
		this.initWindow();
		this.initCamera();
		this.initManagers();
		this.initHotKeys();
		TaskManager.create(this);
	}

	protected void initWindow() {
		this.display.setMinSamples(0);
		this.display.setVSyncEnabled(false);
		this.display.createWindow(this.settings.getWidth(), this.settings.getHeight(), this.settings.getDepth(),
				this.settings.getFrequency(), false);
		this.display.getRenderer().setBackgroundColor(ColorRGBA.gray);

	}

	protected void initCamera() {
		// Create the camera.

		Camera camera = this.display.getRenderer().createCamera(this.display.getWidth(),
				this.display.getHeight());
		/*camera.setFrustumPerspective(45.0f, this.display.getWidth()/this.display.getHeight(), 
				1f, 5000);
		Vector3f location = new Vector3f(0.0f, 35.0f, 600f);
		Vector3f left = new Vector3f(-1.0f, 0.0f, 0.0f);
		Vector3f up = new Vector3f(0.0f, 1.0f, 0.0f);
		Vector3f direction = new Vector3f(0.0f, 0.0f, 1.0f);
		camera.setFrame(location, left, up, direction);
		camera.setParallelProjection(false);*/
		camera.update();
		// Assign Camera.
		this.display.getRenderer().setCamera(camera);

	}

	protected void initManagers() {
		setEntityManager(EntityManager.getInstance());
		
		setGameStateManager(GameStateManager.create());
		this.gameStateManager.setActive(true);

		setInputManager(InputManager.getInstance());

		setPhysicsManager(PhysicsManager.getInstance());
		setTaskManager(TaskManager.getInstance());
		setViewManager(ViewManager.getInstance());
		setHudManager(U3dHudManager.getInstance());
		getHudManager().initialize(timer);
		CollisionManager.getInstace().init();
	}
	
	

	protected void initHotKeys() {
		KeyBindingManager.getKeyBindingManager().set("exit", KeyInput.KEY_ESCAPE);
		KeyBindingManager.getKeyBindingManager().set("control", KeyInput.KEY_C);
	}

	protected void initGame() {
		// U3dLoginState login = new U3dLoginState();
		// this.getGamestatemanager().attachChild(login);
		// login.setActive(true);
		// login.initialize();

		U3dLoginState login = new U3dLoginState("login");
		this.getGameStateManager().attachChild(login);
		login.setActive(true);
		login.initialize();
		
		U3dExteriorState exterior = new U3dExteriorState("Exterior");
		this.getGameStateManager().attachChild(exterior);
		exterior.setActive(false);
		
		U3dIntEcoState eco = new U3dIntEcoState("Eco");
		this.getGameStateManager().attachChild(eco);
		eco.setActive(false);
		
		U3dIntExaState exa = new U3dIntExaState("Exa");
		this.getGameStateManager().attachChild(exa);
		exa.setActive(false);
		
		U3dIntIsistanState isistan = new U3dIntIsistanState("Isi");
		this.getGameStateManager().attachChild(isistan);
		isistan.setActive(false);
		
		U3dIntBuffetState buffet = new U3dIntBuffetState("Buf");
		this.getGameStateManager().attachChild(buffet);
		buffet.setActive(false);
		
		U3dIntACIState acI = new U3dIntACIState("AC1");
		this.getGameStateManager().attachChild(acI);
		acI.setActive(false);
		
		
		//Minijuegos
		U3dWordChallengeState wch = new U3dWordChallengeState("WordChallenge");
		this.getGameStateManager().attachChild(wch);
		wch.setActive(false);
		
		/*
		 * U3dEndState end = new U3dEndState(); end.setActive(false);
		 * this.getGameStateManager().attachChild(end);
		 */

		// login immediately with the current system time as the login name
		// TaskManager.getInstance().createTask("U3dSetupPlayerTask.class");
	}

	protected void update(float arg0) {
		//float arg0=0.5f;
		this.timer.update();
		this.intervalo = this.timer.getTimePerFrame();
		
		GameTaskQueueManager.getManager().getQueue(GameTaskQueue.UPDATE).execute();
		this.inputManager.update(arg0);
/*		
		// Update input manager.
		this.inputManager.update(this.intervalo);
		
		// Update physics.
		this.physicsManager.update();
*/		// Update the game states.
		// Execute tasks.
		this.taskManager.getInstance().update();
		PhysicsManager.getInstance().update(intervalo);
		ViewManager.getInstance().update(this.intervalo);
		
		this.gameStateManager.update(this.intervalo);
		if(KeyBindingManager.getKeyBindingManager().isValidCommand("exit", false)) 
			this.finish();
		if(KeyBindingManager.getKeyBindingManager().isValidCommand("control", false)) 
			U3dHudManager.getInstance().muestraControl();		
	}

	protected void render(float arg0) {
		Renderer r = this.display.getRenderer();
		r.clearBuffers();
		GameTaskQueueManager.getManager().getQueue(GameTaskQueue.RENDER).execute();
        if ( dibujaBounds ) {
            Debugger.drawBounds( ((U3dState)gameStateManager.getChild("Exterior")).getRootNode(), r, true );
        }
		this.gameStateManager.render(arg0);
	}

	protected void reinit() {
		// TODO Auto-generated method stub

	}

	protected void cleanup() {
		// TODO Auto-generated method stub

	}
	
	public void updateHuds() {
		U3dHudManager.getInstance().getRoot().updateGeometricState(0.0f, true);
		U3dHudManager.getInstance().getRoot().updateRenderState();
	}

}
