package client.game.entity;

public class U3DPlayerFactory implements IEntityFactory {

	private String id="1";
	@Override
	public IEntity createEntity() {
		
		return new U3DPlayer();
	}

	@Override
	public String getId() {
		return id;
	}

}
