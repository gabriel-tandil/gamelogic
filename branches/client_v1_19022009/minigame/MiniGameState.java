/**
 * 
 */
package client.minigame;

import client.communication.tasks.TaskCommFactory;
import client.game.state.U3dState;
import client.game.state.WorldGameState;
import client.game.task.ITask;
import client.game.view.DynamicView;
import client.manager.TaskManager;

import com.jme.math.Vector3f;
import com.jmex.game.state.BasicGameState;
import common.datatypes.D2GameScore;
import common.datatypes.Ranking;
import common.exceptions.UnsopportedMessageException;
import common.messages.MessageFactory;
import common.messages.MsgTypes;
import common.messages.requests.MsgAdd2DGameScore;

/**
 * @author Mara
 * @generated "De UML a Java V5.0
 *            (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class MiniGameState extends U3dState {
	public MiniGameState(String arg0) {
		super(arg0);
		// TODO Apéndice de constructor generado automáticamente
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
	 * @generated "De UML a Java V5.0
	 *            (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private I2DGame i2dgame2;

	/**
	 * @return el i2dgame2
	 * @generated "De UML a Java V5.0
	 *            (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public I2DGame getI2dgame2() {
		// begin-user-code
		return i2dgame2;
		// end-user-code
	}

	/**
	 * @param theI2dgame2
	 *            el i2dgame2 a establecer
	 * @generated "De UML a Java V5.0
	 *            (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setI2dgame2(I2DGame theI2dgame2) {
		// begin-user-code
		i2dgame2 = theI2dgame2;
		// end-user-code
	}

	/**
	 * @param juego
	 * @param puntaje
	 * @param session
	 */
	public void sendResult(Object juego, D2GameScore score) {
		try {
			// creo el mensaje de tipo score a enviar.
			MsgAdd2DGameScore msg = (MsgAdd2DGameScore) MessageFactory
					.getInstance().createMessage(
							MsgTypes.MSG_ADD_2D_GAME_SCORE_TYPE);
			// seteo el score a enviar
			msg.setScore(score);
			
			// solicita la tarea para enviar l mensaje y la encola en task
			// manager.
			ITask task = TaskCommFactory.getInstance().createComTask(msg);
			TaskManager.getInstance().submit(task);

		} catch (UnsopportedMessageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * (sin Javadoc)
	 * 
	 * @see IGameState#initialize()
	 * @generated "De UML a Java V5.0
	 *            (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void initialize() {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
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
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
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

	// metodo agregado para soportar el asincronismo de la comunicacion al
	// resivir el ranking del minijuego asociado al estado.
	public void setRanking(Ranking ranking) {

		// FIXME IMPLEMENTAR ESTE METODO. QUE ES YAMADO ASINCRONICAMENTE PARA
		// QUE SE CONTINUE LA EJECUCION DEL ESTADO AL RECIBIR EL RANKING
		// ASOCIADO AL MISMO

	}


	@Override
	public String getDialogText() {
		// TODO Auto-generated method stub
		return "Est\u00E1s frente a la puerta de ingreso al Juego "+getName()+". \u00BFQuer\u00E9s Entrar?";
		
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

}