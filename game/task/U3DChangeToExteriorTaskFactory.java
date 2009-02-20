package client.game.task;

public class U3DChangeToExteriorTaskFactory implements ITaskFactory {

	private String id="3";

	public ITask createTask() {
		return new U3dChangeToExterior(null);// aca puse este null porque no tiene sentido que le pase el state aca en el constructor, arreglar en el framework
	}


	public String getId() {
		return id;
	}

}
