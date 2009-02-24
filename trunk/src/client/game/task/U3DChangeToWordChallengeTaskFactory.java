package client.game.task;

public class U3DChangeToWordChallengeTaskFactory implements ITaskFactory {

	private String id="11";

	public ITask createTask() {
		return new U3dChangeToWordChallengeGame(null);// aca puse este null porque no tiene sentido que le pase el state aca en el constructor, arreglar en el framework
	}


	public String getId() {
		return id;
	}

}
