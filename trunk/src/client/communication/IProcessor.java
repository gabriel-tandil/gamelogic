/**
 * 
 */
package client.communication;

import java.nio.ByteBuffer;

/** 
 *  Este metodo tiene que terminar de parsear el mensaje que es pasado como parametro ( para obtener los parametros de la tarea a crear) y ademas debe crear dicha tarea en el TaskManager
 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public interface IProcessor {
	/** 
	 * @param Parámetro1
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void process(ByteBuffer Parámetro1);
}