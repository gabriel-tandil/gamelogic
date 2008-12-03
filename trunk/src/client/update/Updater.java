/*
* updatemanager.Updater
* Version 1.0
* Date: 27/10/2008
*/
package client.update;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

import com.enterprisedt.net.ftp.FTPException;

/**
 * Thread encargado de la descarga de archivos.
 * 
 * @author Guillermo Fiorini, Gabriel Alvarez, Gouvert Rodolfo
 * @version 1.0
 */
public class Updater extends Thread {
	
	/** La lista de archivos a descargar. */
	private Vector<FileData> filesToUpdate;
	
	/** Instancia de FTPManager. */
	private FTPManager ftpManager;
	
	/** La barra de progreso de descarga de archivo. */
	private JProgressBar fileProgressbar;
	
	/** La barra de progreso de descarga global. */
	private JProgressBar overallProgressBar;
	
	/** Instania de UpdateManager a la que se reporta el resultado. */
	private MyUpdateManager reportTo;
	
	/** Label utilizado para mostrar por pantalla el estado actual. */
	private JLabel repLabel;
	
	/**
	 * Inicializa un nuevo objeto Updater.
	 * 
	 * @param fToUpdate La lista de archivos a descargar
	 * @param fProgressBar La barra de progreso de descarga de archivo
	 * @param ovProgressBar La barra de progreso de descarga global
	 * @param repTo Instania de UpdateManager a la que 
	 * se reporta el resultado
	 * @param repLl Label utilizado para mostrar por 
	 * pantalla el estado actual
	 */
	public Updater(final Vector<FileData> fToUpdate, 
			final JProgressBar fProgressBar, final JProgressBar ovProgressBar,
			final MyUpdateManager repTo, final JLabel repLl) {
		this.filesToUpdate = fToUpdate;
		this.fileProgressbar = fProgressBar;
		this.overallProgressBar = ovProgressBar;
		this.reportTo = repTo;
		this.repLabel = repLl;
		ftpManager = new FTPManager(fProgressBar, overallProgressBar);
	}
	
	/**
	 * Obtiene el total de bytes a descargar.
	 * 
	 * @return El numero total de bytes a descargar
	 */
	public final double getTotalBytesDownload() {
		double toReturn = 0;
		Enumeration<FileData> e = filesToUpdate.elements();
		while (e.hasMoreElements()) {
			toReturn += e.nextElement().getSize();
		}
		return toReturn;
	}
	
	/**
	 * Implementa el metodo run de la clase Thread.
	 * Realiza todas las descargas y reporta el resultado
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public final void run() {
		super.run();
		Enumeration<FileData> e = filesToUpdate.elements();
		try {
			ftpManager.conectar();
			if (overallProgressBar != null) {
				overallProgressBar.setMinimum(0);
				overallProgressBar.setMaximum((int) getTotalBytesDownload());
			}
			while (e.hasMoreElements()) {
				FileData temp = e.nextElement();
				if (fileProgressbar != null) {
					fileProgressbar.setMinimum(0);
					fileProgressbar.setMaximum((int) temp.getSize());
					fileProgressbar.setValue(0);
				}
				if (repLabel != null) {
					repLabel.setText("Cambiando a directorio: " 
							+ Config.getInstancia().getRemoteFolder()
							+ "/" 
							+ temp.getRemoteDir().replace(File.separator, "/"));
				}
				ftpManager.changeDir(Config.getInstancia().getRemoteFolder() 
						+ "/" 
						+ temp.getRemoteDir().replace(File.separator, "/"));
				
					File f = new File(Config.getInstancia().getLocalFolder()
							+ temp.getRemoteDir());
					if (!f.exists()) {
						if (repLabel != null) {
							repLabel.setText("Creando directorio: "
									+ Config.getInstancia().getLocalFolder()
									+ temp.getRemoteDir() + "/");
						}
						f.mkdir();
					}
				
				if (repLabel != null) {
					repLabel.setText("Descargando: "
						+ Config.getInstancia().getLocalFolder()
						+ temp.getLocalDir()
						+ ("".equals(temp.getLocalDir()) ? "" : File.separator)
						+ temp.getFilename());
				}
				ftpManager.downloadFile(temp.getFilename(), 
						Config.getInstancia().getLocalFolder()
						+ temp.getRemoteDir());
				f = new File(temp.getFullPath());
				f.setLastModified(temp.getDate().getTime());
			}
			ftpManager.shutDown();
		} catch (IOException e1) {
			int res = JOptionPane.showConfirmDialog(null, 
					"Ha ocurrido un error en la descarga de la "
					+ "actualizacion.\n Esto puede deberse a problemas con "
					+ "la coneccion de internet. Por favor verifique \n"
					+ "que la coneccion a internet este disponible "
					+ "y vuelva a intentarlo.\n\n "
					+ "Presione ACEPTAR para volver reiniciar la "
					+ "actualizacion o CANCELAR para salir de la aplicacion",
					"Error de actualizacion", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.ERROR_MESSAGE);
			if (res == JOptionPane.OK_OPTION) {
				reportTo.setUpdateFailed();
			} else {
				reportTo.setAbortUpdate();
			}
			e1.printStackTrace();
			return;
		} catch (FTPException e1) {
			int res = JOptionPane.showConfirmDialog(null, 
					"Ha ocurrido un error en la descarga de la actualizacion.\n"
					+ " Esto puede deberse a problemas con la coneccion de "
					+ "internet. Por favor verifique \nque la coneccion a"
					+ " internet este disponible y vuelva a intentarlo.\n\n"
					+ " Presione ACEPTAR para volver reiniciar la "
					+ "actualizacion o CANCELAR para salir de la aplicacion",
					"Error de actualizacion", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.ERROR_MESSAGE);
			if (res == JOptionPane.OK_OPTION) {
				reportTo.setUpdateFailed();
			} else {
				reportTo.setAbortUpdate();
			}
			e1.printStackTrace();
			return;
		}
		reportTo.setUpdateComplete();
	}
}
