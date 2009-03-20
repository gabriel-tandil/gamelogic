package client.manager;

import java.util.HashMap;
import java.util.Iterator;

import com.jme.input.MouseInput;
import com.jme.system.DisplaySystem;
import com.jme.util.Timer;
import com.jmex.bui.BButton;
import com.jmex.bui.BContainer;
import com.jmex.bui.BLabel;
import com.jmex.bui.BStyleSheet;
import com.jmex.bui.BWindow;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.PolledRootNode;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.ActionListener;
import com.jmex.bui.event.ComponentListener;
import com.jmex.bui.layout.AbsoluteLayout;
import com.jmex.bui.layout.GroupLayout;
import com.jmex.game.state.BasicGameState;
import com.jmex.game.state.GameStateManager;

/**
 * <code>HudManager</code> implementa la interface IHudManager, encargada de la
 * visualización del Hud. Es la encargada de manejar el gbui. se trata de
 * encapsular todo el compartamiento de hud aqui.
 * 
 * @author Gabriel Alvarez
 * 
 */
public class HudManager implements IHudManager {
	/**
	 * Usado para debug, en caso de que se quiera inhivir la ocultacion del
	 * mouse
	 */
	private boolean ocultaCursor = true;

	/**
	 * La instancia unica del <code>HudManager</code>.
	 */
	private static HudManager instance = null;

	/**
	 * El nodo raiz del gbui
	 */
	protected PolledRootNode _root;

	/**
	 * El estilo por defecto usado en todo el juego para los huds
	 */
	BStyleSheet style = null;

	/**
	 * mapa que mantiene la lista de ventanas
	 */
	private HashMap<String, BWindow> ventanas;

	/**
	 * La ventana con los botones del control del juego.
	 */
	private BWindow ventanaControl;

	/**
	 * Mantiene la instancia del mapa indicativo de la posicion del player.
	 */
	Map map = new Map();

	/**
	 * flag que indica si el mapa se esta mostrando.
	 */
	private boolean muestraMapa = false;

	/**
	 *@return la instancia del mapa indicativo de la posicion del player.
	 */
	public Map getMap() {
		return map;
	}

	/**
	 * constructor por defecto
	 */
	public HudManager() {

	}

	/**
	 *@return la instancia unica del <code>HudManager</code>.
	 */
	public static HudManager getInstance() {
		if (instance == null)
			instance = new HudManager();
		return instance;
	}

	/**
	 * inicializa la clase, construye el nodo raiz del gbui, el mapa de
	 * ventanas, configura el estilo por defecto crea la ventana de control y
	 * carga el mapa indicativo de posicion
	 * 
	 * @param timer
	 *            el timer del sistema
	 */
	public void initialize(Timer timer) {
		BuiSystem.init(new PolledRootNode(timer), "/HUD/style2.bss");
		_root = (PolledRootNode) BuiSystem.getRootNode();
		ventanas = new HashMap<String, BWindow>();
		style = BuiSystem.getStyle();
		crearVentanaControl();
		setCursorVisible(true);
		map.loadMap();
	}

	/**
	 * actualmente no se usa en esta implementacion, se dejo porque esta
	 * definido en el diseño
	 */
	public void render() {
		// TODO Auto-generated method stub

	}

	/**
	 * vincula el nodo raiz del hud y el del mapa al state activo actualmente y
	 * los marca para actualizar.
	 */
	public void update() {

		for (Iterator<?> iterator = GameStateManager.getInstance()
				.getChildren().iterator(); iterator.hasNext();) {
			BasicGameState gs = (BasicGameState) iterator.next();
			if (gs.isActive()) {
				if (!ventanas.isEmpty())
					gs.getRootNode().attachChild(_root);
				if (muestraMapa)
					gs.getRootNode().attachChild(map.getHudMap());
				else
					gs.getRootNode().detachChild(map.getHudMap());
			} else {
				gs.getRootNode().detachChild(_root);
				gs.getRootNode().detachChild(map.loadMap());

			}
			_root.updateGeometricState(0.0f, true);
			_root.updateRenderState();
		}

	}

	/**
	 * 
	 * @return el nodo raiz del gbui
	 */
	public PolledRootNode getRoot() {
		return _root;
	}

	/**
	 * 
	 * @return el estilo por defecto
	 */
	public BStyleSheet getStyle() {
		return style;
	}

