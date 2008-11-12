/**
 * 
 */
package client.game.controller;

import client.game.entity.IDynamicEntity;

/** 
 * Esta clase contendrá el algoritmo de comportamiento del NPC.
 */
public class NPCController extends Controller {
	
	
	/**
	 * Contructor del <code>Controller<code> del NPC.
	 * @param entity La entidad que representará al NPC.
	 */
	public NPCController(IDynamicEntity entity) {
		super(entity);		
	}
		
	public void updateLogic(float interpolation) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente		
		// end-user-code
	}
	
	public IControllerFactory createController(IDynamicEntity entity){
		return new NPCController(entity);
	}
		
}