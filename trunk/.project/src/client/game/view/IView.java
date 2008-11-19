/**
 * 
 */
package client.game.view;

import client.game.entity.IEntity;

import com.jme.scene.Node;
import com.jme.scene.Spatial;
import com.jme.util.export.Savable;

/** 
 * <code>IView</code> define la interface para todos los tipos de vistas que
 * reprentan entidades en el sistema.
 * <code>IView</code> define la representación gráfica de una entidad. Mantiene
 * información que incluye meshes, animaciones, etc. asociadas con la entidad, la
 * cual puede ser adjuntada a la escena gráfica para renderización.
 * <code>IView</code> extiende de la interface <code>Savable</code> para que pueda
 * ser directamente guardada en un formato binario JME el cual luego puede ser 
 * importado en tiempo de ejecución del juego.
 * 
 * @author Maria Hansen
 * @version Creation date: 29-10-2008
 */
public interface IView extends Savable {
	/** 
	 * Agrega el mesh dado a esta vista.
	 * @param mesh El Spatial a ser agregado.
	 */
	public void attachSpatial(Spatial mesh);

	/** 
	 * Agrega esta vista al nodo dado.
	 * @param parent El nodo padre al cual agregarse.
	 */
	public void attachTo(Node parent);

	/**
	 * Separa esta vista de la escena gráfica padre.
	 * @return True si la vista es separada exitosamente. False si esta vista no es 
	 * una escena gráfica.
	 */
	public boolean detachFromParent();

	/** 
	 * Devuelve la entidad que esta vista representa.
	 * @return La instancia <code>IEntity</code>.
	 */
	public IEntity getEntity();

	/**
	 * @return True si esta vista es una instancia de IDynamicView.
	 */
	public boolean isDynamicView();

}