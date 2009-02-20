package client.game.task;

public class U3DChangeToIntEcoTaskFactory implements ITaskFactory {

	private String id="4";

	public ITask createTask() {
		return new U3dChangeToIntEco(null);// aca puse este null porque no tiene sentido que le pase el state aca en el constructor, arreglar en el framework
	}


	public String getId() {
		return id;
	}

}
