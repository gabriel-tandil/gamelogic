package client.game.controller;

import client.game.entity.IDynamicEntity;

public class PlayerControllerFactory implements IControllerFactory {

	private String id="PlayerControllerFactory"; 
	
	public IController createController(IDynamicEntity entity) {
		return new PlayerController(entity);
	}

	public String getId() {
		return id;
	}

}
