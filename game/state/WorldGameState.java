/**
 * 
 */
package client.game.state;

import client.game.Game;

import com.jmex.game.state.BasicGameState;

/** 
 * Es la forma abstracta de los posibles estados que el juego puede tener.
 * Hereda de la clase <code>BasicGameState</code> provista por el jme e 
 * implementa la interface <code>IGameState</code> para definir la 
 * abstraccion basica de los estados del juego.
 * @author Martin Sabatini - Sebastian Bisbal
 * @version Fecha de creación: 05-11-2008
 */
public abstract class WorldGameState extends BasicGameState implements
		IGameState {
	
	public WorldGameState(String arg0) {
		super(arg0);
	}

	/** 
	 * el game asociado
	 */
	private Game game;

	/** 
	 * @return el game
	 * 
	 */
	public Game getGame() {
		// begin-user-code
		return game;
		// end-user-code
	}

	/** 
	 * @param theGame el game a establecer
	 * 
	 */
	public void setGame(Game theGame) {
		// begin-user-code
		game = theGame;
		// end-user-code
	}

	/** 
	 * el iwordbuilder asociado al estado correspondiente
	 */
	protected IWorldBuilder iworldbuilder;
	
	/** 
	 * @return el iworldbuilder
	 * 
	 */
	
	public IWorldBuilder getIworldbuilder() {
		// begin-user-code
		return iworldbuilder;
		// end-user-code
	}

	/** 
	 * @param theIworldbuilder el iworldbuilder a establecer
	 * 
	 */
	public void setIworldbuilder(IWorldBuilder theIworldbuilder) {
		// begin-user-code
		iworldbuilder = theIworldbuilder;
		// end-user-code
	}

	/** 
	 * Se encarga de inicializar el mundo correspondiente al estado, llamando
	 * al metodo buildWorld del iworldbuilder asociado y pasandole como 
	 * parametro el nodo raiz.
	 */
	private void initializeWorld() {
		// begin-user-code
		iworldbuilder.buildWorld(this.getRootNode());

		// end-user-code
	}
	
	/**
	 * Este metodo llama al metodo update de <code>BasicGameState</code>
	 * que se encarga de actualizar el rootNode. Tambien se encarga de llamar 
	 * al metodo updateState que sera implementado por las clases que 
	 * representan los distintos estados del juego.
	 * @param interpolation es el valor de la frecuencia de interpolacion 
	 * en segundos.
	 */
	public void update(float interpolation) {
		super.update(interpolation);
		this.updateState(interpolation);
	}
	
	/** 
	 * Metodo que debe ser implementado por toda clase que extienda de esta.
	 * Se encarga de inicializar el estado correspondiente.
	 */
	public abstract void initializeState();

	/** 
	 * Metodo que debe ser implementado por toda clase que extienda de esta. 
	 * Se encarga de actualizar el estado.
	 */
	public abstract void updateState(float interpolation);
	
	
	/**
	 * Metodo que debe ser implementado por toda clase que extienda de esta.
	 * 
	 * @return el dialogo correspondiente segun el estado al que se este 
	 * cambiando.
	 */
	public abstract String getDialogText();
	
	public abstract boolean needClean();
	
	
	/**
	 * el String con el nombre del estado actual.
	 */
	public static String actualState;
		
}