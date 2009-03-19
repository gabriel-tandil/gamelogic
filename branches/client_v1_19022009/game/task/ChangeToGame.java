package client.game.task;

import client.minigame.MiniGameState;
import com.jmex.game.state.GameStateManager;

/**
 * <p>Title: ChangeToGame</p>
 * <p>Description: clase que representa el cambio de estado generado al querer
 * iniciar un minijuego.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * @author L. Rudenick
 * @version 1.0
 */
public class ChangeToGame extends ChangeStateTask {
	
	private String proxEstado;

	/**
	 * Constructora principal de la clase. Inicializa los componentes a
	 * utilizar en el objeto.
	 * @param proxEstado nuevo estado al que se va a cambiar
	 */
	public ChangeToGame(String proxEstado) {
		this.proxEstado = proxEstado;
	}

	/**
	 * Este método se encarga de iniciar el minijuego al que se está tratando 
	 * de acceder.
	 */
	@Override
	public void execute() {
		((MiniGameState)GameStateManager.getInstance().getChild(proxEstado)).initialize();
	}
	
	
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

}
