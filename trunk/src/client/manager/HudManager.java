package client.manager;

import java.util.Iterator;

import com.jme.scene.Node;
import com.jme.scene.Spatial;
import com.jmex.game.state.BasicGameState;
import com.jmex.game.state.GameStateManager;

public class HudManager implements IHudManager {
	private static HudManager instance=null;
	private Node huds;
	
	public HudManager() {
		this.huds = new Node("huds");
	}

	public static HudManager getInstance(){
		if(instance==null) instance=new HudManager();
		return instance;
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
				gs.getRootNode().attachChild(huds);
			}
			else{
				gs.getRootNode().detachChild(huds);
			}
		}

	}
	public void addHud(Spatial elHud){
		huds.attachChild(elHud);
	}
	public void removeHud(Spatial elHud){
		huds.detachChild(elHud);
	}

}
