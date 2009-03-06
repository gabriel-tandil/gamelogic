package client.communication;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;


import com.jme.math.Vector3f;

/**
 * Contiene un mapeo entre los identificadores de mundos y los vectores de
 * traslacion correspondientes.
 */
public class PositionsTranslator {

	/**
	 * Key = ID World utilizado en el cliente.<BR>
	 * Value = Vector de traslacion del mundo denrto del cliente.
	 */
	public static final Map<String, Vector3f> CLIENT_TO_SERVER = Collections
			.synchronizedMap(new Hashtable<String, Vector3f>());

	/**
	 * Inicializa el mapeo de la clase con la informacion que se encuentra en el
	 * archivo pasado como parametro.
	 * 
	 * @param mapping
	 *            Archivo de formato "properties" en el que se espera tener el
	 *            mapeo:<BR>
	 *            idWorldClient=x,y,z<BR>
	 *            Donde x,y,z son numero separados por ",".
	 */
	public static final void initWorldsMapping(File mapping) {
		try {
			// carga un properties para facilitar la lectura desde el archivo
			Properties mapProperties = new Properties();
			FileInputStream is = new FileInputStream(mapping);
			mapProperties.load(is);

			GameContext.setClientCommunication(new ClientCommunication());

			// configura los mapas internos en base al Properties.
			for (Entry<Object, Object> translation : mapProperties.entrySet()) {
				String[] vector = ((String) translation.getValue()).split(",");

				CLIENT_TO_SERVER.put((String) translation.getKey(),
						new Vector3f(Float.parseFloat(vector[0]), Float
								.parseFloat(vector[1]), Float
								.parseFloat(vector[2])));

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Calcula y retorna la posicion correspondiente a los parametros en el
	 * cliente.
	 * 
	 * @param idServerWorld
	 *            El String que representa el mundo (segun el servidor) en el
	 *            que se encuentra la posiction pasada como parametro.
	 * @param serverPosition 
	 *            La posicion que se debe trasladar a una posicion valida en el
	 *            cliente.
	 */
	public static Vector3f clientPositionServerWorld(String idServerWorld,
			Vector3f serverPosition) {

		
		return clientPositionClientWorld(WorldsMaper.SERVER_TO_CLIENT
				.get(idServerWorld), serverPosition);
	}
	
	/**
	 * Calcula y retorna la posicion correspondiente a los parametros en el
	 * cliente.
	 * 
	 * @param idClientWorld
	 *            El String que representa el mundo (segun el cliente) en el
	 *            que se encuentra la posiction pasada como parametro.
	 * @param serverPosition
	 *            La posicion que se debe trasladar a una posicion valida en el
	 *            cliente.
	 */
	public static Vector3f clientPositionClientWorld(String idClientWorld,
			Vector3f serverPosition) {

		Vector3f traslation = CLIENT_TO_SERVER.get(idClientWorld);

		return new Vector3f(serverPosition.x - traslation.x, serverPosition.y
				- traslation.y, serverPosition.z - traslation.z);
	}

	/**
	 * Calcula y retorna la posicion correspondiente a los parametros en el
	 * servidor.
	 * 
	 * @param idClientWorld
	 *            El String que representa el mundo (segun el servidor) en el
	 *            que se encuentra la posiction pasada como parametro.
	 * @param clientPosition
	 *            La posicion que se debe trasladar a una posicion valida en el
	 *            cliente.
	 */
	public static Vector3f serverPosition(String idClientWorld,
			Vector3f clientPosition) {

		Vector3f traslation = CLIENT_TO_SERVER.get(idClientWorld);

		return new Vector3f(clientPosition.x + traslation.x, clientPosition.y
				+ traslation.y, clientPosition.z + traslation.z);
	}

}
