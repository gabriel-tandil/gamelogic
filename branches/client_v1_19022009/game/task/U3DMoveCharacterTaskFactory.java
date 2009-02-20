package client.game.task;

public class U3DMoveCharacterTaskFactory implements ITaskFactory {

	private String id="1";

	public ITask createTask() {
		return new U3DMoveCharacterTask();
	}


	public String getId() {
		return id;
	}

}
