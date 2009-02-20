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
	private boolean ocultaCursor = false;
	private static HudManager instance = null;
	protected PolledRootNode _root;
	BStyleSheet style = null;
	private HashMap<String, BWindow> ventanas;

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
		setCursorVisible(true);
	}

	public void render() {
		// TODO Auto-generated method stub

	}

	public void update() {

		for (Iterator iterator = GameStateManager.getInstance().getChildren()
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
		try { // esto es una cagada pero es porque arrancaba a ejecutar una
			// tarea
			// encolada antes de hacer el render, con esta espera el task
			// manager
			// no alcanza a meter mas tareas y todo funciona bien
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

		BWindow ventTexto = new BWindow(HudManager.getInstance().getStyle(),
				GroupLayout.makeVStretch());

		BLabel label = new BLabel(texto);
		ventTexto.add(label);
		ventTexto.setSize(200, 50);
		ventTexto.center();
		ventTexto.setBackground(0, new TintedBackground(ColorRGBA.green));
		_root.addWindow(ventTexto, true);
		ventanas.put(id, ventTexto);

	}

	public void muestraDialogo(String texto, HashMap<String, String> botones,
			ComponentListener listener) {
		setCursorVisible(true);
		final BWindow ventDialogo = new BWindow(style, GroupLayout
				.makeVStretch());

		ventDialogo.setSize(270, 150 + (50 * botones.size()));
		BLabel label = new BLabel(texto);
		ventDialogo.add(label);
		ventDialogo.setBackground(0, new TintedBackground(ColorRGBA.darkGray));
		for (Iterator iterator = botones.keySet().iterator(); iterator
				.hasNext();) {
			String clave = (String) iterator.next();
			BButton button = new BButton(botones.get(clave), clave);
			ventDialogo.add(button);
			button.addListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					setCursorVisible(false);
					ventDialogo.dispatchEvent(event);
					ventDialogo.getRootNode().removeWindow(ventDialogo);
				}
			});
		}

		ventDialogo.addListener(listener);
		ventDialogo.center();

		_root.addWindow(ventDialogo); // a esta ventana no preciso agregarla
		// ya que se saca sola al cerrarse el
		// dialogo

	}

	public void addWindow(BWindow wind, String id) {
		ventanas.put(id, wind);
		_root.addWindow(wind);
	}

	public void removeWindow(String id) {
		if (ventanas.get(id) != null)
			_root.removeWindow(ventanas.get(id));
		ventanas.remove("id");
	}

	public BWindow getWindow(String id) {
		return ventanas.get(id);
	}

	public void muestraControl() {
		setCursorVisible(true);
		GroupLayout gl= GroupLayout
		.makeHoriz(GroupLayout.CENTER);
		gl.setOffAxisJustification(GroupLayout.BOTTOM);
		final BWindow ventana = new BWindow(style, gl);

		ventana.setSize(DisplaySystem.getDisplaySystem().getWidth(),
				DisplaySystem.getDisplaySystem().getHeight());
		ventana.setStyleClass("control-window");

		ventana.add(crearBoton("minimize",30));
		ventana.add(crearBoton("map",60));
		ventana.add(crearBoton("chat",80));
		ventana.add(crearBoton("help",60));
		ventana.add(crearBoton("close",30));
		
		
		
		// button.addListener(new ActionListener() {
		// public void actionPerformed(ActionEvent event) {
		// MouseInput.get().setCursorVisible(false);
		// ventana.dispatchEvent(event);
		// ventana.getRootNode().removeWindow(ventana);
		// }
		// });

		ventana.center();

		_root.addWindow(ventana); // a esta ventana no preciso agregarla
		// ya que se saca sola al cerrarse el
		// dialogo
		this.update();
	}

	private BButton crearBoton(String tipo,int tam) {
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

		return button;
	}

	public void setCursorVisible(boolean sn) {
		if (sn || ocultaCursor)
			MouseInput.get().setCursorVisible(sn);
	}

	public void desvincula() {
		for (Iterator iterator = GameStateManager.getInstance().getChildren()
				.iterator(); iterator.hasNext();) {
			BasicGameState gs = (BasicGameState) iterator.next();
			if (gs.isActive()) {
				gs.getRootNode().detachChild(_root);
			}
			_root.updateGeometricState(0.0f, true);
			_root.updateRenderState();
		}
		
	}
}
