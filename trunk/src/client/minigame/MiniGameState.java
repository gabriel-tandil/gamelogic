/**
 * 
 */
package client.minigame;

import client.game.state.WorldGameState;
import com.jmex.game.state.BasicGameState;
import com.sun.sgs.app.ClientSession;

/** 
 * @author Mara
 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class MiniGameState extends BasicGameState {
	public MiniGameState(String arg0) {
		super(arg0);
		
	}

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private I2DGame i2dgame;

	/** 
	 * @return el i2dgame
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public I2DGame getI2dgame() {
		// begin-user-code
		return i2dgame;
		// end-user-code
	}

	/** 
	 * @param theI2dgame el i2dgame a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setI2dgame(I2DGame theI2dgame) {
		// begin-user-code
		i2dgame = theI2dgame;
		// end-user-code
	}

	/** 
	 * @param juego
	 * @param puntaje
	 * @param session
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void sendResult(I2DGame juego, Double puntaje, ClientSession session) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

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
	public void updateState() {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
	}
}