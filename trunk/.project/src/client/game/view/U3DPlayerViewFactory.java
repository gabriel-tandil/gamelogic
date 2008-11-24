package client.game.view;

import client.game.entity.IDynamicEntity;
import client.game.entity.IEntity;

public class U3DPlayerViewFactory implements IViewFactory {

	private String id="2";


	public IView createView(IEntity entity) {
		return new U3dPlayerView((IDynamicEntity) entity);
	}

	public String getId() {
		return id;
	}

}
