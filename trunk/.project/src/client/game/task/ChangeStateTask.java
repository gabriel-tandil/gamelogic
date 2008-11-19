package client.game.task;

import com.jmex.game.state.BasicGameState;

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
	public ChangeStateTask(BasicGameState state) {		
		super();		
	}	
		
	public void execute() {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente		
		// end-user-code
	}		
	
}