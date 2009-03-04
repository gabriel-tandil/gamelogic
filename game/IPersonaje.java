package client.game;

import com.jme.animation.AnimationController;
import com.jme.scene.Node;
/**
 * clase abstracta de la cual deben heredar las clases que implementen la importación 
 * de los modelos de los personajes que son cargados en el juego
 * @author kike
 *
 */

public abstract class IPersonaje extends Node{
	
	private static final long serialVersionUID = 1L;
	protected AnimationController ac;
	protected Node padre;	
	protected String paquete, personaje,animaciones;	
	
	public IPersonaje(Node p)
	{
		this.padre = p;	
	}
	public void setPaquete(String p)
	{
		this.paquete = p;
	}
	public void setPersonaje(String p)
	{
		this.personaje = p;
	}
	public void setAnimaciones(String a)
	{
		this.animaciones = a;
	}
	
	abstract public Node cargar();
    abstract public void mover(boolean state, boolean run, boolean forward);
    abstract protected void run(boolean running);
    abstract public void clearAll();
	

}
