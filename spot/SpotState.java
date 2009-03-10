/**
 * 
 */
package client.spot;

import client.game.state.IGameState;
import client.game.state.U3dState;
import client.game.state.WorldGameState;
import client.game.view.DynamicView;
import client.manager.HudManager;

import com.jme.math.Vector3f;

/**
 * @author Mara
 * @generated "De UML a Java V5.0
 *            (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class SpotState extends U3dState {
	private String url;
	public SpotState(String name, String url) {
		super(name);
		this.url = url;
		// TODO Apéndice de constructor generado automáticamente
	}
	
	public String getUrl(){
		return this.url;
	}

	/**
	 * @generated "De UML a Java V5.0
	 *            (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Object i2dgame;

	/**
	 * @return el i2dgame
	 * @generated "De UML a Java V5.0
	 *            (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Object getI2dgame() {
		// begin-user-code
		return i2dgame;
		// end-user-code
	}

	/**
	 * @param theI2dgame
	 *            el i2dgame a establecer
	 * @generated "De UML a Java V5.0
	 *            (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setI2dgame(Object theI2dgame) {
		// begin-user-code
		i2dgame = theI2dgame;
		// end-user-code
	}

	

	/**
	 * (sin Javadoc)
	 * 
	 * @see IGameState#initialize()
	 * @generated "De UML a Java V5.0
	 *            (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void initialize() {
		this.initializeState();
		this.inicializaHUD();
	}

	/**
	 * (sin Javadoc)
	 * 
	 * @see IGameState#getWorld()
	 * @generated "De UML a Java V5.0
	 *            (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public WorldGameState getWorld() {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		return null;
		// end-user-code
	}

	/**
	 * (sin Javadoc)
	 * 
	 * @see WorldGameState#initializeState()
	 * @generated "De UML a Java V5.0
	 *            (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void initializeState() {
		new InformationSpot(this.getUrl()).loadInformationSpot();
	}

	/**
	 * (sin Javadoc)
	 * 
	 * @see WorldGameState#updateState()
	 * @generated "De UML a Java V5.0
	 *            (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	@Override
	public void updateState(float interpolation) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public String getDialogText() {
		// TODO Auto-generated method stub
		return "Est\u00E1s frente a un Spot informativo. \u00BFQuer\u00E9s Entrar?";
		
	}


	@Override
	public void initializeCamera(DynamicView playerView) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean needClean() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Vector3f getTranslation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCamera() {
		// TODO Auto-generated method stub
		
	}
	
	private void inicializaHUD() {
		HudManager.getInstance().unSetCargando();
	}

}