package client.game.controller;

import java.util.Hashtable;

import client.game.entity.IDynamicEntity;
import client.game.entity.Player;
import client.game.task.U3DMoveCharacterTask;
import client.manager.TaskManager;

import com.jme.input.KeyInput;
import com.jme.input.KeyInputListener;

public class U3DPlayerController extends Controller implements KeyInputListener {

	public U3DPlayerController(IDynamicEntity entity) {
		super(entity);
		flags = new Hashtable<Integer, Integer>();
	}

	@Override
	public void updateLogic(float interpolation) {
		Player player = (Player) this.getIDynamicEntity();
		float x, z;
		x = player.getPosition().x;
		z = player.getPosition().z;
		if (flags.contains(KeyInput.KEY_D))
			x += .1f;
		if (flags.contains(KeyInput.KEY_A))
			x -= .1f;

		if (flags.contains(KeyInput.KEY_S))
			z += .1f;
		if (flags.contains(KeyInput.KEY_W))
			z -= .1f;
		if ((x != player.getPosition().x) || (z != player.getPosition().z)) {
			U3DMoveCharacterTask task = (U3DMoveCharacterTask) TaskManager
					.getInstance().createTask("1");

			task.initTask(player, false, x, player.getPosition().y, z);
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