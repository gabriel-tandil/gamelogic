package client.game;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import client.communication.ClientCommunication;
import client.communication.GameContext;
import client.communication.msgprocessor.ClientMsgProcessor;
import client.game.state.U3dExteriorState;
import client.game.state.U3dIntEcoState;
import client.game.state.U3dLoginState;
import client.game.state.U3dState;
import client.gameEngine.InputManager;
import client.gameEngine.PhysicsManager;
import client.manager.CollisionManager;
import client.manager.EntityManager;
import client.manager.HudManager;
import client.manager.TaskManager;
import client.manager.ViewManager;

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

public class U3dgame extends Game {

	private int numPlayers;
	private int realPlayers = 0;
	private int readyPlayers = 0;
	private int nextPlayerId = 1;
	private String gameName;
	private boolean dibujaBounds;

	public U3dgame() {
		super();
	}

	protected void initSystem() {
		this.display = DisplaySystem.getDisplaySystem(this.settings
				.getRenderer());
		this.display.setTitle("U3d");
		this.timer = new NanoTimer();
		dibujaBounds = false;
		this.initCommunication();
		this.initWindow();
		this.initCamera();
		this.initManagers();
		this.initHotKeys();
		TaskManager.create(this);
	}

	private void initCommunication() {
		ClientMsgProcessor.configureMsgProcessorFactory();
		Properties a = new Properties();

		FileInputStream is;
		try {
			is = new FileInputStream(new File(
					"d:/Cliente SVN/src/u3dproperties.properties"));
			a.load(is);
			GameContext.setProperties(a);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
GameContext.setClientCommunication(new ClientCommunication());
	}

	protected void initWindow() {
		this.display.setMinSamples(0);
		this.display.setVSyncEnabled(false);
		this.display.createWindow(this.settings.getWidth(), this.settings
				.getHeight(), this.settings.getDepth(), this.settings
				.getFrequency(), false);
		this.display.getRenderer().setBackgroundColor(ColorRGBA.black);

	}

	protected void initCamera() {
		// Create the camera.

		Camera camera = this.display.getRenderer().createCamera(
				this.display.getWidth(), this.display.getHeight());
		camera.setFrustumPerspective(45.0f, this.display.getWidth()
				/ this.display.getHeight(), 1f, 5000);
		Vector3f location = new Vector3f(0.0f, 35.0f, 600f);
		Vector3f left = new Vector3f(-1.0f, 0.0f, 0.0f);
		Vector3f up = new Vector3f(0.0f, 1.0f, 0.0f);
		Vector3f direction = new Vector3f(0.0f, 0.0f, 1.0f);
		camera.setFrame(location, left, up, direction);
		camera.setParallelProjection(false);
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
		setHudManager(HudManager.getInstance());
		getHudManager().initialize(timer);
		CollisionManager.getInstace().init();
	}

	protected void initHotKeys() {
		KeyBindingManager.getKeyBindingManager().set("exit",
				KeyInput.KEY_ESCAPE);
	//	KeyBindingManager.getKeyBindingManager().set("control", KeyInput.KEY_C);
	}

	protected void initGame() {
		U3dLoginState login = new U3dLoginState("login");
		this.getGameStateManager().attachChild(login);
		login.setActive(true);
		login.initialize();

		U3dExteriorState exterior = new U3dExteriorState("Exterior","protCampusXML/data/campus.xml");
		this.getGameStateManager().attachChild(exterior);
		exterior.setActive(false);

		U3dIntEcoState eco = new U3dIntEcoState("Eco","protEconIntXML/data/EconInt.xml");
		this.getGameStateManager().attachChild(eco);
		eco.setActive(false);
		
		U3dIntEcoState exa = new U3dIntEcoState("Exa","protExaIntXML/data/ExaInt.xml");
		this.getGameStateManager().attachChild(exa);
		exa.setActive(false);
		
		U3dIntEcoState isistan = new U3dIntEcoState("Isi","protIsistanIntXML/data/IsistanInt.xml");
		this.getGameStateManager().attachChild(isistan);
		isistan.setActive(false);
		
		U3dIntEcoState AC1 = new U3dIntEcoState("AC1","protAC1IntXML/data/AulasComunes1.xml");
		this.getGameStateManager().attachChild(AC1);
		AC1.setActive(false);
		
		U3dIntEcoState buffet = new U3dIntEcoState("Buf","protBuffetIntXML/data/BuffetInt.xml");
		this.getGameStateManager().attachChild(buffet);
		buffet.setActive(false);

		/*
		 * U3dEndState end = new U3dEndState(); end.setActive(false);
		 * this.getGameStateManager().attachChild(end);
		 */

		// login immediately with the current system time as the login name
		// TaskManager.getInstance().createTask("U3dSetupPlayerTask.class");
	}

	protected void update(float arg0) {
		// float arg0=0.5f;
		this.timer.update();
		this.intervalo = this.timer.getTimePerFrame();

		GameTaskQueueManager.getManager().getQueue(GameTaskQueue.UPDATE)
				.execute();
		this.inputManager.update(arg0);
		/*
		 * // Update input manager. this.inputManager.update(this.intervalo);
		 *  // Update physics. this.physicsManager.update();
		 */// Update the game states.
		// Execute tasks.
		this.taskManager.getInstance().update();
		PhysicsManager.getInstance().update(intervalo);
		ViewManager.getInstance().update(this.intervalo);

		this.gameStateManager.update(this.intervalo);
		if (KeyBindingManager.getKeyBindingManager().isValidCommand("exit",
				false))
			this.finish();

	}

	protected void render(float arg0) {
		Renderer r = this.display.getRenderer();
		r.clearBuffers();
		GameTaskQueueManager.getManager().getQueue(GameTaskQueue.RENDER)
				.execute();
		if (dibujaBounds) {
			Debugger.drawBounds(((U3dState) gameStateManager
					.getChild("Exterior")).getRootNode(), r, true);
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
		// TODO Auto-generated method stub

	}

}
