package client.game.controller;

import com.jme.input.KeyInput;
import com.jme.input.KeyInputListener;
import com.jme.renderer.ColorRGBA;
import com.jme.system.DisplaySystem;

import client.game.entity.IDynamicEntity;
import client.game.entity.Player;
import client.game.task.ETask;
import client.game.task.U3DMoveCharacterTask;
import client.manager.TaskManager;

public class U3DPlayerController extends Controller implements KeyInputListener{

	public U3DPlayerController(IDynamicEntity entity) {
		super(entity);
	}

	@Override
	public void updateLogic(float interpolation) {
		

	}

	public void onKey(char arg0, int arg1, boolean arg2) {
		if(arg1 == KeyInput.KEY_W) {
			U3DMoveCharacterTask task=(U3DMoveCharacterTask) TaskManager.getInstance().createTask("1");
			Player player= (Player)this.getIDynamicEntity();
			task.initTask(player, false,player.getPosition().x+0.000001f,player.getPosition().y+0.00001f,player.getPosition().z+0.00001f);
			TaskManager.getInstance().enqueue(task);
			}
		if(arg1 == KeyInput.KEY_S) {
			U3DMoveCharacterTask task=(U3DMoveCharacterTask) TaskManager.getInstance().createTask("1");
			Player player= (Player)this.getIDynamicEntity();
			task.initTask(player, false,player.getPosition().x-0.000001f,player.getPosition().y+0.00001f,player.getPosition().z+0.00001f);
			TaskManager.getInstance().enqueue(task);
		}
		if(arg1 == KeyInput.KEY_A) {
			U3DMoveCharacterTask task=(U3DMoveCharacterTask) TaskManager.getInstance().createTask("1");
			Player player= (Player)this.getIDynamicEntity();
			task.initTask(player, false,player.getPosition().x+0.000001f,player.getPosition().y-0.00001f,player.getPosition().z+0.00001f);
			TaskManager.getInstance().enqueue(task);
		}
		if(arg1 == KeyInput.KEY_D) {
			U3DMoveCharacterTask task=(U3DMoveCharacterTask) TaskManager.getInstance().createTask("1");
			Player player= (Player)this.getIDynamicEntity();
			task.initTask(player, false,player.getPosition().x+0.000001f,player.getPosition().y+0.00001f,player.getPosition().z-0.00001f);
			TaskManager.getInstance().enqueue(task);
		}
		
	}

}
