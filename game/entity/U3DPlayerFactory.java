package client.game.entity;

public class U3DPlayerFactory implements IEntityFactory {

	private String id="2";

	public IEntity createEntity() {
		
		return new U3DPlayer(id);
	}

	public String getId() {
		return id;
	}

}
