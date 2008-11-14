/**
 * 
 */
package client.game.task;

/**
 * <code>ITask</code> define la interface para todos los tipos de tareas que 
 * son generadas en respuesta a las entradas del usuario recibidas por los 
 * diferentes <code>IController</code>.
 * <p>
 * <code>ITask</code> es una unidad l�gica la cual contiene la l�gica de 
 * ejecuci�n que modifica el estado del <code>Game</code> o una <code>IEntity</code>.
 * <p>
 * Las <code>ITask</code> son creadas de manera separada pero mantenidas y ejecutadas
 * dentro del loop update/render del main del Game por el <code>TaskManager</code>.
 */
public interface ITask {	
	
	/**
	 * Este m�todo contendr� la l�gica de ejecuci�n de la tarea. 	
	 */
	public void execute();
	
	/**
	 * Chequea si un objeto dado es el mismo que esta tarea.
	 * @param object El <code>Object</code> a chequear.
	 * @return True si el objeto dado es el mismo que esta tarea. De otro modo, False.
	 */
	public boolean equals(Object object);
	
	/**
	 * Compara el time stamp de creaci�n de esta tarea con el de una tarea dada.
	 * @param task The <code>ITask</code> que ser� comparada.
	 * @return True si esta tarea fue construida m�s tarde que una dada.
	 */
	public boolean isLaterThan(ITask task);
	
	/** 
	 * @return El time stamp de creaci�n de esta <code>ITask</code>.
	 */
	public long getTimer();
	
	/**
	 * @return El identificador de la tarea.
	 */
	public String getId();
	
	/**
	 * @return
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public ETask getType();
}