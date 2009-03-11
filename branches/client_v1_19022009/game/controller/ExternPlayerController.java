package client.game.controller;

import java.util.Hashtable;

import client.game.entity.DynamicEntity;
import client.game.entity.IDynamicEntity;
import client.game.task.U3DMoveCharacterTask;
import client.game.task.U3DMoveExternCharacterTask;
import client.manager.TaskManager;

import com.jme.input.KeyInput;
import com.jme.input.KeyInputListener;

public class ExternPlayerController extends Controller implements KeyInputListener {

	public ExternPlayerController(IDynamicEntity entity) {
		super(entity);
		flags = new Hashtable<Integer, Integer>();
	}

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

	private Hashtable<Integer, Integer> flags;

	public void onKey(char arg0, int arg1, boolean arg2) {
		

	}
}
