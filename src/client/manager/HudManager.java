package client.manager;

import java.util.HashMap;
import java.util.Iterator;

import com.jme.input.MouseInput;
import com.jme.renderer.ColorRGBA;
import com.jme.system.DisplaySystem;
import com.jme.util.Timer;
import com.jmex.bui.BButton;
import com.jmex.bui.BLabel;
import com.jmex.bui.BStyleSheet;
import com.jmex.bui.BWindow;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.PolledRootNode;
import com.jmex.bui.background.TintedBackground;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.ActionListener;
import com.jmex.bui.event.ComponentListener;
import com.jmex.bui.layout.GroupLayout;
import com.jmex.game.state.BasicGameState;
import com.jmex.game.state.GameStateManager;

public class HudManager implements IHudManager {
	private boolean ocultaCursor = true;
	private static HudManager instance = null;
	protected PolledRootNode _root;
	BStyleSheet style = null;
	private HashMap<String, BWindow> ventanas;
	private BWindow ventanaControl;
	public HudManager() {

	}

	public static HudManager getInstance() {
		if (instance == null)
			instance = new HudManager();
		return instance;
	}

	public void initialize(Timer timer) {
		BuiSystem.init(new PolledRootNode(timer), "/HUD/style2.bss");
		_root = (PolledRootNode) BuiSystem.getRootNode();
		ventanas = new HashMap<String, BWindow>();
		style = BuiSystem.getStyle();
		crearVentanaControl();
		setCursorVisible(true);
	}

	public void render() {
		// TODO Auto-generated method stub

	}

	public void update() {

		for (Iterator<?> iterator = GameStateManager.getInstance().getChildren()
				.iterator(); iterator.hasNext();) {
			BasicGameState gs = (BasicGameState) iterator.next();
			if (gs.isActive()) {
				gs.getRootNode().attachChild(_root);

			} else {
				gs.getRootNode().detachChild(_root);

			}
			_root.updateGeometricState(0.0f, true);
			_root.updateRenderState();
		}

	}

	public PolledRootNode getRoot() {
		return _root;
	}

	public BStyleSheet getStyle() {
		return style;
	}

	public void setCargando() {
		try { // FIXME: este sleep cumple la funcion de que el taskManager no
				// meta ninguna otra tarea despues de esta en la iteracion
				// actual esto hace que se llame al update y al render y que el
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

	public void unSetCargando() {
		quitarEscrito("cargando");
	}

	public void quitarEscrito(String id) {
		removeWindow(id);
	}

	public void escribir(String texto) {

		escribir(texto, texto);

	}

	public void escribir(String texto, String id) {

		BWindow ventTexto = new BWindow(id,HudManager.getInstance().getStyle(),
				GroupLayout.makeVStretch());

		BLabel label = new BLabel(texto);
		ventTexto.add(label);
		ventTexto.setSize(200, 50);
		ventTexto.center();
		ventTexto.setBackground(0, new TintedBackground(ColorRGBA.green));
		addWindow(ventTexto,id);

	}

	public BWindow muestraDialogo(String texto, HashMap<String, String> botones,
			ComponentListener listener) {
		setCursorVisible(true);
		final BWindow ventDialogo = new BWindow(style, GroupLayout
				.makeVStretch());
		ventDialogo.setSize(270, 150 + (50 * botones.size()));
		BLabel label = new BLabel(texto);
		ventDialogo.add(label);
		ventDialogo.setBackground(0, new TintedBackground(ColorRGBA.darkGray));
		for (Iterator<String> iterator = botones.keySet().iterator(); iterator
				.hasNext();) {
			String clave = (String) iterator.next();
			BButton button = new BButton(botones.get(clave), clave);
			ventDialogo.add(button);
			button.addListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					ventDialogo.dispatchEvent(event);
					removeWindow("ventDialogo");
				}
			});
		}

		ventDialogo.addListener(listener);
		ventDialogo.center();

		addWindow(ventDialogo,"ventDialogo"); 
		return ventDialogo;
	}

	public void addWindow(BWindow wind, String id) {
		ventanas.put(id, wind);
		_root.addWindow(wind);
	}

	public void removeWindow(String id) {
		
		if (ventanas.get(id) != null){

			ventanas.get(id).dismiss();

			//	_root.removeWindow();
		ventanas.remove(id);
		if (ventanas.isEmpty()) desvincula();
		}
		
	}

	public BWindow getWindow(String id) {
		return ventanas.get(id);
	}

	public void muestraControl() {
		setCursorVisible(true);
		if (!GameStateManager.getInstance().getChild("login").isActive()) //en el login no muestro la ventana de control
			addWindow(ventanaControl,"control");
			update();	
	}

	private void crearVentanaControl() {
		GroupLayout gl= GroupLayout
		.makeHoriz(GroupLayout.CENTER);
		gl.setOffAxisJustification(GroupLayout.BOTTOM);
		ventanaControl = new BWindow(style, gl);

		ventanaControl.setSize(DisplaySystem.getDisplaySystem().getWidth(),
				DisplaySystem.getDisplaySystem().getHeight());
		ventanaControl.setStyleClass("control-window");

		ventanaControl.add(crearBoton("minimize",30,ventanaControl));
		ventanaControl.add(crearBoton("map",60,ventanaControl));
		ventanaControl.add(crearBoton("chat",80,ventanaControl));
		ventanaControl.add(crearBoton("help",60,ventanaControl));
		ventanaControl.add(crearBoton("close",30,ventanaControl));
		ventanaControl.addListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if ("minimize".equals(event.getAction())){
					removeWindow("control");
				}
				if ("map".equals(event.getAction())){}
				if ("chat".equals(event.getAction())){}
				if ("help".equals(event.getAction())){}
				if ("close".equals(event.getAction())){
					HashMap<String, String> botones = new HashMap<String, String>();
					botones.put("salir", "Salir");
					botones.put("noSalir", "Quedarme");
					muestraDialogo("Esto cerrara el programa.  \u00BFEst\u00E1s seguro?", botones, new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							if (event.getAction().equals("salir")) {
								System.exit(0);
							}								
						}
					});
				}
			}});
		
		ventanaControl.center();
		
	}

	private BButton crearBoton(String tipo,int tam,final BWindow ventana) {
		BButton button = new BButton("", tipo);
		button.setStyleClass("button-" + tipo);
//		button.setSize((int)((float)DisplaySystem.getDisplaySystem().getWidth()
//				/ 800 * tam), (int)((float)DisplaySystem.getDisplaySystem()
//				.getWidth()
//				/ 800 * tam));
//		button.setPreferredSize((int)((float)DisplaySystem.getDisplaySystem().getWidth()
//				/ 800 * tam), (int)((float)DisplaySystem.getDisplaySystem()
//				.getWidth()
//				/ 800 * tam));
		button.addListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				ventana.dispatchEvent(event);
			}
		});
		return button;
	}

	public void setCursorVisible(boolean sn) {
		if (sn)
			MouseInput.get().setCursorVisible(true);
		else if (ocultaCursor
				&& (!ventanas.containsKey("control") || !ventanas
						.containsKey("ventDialogo")))
			MouseInput.get().setCursorVisible(false);
	}

	public void desvincula() {
		for (Iterator<?> iterator = GameStateManager.getInstance().getChildren()
				.iterator(); iterator.hasNext();) {
			BasicGameState gs = (BasicGameState) iterator.next();
			if (gs.isActive()) {
				gs.getRootNode().detachChild(_root);
			}
			_root.updateGeometricState(0.0f, true);
			_root.updateRenderState();
		}
		setCursorVisible(false);
	}
}
