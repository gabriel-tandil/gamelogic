package client.game.controller;

import client.game.entity.IDynamicEntity;

public class U3DPlayerControllerFactory implements IControllerFactory {

	private String id="1"; 
	@Override
	public IController createController(IDynamicEntity entity) {
		return new U3DPlayerController(entity);
	}

	@Override
	public String getId() {
		return id;
	}

}
