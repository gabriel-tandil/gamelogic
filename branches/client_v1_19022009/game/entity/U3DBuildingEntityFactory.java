package client.game.entity;

/**
 * La clase <code>U3DBuildingEntityFactory</code> implementa
 * la clase <code>IEntityFactory</code>, 
 * es la clase que representa a un jugador 
 * particular en el juego.
 * 
 * @author kike
*/

public class U3DBuildingEntityFactory implements IEntityFactory {

	private String id="EntityFactory";
	
	public IEntity createEntity(String idEntity) {
		return new U3DBuildingEntity(idEntity);
	}
	
	public String getId() {
		return id;
	}
}
