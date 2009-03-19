package client.game.task;

/**
 * <code>U3DRotateCharacterTaskFactory</code> proporciona la abstraccion
 * necesaria para la creacion de tareas que son instancias de la clase
 * <code>U3DRotateCharacterTask</code>. Implementa la interfaz
 * <code>ITaskFactory</code>.
 * 
 * @author Sebastián Sampaoli (Javadoc)
 *
 */

public class U3DRotateCharacterTaskFactory implements ITaskFactory {

	/**
	 * El identificador de la tarea.
	 */
	private String id="2";
	
	@Override
	public ITask createTask() {
		return new U3DRotateCharacterTask();
	}

	@Override
	public String getId() {
		return id;
	}

}
