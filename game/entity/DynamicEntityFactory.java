package client.game.entity;

import java.io.IOException;

import com.jme.math.Vector3f;
import com.jme.util.export.JMEExporter;
import com.jme.util.export.JMEImporter;

public class DynamicEntityFactory implements IEntityFactory {

	private String id="DynamicEntityFactory";
	
	public IEntity createEntity(String idEntity) {
		return new DynamicEntity(idEntity);
	}
	
	public String getId() {
		return id;
	}
}
