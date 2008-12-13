package client.game.state;


public abstract class U3dState extends WorldGameState {

	public U3dState(String name) {
		super(name);
	}

	public abstract void initialize();

}
