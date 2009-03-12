package client.communication;

import java.util.Collections;
import java.util.Map;
import java.util.Hashtable;

/**
 * La clase mantiene estados de las entidades dinamicas utilizadas localemente.
 * Los estado puede ser alguna de las constantes, EXISTS o SOLICITED. Mapea para
 * cada id de entidad, el estado, si existe de forma local (EXISTS), si no
 * existe pero fue solicitado (SOLICITED). Si un id no se encuenrta en el Map,
 * significa que la entidad no existe localemnte y no fue solicitada.
 */
public class DynamicEntitysSolicitations {

	/** La entidad existe localmente */
	public static final String EXISTS = "exists";

	/** La entidad no existe localmente pero se realizo una solicitud. */
	public static final String SOLICITED = "solicited";

	/**
	 * Key = ID Dynamic Entity.<BR>
	 * Value = Estado, puede ser alguna de las constantes, EXISTS o SOLICITED.
	 * Mapea para cada id de entidad, el estado, si existe de forma local
	 * (EXISTS), si no existe pero fue solicitado (SOLICITED). Si un id no se
	 * encuenrta en el Map, significa que la entidad no existe localemnte y no
	 * fue solicitada.
	 */
	public static Map<String, String> DYNAMIC_ENTITYS_STATES = Collections
			.synchronizedMap(new Hashtable<String, String>());

}
