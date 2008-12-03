package client.game.view;

import client.game.entity.IEntity;

public class U3DBuildingViewFactory implements IViewFactory {

	private String id="1";


	public IView createView(IEntity entity) {
		return new U3dBuildingView(entity);
	}

	public String getId() {
		return id;
	}

}
