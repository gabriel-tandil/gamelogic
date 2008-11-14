/**
 * 
 */
package client.game.view;

/** 
 * <code>IDynamicView</code> define la interface para vistas que representan
 * <code>IDynamicEntity</code> en el mundo del juego.
 * <code>IDynamicView</code> permite a la vista ser modificada mediante el método
 * update. Una vez que el estado de <code>IDynamicEntity</code> cambia, esta
 * <code>IDynamicView</code> es considerada sucia y debería ser actualizada por el 
 * <code>ViewManager</code>
 * 
 * @author Maria Hansen
 * @version Creation date: 29-10-2008
 */
public interface IDynamicView extends IView {
	/** 
	 * Actualiza esta vista dinámica basándose en el nuevo estado de su entidad.
	 * @param interpolation El valor de interpolación del frame.
	 */
	public void update(float interpolation);
}