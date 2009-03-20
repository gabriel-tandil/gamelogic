package client.game.state;

import java.util.HashMap;

import client.communication.GameContext;
import client.communication.tasks.TaskCommFactory;
import client.game.task.ITask;
import client.game.task.TaskManagerFactory;
import client.game.task.U3DCargandoTaskFactory;
import client.game.task.U3DLoginRequestTask;
import client.game.task.U3DLoginRequestTaskFactory;
import client.game.task.U3dCargandoTask;
import client.game.view.DynamicView;
import client.manager.HudManager;
import client.manager.TaskManager;

import com.jme.math.Vector3f;
import com.jme.system.DisplaySystem;
import com.jmex.bui.BButton;
import com.jmex.bui.BComponent;
import com.jmex.bui.BLabel;
import com.jmex.bui.BPasswordField;
import com.jmex.bui.BTextField;
import com.jmex.bui.BWindow;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.ActionListener;
import com.jmex.bui.layout.AbsoluteLayout;
import common.exceptions.UnsopportedMessageException;
import common.messages.MessageFactory;
import common.messages.MsgPlainText;
import common.messages.MsgTypes;

/**
 * Clase que representa el estado del login. Esta clase hereda de 
 * <code>U3dState</code> que a su vez hereda de <code>WorldGameState</code>
 * Se encarga de mostrar la pantalla de login del usuario y verificar
 * que el logueo haya sido correcto 
 * 
 * @author kike
 */
public class U3dLoginState extends U3dState {
	public static final String LOGUEO_OK = "loggin Ok";
	public static final String LOGUEO_ERROR = "login Failed";

	/**
	 * Indica el ancho de la textura
	 */
	private int textureWidth;
	
	
	/**
	 * Indica la altura de la textura
	 */
	private int textureHeight;

	/**
	 * Variable que se usa para manejar el logueo de un usuario
	 */
	private boolean loguear;
	
	
	/**
	 * String que se usa para verificar si el logueo se realizo correctamente
	 * o no.	
	 */
	private String respuestaLogueo = null;
	
	
	/**
	 * Representa el usuario que se loguea.
	 */
	private String user;

	/**
	 * Metodo constructor de U3DLoginState. Se encarga de setear el nombre 
	 * al estado.
	 * @param name el nombre a establecer al estado.
	 */
	public U3dLoginState(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub

	}

	/**
	 * Se encarga de dibujar el nodo raiz. Es llamado siempre por el 
	 * <code>GameStateManager</code> despues del update(float).
	 */
	public void render(float arg0) {
		super.render(arg0);
	}

	/**
	 * Actualiza el nodo raiz. Es llamado por el padre <code>GameStateNode</code>
	 * siempre antes del render(float).
	 */
	public void update(float arg0) {

		// HudManager.getInstance().getRoot()// solo necesito actualizar los
		HudManager.getInstance().getRoot()// solo necesito actualizar los
				// nodos del hud
				.updateGeometricState(0.0f, true);
		HudManager.getInstance().getRoot().updateRenderState();
		manejaLogueo();
	}

	
	/**
	 * Metodo que se encarga del logueo de los usuarios. En caso de ser 
	 * correcto genera una tarea <code>U3dCargandoTask</code> y la agrega 
	 * a la lista de tareas. En caso que el logueo sea incorrecto muestra 
	 * un dialogo pidiendo verificacion de los datos ingresados.
	 */
	private void manejaLogueo() {
		if (loguear) {

			if (LOGUEO_OK.equals(respuestaLogueo)) {
				U3dCargandoTask taskCargando = (U3dCargandoTask) TaskManager
						.getInstance().createTask("7"); // lo hago con un task
				// poruqe sino gana la
				// otra tarea y no llega
				// a mostrar el cartel
				// de cargando
				// deshacer
				TaskManager.getInstance().enqueue(taskCargando);

				// Solicita al servidor el ultimo estado del player
				try {
					MsgPlainText msg = (MsgPlainText) MessageFactory
							.getInstance().createMessage(
									MsgTypes.MSG_GET_PLAYER_TYPE);
					msg.setMsg(user);

					GameContext.setUserName(user);

					ITask taskGetPlayer = TaskCommFactory.getInstance()
							.createComTask(msg);
					TaskManager.getInstance().submit(taskGetPlayer);

					loguear = false;
				} catch (UnsopportedMessageException e) {
					e.printStackTrace();
				}
				HudManager.getInstance().setCursorVisible(false);
			}
			if (LOGUEO_ERROR.equals(respuestaLogueo)) {
				HudManager.getInstance().setCursorVisible(true);
				HashMap<String, String> botones = new HashMap<String, String>();
				botones.put("aceptar", "Aceptar");
				HudManager
						.getInstance()
						.muestraDialogo(
								"Fallo al intentar ingresar, verifique los datos e intente nuevamente.",
								botones, null);
				respuestaLogueo = null;
				loguear = false;

			}

		}
	}

