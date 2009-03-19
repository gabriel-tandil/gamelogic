package client.game.task;

/**
 * <code>U3DMoveExternCharacterTaskFactory</code> proporciona la abstraccion
 * necesaria para la creacion de tareas que son instancias de la clase
 * <code>U3DMoveExternCharacterTask</code>. Implementa la interfaz
 * <code>ITaskFactory</code>.
 * 
 * @author Sebastián Sampaoli (Javadoc)
 *
 */
public class U3DMoveExternCharacterTaskFactory implements ITaskFactory {

	/**
	 * El identificador de la tarea.
	 */
	private String id="15";

	@Override
	public ITask createTask() {
		return new U3DMoveExternCharacterTask();
	}


	@Override
	public String getId() {
		return id;
	}

}
