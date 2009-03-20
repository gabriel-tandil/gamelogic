/**
 * 
 */
package client.game.state;

import client.game.view.DynamicView;
import client.game.view.U3dPlayerView;

/**
 * <code>IGameState</code> define la interface para todos los tipos de 
 * Game States que representan una etapa particular del juego.
 * @author Martin Sabatini - Sebastian Bisbal
 * @version Fecha de creación: 04-11-2008 
 */
public interface IGameState {
	/** 
	 * inicializa el estado correspondiente
	 */
	public void initialize();

	/** 
	 * @return el WorldGameState 
	 */
	public WorldGameState getWorld();
	/**
	 * 
	 * @param playerView la vista asociada al player
	 */
	public void initializeCamera(DynamicView playerView);
}