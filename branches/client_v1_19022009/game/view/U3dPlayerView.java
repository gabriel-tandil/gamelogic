package client.game.view;

import client.game.entity.DynamicEntity;
import client.game.entity.IDynamicEntity;
import client.game.entity.Player;


public class U3dPlayerView extends DynamicView {
	
	public U3dPlayerView(IDynamicEntity entity) {
		super(entity);
	}


	public void update(float interpolation) {
		this.localTranslation = ((Player)this.getEntity()).getPosition();
		this.updateWorldTranslation();
	}

}
