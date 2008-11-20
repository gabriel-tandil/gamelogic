/**
 * 
 */
package client.communication;

import java.net.PasswordAuthentication;
import java.nio.ByteBuffer;

import com.sun.sgs.client.ClientChannel;
import com.sun.sgs.client.ClientChannelListener;
import com.sun.sgs.client.ServerSessionListener;
import com.sun.sgs.client.simple.SimpleClientListener;

/** 
 *  El receiveMessage{ channel y bytebuffer) hace messageParser.parser(bytebuffer)
 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class ClientListener implements ClientChannelListener, ClientChannel,
		SimpleClientListener {
	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private ClientCommunication clientCommunication;

	/** 
	 * @return el clientCommunication
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public ClientCommunication getClientCommunication() {
		// begin-user-code
		return clientCommunication;
		// end-user-code
	}

	/** 
	 * @param theClientCommunication el clientCommunication a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setClientCommunication(
			ClientCommunication theClientCommunication) {
		// begin-user-code
		clientCommunication = theClientCommunication;
		// end-user-code
	}

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private MsgParser msgparser;

	/** 
	 * @return el msgparser
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public MsgParser getMsgparser() {
		// begin-user-code
		return msgparser;
		// end-user-code
	}

	/** 
	 * @param theMsgparser el msgparser a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setMsgparser(MsgParser theMsgparser) {
		// begin-user-code
		msgparser = theMsgparser;
		// end-user-code
	}

	/** 
	 *  (sin Javadoc)
	 * @see ClientChannelListener#receivedMessage(ClientChannel arg0,ByteBuffer arg1)
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void receivedMessage(ClientChannel arg0, ByteBuffer arg1) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
	}

	/** 
	 *  (sin Javadoc)
	 * @see ClientChannelListener#leftChannel(ClientChannel arg0)
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void leftChannel(ClientChannel arg0) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
	}

	/** 
	 *  (sin Javadoc)
	 * @see ClientChannel#getName()
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public String getName() {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		return null;
		// end-user-code
	}

	/** 
	 *  (sin Javadoc)
	 * @see ClientChannel#send(ByteBuffer arg0)
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void send(ByteBuffer arg0) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
	}

	/** 
	 *  (sin Javadoc)
	 * @see ServerSessionListener#joinedChannel(ClientChannel arg0)
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public ClientChannelListener joinedChannel(ClientChannel arg0) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		return null;
		// end-user-code
	}

	/** 
	 *  (sin Javadoc)
	 * @see ServerSessionListener#receivedMessage(ByteBuffer arg0)
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void receivedMessage(ByteBuffer arg0) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
	}

	/** 
	 *  (sin Javadoc)
	 * @see ServerSessionListener#reconnecting()
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void reconnecting() {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
	}

	/** 
	 *  (sin Javadoc)
	 * @see ServerSessionListener#reconnected()
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void reconnected() {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
	}

	/** 
	 *  (sin Javadoc)
	 * @see ServerSessionListener#disconnected(boolean arg0,String arg1)
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void disconnected(boolean arg0, String arg1) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
	}

	/** 
	 *  (sin Javadoc)
	 * @see SimpleClientListener#getPasswordAuthentication()
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public PasswordAuthentication getPasswordAuthentication() {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		return null;
		// end-user-code
	}

	/** 
	 *  (sin Javadoc)
	 * @see SimpleClientListener#loggedIn()
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void loggedIn() {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
	}

	/** 
	 *  (sin Javadoc)
	 * @see SimpleClientListener#loginFailed(String arg0)
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void loginFailed(String arg0) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
	}
}