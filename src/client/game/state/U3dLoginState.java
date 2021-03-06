package client.game.state;

import java.util.HashMap;

import client.game.task.TaskManagerFactory;
import client.game.task.U3DCargandoTaskFactory;
import client.game.task.U3DChangeToExteriorTaskFactory;
import client.game.task.U3DLoginRequestTask;
import client.game.task.U3DLoginRequestTaskFactory;
import client.game.task.U3dCargandoTask;
import client.game.task.U3dChangeToExterior;
import client.manager.U3dHudManager;
import client.manager.TaskManager;

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
/**
 * clase que representa el estado del login. Desde esta clase se maneja la visualización
 * del cartel que dice "cargando" una vez q se loguea un usuario.Esta clase hereda de U3dState que 
 * a su vez hereda de WorldGameState
 * 
 *
 */
public class U3dLoginState extends U3dState {
	private static final String LOGUEO_OK = "loggin Ok";
	private static final String LOGUEO_ERROR = "login Failed";
	private boolean loguear=false;
	private String respuestaLogueo = null;

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
		manejaLogueo();
	}

	private void manejaLogueo() {
		if (loguear) {

			if (LOGUEO_OK.equals(respuestaLogueo)) {
				U3dCargandoTask taskCargando = (U3dCargandoTask) TaskManager
						.getInstance().createTask("7"); // lo hago con un task
				// poruqe sino gana la
				// otra tarea y no llega
				// a mostrar el cartel
				// de cargando
				TaskManager.getInstance().enqueue(taskCargando);
				U3dChangeToExterior task = (U3dChangeToExterior) TaskManager
						.getInstance().createTask("3");
				TaskManager.getInstance().enqueue(task);
				U3dHudManager.getInstance().setCursorVisible(false);
				loguear = false;
			}
			if (LOGUEO_ERROR.equals(respuestaLogueo)) {
				U3dHudManager.getInstance().setCursorVisible(true);
				HashMap<String, String> botones = new HashMap<String, String>();
				botones.put("aceptar", "Aceptar");
				U3dHudManager.getInstance().muestraDialogo("Fallo al intentar ingresar, verifique los datos e intente nuevamente.", botones,null);
				
				loguear = false;

			}

		}
	}

	private int getAbsoluteX(double porcent) {
		return (int) (porcent * DisplaySystem.getDisplaySystem().getWidth() / 100);
	}

	private int getAbsoluteY(double porcent) {
		return (int) (porcent * DisplaySystem.getDisplaySystem().getHeight() / 100);
	}

	public void initialize() {
		TaskManagerFactory.getInstance().add(
				new U3DChangeToExteriorTaskFactory());
		TaskManagerFactory.getInstance().add(new U3DCargandoTaskFactory());
		TaskManagerFactory.getInstance().add(new U3DLoginRequestTaskFactory());
		inicializaHUD();
		U3dHudManager.getInstance().update();
	}

	public void handleInput(ActionEvent event) {
		String action = event.getAction();
		if (action.equals("login")) {
			System.out.println("loguear");
			respuestaLogueo = null;
			loguear = true;
			U3dHudManager.getInstance().update();

			U3DLoginRequestTask task = (U3DLoginRequestTask) TaskManager
					.getInstance().createTask("8");
			task.initTask(((BTextField) U3dHudManager.getInstance().getWindow(
					"login").getComponent(0)).getText(),
					((BPasswordField) U3dHudManager.getInstance().getWindow(
							"login").getComponent(2)).getText());
			TaskManager.getInstance().enqueue(task);

			// TODO cuando se integre con el modulo de comuniocacion eliminar
			// la linea proxima
			setRespuestaLogueo(LOGUEO_OK);
		}
	}

	private void inicializaHUD() {
		BWindow login;
		BTextField userNameField;
		BComponent passwordField;
		// instantiate our login window
		login = new BWindow(U3dHudManager.getInstance().getStyle(),
				new AbsoluteLayout(true)/* GroupLayout.makeVStretch() */);
		login.setStyleClass("login-window");

		login.setSize(DisplaySystem
				.getDisplaySystem().getWidth(), DisplaySystem
				.getDisplaySystem().getHeight());

		userNameField = new BTextField();
		userNameField.setLocation(this.getAbsoluteX(58), this
				.getAbsoluteY(47));
		userNameField.setSize((int) (getSizeScaled(17)),
				getSizeScaled(4));
		login.add(userNameField, userNameField.getBounds());
		BLabel labelUsuario=new BLabel("Usuario");
		labelUsuario.setLocation(this.getAbsoluteX(45), this
				.getAbsoluteY(47));
		labelUsuario.setSize((int) (getSizeScaled(17)),
				getSizeScaled(4));		
		login.add(labelUsuario,labelUsuario.getBounds());
		passwordField = new BPasswordField();
		passwordField.setLocation(this.getAbsoluteX(58), this
				.getAbsoluteY(54.5));
		passwordField.setSize((int) getSizeScaled(17),
				getSizeScaled(4));
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
		loginButton.setSize( getSizeScaled(14), getSizeScaled(5));

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
		U3dHudManager.getInstance().addWindow(login, "login");

		// center our window -- this could go anywhere in the code I simply
		// place it after my addWindow so I remember that I did it
		login.center();

	}

	private int getSizeScaled(double d) {
		return (int) (((float) DisplaySystem.getDisplaySystem().getWidth() / 100) * d);
	}
	@Override
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
	}
}
