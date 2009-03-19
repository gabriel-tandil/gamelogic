package client.game.task;

import client.spot.SpotState;

import com.jmex.game.state.GameStateManager;

/**
 * <p>Title: ChangeToSpot</p>
 * <p>Description: clase que representa el cambio de estado generado al querer
 * ver un spot informativo.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * @author L. Rudenick
 * @version 1.0
 */
public class ChangeToSpot extends ChangeStateTask {
	private String proxEstado;

	/**
	 * Constructora principal de la clase. Inicializa los componentes a
	 * utilizar en el objeto.
	 * @param proxEstado nuevo estado al que se va a cambiar
	 */
	public ChangeToSpot(String proxEstado) {
		this.proxEstado = proxEstado;
	}

	/**
	 * Este método se encarga de que se inicialice y muestre el spot informativo
	 * en cuestión.
	 */
	@Override
	public void execute() {
		((SpotState)GameStateManager.getInstance().getChild(proxEstado)).initialize();
	}
	
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

}
