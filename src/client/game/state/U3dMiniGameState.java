package client.game.state;

/**
 * Representa el estado para un minijuego
 * @author Polo
 *
 */

public abstract class U3dMiniGameState extends WorldGameState {

	public U3dMiniGameState(String name) {
		super(name);
	}

	public abstract void initialize();
}