	/**
	 * crea una ventana con la leyenda de que se esta cargando el juego para
	 * indicarle al usuario que espere
	 */
	public void setCargando() {
		try { // FIXME: este sleep cumple la funcion de que el taskManager no
			// meta ninguna otra tarea despues de esta en la iteracion
			// actual; esto hace que se llame al update y al render y que el
			// cartel de "Cargando" sea mostrado, sino puede fallar y
			// mostrarse o no erraticamente. Cuando se mejore el
			// taskManager, con por ejemplo soporte para threads corregir
			// esto.
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		escribir("Cargando el mundo...", "cargando");

	}

	/**
	 * quita la leyenda informativa de que se esta cargando el juego
	 */
	public void unSetCargando() {
		quitarEscrito("cargando");
	}

	/**
	 * elimina un texto mostrado en la pantalla con la etiqueta de ventana id
	 * 
	 * @param id
	 */
	public void quitarEscrito(String id) {
		removeWindow(id);
	}

	/**
	 * escribe <code>texto</code> en un label en una ventana
	 * 
	 * @param texto
	 *            a mostrar
	 */
	public void escribir(String texto) {

		escribir(texto, texto);

	}

	/**
	 * escribe <code>texto</code> en un label en una ventana almacenada con el
	 * identificador <code>id</code>
	 * 
	 * @param texto
	 *            a mostrar
	 * @param id
	 *            identificador
	 */
	public void escribir(String texto, String id) {

		BWindow ventTexto = new BWindow(id,
				HudManager.getInstance().getStyle(), GroupLayout.makeVStretch());
		BLabel label = new BLabel(texto);
		ventTexto.add(label);
		ventTexto.setSize(200, 50);
		ventTexto.center();
		addWindow(ventTexto, id);

	}

	/**
	 * 
	 * @param texto
	 *            mensaje del dialogo
	 * @param botones
	 *            hashing con nombres de botones y sus string de accion
	 * @param listener
	 *            un listener que maneje las acciones de los botones
	 * @return
	 */
	public BWindow muestraDialogo(String texto,
			HashMap<String, String> botones, ComponentListener listener) {
		setCursorVisible(true);
		final BWindow ventDialogo = new BWindow(style, GroupLayout
				.makeVStretch());
		final BContainer ventBotones = new BContainer(GroupLayout
				.makeHStretch());

		ventDialogo.setSize(180 + (50 * botones.size()),
				80 + ((texto.length() / 40) * 60));
		BLabel label = new BLabel(texto);
		ventDialogo.add(label);

		for (Iterator<String> iterator = botones.keySet().iterator(); iterator
				.hasNext();) {
			String clave = (String) iterator.next();
			BButton button = new BButton(botones.get(clave), clave);
			ventBotones.add(button);
			button.addListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					ventDialogo.dispatchEvent(event);
					removeWindow("ventDialogo");
				}
			});
		}
		ventDialogo.add(ventBotones);
		ventDialogo.addListener(listener);
		ventDialogo.center();

		addWindow(ventDialogo, "ventDialogo");
		return ventDialogo;
	}

	/**
	 * 
	 * @return verdadero si esta mostrando algun dialogo
	 */
	public boolean isMostrandoDialogo() {
		return (ventanas.get("ventDialogo") != null);
	}

	/**
	 * configura la ventana dada bajo el control del hudManager y llama a update
	 * para mostrar el nodo si no se estaba mostrando
	 * 
	 * @param wind
	 *            ventana
	 * @param id
	 *            identificador
	 */
	public void addWindow(BWindow wind, String id) {
		ventanas.put(id, wind);
		_root.addWindow(wind);
		update();
	}

	/**
	 * quita la ventana asociada a id y si no hay mas ventanas visibles llama a
	 * desvincula para quitar el nodo raiz del state activo
	 * 
	 * @param id
	 */
	public void removeWindow(String id) {

		if (ventanas.get(id) != null) {

			ventanas.get(id).dismiss();

			// _root.removeWindow();
			ventanas.remove(id);
			if (ventanas.isEmpty())
				desvincula();
		}

	}

	/**
	 * 
	 * @param id
	 * @return la ventana referenciada por id
	 */
	public BWindow getWindow(String id) {
		return ventanas.get(id);
	}

	/**
	 * hace visible la ventana con los controles del juego
	 */
	public void muestraControl() {
		setCursorVisible(true);
		if (!GameStateManager.getInstance().getChild("login").isActive()) // en
			// el
			// login
			// no
			// muestro
			// la
			// ventana
			// de
			// control
			addWindow(ventanaControl, "control");
	}

	/**
	 * inicializa la ventana con los botones de control del juego
	 */
	private void crearVentanaControl() {
		GroupLayout gl = GroupLayout.makeHoriz(GroupLayout.CENTER);
		gl.setOffAxisJustification(GroupLayout.BOTTOM);
		ventanaControl = new BWindow(style, new AbsoluteLayout(true));

		ventanaControl.setSize(DisplaySystem.getDisplaySystem().getWidth(),
				DisplaySystem.getDisplaySystem().getHeight());
		ventanaControl.setStyleClass("control-window");
		BButton boton = crearBoton("minimize", 30, ventanaControl, 32,
				"Minimiza la vantana de controles. Vuelva a abrirla con 'C'");
		ventanaControl.add(boton, boton.getBounds());
		boton = crearBoton("map", 60, ventanaControl, 40,
				"Conmuta la visibilidad del mapa de referencia.");
		ventanaControl.add(boton, boton.getBounds());
		boton = crearBoton("chat", 80, ventanaControl, 50, null);
		ventanaControl.add(boton, boton.getBounds());
		boton = crearBoton("help", 60, ventanaControl, 60, null);
		ventanaControl.add(boton, boton.getBounds());
		boton = crearBoton("close", 30, ventanaControl, 67, "Cierra el juego.");
		ventanaControl.add(boton, boton.getBounds());

		ventanaControl.addListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if ("minimize".equals(event.getAction())) {
					removeWindow("control");
				}
				if ("map".equals(event.getAction())) {
					muestraMapa = !muestraMapa;
					update();
				}
				if ("chat".equals(event.getAction())) {
				}
				if ("help".equals(event.getAction())) {
				}
				if ("close".equals(event.getAction())) {
					HashMap<String, String> botones = new HashMap<String, String>();
					botones.put("salir", "Salir");
					botones.put("noSalir", "Quedarme");
					muestraDialogo(
							"Esto cerrara el programa.  \u00BFEst\u00E1s seguro?",
							botones, new ActionListener() {
								public void actionPerformed(ActionEvent event) {
									if (event.getAction().equals("salir")) {
										System.exit(0);
									}
								}
							});
				}
			}
		});

		ventanaControl.center();

	}

	/**
	 * 
	 * @param tipo
	 *            el tipo del boton, para configurar su accion y estilo
	 * @param tam
	 *            tamaño
	 * @param ventana
	 *            para vincular el listener
	 * @param porcentajeX
	 *            posicion en x donde mostrar el boton
	 * @param tooltip
	 *            texto de ayuda
	 * @return
	 */
	private BButton crearBoton(String tipo, int tam, final BWindow ventana,
			float porcentajeX, String tooltip) {
		BButton button = new BButton("", tipo);
		button.setStyleClass("button-" + tipo);
		button.setSize((int) ((float) DisplaySystem.getDisplaySystem()
				.getWidth() / 800 * tam), (int) ((float) DisplaySystem
				.getDisplaySystem().getWidth() / 800 * tam));
		button
				.setLocation(
						(int) (DisplaySystem.getDisplaySystem().getWidth() * (porcentajeX / 100))
								- button.getWidth() / 2, DisplaySystem
								.getDisplaySystem().getHeight()
								- button.getHeight());
		button.addListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				ventana.dispatchEvent(event);
			}
		});
		button.setTooltipText(tooltip);
		return button;
	}

	/**
	 * configura si se muestra o no el cursor durante el juego
	 * 
	 * @param sn
	 *            si o no
	 */
	public void setCursorVisible(boolean sn) {
		if (sn)
			MouseInput.get().setCursorVisible(true);
		else if (ocultaCursor
				&& (!ventanas.containsKey("control") || !ventanas
						.containsKey("ventDialogo")))
			MouseInput.get().setCursorVisible(false);
	}

	/**
	 * quita el nodo raiz del gbui del state activo del juego
	 */
	public void desvincula() {
		for (Iterator<?> iterator = GameStateManager.getInstance()
				.getChildren().iterator(); iterator.hasNext();) {
			BasicGameState gs = (BasicGameState) iterator.next();
			if (gs.isActive()) {
				gs.getRootNode().detachChild(_root);
				// gs.getRootNode().detachChild(map.loadMap());
			}
			_root.updateGeometricState(0.0f, true);
			_root.updateRenderState();
		}
		setCursorVisible(false);
	}

	/**
	 * confgura si el mapa de referencia es mostrado o no
	 * 
	 * @param sn
	 *            si o no
	 */
	public void setMuestraMapa(boolean sn) {
		muestraMapa = sn;
	}

}
