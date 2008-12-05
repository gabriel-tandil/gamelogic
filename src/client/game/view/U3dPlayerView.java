package client.game.view;

import client.game.entity.DynamicEntity;
import client.game.entity.IDynamicEntity;
import client.game.entity.Player;


public class U3dPlayerView extends DynamicView {
	
	public U3dPlayerView(IDynamicEntity entity) {
		super(entity);
	}


	public void update(float interpolation) {
//		this.localTranslation.x = ((Player)this.getEntity()).getPosition().x;
//		this.localTranslation.y = ((Player)this.getEntity()).getPosition().y;
//		this.localTranslation.z = ((Player)this.getEntity()).getPosition().z;
//		this.updateWorldTranslation();
	}

}
