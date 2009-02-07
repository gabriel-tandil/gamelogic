package client.game.state;

import java.net.URISyntaxException;
import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Iterator;

import client.game.Game;
import client.game.task.TaskManagerFactory;
import client.game.task.U3DChangeToExteriorTaskFactory;
import client.game.task.U3DChangeToIntEcoTaskFactory;
import client.game.task.U3dChangeToExterior;
import client.game.task.U3dChangeToIntEco;
import client.manager.HudManager;
import client.manager.TaskManager;

import com.jme.image.Texture;
import com.jme.input.KeyBindingManager;
import com.jme.input.KeyInput;
import com.jme.input.MouseInput;
import com.jme.math.Vector3f;
import com.jme.renderer.Renderer;
import com.jme.scene.Spatial;
import com.jme.scene.TexCoords;
import com.jme.scene.shape.Quad;
import com.jme.scene.state.TextureState;
import com.jme.system.DisplaySystem;
import com.jme.util.TextureManager;
import com.jme.util.geom.BufferUtils;
import com.jme.util.resource.ResourceLocatorTool;
import com.jme.util.resource.SimpleResourceLocator;
import com.jmex.bui.BButton;
import com.jmex.bui.BComponent;
import com.jmex.bui.BLabel;
import com.jmex.bui.BPasswordField;
import com.jmex.bui.BTextField;
import com.jmex.bui.BWindow;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.ActionListener;
import com.jmex.bui.event.ComponentListener;
import com.jmex.bui.layout.AbsoluteLayout;
import com.jmex.bui.layout.GroupLayout;

public class U3dLoginState extends U3dState {
	private int textureWidth;
	// initialize texture height
	private int textureHeight;


	private boolean loguear;
	private boolean espera;

	public U3dLoginState(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float arg0) {
		super.render(arg0);
	}

	@Override
	public void update(float arg0) {
		HudManager.getInstance().getRoot()// solo necesito actualizar los
				// nodos del hud
				.updateGeometricState(0.0f, true);
		HudManager.getInstance().getRoot().updateRenderState();
		if (loguear && espera) {
			U3dChangeToExterior task = (U3dChangeToExterior) TaskManager
					.getInstance().createTask("3");

			task.initTask();
			TaskManager.getInstance().enqueue(task);
			HudManager.getInstance().unSetCargando();
			HudManager.getInstance().removeWindow("login");
		}
		if (loguear) // cosa medio fea para que muestre el cartel de cargando,
						// sino empieza a cargar el campus y no actualiza
			espera = true;
	}

	private int getAbsoluteX(double porcent) {
		return (int) (porcent * this.currentResolution[0] / 100);
	}

	private int getAbsoluteY(double porcent) {
		return (int) (porcent * this.currentResolution[1] / 100);
	}

