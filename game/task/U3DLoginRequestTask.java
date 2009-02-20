package client.game.task;

import client.communication.GameContext;

public class U3DLoginRequestTask extends Task {

	private String nombre = null;
	private String clave = null;

	public U3DLoginRequestTask() {
		super();
	}

	@Override
	public boolean equals(Object o) {

		return false;
	}

	public void execute() {
		try {
			GameContext.setUserName(nombre);

			GameContext.setPassword(clave);

			GameContext.getClientCommunication().login();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initTask(String nombre, String clave) {
		this.nombre = nombre;
		this.clave = clave;
	}

}
