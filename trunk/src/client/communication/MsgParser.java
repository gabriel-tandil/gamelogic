/**
 * 
 */
package client.communication;

import java.util.Hashtable;
import java.util.Set;
import java.nio.ByteBuffer;

/** 
 *  Todos los Hashtable estan parametrizados de la siguiente manera <EMessage,IProcessor>
 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class MsgParser {
	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private MsgParser msgParser;

	/** 
	 * @return el msgParser
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public MsgParser getMsgParser() {
		// begin-user-code
		return msgParser;
		// end-user-code
	}

	/** 
	 * @param theMsgParser el msgParser a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setMsgParser(MsgParser theMsgParser) {
		// begin-user-code
		msgParser = theMsgParser;
		// end-user-code
	}

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Hashtable Hash;

	/** 
	 * @return el Hash
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Hashtable getHash() {
		// begin-user-code
		return Hash;
		// end-user-code
	}

	/** 
	 * @param theHash el Hash a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setHash(Hashtable theHash) {
		// begin-user-code
		Hash = theHash;
		// end-user-code
	}

	/** 
	 * @uml.annotations for <code>emessage</code>
	 *     collection_type="client.communication.EMessage"
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Set<EMessage> emessage;

	/** 
	 * @return el emessage
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Set<EMessage> getEmessage() {
		// begin-user-code
		return emessage;
		// end-user-code
	}

	/** 
	 * @param theEmessage el emessage a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setEmessage(Set<EMessage> theEmessage) {
		// begin-user-code
		emessage = theEmessage;
		// end-user-code
	}

	/** 
	 * @uml.annotations for <code>iprocessor</code>
	 *     collection_type="client.communication.IProcessor"
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Set<IProcessor> iprocessor;

	/** 
	 * @return el iprocessor
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Set<IProcessor> getIprocessor() {
		// begin-user-code
		return iprocessor;
		// end-user-code
	}

	/** 
	 * @param theIprocessor el iprocessor a establecer
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setIprocessor(Set<IProcessor> theIprocessor) {
		// begin-user-code
		iprocessor = theIprocessor;
		// end-user-code
	}

	/** 
	 * @param message
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void parse(ByteBuffer message) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
	}

	/** 
	 * @param Hash
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public static void createInstance(Hashtable Hash) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
	}

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private MsgParser() {
		// begin-user-code
		// TODO Apéndice de constructor generado automáticamente
		// end-user-code
	}

	/** 
	 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public static void getInstance() {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
	}
}