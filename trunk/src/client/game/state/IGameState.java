/**
 * 
 */
package client.game.state;

/**
 * <code>IGameState</code> define la interface para todos los tipos de 
 * Game States que representan una etapa particular del juego.
 * @author Martin Sabatini - Sebastian Bisbal
 * @version Fecha de creación: 04-11-2008 
 */
public interface IGameState {
	/** 
	 * inicializa el estado correspondiente
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void initialize();

	/** 
	 * @return el WorldGameState
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public WorldGameState getWorld();
}