package client.game.task;

import java.util.ArrayList;

import com.jmex.game.state.BasicGameState;
import com.jmex.game.state.GameState;
import com.jmex.game.state.GameStateManager;

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
		
	public void endState(String myState){
		ArrayList<GameState> States =  GameStateManager.getInstance().getChildren();
		for (int i = 0;i<States.size();i++)
			if ((States.get(i).isActive()) && (!States.get(i).getName().equals(myState)))
				States.get(i).cleanup();	
	}
	
	public void execute() {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente		
		// end-user-code
	}		
	
}