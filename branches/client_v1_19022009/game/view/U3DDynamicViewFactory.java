package client.game.view;

import client.game.entity.IDynamicEntity;
import client.game.entity.IEntity;


public class U3DDynamicViewFactory implements IViewFactory {

	
	public IView createView(IEntity entity) {
		return new U3DDynamicView((IDynamicEntity) entity);
	}
	
	private String id="DynamicEntity";

	public String getId() {
		return id;
	}

}