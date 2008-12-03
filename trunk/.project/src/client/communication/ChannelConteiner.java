/**
 * 
 */
package client.communication;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import com.sun.sgs.client.ClientChannel;

import common.util.ChannelNameParser;

/**
 * La clase es un contenedor de {@link ClientChannel}. Los cuales estan
 * organizados en una estructura que los agrupa por el tipo de canal.
 * 
 * @author lito
 */
public final class ChannelConteiner {
	
	/** Estructura para asociar conjuntos de canales del mismo tipo. */
	private Hashtable<String, Set<ClientChannel>> channelsByType;
	
	/**
	 * Cconstructor por defecto, inicializa la estrucutra interna en vacio.
	 */
	public ChannelConteiner() {
		channelsByType = new Hashtable<String, Set<ClientChannel>>();
	}

	/** 
	 * Agrega un un canal de comunicacion al contnedor, El mismo sera
	 * almacenado en un conjunto con todos los canales de sumismo tipo.
	 * 
	 * @param channel El canal a ser agregado a este contenedor.
	 */
	public void addChannel(final ClientChannel channel) {
		/** Se obtiene el tipo de canal. */
		String channelType = 
			ChannelNameParser.parseChannelType(channel.getName());
		/** Se obtienen todos los canales del mismo tipo. */
		Set<ClientChannel> channels = this.channelsByType.get(channelType);
		/** Si no habia ningun canal del mismo tipo, debo crear el
		 *  conjunto y agregalo al hash. */
		if (channels == null) {
			channels = new HashSet<ClientChannel>();
			this.channelsByType.put(channelType, channels);
		}
		/** se agrega el canal. */
		channels.add(channel);		
	}
	
	/**
	 * Remueve de este contenedor el canala pasado como parametro.
	 * 
	 * @param channel el canal a ser removido de este contenedor.
	 * 
	 * @return true Si el canal estaba en este contenedor.
	 */
	public boolean removeChannel(final ClientChannel channel) {
		String channelType =
			ChannelNameParser.parseChannelType(channel.getName());
		Set<ClientChannel> channels = this.channelsByType.get(channelType);
		
		if (channels != null) {
			return channels.remove(channel);
		} 
		return false;
	}
	
	/**
	 * Devuelve un conjunto de con todos los canales del tipo pasado
	 * como parametro.<BR/>
	 * El {@link Set} devuelto, es una referencia al conjunto con el que
	 * se almancenan internamente los canales, por lo que cambios al mismo,
	 * se reflejaran en el contenedor.<BR/>
	 * NOTA: no se recomienda  modificar el conjunto retornado.
	 * 
	 * @param channelType El tipo de canal para el cual se pretende
	 * obtener todos los canales del contendor.
	 * 
	 * @return Un conjunto con todos los canales del tipo pasado como
	 * paramentro.
	 */
	public Set<ClientChannel> getChannelsOfType(
			final String channelType) {
		
		return this.channelsByType.get(channelType);
	}
	
}
