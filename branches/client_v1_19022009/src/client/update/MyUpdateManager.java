/*
* updatemanager.ProgressMonitor
* Version 1.0
* Date: 27/10/2008
*/
package client.update;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

import org.jdom.DataConversionException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

// TODO: Auto-generated Javadoc
/**
 * Encargada de la actualizacion del cliente.
 * Sincroniza una carpeta remota con otra local
 * mediante la descarga de descriptores XML
 * 
 * @author Guillermo Fiorini, Gabriel Alvarez, Gouvert Rodolfo
 * @version 1.0
 */
public class MyUpdateManager extends UpdateManager {
	
	/** La version descargada. */
	private String version;
	
	/** El modulo descargado. */
	private String module;
	
	/** "Features/Bugfixes" de la version descargada. */
	private String feature;
	
	/** Separador de archivos (multiplataforma). */
	private String separador = File.separator;
	
	/** El jFrame que muestra la ventana. */
	private Visual frame = null;
	
	/**
	 * Setea el fin de la actualizacion.
	 */
	public final void setUpdateComplete() {
		if (frame != null) { 
			frame.dispose();
		}
		//Muevo al siguiente estado
	}
	
	/**
	 * Reinicia la actulizacion.
	 * Reinicia la descarga cuando ocurre un error en la actualizacion
	 */
	public final void setUpdateFailed() {
		if (frame != null) { 
			frame.dispose();
		}
		//Fallo la actualizacion, vuelvo a intentarlo
		checkForUpdate();
	}
	
	/**
	 * Cancela la actualizacion.
	 * Aborta la descarga cuando ocurre un error en la actulizacion
	 */
	public final void setAbortUpdate() {
		if (frame != null) { 
			frame.dispose();
		}
		//No se puede completar la actualizacion 
	}
	
	/**
	 * Verifica si existen nuevas actualizaciones.
	 * Descarga el contenido de un archivo XML desde
	 * el servidor FTP y simcroniza las carpetas
	 */
	public final void checkForUpdate() {
		FTPManager ftm = new FTPManager(null, null);
		try {
			ftm.conectar();
			ftm.changeDir(Config.getInstancia().getRemoteFolder());
			ftm.downloadFile(Config.getInstancia().getRemoteXMLFile(), ".");
			ftm.shutDown();
			Vector<FileData> filesToUpdate = 
				getFilesToUpdate(Config.getInstancia().getRemoteXMLFile());
			if (filesToUpdate != null) {
				//Es necesario descagar nuevos archivos
				frame = new Visual(filesToUpdate, this);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			} else {
				setUpdateComplete();
			}
		} catch (Exception e) {
			int res = JOptionPane.showConfirmDialog(frame, 
					"Ha ocurrido un error en la descarga de la " 
					+ "actualizacion.\n Esto puede deberse a problemas " 
					+ "con la coneccion de internet. Por favor verifique \n"
					+ "que la coneccion a internet este disponible y vuelva "
					+ "a intentarlo.\n\n "
					+ "Presione ACEPTAR para volver reiniciar la "
					+ "actualizacion o CANCELAR para salir de la aplicacion", 
					"Error de actualizacion", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.ERROR_MESSAGE);
			if (res == JOptionPane.OK_OPTION) {
				setUpdateFailed();
			} else {
				setAbortUpdate();
			}
			e.printStackTrace();
		}
	}
	
	/**
	 * Obtiene los archivos a descargar.
	 * Realiza el parsing del archivo XML utilizando JDOM
	 * y retorna el listado de archivos a descargar que
	 * cumplan una de las siguientes condiciones: <br>
	 * 1) El archivo no existe en la carpeta local<br>
	 * 2) La fecha de ultima modificacion local es mas antigua
	 * que la remota<br>
	 * 3) El tamaño del archivo local es diferete al remoto
	 * 
	 * @param fileName El nombre del archi XML
	 * 
	 * @return Los archivos a descargar
	 * 
	 * @throws ParseException Señal de que ocurrio un error de parsing
	 * @throws JDOMException Señal de que ocurrio una excepcion de JDOM
	 * @throws IOException Señal de que ocurrio una excepcion I/O.
	 */
	@SuppressWarnings("unchecked")
	private Vector<FileData> getFilesToUpdate(final String fileName) 
		throws ParseException, JDOMException, IOException {
		Vector<FileData> files = new Vector<FileData>();
		Vector<FileData> result;
		
		SAXBuilder builder = new SAXBuilder();
		File f = new File(fileName);
		Document doc = builder.build(f);
		Element updates = doc.getRootElement();
		List lUpdates = updates.getChildren("update");

		Element update = (Element) lUpdates.get(0); // deberia ser uno
		// solo, pero se
		// definio un XML
		// mas amplliable
		// por las dudas
		version = update.getAttributeValue("version");
		module = update.getAttributeValue("module");
		feature = update.getChild("feature").getTextTrim();
		Element content = update.getChild("content");
		processFolder(content, files, module, module);
		result = null;
		for (Iterator<FileData> iterator = files.iterator(); 
			iterator.hasNext();) {
			FileData fd = iterator.next();
			f = new File(fd.getFullPath()); 
			if (!f.exists()
					 || (new Date(f.lastModified())).before(fd.getDate())
					 || (f.length() != fd.getSize())) {
				if (result == null) {
					result = new Vector<FileData>();
				}
				result.add(fd);
			}
				
		}
		return result;
	}

	/**
	 * Recorre un directorio en el XML buscando archivos.
	 * 
	 * @param current Elemento Actual
	 * @param result Vector que acumula el resultado
	 * @param rootRemoteFolder La raiz de la carpeta remota
	 * @param rootLocalFolder La raiz de la carpeta local
	 * 
	 * @throws DataConversionException Señal de que ocurrio
	 * una excepcion de converion de datos
	 * @throws ParseException Señal de que ocurrio
	 * una excepcion de parsing
	 */
	@SuppressWarnings("unchecked")
	private void processFolder(final Element current, 
			final Vector<FileData> result,
			String rootRemoteFolder, String rootLocalFolder) 
			throws DataConversionException, ParseException {
		List files = current.getChildren("file");
		List folders = current.getChildren("folder");
		if (rootRemoteFolder.equals(".")) {
			rootRemoteFolder = "";
			rootLocalFolder = "";
		}
		for (Iterator iterator = files.iterator(); iterator.hasNext();) {
			Element file = (Element) iterator.next();
			java.text.SimpleDateFormat sdf = 
				new java.text.SimpleDateFormat("yyyyMMddHHmmss");

			Date d = sdf.parse(file.getAttributeValue("date"));
			FileData data = new FileData(rootRemoteFolder, 
					rootLocalFolder, file.getAttributeValue("name"), 
					file.getAttribute("size").getIntValue(), d);
			result.add(data);
		}
		for (Iterator iterator = folders.iterator(); iterator.hasNext();) {
			Element folder = (Element) iterator.next();
			if (!rootRemoteFolder.equals("")) {
				processFolder(folder, result, 
					rootRemoteFolder + "/" + folder.getAttributeValue("name"),
					rootLocalFolder + separador 
					+ folder.getAttributeValue("name"));
			} else {
				processFolder(folder, result, folder.getAttributeValue("name"),
				folder.getAttributeValue("name"));
			}
		}
	}

	/**
	 * Obtiene la version de la actualizacion.
	 * 
	 * @return La version de la actualizacion
	 */
	public final String getVersion() {
		return version;
	}

	/**
	 * Obtiene los "features" o "bugfixes" de la actualizacion.
	 * 
	 * @return Features/Bugfixes
	 */
	public final String getFeature() {
		return feature;
	}
}
