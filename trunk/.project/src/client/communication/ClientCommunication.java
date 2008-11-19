/**
 * 
 */
package client.communication;

import com.sun.sgs.client.simple.SimpleClient;
import java.util.Set;
import com.sun.sgs.client.ClientChannel;
import java.nio.ByteBuffer;

/** 
 * @author Mara
 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class ClientCommunication {
	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private SimpleClient simpleclient;

	/** 
	 * @return el simpleclient
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public SimpleClient getSimpleclient() {
		// begin-user-code
		return simpleclient;
		// end-user-code
	}

	/** 
	 * @param theSimpleclient el simpleclient a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setSimpleclient(SimpleClient theSimpleclient) {
		// begin-user-code
		simpleclient = theSimpleclient;
		// end-user-code
	}

	/** 
	 * @uml.annotations for <code>clientchannel</code>
	 *     collection_type="com.sun.sgs.client.ClientChannel"
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Set<ClientChannel> clientchannel;

	/** 
	 * @return el clientchannel
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Set<ClientChannel> getClientchannel() {
		// begin-user-code
		return clientchannel;
		// end-user-code
	}

	/** 
	 * @param theClientchannel el clientchannel a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setClientchannel(Set<ClientChannel> theClientchannel) {
		// begin-user-code
		clientchannel = theClientchannel;
		// end-user-code
	}

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private ClientListener clientListener;

	/** 
	 * @return el clientListener
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public ClientListener getClientListener() {
		// begin-user-code
		return clientListener;
		// end-user-code
	}

	/** 
	 * @param theClientListener el clientListener a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setClientListener(ClientListener theClientListener) {
		// begin-user-code
		clientListener = theClientListener;
		// end-user-code
	}

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void logout() {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
	}

	/** 
	 * @param pass
	 * @param user
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void login(String pass, String user) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
	}

	/** 
	 * @param message
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void sendEvent(ByteBuffer message) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
	}

	/** 
	 * @param message
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void send(ByteBuffer message) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
	}
}