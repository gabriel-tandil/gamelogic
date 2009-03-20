package client.game.controller;

import java.util.Hashtable;

import client.game.entity.DynamicEntity;
import client.game.entity.IDynamicEntity;
import client.game.task.U3DMoveCharacterTask;
import client.game.task.U3DMoveExternCharacterTask;
import client.manager.TaskManager;

import com.jme.input.KeyInput;
import com.jme.input.KeyInputListener;

/**
 * Extiende {@link Controller}.<br>
 * Se encarga de llevar a cabo la lógica de update del Player asociado a este.    
 * */
public class ExternPlayerController extends Controller implements KeyInputListener {		

	/**
	 * Contructor de {@link ExternPlayerController}.
	 * @param entity es la {@link IDynamicEntity} que 
	 * será asociada al {@link ExternPlayerController}.
	 */
	public ExternPlayerController(IDynamicEntity entity) {
		super(entity);		
	}

	/**
	 * Realiza el update de la logica de este controlador.<br>
	 * Creará la correspondiente {@link U3DMoveExternCharacterTask}
	 * que llevará a cabo el comportamiento respectivo de la 
	 * {@link IDynamicEntity}.
	 * @param interpolation es el valor de la frecuencia de interpolacion 
	 * en segundos.
	 */
	@Override
	public void updateLogic(float interpolation) {
		DynamicEntity player = (DynamicEntity) this.getIDynamicEntity();
		if ((player != null) && (player.hasMove())){
			U3DMoveExternCharacterTask task = (U3DMoveExternCharacterTask) TaskManager.getInstance().createTask("15");
			task.initTask(player, false, true, false);
	           		//(DynamicEntity,isLocal,adelante,corriendo)
			TaskManager.getInstance().enqueue(task);
			((DynamicEntity) player).isMoving(true, false, true);
			}
		else
			((DynamicEntity) player).isMoving(false, false, true);
               		//(boolean state, boolean running, boolean forward)
	}	

	public void onKey(char arg0, int arg1, boolean arg2) {
		
	}
}
