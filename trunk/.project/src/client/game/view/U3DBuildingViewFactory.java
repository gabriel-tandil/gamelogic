package client.game.view;

import client.game.entity.IEntity;

public class U3DBuildingViewFactory implements IViewFactory {

	private String id="1";
	@Override
	public IView createView(IEntity entity) {
		return new U3dBuildingView(entity);
	}

	@Override
	public String getId() {
		return id;
	}

}
