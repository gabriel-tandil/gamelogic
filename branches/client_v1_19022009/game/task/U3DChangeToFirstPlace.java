package client.game.task;

import client.game.state.U3dState;

import com.jmex.game.state.GameStateManager;

/**
 * <p>Title: U3DChangeToFirstPlace</p>
 * <p>Description: representa a la tarea que se genera al cambiar del estado de login
 * al ingreso al mundo.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * @author L. Rudenick
 * @version 1.0
 */
public class U3DChangeToFirstPlace extends ChangeStateTask {

	private String proxEstado;

	/**
	 * Constructora principal de la clase. Inicializa los componentes a
	 * utilizar en el objeto.
	 * @param proxEstado nuevo estado al que se va a cambiar
	 */
	public U3DChangeToFirstPlace(String proxEstado) {
		this.proxEstado = proxEstado;
	}
	
	/**
	 * Limpia los estados anteriores, e inicializa el nuevo estado al 
	 * que se quiere cambiar.
	 */
	@Override
	public void execute() 
	{
		this.endState(proxEstado);

		GameStateManager.getInstance().deactivateAllChildren();
		GameStateManager.getInstance().activateChildNamed(proxEstado);

		U3dState proximoEstado = ((U3dState) GameStateManager.getInstance()
				.getChild(proxEstado));

		proximoEstado.initialize();

	}
	
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
