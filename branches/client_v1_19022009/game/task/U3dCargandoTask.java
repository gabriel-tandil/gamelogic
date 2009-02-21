package client.game.task;

import client.manager.HudManager;


public class U3dCargandoTask extends ChangeStateTask {

	public U3dCargandoTask() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		HudManager.getInstance().setCargando();
		super.execute();
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public void initTask() {

	}

}
