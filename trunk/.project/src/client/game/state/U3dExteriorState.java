package client.game.state;

import com.jme.scene.Node;

public class U3dExteriorState extends WorldGameState {
	
	private XMLWorldBuilder builder;

	private boolean initialized;
	
	public boolean isInitialized() {
		return initialized;
	}

	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}

	public U3dExteriorState(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(float arg0) {
		// TODO Auto-generated method stub

	}
	
	public void initialize() {
		this.initializeWorld();
		this.initialized = true;
	}

	private void initializeWorld() {
		//this.world = (World) DataManager.getInstance().getWorld(EWorld.Battle);
		//this.world.setModelBound(new BoundingBox());
		//this.world.updateModelBound();
		//this.world.updateWorldBound();
		builder=new XMLWorldBuilder();
		builder.buildWorld(this.rootNode);
	}
	@Override
	public void initializeState() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateState() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public WorldGameState getWorld() {
		// TODO Auto-generated method stub
		return null;
	}

}
