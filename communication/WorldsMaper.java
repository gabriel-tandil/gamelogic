package client.communication;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

/**
 * La clase sirve para mantener un doble mapeo entre los identificadores de
 * mundos utilizados por el cliente y los correspondientes utilizados por el
 * servidor.
 */
public class WorldsMaper {

	/**
	 * Key = ID World utilizado en el cliente.<BR>
	 * Value = ID World utilizado en el servidor.
	 */
	public static final Map<String, String> CLIENT_TO_SERVER = Collections
			.synchronizedMap(new Hashtable<String, String>());

	/**
	 * Key = ID World utilizado en el servidor.<BR>
	 * Value = ID World utilizado en el cliente.
	 */
	public static final Map<String, String> SERVER_TO_CLIENT = Collections
			.synchronizedMap(new Hashtable<String, String>());

	/**
	 * Inicializa el doble mapeo de la clase con la informacion que se encuentra
	 * en el archivo pasado como parametro.
	 * 
	 * @param mapping
	 *            Archivo de formato "properties" en el que se espera tener el
	 *            mapeo:<BR>
	 *            idWorldClient=idWorldServer
	 */
	public static final void initWorldsMapping(File mapping) {
		try {
			// carga un properties para facilitar la lectura desde el archivo
			Properties mapProperties = new Properties();
			FileInputStream is = new FileInputStream(mapping);
			mapProperties.load(is);
			
			GameContext.setClientCommunication(new ClientCommunication());

			// configura los mapas internos en base al Properties.
			for (Entry<Object, Object> world : mapProperties.entrySet()) {
				CLIENT_TO_SERVER.put((String) world.getKey(), (String) world
						.getValue());
				SERVER_TO_CLIENT.put((String) world.getValue(), (String) world
						.getKey());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
