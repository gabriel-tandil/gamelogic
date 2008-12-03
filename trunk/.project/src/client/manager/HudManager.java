package client.manager;

public class HudManager implements IHudManager {
	private static HudManager instance=null;
	
	public static HudManager getInstance(){
		if(instance==null) instance=new HudManager();
		return instance;
	}
	
	private HudManager() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void render() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