	private int getAbsoluteX(double porcent) {
		return (int) (porcent * this.currentResolution[0] / 100);
	}

	private int getAbsoluteY(double porcent) {
		return (int) (porcent * this.currentResolution[1] / 100);
	}

	
	/**
	 * Agrega al <code>TaskManagerFactory</code> las factorys 
	 * <code>U3DCargandoTaskFactory</code> y <code>U3DLoginRequestTaskFactory</code>,
	 * setea las distintas caracteristicas de la pantalla de logueo
	 * llamando al metodo inicializaHUD() y actualiza el nodo raiz
	 * del hud y del mapa.
	 */
	public void initialize() {

		actualState = getName();
		TaskManagerFactory.getInstance().add(new U3DCargandoTaskFactory());
		TaskManagerFactory.getInstance().add(new U3DLoginRequestTaskFactory());
		inicializaHUD();

		// Quad imagenFondo = new Quad("fondo", DisplaySystem.getDisplaySystem()
		// .getWidth(), DisplaySystem.getDisplaySystem().getHeight());
		//
		// // create the texture state to handle the texture
		// final TextureState ts =
		// DisplaySystem.getDisplaySystem().getRenderer()
		// .createTextureState();
		// // load the image bs a texture (the image should be placed in the
		// same
		// // directory bs this class)
		// try {
		// ResourceLocatorTool.addResourceLocator(
		// ResourceLocatorTool.TYPE_TEXTURE,
		// new SimpleResourceLocator(Game.class.getClassLoader()
		// .getResource("login/")));
		// } catch (URISyntaxException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// final Texture texture = TextureManager.loadTexture(
		// "pantallalogin2.PNG", Texture.MinificationFilter.Trilinear, // of
		// // no
		// // use
		// // for
		// // the
		// // quad
		// Texture.MagnificationFilter.Bilinear, // of no use for the
		// // quad
		// 1.0f, true);
		// // set the texture for this texture state
		// ts.setTexture(texture);
		// // initialize texture width
		// textureWidth = ts.getTexture().getImage().getWidth();
		// // initialize texture height
		// textureHeight = ts.getTexture().getImage().getHeight();
		// // activate the texture state
		// ts.setEnabled(true);
		// // correct texture application:
		// final FloatBuffer texCoords = BufferUtils.createVector2Buffer(4);
		// // coordinate (0,0) for vertex 0
		// texCoords.put(getUForPixel(0)).put(getVForPixel(0));
		// // coordinate (0,40) for vertex 1
		// texCoords.put(getUForPixel(0)).put(getVForPixel(600));
		// // coordinate (40,40) for vertex 2
		// texCoords.put(getUForPixel(800)).put(getVForPixel(600));
		// // coordinate (40,0) for vertex 3
		// texCoords.put(getUForPixel(800)).put(getVForPixel(0));
		// // assign texture coordinates to the quad
		// imagenFondo.setTextureCoords(new TexCoords(texCoords));
		// // apply the texture state to the quad
		// imagenFondo.setRenderState(ts);
		// imagenFondo.setRenderQueueMode(Renderer.QUEUE_ORTHO);
		// imagenFondo.setLocalTranslation(new Vector3f(DisplaySystem
		// .getDisplaySystem().getWidth() / 2, DisplaySystem
		// .getDisplaySystem().getHeight() / 2, 0));
		// imagenFondo.setLightCombineMode(Spatial.LightCombineMode.Off);
		// imagenFondo.updateRenderState();
		//
		// rootNode.attachChild(imagenFondo);

		HudManager.getInstance().update();
	}

	private int[] currentResolution;

