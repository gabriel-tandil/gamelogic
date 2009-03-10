/**
 * 
 */
package client.manager;

import java.util.concurrent.ConcurrentLinkedQueue;

import client.game.Game;
import client.game.task.ITask;
import client.game.task.TaskManagerFactory;

/**
 * <code>TaskManager</code> es responsable de manejar todas las
 * <code>ITask</code> generadas por los packets recibidos del servidor.
 * <code>TaskManager</code> es construido por el <code>Game</code> durante
 * la inicialización del sistema.
 * <p>
 * <code>TaskManager</code> es "updated" por el <code>Game</code> dentro del
 * loop update/render en el main del game ejecutando todos las
 * <code>ITask</code> encoladas.
 * <p>
 * <code>TaskManager</code> ejecuta las buffered <code>ITask</code> en el
 * orden en el cual ellas han sido generadas y almacendas en la cola buffered.
 * <p>
 * Durante un simple ciclo de update, <code>TaskManager</code> intenta
 * ejecutar tantas buffered <code>ITask</code> como sea posible. Sin embargo,
 * no puede ejecutar todas las <code>ITask</code> si el tiempo total de
 * ejecución excede el límite máximo permitido. Las restantes <code>ITask</code>
 * son puestas, entonces, en el top de la pila y serán ejecutadas primeras
 * durante el siguiente ciclo de update.
 */
public class TaskManager {

	/**
	 * El delta utilizado para descartar las tareas cuyo timesamp menos el
	 * tiempo actuales mayor, en modulo que este delta.
	 */
	public static long DELTA = 2000000;

	/**
	 * La instancia de <code>TaskManager</code>.
	 */
	private static TaskManager instance;

	/**
	 * La cola de buffered <code>ITask</code>.
	 */
	private ConcurrentLinkedQueue<ITask> taskQueue;

	/**
	 * La temporaria <code>LinkedList</code> de submitted <code>ITask</code>.
	 */
	private final ConcurrentLinkedQueue<ITask> submitted;

	/**
	 * La instancia del <code>Game</code>.
	 */
	private Game game;

	/**
	 * El tiempo antes que la última ejecución haya empezado. Es en
	 * nanosegundos.
	 */
	private long starttime;

	/**
	 * El tiempo después que la última ejecución haya finalizado. Es en
	 * nanosegundos.
	 */
	private long endtime;

	/**
	 * El tiempo trasncurrido desde el comienzo del actual update. Es en
	 * milisegundos.
	 */
	private float totaltime;

	/**
	 * El máximo tiempo de ejecución permitido por ciclo en milisegundos.
	 */
	private float executeTime;

	/**
	 * El máximo tiempo de encolamiento permitido por ciclo en milisegundos.
	 */
	private float enqueueTime;

	/**
	 * Constructor <code>TaskManager</code>.
	 * 
	 * @param una
	 *            instancia del <code>Game</code>.
	 */
	private TaskManager(Game game) {
		this.game = game;
		this.taskQueue = new ConcurrentLinkedQueue<ITask>();
		this.submitted = new ConcurrentLinkedQueue<ITask>();
		this.executeTime = 20;
		this.enqueueTime = 10;
	}

	/**
	 * Crea el <code>TaskManager</code> por primera y única vez.
	 * 
	 * @param La
	 *            instancia del <code>Game</code>.
	 * @return La instancia de <code>TaskManager</code>.
	 */
	public static TaskManager create(Game game) {
		if (game == null)
			return null;
		if (TaskManager.instance == null) {
			TaskManager.instance = new TaskManager(game);
		}
		return TaskManager.instance;
	}

	/**
	 * Recobra la instancia singleton de <code>TaskManager</code>.
	 * 
	 * @return La instancia <code>TaskManager</code>.
	 */
	public static TaskManager getInstance() {
		return TaskManager.instance;
	}

	/**
	 * Hace el submit de la tarea dada al <code>TaskManager</code> para su
	 * posterior ejecución. Sin embargo, no hay garantía de que la tarea será
	 * encolada.
	 * 
	 * @param task
	 *            La <code>ITask</code> a ser submitted.
	 * @return True si la tarea pudo ser submitted.
	 */
	public boolean submit(ITask task) {
		if (task == null)
			return false;
		return this.submitted.add(task);
	}

	/**
	 * Encola la tareas dadas al <code>TaskManager</code> para la posterior
	 * ejecución. Si hay una <code>ITask</code> anterior que es considerada
	 * 'igual' que otra nueva, la más vieja es automáticamente removida antes
	 * que la nueva sea agregada. Si la tarea dada es mas antigua que una
	 * 'igual' en la cola, la tarea dada es descartada.
	 * 
	 * @param La
	 *            <code>ITask</code> a ser agregada.
	 */
	/**
	 * Encola la terea pasada como parametro.
	 * 
	 * @param La
	 *            <code>ITask</code> a ser agregada.
	 */
	public void enqueue(ITask task) {
/*
		ITask given = task;
		ITask inQueue = null;
		for (ITask t : this.taskQueue) {
			inQueue = t;
			if (inQueue.equals(given)) { 
				if (given.isLaterThan(inQueue)) {
					this.taskQueue.remove(inQueue);
					break;
				}
			}
		}
*/
		this.taskQueue.add(task);
	}

	/**
	 * @return La instancia del <code>Game<code>.
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * @param game
	 *            Es la instancia del <code>Game<code> a establecer.
	 */
	public void setGame(Game theGame) {
		game = theGame;
	}

	/**
	 * @param execTime
	 *            Es el tiempo máximo de ejecución a establecer.
	 */
	public void setExecuteTime(float execTime) {
		this.executeTime = execTime;
	}

	/**
	 * @param enqTime
	 *            Es el tiempo máximo de encolado a establecer.
	 */
	public void setEnqueueTime(float enqTime) {
		this.enqueueTime = enqTime;
	}

	/**
	 * Creará y agregará la tarea a la cola de tareas submitted para
	 * posteriormente, si corresponde, ser encoladas y ejecutadas.
	 * 
	 * @param id
	 *            El identificador de la tarea que se desea crear.
	 */
	public ITask createTask(String id) {
		return (TaskManagerFactory.getInstance().createTask(id));
	}

	/**
	 * Hace el update del <code>TaskManager</code> para ejecutar las tareas
	 * que se encuentran en la cola buffered.<BR/> no realiza un encolamiento
	 * ni una ejecucion de tareas cuyo timestamp menos el tiempo actual es mayor
	 * (en modulo) al DELTA.
	 */
	public void update() {
		// Enqueue tasks.
		while (!this.submitted.isEmpty() && this.totaltime < this.enqueueTime) {
			this.starttime = System.nanoTime();
			ITask task = this.submitted.poll();
			//if (starttime - task.getTimestamp() <= DELTA)
				this.enqueue(task);
			this.endtime = System.nanoTime();
			this.totaltime += (this.endtime - this.starttime) / 1000000.0f;
		}
		// Reset total time.
		this.totaltime = 0;
		// Execute as many tasks as possible.
		while (!this.taskQueue.isEmpty() && this.totaltime < this.executeTime) {
			this.starttime = System.nanoTime();
			ITask task = this.taskQueue.poll();
			//if (starttime - task.getTimestamp() <= DELTA)
				task.execute();
			this.endtime = System.nanoTime();
			this.totaltime += (this.endtime - this.starttime) / 1000000.0f;
		}

	}
}