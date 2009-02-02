package client.manager;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

import client.game.U3dgame;

import com.jme.app.SimpleGame;
import com.jme.input.FirstPersonHandler;
import com.jme.input.MouseInput;
import com.jme.renderer.Camera;
import com.jme.system.DisplaySystem;
import com.jme.util.Timer;
import com.jmex.bui.BStyleSheet;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.PolledRootNode;
import com.jmex.bui.provider.DefaultResourceProvider;
import com.jmex.game.state.BasicGameState;
import com.jmex.game.state.GameStateManager;

public class HudManager implements IHudManager {
	private static HudManager instance=null;
	protected PolledRootNode _root;
	BStyleSheet style = null;
	public HudManager() {
		
	}

	public static HudManager getInstance(){
		if(instance==null) instance=new HudManager();
		return instance;
	}
	public void initialize(Timer timer){

	
        BuiSystem.init(new PolledRootNode(timer), "/HUD/style2.bss");
        _root  = (PolledRootNode) BuiSystem.getRootNode();
		MouseInput.get().setCursorVisible(true);


			style = BuiSystem.getStyle();
	}
	@Override
	public void render() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {

		for (Iterator iterator =GameStateManager.getInstance().getChildren().iterator(); iterator.hasNext();) {
			BasicGameState gs= (BasicGameState) iterator.next();
			if (gs.isActive()){
				gs.getRootNode().attachChild(_root);
			}
			else{
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

}
