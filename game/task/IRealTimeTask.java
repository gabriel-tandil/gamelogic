package client.game.task;
/**
 * <p>Title: IRealTimeTask</p>
 * <p>Description: <code>IRealTimeTask</code> define la inteface para las tareas 
 * que dependen del tiempo. Esto significa que las versiones viejas de la tarea pueden
 * descartarse de forma segura si hay una nueva versión de la misma tarea disponible.
 * <p>
 * <code>IRealTimeTask</code> genera un time stamp en el momento que se contruye
 * que representa el tiempo en que esa <code>IRealTimeTask</code> es crada.
 * <p>
 * <code>IRealTimeTask</code> prvee la funcionalidad para comparar con otra 
 * <code>IRealTimeTask</code> y determinar el orden de contrucción.
 * <p></p>
 * <p>Copyright: Copyright (c) 2009</p>
 * @author L. Rudenick
 * @version 1.0
 */
public interface IRealTimeTask extends ITask {
	
	/**
	 * Chequea si el objeto pasado como parámetro es el mismo que el
	 * real time task.
	 * @param object El <code>Object</code> con el que se comparará.
	 * @return True si el objeto pasado como parámetro es el mismo que this y 
	 * False en caso contrario.
	 */
	public boolean equals(Object object);

	/**
	 * Retorna el time stamp de la real time task.
	 * @return El time stamp de la <code>IRealTimeTask</code>.
	 */
	public long getTimestamp();

	/**
	 * Compara el tiempo de contrucción de la tarea this, con la pasada
	 * como parámetro.
	 * @param task La <code>IRealTimeTask</code> con la cual se comparará.
	 * @return True se la tarea fue contruida después de la pasada como parámetro.
	 */
	public boolean isLaterThan(IRealTimeTask task);
}
