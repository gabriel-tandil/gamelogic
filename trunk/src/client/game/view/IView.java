/**
 * 
 */
package client.game.view;

import com.jme.util.export.Savable;
import com.jme.scene.Spatial;
import com.jme.scene.Node;
import client.game.entity.IEntity;

/** 
 * @author
 */
public interface IView extends Savable {
	/** 
	 * @param spatial
	 */
	public void attachSpatial(Spatial spatial);

	/** 
	 * @param parent
	 */
	public void attachTo(Node parent);

	/** 
	 * @return
	 */
	public IEntity getEntity();
	
	/**
	 * @return
	 */
	public boolean isValidView();
}