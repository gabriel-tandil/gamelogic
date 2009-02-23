package client.game.entity;

public class U3DBuildingEntityFactory implements IEntityFactory {

	private String id="EntityFactory";
	
	public IEntity createEntity(String idEntity) {
		return new U3DBuildingEntity(idEntity);
	}
	
	public String getId() {
		return id;
	}
}
