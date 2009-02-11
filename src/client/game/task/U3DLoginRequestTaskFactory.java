package client.game.task;

public class U3DLoginRequestTaskFactory implements ITaskFactory {

	private String id="8"; //si, estoy seguro que es la tarea 8, que ids poco descriptivos, si es un string, que nesicidad?

	public ITask createTask() {
		return new U3DLoginRequestTask();
	}


	public String getId() {
		return id;
	}

}
