/*
* updatemanager.Config
* Version 1.0
* Date: 27/10/2008
*/

package client.update;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Mantiene los datos de la configuracion del Update Manager.
 * La clase implementa el patron singleton proporcionando
 * una unica instancia que puede ser accedida globalmente.
 * 
 * @author Guillermo Fiorini, Gabriel Alvarez, Gouvert Rodolfo
 * @version 1.0
 */

public final class Config {
	/** Ruta al archivo de configuracion FTP, 
	 * este archivo almacena los datos de coneccio con el servidor. */
	private String filename = "configuracionFTP.txt";
	
	/** Instancia unica de la clase. */
	private static Config instancia = null;
	
	/** Carpeta remota de sincronizacion. */
	private String remoteFolder;
	
	/** Carpeta local de sincronizacion. */
	private String localFolder;
	
	/** Host del servicio FTP. */
	private String fTPHost;
	
	/** Usuario del servicio FTP. */
	private String fTPUser;
	
	/** Password para el usuario del servicio FTP. */
	private String fTPPass;
	
	/** Nombre de la carpeta remota para la sincronizacion. */
	private String remoteXMLFile;
	
	/**
	 * Constructor por defecto de la clase.
	 * Inicializa los datos de configuracion llamando al metodo loadConfig
	 * 
	 * @see loadConfig
	 */
	private Config() {
		loadConfig();
	}
	
	/**
	 * Retorna la instancia unica de Configuracion.
	 * Si el objeto aun no ha sido inicializado,
	 * lo inicializa antes de retornarlo
	 * 
	 * @return      una instancia unica de Configuracion
	 */
	public static Config getInstancia() {
		if (instancia == null) {
			instancia = new Config();
		}
		return instancia;
	}
	
	/**
	 * Carga la informacion de configuracion.
	 * Inicializa los datos de configuracion (Host FTP, User FTP,
	 * Password FTP, Carpeta Remota, Carpeta Local, Nombre del archiovo XML)
	 * cargando una configuracion que se encuentra en el 
	 * archivo "configuracionFTP.txt que debe encontrarse en el directorio
	 * raiz de la aplicacion y debe poseer el siguiente formato:<br><br>
	 * Host FTP=ftp.mihostftp.com<br>
	 * Usuario FTP=nombredeusuario<br>
	 * Password FTP=clavedeacceso<br>
	 * Carpeta Remota=/carpetaremota<br>
	 * Carpeta Local=carpetalocal/<br>
	 * Archivo XML Remoto=nombredearchivo.xml<br>
	 */
	public void loadConfig() {
		BufferedReader input;
		try {
			input = new BufferedReader(new FileReader(new File(filename)));
			String line = input.readLine();
			while (line != null) {
				if (line.indexOf('=') != -1) {
					if (line.substring(0 , 
							line.indexOf('=')).equals("Host FTP")) {
						fTPHost = line.substring(line.indexOf('=') + 1 ,
								line.length());
					} else if (line.substring(0 , 
							line.indexOf('=')).equals("Usuario FTP")) {
						fTPUser = line.substring(line.indexOf('=') + 1 ,
								line.length());
					} else if (line.substring(0 , 
							line.indexOf('=')).equals("Password FTP")) {
						fTPPass = line.substring(line.indexOf('=') + 1 ,
								line.length());
					} else if (line.substring(0 , 
							line.indexOf('=')).equals("Carpeta Remota")) {
						remoteFolder = line.substring(line.indexOf('=') + 1 ,
								line.length());
					} else if (line.substring(0 , 
							line.indexOf('=')).equals("Carpeta Local")) {
						localFolder = line.substring(line.indexOf('=') + 1 ,
								line.length());
					} else if (line.substring(0 , 
							line.indexOf('=')).equals("Archivo XML Remoto")) {
						remoteXMLFile = line.substring(line.indexOf('=') + 1 ,
								line.length());
					}
				}
				line = input.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Retorna el path a la carpeta remota.
	 * En esta carpeta se encuentran los archivos a sincronizar
	 * por el Update Manager
	 * 
	 * @return Path a la carpeta remota
	 */
	public String getRemoteFolder() {
		return remoteFolder;
	}
	
	/**
	 * Retorna el valor para la carpeta local.
	 * La carpeta Local contiene los archivos a sincronizar
	 * por el Update Manager en la instalacion local
	 * 
	 * @return La carpeta local
	 */
	public String getLocalFolder() {
		return localFolder;
	}
	
	/**
	 * Retorna el valor del host FTP.
	 * 
	 * @return El Host FTP
	 */
	public String getFTPHost() {
		return fTPHost;
	}
	
	/**
	 * Retorna el valor del usuario del FTP.
	 * 
	 * @return El Usuario del FTP
	 */
	public String getFTPUser() {
		return fTPUser;
	}
	
	/**
	 * Retorna el valor del password del usuario del FTP.
	 * 
	 * @return El password del FTP
	 */
	public String getFTPPass() {
		return fTPPass;
	}
	
	/**
	 * Retorna el nombre del archivo XML de sincronizacion.
	 * 
	 * @return El nombre del archivo XML remoto
	 */
	public String getRemoteXMLFile() {
		return remoteXMLFile;
	}

}
