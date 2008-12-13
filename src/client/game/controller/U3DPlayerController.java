package client.game.controller;

import java.util.Hashtable;

import client.game.entity.IDynamicEntity;
import client.game.entity.Player;
import client.game.task.U3DMoveCharacterTask;
import client.game.task.U3DRotateCharacterTask;
import client.manager.TaskManager;
import client.manager.ViewManager;

import com.jme.input.KeyInput;
import com.jme.input.KeyInputListener;
import com.jme.scene.Spatial;

public class U3DPlayerController extends Controller implements KeyInputListener {

	public U3DPlayerController(IDynamicEntity entity) {
		super(entity);
		flags = new Hashtable<Integer, Integer>();
	}

	@Override
	public void updateLogic(float interpolation) {
		Player player = (Player) this.getIDynamicEntity();
		boolean adelante=true;
		boolean move=false;
		float rot=0;
		if (flags.contains(KeyInput.KEY_D))
			rot -= .025f;
		if (flags.contains(KeyInput.KEY_A))
			rot += .025f;

		if (flags.contains(KeyInput.KEY_S)){
			move=true;
			adelante=true;
		}	
		if (flags.contains(KeyInput.KEY_W)){
			move=true;
			adelante=false;
		}
		if(rot!=0){
			U3DRotateCharacterTask task = (U3DRotateCharacterTask) TaskManager
			.getInstance().createTask("2");

			task.initTask(player,rot);
			TaskManager.getInstance().enqueue(task);
		}
		if (move) {
			U3DMoveCharacterTask task = (U3DMoveCharacterTask) TaskManager
					.getInstance().createTask("1");

			boolean corriendo=flags.contains(KeyInput.KEY_LSHIFT);
			task.initTask(player, false, adelante,corriendo);
			TaskManager.getInstance().enqueue(task);
		}

	}

	private Hashtable<Integer, Integer> flags;

	public void onKey(char arg0, int arg1, boolean arg2) {
		if (arg2)
			flags.put(arg1, arg1);
		else
			flags.remove(arg1);
	}

}