	public void initialize() {

		TaskManagerFactory.getInstance().add(
				new U3DChangeToExteriorTaskFactory());

		inicializaHUD();

		Quad imagenFondo = new Quad("fondo", DisplaySystem.getDisplaySystem()
				.getWidth(), DisplaySystem.getDisplaySystem().getHeight());

		// create the texture state to handle the texture
		final TextureState ts = DisplaySystem.getDisplaySystem().getRenderer()
				.createTextureState();
		// load the image bs a texture (the image should be placed in the same
		// directory bs this class)
		try {
			ResourceLocatorTool.addResourceLocator(
					ResourceLocatorTool.TYPE_TEXTURE,
					new SimpleResourceLocator(Game.class.getClassLoader()
							.getResource("login/")));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		final Texture texture = TextureManager.loadTexture(
				"pantallalogin2.PNG", Texture.MinificationFilter.Trilinear, // of
				// no
				// use
				// for
				// the
				// quad
				Texture.MagnificationFilter.Bilinear, // of no use for the
				// quad
				1.0f, true);
		// set the texture for this texture state
		ts.setTexture(texture);
		// initialize texture width
		textureWidth = ts.getTexture().getImage().getWidth();
		// initialize texture height
		textureHeight = ts.getTexture().getImage().getHeight();
		// activate the texture state
		ts.setEnabled(true);
		// correct texture application:
		final FloatBuffer texCoords = BufferUtils.createVector2Buffer(4);
		// coordinate (0,0) for vertex 0
		texCoords.put(getUForPixel(0)).put(getVForPixel(0));
		// coordinate (0,40) for vertex 1
		texCoords.put(getUForPixel(0)).put(getVForPixel(600));
		// coordinate (40,40) for vertex 2
		texCoords.put(getUForPixel(800)).put(getVForPixel(600));
		// coordinate (40,0) for vertex 3
		texCoords.put(getUForPixel(800)).put(getVForPixel(0));
		// assign texture coordinates to the quad
		imagenFondo.setTextureCoords(new TexCoords(texCoords));
		// apply the texture state to the quad
		imagenFondo.setRenderState(ts);
		imagenFondo.setRenderQueueMode(Renderer.QUEUE_ORTHO);
		imagenFondo.setLocalTranslation(new Vector3f(DisplaySystem
				.getDisplaySystem().getWidth() / 2, DisplaySystem
				.getDisplaySystem().getHeight() / 2, 0));
		imagenFondo.setLightCombineMode(Spatial.LightCombineMode.Off);
		imagenFondo.updateRenderState();

		rootNode.attachChild(imagenFondo);

		HudManager.getInstance().update();
	}

	private ActionListener listener2 = new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			handleInput(event);
		}
	};
	private int[] currentResolution;

	public void handleInput(ActionEvent event) {
		String action = event.getAction();
		if (action.equals("login")) {
			System.out.println("loguear");
			HudManager.getInstance().setCargando();
			HudManager.getInstance().update();
			MouseInput.get().setCursorVisible(false);
			loguear = true;

		} 
	}

	private void inicializaHUD() {
		BWindow login;
		BTextField userNameField;

		BComponent passwordField;
		// instantiate our login window
		// set our style from our BuiSystem and set our layout to stretch
		// everything vertically
		login = new BWindow(HudManager.getInstance().getStyle(),
				new AbsoluteLayout(true)/* GroupLayout.makeVStretch() */);
		// this.currentResolution = (new
		// Integer(DisplaySystem.getDisplaySystem().getWidth())).toString() +
		// (new
		// Integer(DisplaySystem.getDisplaySystem().getHeight())).toString();
		this.currentResolution = new int[2];
		this.currentResolution[0] = (new Integer(DisplaySystem
				.getDisplaySystem().getWidth())).intValue();
		this.currentResolution[1] = (new Integer(DisplaySystem
				.getDisplaySystem().getHeight())).intValue();

		login.setSize(this.currentResolution[0],
				this.currentResolution[1]);

		userNameField = new BTextField();
		userNameField.setLocation(/* 470, 288 */this.getAbsoluteX(58.75), this
				.getAbsoluteY(48));
		userNameField.setSize((int) (16.125 * this.currentResolution[0] / 100),
				23/* 129, 23 */);
		login.add(userNameField, userNameField.getBounds());

		passwordField = new BPasswordField();
		passwordField.setLocation(/* 475, 333 */this.getAbsoluteX(58.75), this
				.getAbsoluteY(55.5));
		passwordField.setSize((int) (16.125 * this.currentResolution[0] / 100),
				23/* 129, 23 */);
		login.add(passwordField, passwordField.getBounds());

		// create a new BButton called "loginButton" with the display "Login"
		// and an "actionMessage" of "login"
		BButton loginButton = new BButton("Entrar", "login");
		loginButton.setLocation(this.getAbsoluteX(68.25), this
				.getAbsoluteY(64.833333));
		loginButton.setSize((int) (7.5 * this.currentResolution[0] / 100), 31);

		// add our listener2 to the loginButton so it knows what to do with the
		// "actionMessage" when the button is clicked
		loginButton.addListener(listener2);

		// add the loginButton to our login window
		login.add(loginButton, loginButton.getBounds());

		// add our login window to our BRootNode
		HudManager.getInstance().addWindow(login,"login");

		// center our window -- this could go anywhere in the code I simply
		// place it after my addWindow so I remember that I did it
		login.center();

	}

	private float getUForPixel(int xPixel) {
		return (float) xPixel / textureWidth;
	}

	private float getVForPixel(int yPixel) {
		return 1f - (float) yPixel / textureHeight;
	}

	@Override
	public void initializeState() {
		// TODO Auto-generated method stub

	}

	public WorldGameState getWorld() {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateCamera(Vector3f direction) {
	}

	public void updateState(float interpolation) {

	}

}
