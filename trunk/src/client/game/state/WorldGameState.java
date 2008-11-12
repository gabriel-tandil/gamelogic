/**
 * 
 */
package client.game.state;

import com.jmex.game.state.BasicGameState;
import client.game.Game;

/** 
 * @author Martin Sabatini - Sebastian Bisbal
 * @version Fecha de creación: 05-11-2008
 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public abstract class WorldGameState extends BasicGameState implements
		IGameState {
	public WorldGameState(String arg0) {
		super(arg0);
		// TODO Apéndice de constructor generado automáticamente
	}

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Game game;

	/** 
	 * @return el game
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Game getGame() {
		// begin-user-code
		return game;
		// end-user-code
	}

	/** 
	 * @param theGame el game a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setGame(Game theGame) {
		// begin-user-code
		game = theGame;
		// end-user-code
	}

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private IWorldBuilder iworldbuilder;
	
	/** 
	 * @return el iworldbuilder
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	
	public IWorldBuilder getIworldbuilder() {
		// begin-user-code
		return iworldbuilder;
		// end-user-code
	}

	/** 
	 * @param theIworldbuilder el iworldbuilder a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setIworldbuilder(IWorldBuilder theIworldbuilder) {
		// begin-user-code
		iworldbuilder = theIworldbuilder;
		// end-user-code
	}

	/** 
	 *  Siempre llama al construir el mundo del IWorldBuilder
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void initializeWorld() {
		// begin-user-code
		iworldbuilder.buildWorld(this.getRootNode());

		// end-user-code
	}

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public abstract void initializeState();

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public abstract void updateState();
	
	/**
	 * 
	 */
	public abstract WorldGameState getWorld();
	
	/**
	 * inicializa el mundo correspondiente al estado 
	 * y el estado
	 */
	public abstract void initialize();
		
}