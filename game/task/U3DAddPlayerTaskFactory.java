package client.game.task;

public class U3DAddPlayerTaskFactory implements ITaskFactory {

	private String id="8";

	public ITask createTask() {
		return new U3DAddPlayerTask();
	}

	public String getId() {
		return id;
	}

}
