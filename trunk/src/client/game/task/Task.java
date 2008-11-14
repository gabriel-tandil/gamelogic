/**
 * 
 */
package client.game.task;

import client.game.Game;

/**
 * <code>Task</code> define la m�s b�sica abstracci�n de todos los tipos de tareas.
 * <p>
 * <code>Task</code> mantiene una referencia al <code>Game</code> con el objeto de 
 * de permitir a las subclases acceder a los datos del Game para llevar a cabo la
 * l�gica de ejecuci�n.
 * <p>
 * Las subclases de <code>Task</code> necesitan implementar los detalles de la 
 * l�gica de ejecuci�n.
 */
public abstract class Task implements ITask {
	/**
	 * El time stamp de creaci�n de esta <code>Task</code>
	 */
	protected long timer;	
	
	/**
	 * Constructor de <code>Task</code>.
	 * @param game The <code>Game</code> instance.
	 */
	public Task() {
		this.timer = System.currentTimeMillis();
	}
	
	
	public boolean isLaterThan(ITask task) { 
		return (this.timer >= task.getTimer());
	}

	public boolean equals(Object o) {
		if(o instanceof ITask) {
			ITask task = (ITask)o;
			return this.getId() == task.getId();
		}
		return false;
	}
	
	public long getTimer() {
		return this.timer;
	}
	
	public void setTimer(long theTimer) {
		// begin-user-code
		timer = theTimer;
		// end-user-code
	}
	
	public abstract ETask getType();
	
}