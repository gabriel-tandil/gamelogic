package client.game.controller;

import java.util.Hashtable;

import client.game.entity.IDynamicEntity;
import client.game.entity.Player;
import client.game.task.U3DMoveCharacterTask;
import client.game.task.U3DMoveExternCharacterTask;
import client.game.task.U3DRotateCharacterTask;
import client.manager.HudManager;
import client.manager.TaskManager;

import com.jme.input.KeyInput;
import com.jme.input.KeyInputListener;

/**
 * Extiende {@link Controller} e implementa {@link KeyInputListener}.<br>
 * Se encarga de llevar a cabo la lógica de update del Player asociado a este. 
 *
 */
public class PlayerController extends Controller implements KeyInputListener {

	/**
	 * Instancia de {@link Hashtable} que almacenará las
	 * keys presionadas por el usuario.
	 */
	private Hashtable<Integer, Integer> flags;
	
	/**
	 * Contructor de {@link PlayerController}.
	 * @param entity es la {@link IDynamicEntity} que 
	 * será asociada al {@link PlayerController}.
	 */
	public PlayerController(IDynamicEntity entity) {
		super(entity);
		flags = new Hashtable<Integer, Integer>();
	}

	/**
	 * Realiza el update de la logica de este controlador.<br>
	 * Creará las correspondientes {@link U3DRotateCharacterTask}
	 * y {@link U3DMoveCharacterTask}, según corresponda, que 
	 * llevarán a cabo el comportamiento respectivo de la 
	 * {@link IDynamicEntity} asociada.
	 * 
	 * @param interpolation es el valor de la frecuencia de interpolacion 
	 * en segundos.
	 */
	@Override
	public void updateLogic(float interpolation) {
		Player player = (Player) this.getIDynamicEntity();
		if (player != null) {
			boolean forward = false;
			boolean move = false;
			float rot = 0;
			if (flags.contains(KeyInput.KEY_D))
				rot -= .025f;
			if (flags.contains(KeyInput.KEY_A))
				rot += .025f;

			if (flags.contains(KeyInput.KEY_S)) {
				move = true;
				forward = false;
			}
			if (flags.contains(KeyInput.KEY_W)) {
				move = true;
				forward = true;
			}
			if (rot != 0) {
				U3DRotateCharacterTask task = (U3DRotateCharacterTask) TaskManager
						.getInstance().createTask("2");

				task.initTask(player, rot);
				TaskManager.getInstance().enqueue(task);
			}
			if (move) {
				U3DMoveCharacterTask task = (U3DMoveCharacterTask) TaskManager
						.getInstance().createTask("1");

				boolean corriendo = flags.contains(KeyInput.KEY_LSHIFT);
				task.initTask(player, false, forward, corriendo);
				TaskManager.getInstance().enqueue(task);
				((Player) player).isMoving(true, corriendo, forward);
			} else
				((Player) player).isMoving(false, false, forward);
		}
	}	

	/**
	 * Evento tratado al presionarse una tecla.<br>  
	 * Si {@link HudManager#isMostrandoDialogo()} es true
	 * agrega al Hash arg1, sino hace el remove de este.
	 * @param arg0
	 * @param arg1
	 * @param arg2 
	 */
	public void onKey(char arg0, int arg1, boolean arg2) {
		if (arg2) {
			if (!HudManager.getInstance().isMostrandoDialogo())
				flags.put(arg1, arg1);
		} else
			flags.remove(arg1);

	}
}
