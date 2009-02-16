package client.game;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;

import com.jme.animation.AnimationController;
import com.jme.animation.Bone;
import com.jme.animation.BoneAnimation;
import com.jme.animation.SkinNode;
import com.jme.input.KeyBindingManager;
import com.jme.input.KeyInput;
import com.jme.math.Quaternion;
import com.jme.math.Vector3f;
import com.jme.scene.Controller;
import com.jme.scene.Node;
import com.jme.util.resource.ResourceLocatorTool;
import com.jme.util.resource.SimpleResourceLocator;
import com.jmex.model.collada.ColladaImporter;


public class PersonaDae extends IPersonaje {

	private static final long serialVersionUID = 1L;
	private Bone skel;
	private SkinNode sn;	
	
	
	public PersonaDae(Node p)
	{
		super(p);
	}
	
    protected void run(boolean running){
    	if(running) this.skel.getController(0).setSpeed(2);
    	else this.skel.getController(0).setSpeed(1);
    }
	
    public void mover(boolean state, boolean run, boolean forward){
    	if(forward)
    		this.skel.getController(0).setRepeatType(Controller.RT_WRAP);
    	else
    		this.skel.getController(0).setRepeatType(Controller.RT_BACK);
    	this.skel.getController(0).setActive(state);
    	this.run(run);
    }
	
	public Node cargar() {
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
        
        this.cargarAnimacion();
        
        Quaternion q1 = new Quaternion();
		q1 = q1.fromAngleAxis((float) -Math.PI / 2, new Vector3f(0, 0, 1));
		Quaternion q2 = new Quaternion();
		q2 = q2.fromAngleAxis((float) -Math.PI / 2, new Vector3f(0, 1, 0));

		sn.setLocalRotation(q1.mult(q2));
		sn.setLocalScale(0.8f);

		skel.setLocalRotation(q1.mult(q2));
		skel.setLocalScale(0.8f);
        
        this.skel.setName("Esqueleto");
        this.sn.setName("tipo");
        this.padre.attachChild(sn);
        this.padre.attachChild(skel);
        this.padre.updateGeometricState(0, true);
        
        ColladaImporter.cleanUp();   
        
        return this.sn;
    }	
    public void cargarAnimacion()
    {
    	ArrayList<String> animations = ColladaImporter.getControllerNames();
    	if(animations != null) {
	        for(int i = 0; i < animations.size(); i++) {
	            animations.get(i);
	        }
	        //Obtain the animation from the file by name
	        BoneAnimation anim1 = ColladaImporter.getAnimationController(animations.get(0));
	        
	        //set up a new animation controller with our BoneAnimation
	        ac = new AnimationController();
	        ac.addAnimation(anim1);
	        ac.setRepeatType(Controller.RT_WRAP);
	        ac.setActive(true);
	        ac.setActiveAnimation(anim1);
	        
	        //assign the animation controller to our skeleton
	        skel.addController(ac);
        }
    }     
}
