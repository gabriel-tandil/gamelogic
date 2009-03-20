/**
 * 
 */
package client.game.controller;

import client.game.entity.IDynamicEntity;

/** 
 * Extiende {@link Controller}.<br>
 * Lleva a cabo el algoritmo de comportamiento de la 
 * {@link IDynamicEntity} que representa al NPC.
 */
public class NPCController extends Controller {
	
	
	/**
	 * Contructor de {@link NPCController}.
	 * @param entity es la {@link IDynamicEntity} que 
	 * será asociada al {@link NPCController}.
	 */
	public NPCController(IDynamicEntity entity) {
		super(entity);		
	}
		
	public void updateLogic(float interpolation) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente		
		// end-user-code
	}
	

	/**
	 * Recupera la instancia de {@link IDynamicEntity} asociada
	 * al {@link NPCController}.
	 * @return la instancia de {@link IDynamicEntity}.
	 */
	public IDynamicEntity getEntity() {
		// TODO Auto-generated method stub
		return null;
	}
		
}