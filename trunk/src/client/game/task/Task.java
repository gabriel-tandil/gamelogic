/**
 * 
 */
package client.game.task;

import client.game.Game;
import client.manager.ITaskFactory;

/**
 * <code>Task</code> define la más básica abstracción de todos los tipos de tareas.
 * <p>
 * <code>Task</code> mantiene una referencia al <code>Game</code> con el objeto de 
 * de permitir a las subclases acceder a los datos del Game para llevar a cabo la
 * lógica de ejecución.
 * <p>
 * Las subclases de <code>Task</code> necesitan implementar los detalles de la 
 * lógica de ejecución.
 */
public abstract class Task implements ITaskFactory {
	/**
	 * El time stamp de creación de esta <code>Task</code>
	 */
	protected long timestamp;	
	
	/**
	 * La instancia del <code>Game</code>.
	 */
	protected Game game;
	
	/**
	 * El identificador de <code>Task</code>.
	 */
	protected String id;
	
	/**
	 * Constructor de <code>Task</code>.
	 * @param game The <code>Game</code> instance.
	 */
	public Task(Game game, String id) {
		this.timestamp = System.currentTimeMillis();
		this.game = game;
		this.id = id;
	}
	
	/** 
	 * @return La instancia del game.
	 */
	public Game getGame() {		
		return game;
	}	
	
	public boolean isLaterThan(ITask task) { 
		return (this.timestamp >= task.getTimestamp());
	}

	public boolean equals(Object o) {
		if(o instanceof ITask) {
			ITask task = (ITask)o;
			return this.getId() == task.getId();
		}
		return false;
	}
	
	public long getTimestamp() {
		return this.timestamp;
	}
	
	public String getId() {
		return this.id;
	}
	
}