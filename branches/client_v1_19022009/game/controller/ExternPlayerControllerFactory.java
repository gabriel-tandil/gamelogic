package client.game.controller;

import client.game.entity.IDynamicEntity;

public class ExternPlayerControllerFactory implements IControllerFactory {

	private String id="DynamicEntityControllerFactory"; 
	
	public IController createController(IDynamicEntity entity) {
		return new ExternPlayerController(entity);
	}

	public String getId() {
		return id;
	}

}
