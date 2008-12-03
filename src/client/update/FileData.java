/*
* updatemanager.FileData
* Version 1.0
* Date: 27/10/2008
*/
package client.update;

import java.io.File;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * Mantiene los datos de los archivos a sincronizar.
 * 
 * @author Guillermo Fiorini, Gabriel Alvarez, Gouvert Rodolfo
 * @version 1.0
 */
public class FileData {
	
	/** Nombre del archivo. */
	private String filename;
	
	/** Directorio remoto del archivo. */
	private String remoteDir;
	
	/** Tamaño en bytes del archivo. */
	private double size;
	
	/** Fecha de ultima modificacion del archivo. */
	private Date date;
	
	/** Directorio local donde se encuentra el archivo. */
	private String localDir;
	
	/**
	 * Constructor de la clase.
	 * Crea una instancia de la clase
	 * 
	 * @param rmDir Directorio remoto del archivo
	 * @param lDir 	Directorio local del archivo
	 * @param fname 	Nombre del archivo
	 * @param s 		Tamaño del archivo
	 * @param dat 		Fecha de ultima modificacion del archivo
	 */
	public FileData(final String rmDir
			, final String lDir, final String fname, 
			final double s, final Date dat) {
		this.remoteDir = rmDir;
		this.localDir = lDir;
		this.filename = fname;
		this.size = s;
		this.date = dat;
	}
	
	/**
	 * Retorna el path local completo.
	 * Ej: Directorio + File.separator + nombre_archivo
	 * 
	 * @return El path completo local al archivo
	 */
	public final String getFullPath() {
		if (getLocalDir().equals("")) {
			return Config.getInstancia().getLocalFolder() + getFilename();
		}
		return Config.getInstancia().getLocalFolder()
			+ getLocalDir() + File.separator + getFilename();
	}

	/**
	 * Retorna el nombre del archivo.
	 * 
	 * @return El nombre del archivo
	 */
	public final String getFilename() {
		return filename;
	}

	/**
	 * Setea el nombre del archivo.
	 * 
	 * @param fname El nuevo nombre del archivo
	 */
	public final void setFilename(final String fname) {
		this.filename = fname;
	}

	/**
	 * Retorna el tamaño en bytes del archivo.
	 * 
	 * @return el tamaño en bytes
	 */
	public final double getSize() {
		return size;
	}

	/**
	 * Setea el tamaño en bytes del archivo.
	 * 
	 * @param s El nuevo tamaño
	 */
	public final void setSize(final double s) {
		this.size = s;
	}

	/**
	 * Retorna la fecha de ultima modificacion del archivo.
	 * 
	 * @return La fecha de ultima modificacion
	 */
	public final Date getDate() {
		return date;
	}

	/**
	 * Setea la fecha de ultima modificacion del archivo.
	 * 
	 * @param d La nueva fecha de modificacion
	 */
	public final void setDate(final Date d) {
		this.date = d;
	}

	/**
	 * Retorna el directorio remoto donde se encuentra el archivo.
	 * 
	 * @return El diretorio remoto
	 */
	public final String getRemoteDir() {
		return remoteDir;
	}

	/**
	 * Setea el directorio remoto donde se encuentra el archivo.
	 * 
	 * @param rDir El nuevo directorio remoto
	 */
	public final void setRemoteDir(final String rDir) {
		this.remoteDir = rDir;
	}

	/**
	 * Retorna el directorio local donde se encuentra el archivo.
	 * 
	 * @return El directorio local
	 */
	public final String getLocalDir() {
		return localDir;
	}

	/**
	 * Sets the local dir.
	 * 
	 * @param lDir the new local dir
	 */
	public final void setLocalDir(final String lDir) {
		this.localDir = lDir;
	}

}
