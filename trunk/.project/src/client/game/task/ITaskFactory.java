package client.game.task;

import client.game.task.ITask;

/**
 * Define la interfaz de creación para las posteriores tareas que serán
 * definidas en el Game. 
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
