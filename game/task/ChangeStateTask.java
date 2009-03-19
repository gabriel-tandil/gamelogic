package client.game.task;

import java.util.ArrayList;

import com.jmex.game.state.GameState;
import com.jmex.game.state.GameStateManager;

/**
 * <p>Title: ChangeStateTask</p>
 * <p>Description: clase <code>Task</code> que representa a una tarea de cambio
 * de estado en el juego. Cualquier cambio de estado debe poseer una tarea que extienda 
 * de esta.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * @author L. Rudenick
 * @version 1.0
 */
public abstract class ChangeStateTask extends Task {
	
	/**
	 * Constructor de ChangeStateTask  
	 */	
	public ChangeStateTask() {
		super();
	}
	
	/**
	 * Este método sirve para limpiar los estados generados, y de 
	 * esta manera optimizar el uso de memoria.
	 * @param myState próximo estado
	 */
	public void endState(String myState){
		ArrayList<GameState> States =  GameStateManager.getInstance().getChildren();
		for (int i = 0;i<States.size();i++)
			if ((States.get(i).isActive()) && (!States.get(i).getName().equals(myState)))
				States.get(i).cleanup();	
	}
	
	/**
	 * Método que debe implementar toda clase que extienda de esta, el 
	 * cual representa la funcionalidad principal de la tarea, o sea, 
	 * para que sirve la misma.
	 */
	public abstract void execute();
	
}