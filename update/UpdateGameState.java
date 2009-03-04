/**
 * 
 */
package client.update;

import client.game.state.WorldGameState;
import client.game.view.DynamicView;

/** 
 * @author Mara
 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class UpdateGameState extends WorldGameState {
	public UpdateGameState(String arg0) {
		super(arg0);
		// TODO Apéndice de constructor generado automáticamente
	}

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private UpdateManager updatemanager;

	/** 
	 * @return el updatemanager
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public UpdateManager getUpdatemanager() {
		// begin-user-code
		return updatemanager;
		// end-user-code
	}

	/** 
	 * @param theUpdatemanager el updatemanager a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setUpdatemanager(UpdateManager theUpdatemanager) {
		// begin-user-code
		updatemanager = theUpdatemanager;
		// end-user-code
	}

	/** 
	 *  (sin Javadoc)
	 * @see IGameState#initialize()
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void initialize() {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
	}

	/** 
	 *  (sin Javadoc)
	 * @see IGameState#getWorld()
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public WorldGameState getWorld() {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		return null;
		// end-user-code
	}

	/** 
	 *  (sin Javadoc)
	 * @see WorldGameState#initializeState()
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void initializeState() {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
	}

	/** 
	 *  (sin Javadoc)
	 * @see WorldGameState#updateState()
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void updateState(float interpolation) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
	}

	public void initializeCamera(DynamicView playerView) {
	}

	@Override
	public String getDialogText() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public boolean needClean() {
		// TODO Auto-generated method stub
		return false;
	}
}