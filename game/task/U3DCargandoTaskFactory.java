package client.game.task;

public class U3DCargandoTaskFactory implements ITaskFactory {

	private String id="7"; // por que son numeros los ids?

	public ITask createTask() {
		return new U3dCargandoTask();// aca puse este null porque no tiene sentido que le pase el state aca en el constructor, arreglar en el framework
	}


	public String getId() {
		return id;
	}

}
