/**
 * 
 */
package client.game.task;

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
	protected long timestamp;			
	
	/**
	 * Constructor de <code>Task</code>.
	 * @param game The <code>Game</code> instance.
	 */
	public Task() {
		this.timestamp = System.currentTimeMillis();		
	}			
	
	/**
	 * @param task <code>ITask</code> 
	 * @return True Si la tarea <code>tast</code> fue creada antes a dicha tarea 
	 */
	public boolean isLaterThan(ITask task){
		return timestamp>task.getTimestamp();
	}

	/**
	 * @param o objeto <code>Object</code> 
	 */
	public abstract boolean equals(Object o); 
		
	/**
	 * Retorna el tiempo de creacion. 
	 * @return <code>timestamp</code> de dicha tarea 
	 */
	public long getTimestamp() {
		return this.timestamp;
	}		
	
}