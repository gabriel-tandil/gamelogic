package client.game.task;

public class U3DRotateCharacterTaskFactory implements ITaskFactory {

	private String id="2";
	

	public ITask createTask() {
		return new U3DRotateCharacterTask();
	}

	public String getId() {
		return id;
	}

}
