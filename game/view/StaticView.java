/**
 * 
 */
package client.game.view;

import client.game.entity.IEntity;

/** 
 * <code>StaticView</code> extiende de <code>View</code> e implementa 
 * <code>IStaticView</code> para representar a una entidad est�tica.
 * 
 * @author Maria Hansen, Sebastian Bsbal (javadoc)
 * @version Creation date: 29-10-2008
 */
public abstract class StaticView extends View implements IStaticView {
    /**
     * Constructor de <code>StaticView</code> dada una entidad. 
     * @param entity La <code>IEntity</code> que esta vista representa.
     */
    public StaticView(IEntity entity){
        super(entity);
    }
    
    /**
     * Definicion de la funci�n que traba la geometr�a, 
     * l�mites y transformaci�n de un elemento de la escena.
     * 
     * @see IStaticView#lock().
     */
    public void lock(){
    	super.lockMeshes();
        super.lockBounds();
        super.lockTransforms();
    }
    
    /**
     *  
	 * @return True si esta vista es una instancia de IDynamicView.
     * @see IView#isDynamicView().
     */
    public Boolean isDynamicView(){
        return false;
    }
}