package client.game.task;

import client.game.task.ITask;


/**
 * <p>Title: ITaskFactory</p>
 * <p>Description: define la interfaz de creación para las posteriores tareas que serán
 * definidas en el Game.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * @author L. Rudenick
 * @version 1.0
 */
public interface ITaskFactory {		
	
	/**
	 * @return El identificador de la tarea.
	 */
	public String getId();
	
	/**
	 * Este método será implementado por cada tarea definida para el game.
	 * La clase que define la tarea es la que debe conocer como crear una instancia
	 * de ella misma.
	 * @return La <code>ITask<code> creada.
	 */
	public ITask createTask();	
}
