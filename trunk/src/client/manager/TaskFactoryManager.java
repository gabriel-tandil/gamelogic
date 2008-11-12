package client.manager;

import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import client.game.Game;
import client.game.task.ITask;

/**
 * Esta clase es un singleton que contendrá las diferentes tareas definidas 
 * para el <code>Game<code>. Por medio de esta se pueden agregar, crear y obtener 
 * las tareas del <code>Game<code>. 
 * 
 */
public class TaskFactoryManager {	
	/**
	 * La instancia de <code>TaskFactoryManager</code>.
	 */
	private static TaskFactoryManager instance;
	
	/**
	 * HashMap que contiene las diferentes <code>ITask<code> del Game relacionadas a
	 * sus correspondientes identificadores. 
	 */
	private HashMap<String,ITask> tasks;
	
	
	/**
	 * Constructor <code>TaskFactoryManager</code>.
	 */
	private TaskFactoryManager(){
		this.tasks = new HashMap<String,ITask>();
	}
	
	/**
	 * Crea la instancia de <code>TaskFactoryManager</code> por primera 
	 * y única vez.
	 * @return La instancia de <code>TaskFactoryManager</code>.
	 */
	protected static TaskFactoryManager getInstance() {		
		if(TaskFactoryManager.instance == null) {
			TaskFactoryManager.instance = new TaskFactoryManager();			
		}
		return TaskFactoryManager.instance;
	}
	
	/**
	 * 
	 * @param task Es una nueva tarea definida para el Game.
	 */
	protected void addTask(ITask task) {
		tasks.put(task.getId(), task);
	}
	
	/** 
	 * @param id Es el identificador de la tarea a ser creada.
	 * @return La tarea que fue creada.
	 */
	protected ITask createTask(String id, Game game) {
		return ((ITaskFactory)tasks.get(id)).createTask(id, game);		
	}
	
}
