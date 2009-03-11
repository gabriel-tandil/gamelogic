package client.game.task;

public class U3DMoveExternCharacterTaskFactory implements ITaskFactory {

	private String id="15";

	public ITask createTask() {
		return new U3DMoveExternCharacterTask();
	}


	public String getId() {
		return id;
	}

}
