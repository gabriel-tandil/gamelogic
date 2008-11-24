package client.game.entity;

public class U3DBuildingEntityFactory implements IEntityFactory {

	private String id="1";


	public IEntity createEntity() {
		return new U3DBuildingEntity(id);
	}

	public String getId() {
		return id;
	}

}
