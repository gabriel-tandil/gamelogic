package client.game.view;

import client.game.entity.DynamicEntity;
import client.game.entity.IDynamicEntity;

public class U3DDynamicView extends DynamicView {

	public U3DDynamicView(IDynamicEntity entity) {
		super(entity);
	}

	public void update(float interpolation) {
		this.localTranslation = ((DynamicEntity)this.getEntity()).getPosition();
		this.updateWorldTranslation();
		
	}
}