	/**
	 * Metodo que se encarga de crear la tarea <code>U3DLoginRequestTask</code>
	 * y agregarla a la lista de tareas del <code></TaskManager>
	 * @param event el evento ocurrido
	 */
	public void handleInput(ActionEvent event) {
		String action = event.getAction();
		if (action.equals("login")) {
			System.out.println("loguear");
			respuestaLogueo = null;
			HudManager.getInstance().update();

			U3DLoginRequestTask task = (U3DLoginRequestTask) TaskManager
					.getInstance().createTask("8");

			user = ((BTextField) HudManager.getInstance().getWindow("login")
					.getComponent(0)).getText();

			String password = ((BPasswordField) HudManager.getInstance()
					.getWindow("login").getComponent(2)).getText();

			task.initTask(user, password);

			TaskManager.getInstance().enqueue(task);

			// TODO cuando se integre con el modulo de comuniocacion eliminar
			// la linea proxima
			// setRespuestaLogueo(LOGUEO_OK);
		}
	}

	/**
	 * Este metodo se encarga de configurar los distintos parametros
	 * de la ventana de login 
	 */
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
		login.setStyleClass("login-window");
		this.currentResolution = new int[2];
		this.currentResolution[0] = (new Integer(DisplaySystem
				.getDisplaySystem().getWidth())).intValue();
		this.currentResolution[1] = (new Integer(DisplaySystem
				.getDisplaySystem().getHeight())).intValue();

		login.setSize(DisplaySystem.getDisplaySystem().getWidth(),
				DisplaySystem.getDisplaySystem().getHeight());

		userNameField = new BTextField();
		userNameField.setLocation(this.getAbsoluteX(58), this.getAbsoluteY(47));
		userNameField.setSize((int) (getSizeScaled(17)), getSizeScaled(4));

		login.add(userNameField, userNameField.getBounds());
		BLabel labelUsuario = new BLabel("Usuario");
		labelUsuario.setLocation(this.getAbsoluteX(45), this.getAbsoluteY(47));
		labelUsuario.setSize((int) (getSizeScaled(17)), getSizeScaled(4));
		login.add(labelUsuario, labelUsuario.getBounds());

		passwordField = new BPasswordField();
		passwordField.setLocation(this.getAbsoluteX(58), this
				.getAbsoluteY(54.5));
		passwordField.setSize((int) getSizeScaled(17), getSizeScaled(4));
		
		login.add(passwordField, passwordField.getBounds());
		BLabel labelPass=new BLabel("Contrase\u00F1a");
        labelPass.setLocation(this.getAbsoluteX(45), this
                        .getAbsoluteY(54.5));
        labelPass.setSize((int) (getSizeScaled(17)),
                        getSizeScaled(4));
        login.add(labelPass,labelPass.getBounds());
		// create a new BButton called "loginButton" with the display "Login"
		// and an "actionMessage" of "login"
		BButton loginButton = new BButton("Entrar", "login");
		loginButton.setLocation(this.getAbsoluteX(68.25), this
				.getAbsoluteY(64.833333));
		loginButton.setSize(getSizeScaled(14), getSizeScaled(5));

		// add our listener2 to the loginButton so it knows what to do with the
		// "actionMessage" when the button is clicked
		loginButton.addListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				handleInput(event);
			}
		});

		// add the loginButton to our login window
		login.add(loginButton, loginButton.getBounds());

		// add our login window to our BRootNode
		HudManager.getInstance().addWindow(login, "login");

		// center our window -- this could go anywhere in the code I simply
		// place it after my addWindow so I remember that I did it
		login.center();

	}

	private int getSizeScaled(double d) {
		return (int) (((float) DisplaySystem.getDisplaySystem().getWidth() / 100) * d);
	}

	private float getUForPixel(int xPixel) {
		return (float) xPixel / textureWidth;
	}

	private float getVForPixel(int yPixel) {
		return 1f - (float) yPixel / textureHeight;
	}

	
	public void initializeState() {
		// TODO Auto-generated method stub

	}

	public WorldGameState getWorld() {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateCamera() {
	}

	public void updateState(float interpolation) {

	}

	public void setRespuestaLogueo(String respuesta) {

		respuestaLogueo = respuesta;
		loguear = true;
	}

	public void initializeCamera(DynamicView playerView) {
	}

	public Vector3f getTranslation() {
		return new Vector3f();
	}

	@Override
	public boolean needClean() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getDialogText() {
		// TODO Auto-generated method stub
		return "No hay dialogo";
	}
}
