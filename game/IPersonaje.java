package client.game;

import com.jme.animation.AnimationController;
import com.jme.scene.Node;
/**
 * clase abstracta de la cual deben heredar las clases que 
 * implementen la importación de los modelos de los 
 * personajes que son cargados en el juego. Extiende
 * de Node(clase de jMonkey que representa un objeto 3D)
 * @author kike
 *
 */

public abstract class IPersonaje extends Node{
	
	private static final long serialVersionUID = 1L;
	protected AnimationController ac;
	protected Node padre;	
	protected String paquete, personaje,animaciones;
	protected int minVelocity;
	protected int maxVelocity;
	
	public IPersonaje(Node p)
	{
		this.padre = p;	
		this.minVelocity = 1;
		this.maxVelocity = 5;
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
	public void setVelocity(int minVelocity,int maxVelocity){
		this.minVelocity = minVelocity;
		this.maxVelocity = maxVelocity;
	}
	
	abstract public Node cargar();
    abstract public void mover(boolean state, boolean run, boolean forward);
    abstract protected void run(boolean running);
    abstract public void clearAll();
	

}
