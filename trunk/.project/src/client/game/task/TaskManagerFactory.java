package client.game.task;

import java.util.HashMap;

/**
 * Esta clase es un singleton que contendrá las diferentes tareas definidas 
 * para el <code>Game<code>. Por medio de esta se pueden agregar, crear y obtener 
 * las tareas del <code>Game<code>. 
 * 
 */
public class TaskManagerFactory {	
	/**
	 * La instancia de <code>TaskFactoryManager</code>.
	 */
	private static TaskManagerFactory instance;
	
	/**
	 * HashMap que contiene las diferentes <code>ITask<code> del Game relacionadas a
	 * sus correspondientes identificadores. 
	 */
	private HashMap<String,ITaskFactory> tasks;
	
	
	/**
	 * Constructor <code>TaskFactoryManager</code>.
	 */
	private TaskManagerFactory(){
		this.tasks = new HashMap<String,ITaskFactory>();
	}
	
	/**
	 * Crea la instancia de <code>TaskFactoryManager</code> por primera 
	 * y única vez.
	 * @return La instancia de <code>TaskFactoryManager</code>.
	 */
	public static TaskManagerFactory getInstance() {		
		if(TaskManagerFactory.instance == null) {
			TaskManagerFactory.instance = new TaskManagerFactory();			
		}
		return TaskManagerFactory.instance;
	}
	
	/**
	 * 
	 * @param task Es una nueva tarea definida para el Game.
	 */
	public void addTask(ITaskFactory task) {
		tasks.put(task.getId(), task);
	}
	
	/** 
	 * @param id Es el identificador de la tarea a ser creada.
	 * @return La tarea que fue creada.
	 */
	public ITask createTask(String id) {
		return ((ITaskFactory)tasks.get(id)).createTask(id);
	}
	
}
