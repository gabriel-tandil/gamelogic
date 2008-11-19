package client.game.task;

import client.game.Game;

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
	private static TaskManagerFactory instance=null;
	
//	/**
//	 * HashMap que contiene las diferentes <code>ITask<code> del Game relacionadas a
//	 * sus correspondientes identificadores. 
//	 */
//	private HashMap<String,ITask> tasks;
//
//	private Logger logger;
	
	
//	/**
//	 * Constructor <code>TaskFactoryManager</code>.
//	 */
//	private TaskManagerFactory(){
//		this.tasks = new HashMap<String,ITask>();
//	}
	
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
	
//	/**
//	 * 
//	 * @param task Es una nueva tarea definida para el Game.
//	 */
//	protected void addTask(ITask task) {
//		tasks.put(task.getId(), task);
//	}
	
	/** 
	 * @param id Es el identificador de la tarea a ser creada.
	 * @param c el class de la clase que quiero instanciar
	 * @param game el juego
	 * @return La tarea que fue creada.
	 */
	@SuppressWarnings("unchecked")
	public Task createTask(Class<Task> c, Game game,String id) {
		//return ((ITaskFactory)tasks.get(id)).createTask(id, game);		
	
		Task t=null;
		Class<Task> claseTask;
	try {
		claseTask=(Class<Task>) Class.forName(c.getName());
		t=(Task)claseTask.newInstance();
		t.initializeTask(id, game);
	} catch (Exception e) {
		// TODO Auto-generated catch block
//		logger.log.Level.FATAL,"Error al crear la instancia de la clase "+c.getName());

	}
	return t;
	
	}
	
	
}
