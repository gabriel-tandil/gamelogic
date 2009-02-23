package client.game.task;

public class U3DAddDynamicEntityTaskFactory implements ITaskFactory {

	private String id="9";

	public ITask createTask() {
		return new U3DAddDynamicEntityTask();
	}

	public String getId() {
		return id;
	}

}
