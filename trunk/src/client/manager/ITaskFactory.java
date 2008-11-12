package client.manager;

import client.game.Game;
import client.game.task.ITask;

/**
 * Define la interfaz de creaci�n para las posteriores tareas que ser�n
 * definidas en el Game. 
 */
public interface ITaskFactory extends ITask {		
	
	/**
	 * Este m�todo ser� implementado por cada tarea definida para el game.
	 * La clase que define la tarea es la que debe conocer como crear una instancia
	 * de ella misma.
	 * @param id El identificador de la tarea.
	 * @param game Instancia del juego.
	 * @return La <code>ITask<code> creada.
	 */
	public ITaskFactory createTask(String id, Game game);	
}
