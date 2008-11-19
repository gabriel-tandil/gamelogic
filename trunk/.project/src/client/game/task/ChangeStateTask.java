package client.game.task;

import client.game.Game;


/** 
 * Es la <code>Task<code> que maneja los cambios de estados del Game.
 */
public abstract class ChangeStateTask extends Task {
	
	/**
	 * Constructor de ChangeStateTask  
	 * @param state
	 * @param game Instancia del juego.	
	 * @param id dentificador de <code>Task<code>
	 */
	public ChangeStateTask(Game game, String id) {		
		super(game, id);		
	}	
		
	public void execute() {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente		
		// end-user-code
	}
	
	
}