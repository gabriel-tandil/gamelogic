package client.game;

import java.io.InputStream;
import java.net.URISyntaxException;

import com.jme.animation.AnimationController;
import com.jme.animation.Bone;
import com.jme.animation.SkinNode;
import com.jme.input.KeyBindingManager;
import com.jme.input.KeyInput;
import com.jme.scene.Node;
import com.jme.util.resource.ResourceLocatorTool;
import com.jme.util.resource.SimpleResourceLocator;
import com.jmex.model.collada.ColladaImporter;

public class PersonaDae extends Node {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AnimationController ac;
	private Bone skel;
	private SkinNode sn;
	private Node padre;	
	private String paquete, personaje,animaciones;	
	
	
	public PersonaDae(Node p)
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
	public void cargar() {
    	try {
            ResourceLocatorTool.addResourceLocator(
                    ResourceLocatorTool.TYPE_TEXTURE,
                    new SimpleResourceLocator(Game.class.getClassLoader().getResource(this.paquete)));
        } catch (URISyntaxException e1) {            
        }

        KeyBindingManager.getKeyBindingManager().set( "bones", KeyInput.KEY_SPACE );
        
        InputStream mobboss = Game.class.getClassLoader()
                .getResourceAsStream(this.personaje);
        InputStream animation = Game.class.getClassLoader()
        		.getResourceAsStream(this.animaciones);
    
        ColladaImporter.load(mobboss, "model");
                
        this.sn = ColladaImporter.getSkinNode(ColladaImporter.getSkinNodeNames().get(0));
        this.skel = ColladaImporter.getSkeleton(ColladaImporter.getSkeletonNames().get(0));
        
        ColladaImporter.cleanUp();                
        ColladaImporter.load(animation, "anim");
        
        this.cargarAnimacion(0);
        
        this.skel.setName("Esqueleto");
        this.sn.setName("tipo");
        this.padre.attachChild(this.sn);
        this.padre.attachChild(this.skel);
        this.padre.updateGeometricState(0, true);
        
        this.attachChild(this.skel);
        this.attachChild(this.sn);        
        ColladaImporter.cleanUp();               
    }	
    private void cargarAnimacion(int n)
    {
    /*	ArrayList<String> animations = ColladaImporter.getControllerNames();
        if(animations != null) {	        
	        BoneAnimation anim1 = ColladaImporter.getAnimationController(animations.get(n));
	        
	        ac = new AnimationController();
	        ac.addAnimation(anim1);	        	        
	        ac.setActive(true);
	        ac.setActiveAnimation(anim1);	        
	        this.skel.addController(ac);
        }*/
    }     
    public void mover(){
    	this.skel.getController(0).setActive(true);
    }
}
