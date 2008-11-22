package client.game.view;

import client.game.entity.IDynamicEntity;
import client.game.entity.IEntity;
import client.game.entity.IEntityFactory;

public class U3DPlayerViewFactory implements IViewFactory {

	private String id="2";
	@Override
	public IView createView(IEntity entity) {
		return new U3dPlayerView((IDynamicEntity) entity);
	}

	@Override
	public String getId() {
		return id;
	}

}
