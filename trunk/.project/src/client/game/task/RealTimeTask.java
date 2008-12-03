package client.game.task;

/**
 * <code>RealTimeTask</code> implementa <code>IRealTimeTask</code> para proveer
 * la implementación mas básica de una tarea que depende del tiempo. Esta 
 * the most basic implementation for time dependent tasks. Esta mantiene el tiempo
 * de cración, con el objetivo de hacer una comprobación de que tarea es posterior a cual.
 * <code>RealTimeTask</code> uses the <code>ETask</code> enumeration for the
 * 
 * @author Polo
 */

public abstract class RealTimeTask extends Task implements IRealTimeTask {
	/**
	 * El time stamp de la <code>RealTimeTask</code>
	 */
	protected final long timestamp;

	/**
	 * Constructora de <code>RealTimeTask</code>
	 */
	public RealTimeTask() {
		this.timestamp = System.currentTimeMillis();
	}
	
	//@Override
	public boolean equals(Object o) {
		return false;
	}
	
	//@Override
	public long getTimestamp() {
		return this.timestamp;
	}
	
	//@Override
	public boolean isLaterThan(IRealTimeTask task) {
		// Considera igual como posterior.
		return (this.timestamp >= task.getTimestamp());
	}
}
