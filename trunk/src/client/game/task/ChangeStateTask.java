package client.game.task;

import client.game.Game;
import client.manager.ITaskFactory;

import com.jmex.game.state.BasicGameState;

/** 
 * Es la <code>Task<code> que maneja los cambios de estados del Game.
 */
public class ChangeStateTask extends Task {
	
	/**
	 * Constructor de ChangeStateTask  
	 * @param state
	 * @param game Instancia del juego.	
	 * @param id dentificador de <code>Task<code>
	 */
	public ChangeStateTask(BasicGameState state, Game game, String id) {		
		super(game,id);		
	}	
		
	public void execute() {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente		
		// end-user-code
	}
		
	public ITaskFactory createTask(String id, Game game)
	{
		return new ChangeStateTask(new BasicGameState("consultar!!!"), game, id);
	}
	
}