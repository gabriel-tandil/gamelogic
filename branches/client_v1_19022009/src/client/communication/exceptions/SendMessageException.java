/**
 * SendMessageException.java
 * @author Javier
 */
package client.communication.exceptions;

import common.messages.IMessage;

/**
 * La excepcion representa que ocurrió un error al enviar un mensaje
 * desde el cliente al servidor darkstar de forma directa o a traves
 * de canales. El mensaje posiblemente no se haya enviado.
 * 
 * @author Javier
 */
public final class SendMessageException extends Exception {
	
	/** El mensaje que origino la excepcion al intentar enviarse.
	 *  @author Javier 
	 */
	private IMessage iMessage;
	
	/** El codigo identificador de serializacion de la clase. 
	 *  @author Javier 
	 */
	private static final long	serialVersionUID	= -5193558193720202181L;

	/**
	 * Construye una instancia, seteando el estado interno con los
	 * parametros.
	 * 
	 * @param description La descripcion de la exceptcion.
	 * @param imessage El mensaje que orignio la excepcion.
	 * @param origen La excepcion que origino esta excepcion.
	 *
	 * @author Javier
	 */
	public SendMessageException(final String description,
			final Throwable origen, final IMessage imessage) {
		super(description, origen);
		iMessage = imessage;
	}

	/**
	 * Metodo que retorna el mensaje que origino la exepcion.
	 * @return El mensaje que origino la excepcion al intentar enviarse.
	 * @author Javier
	 */
	public IMessage getIMessage() {
		return iMessage;
	}
	
}
