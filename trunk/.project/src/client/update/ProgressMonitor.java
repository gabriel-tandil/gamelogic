/*
* updatemanager.ProgressMonitor
* Version 1.0
* Date: 27/10/2008
*/
package client.update;

import javax.swing.JProgressBar;

import com.enterprisedt.net.ftp.FTPProgressMonitor;

/**
 * Monitor del progreso de la transferencia de datos por FTP.
 * Esta clase extiende de FTPProgressMonitor 
 * 
 * @author Guillermo Fiorini, Gabriel Alvarez, Gouvert Rodolfo
 * @version 1.0
 */
public class ProgressMonitor implements FTPProgressMonitor {
	
	/** La barra de progreso de archivo. */
	private JProgressBar progressbar;
	
	/** La barra de progreso total. */
	private JProgressBar obPgrsBar;
	
	/** Auxiliar para mantener las dos barras de progreso. */
	private int auxStartOverral;
	
	/**
	 * Instancia un nuevo monitor de progreso.
	 * Inicializa las variables en null
	 */
	public ProgressMonitor() {
		progressbar = null;
	}
	
	/**
	 * Instancia un nuevo monitor de progreso.
	 * Inicializa las variables con los parametros asignados
	 * @param pgrsBar the pgrs bar
	 * @param ovPgrsBar the ov pgrs bar
	 */
	public ProgressMonitor(final JProgressBar pgrsBar, 
			final JProgressBar ovPgrsBar) {
		this.progressbar = pgrsBar;
		this.obPgrsBar = ovPgrsBar;
	}
	
	/**
	 * Setea la barra de progreso de archivos.
	 * 
	 * @param pgrsBar la nueva barra de progreso
	 */
	public final void setProgressBar(final JProgressBar pgrsBar) {
		this.progressbar = pgrsBar;
	}
	
	/**
	 * Retorna la barra de progreso de archivos.
	 * 
	 * @return La barra de progreso de archivos
	 */
	public final JProgressBar getProgressbar() {
		return progressbar;
	}
	
	/**
	 * Metodo implementado de FTPProgressMonitor.
	 * 
	 * @param arg0 los bytes transferidos
	 * @see com.enterprisedt.net.ftp.FTPProgressMonitor#bytesTransferred(long)
	*/
	public final void bytesTransferred(final long arg0) {
		if ((progressbar != null) && (obPgrsBar != null)) {
			if (progressbar.getValue() == 0) {
				auxStartOverral = obPgrsBar.getValue();
			}
			progressbar.setValue((int) arg0);
			obPgrsBar.setValue(auxStartOverral + (int) arg0);
			progressbar.repaint();
			obPgrsBar.repaint();
		}
	}
}
