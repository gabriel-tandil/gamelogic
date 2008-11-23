package client.game.entity;

public class U3DBuildingEntityFactory implements IEntityFactory {

	private String id="2";
	@Override
	public IEntity createEntity() {
		return new U3DBuildingEntity(id);
	}

	@Override
	public String getId() {
		return id;
	}

}
