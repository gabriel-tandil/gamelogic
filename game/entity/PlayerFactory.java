package client.game.entity;

public class PlayerFactory implements IEntityFactory {

	private String id="PlayerFactory";

	public IEntity createEntity(String idEntity) {
		return new Player(idEntity);
	}

	public String getId() {
		return id;
	}

}

