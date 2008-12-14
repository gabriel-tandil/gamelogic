package client.game.state;

import java.net.URISyntaxException;
import java.nio.FloatBuffer;

import client.game.Game;
import client.game.task.TaskManagerFactory;
import client.game.task.U3DChangeToExteriorTaskFactory;
import client.game.task.U3DChangeToIntEcoTaskFactory;
import client.game.task.U3dChangeToExterior;
import client.game.task.U3dChangeToIntEco;
import client.manager.HudManager;
import client.manager.TaskManager;

import com.jme.image.Texture;
import com.jme.input.KeyBindingManager;
import com.jme.input.KeyInput;
import com.jme.math.Vector3f;
import com.jme.renderer.Renderer;
import com.jme.scene.Spatial;
import com.jme.scene.TexCoords;
import com.jme.scene.shape.Quad;
import com.jme.scene.state.TextureState;
import com.jme.system.DisplaySystem;
import com.jme.util.TextureManager;
import com.jme.util.geom.BufferUtils;
import com.jme.util.resource.ResourceLocatorTool;
import com.jme.util.resource.SimpleResourceLocator;

public class U3dLoginState extends U3dState {
    private int textureWidth;
    // initialize texture height
    private int textureHeight;
	public U3dLoginState(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void render(float arg0) {
		super.render(arg0);
	}

	@Override
	public void update(float arg0) {
		if(KeyBindingManager.getKeyBindingManager().isValidCommand("campus")){
			U3dChangeToExterior task =(U3dChangeToExterior) TaskManager.getInstance().createTask("3");
			task.initTask();
			TaskManager.getInstance().enqueue(task);
		}
		if(KeyBindingManager.getKeyBindingManager().isValidCommand("economicas")){
			U3dChangeToIntEco task =(U3dChangeToIntEco) TaskManager.getInstance().createTask("4");
			task.initTask();
			TaskManager.getInstance().enqueue(task);
		}
	}
	
	public void initialize(){
		TaskManagerFactory.getInstance().add(new U3DChangeToExteriorTaskFactory());		
		KeyBindingManager.getKeyBindingManager().set("campus", KeyInput.KEY_L);
		TaskManagerFactory.getInstance().add(new U3DChangeToIntEcoTaskFactory());
		KeyBindingManager.getKeyBindingManager().set("economicas", KeyInput.KEY_K);
		
        Quad hudQuad = new Quad("hud", DisplaySystem.getDisplaySystem().getWidth(), DisplaySystem.getDisplaySystem().getHeight());
        
        // create the texture state to handle the texture
        final TextureState ts = DisplaySystem.getDisplaySystem().getRenderer().createTextureState();
        // load the image bs a texture (the image should be placed in the same directory bs this class)
		try {
			ResourceLocatorTool.addResourceLocator
			(ResourceLocatorTool.TYPE_TEXTURE, new SimpleResourceLocator(Game.class.getClassLoader().getResource("login/")));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        final Texture texture = TextureManager.loadTexture(
                "pantallalogin2.PNG",
                Texture.MinificationFilter.Trilinear, // of no use for the quad
                Texture.MagnificationFilter.Bilinear, // of no use for the quad
                1.0f,
                true);
        // set the texture for this texture state
        ts.setTexture(texture);
        // initialize texture width
        textureWidth = ts.getTexture().getImage().getWidth();
        // initialize texture height
        textureHeight = ts.getTexture().getImage().getHeight();
        // activate the texture state
        ts.setEnabled(true); 

        // correct texture application:
        final FloatBuffer texCoords = BufferUtils.createVector2Buffer(4);
        // coordinate (0,0) for vertex 0
        texCoords.put(getUForPixel(0)).put(getVForPixel(0));
        // coordinate (0,40) for vertex 1
        texCoords.put(getUForPixel(0)).put(getVForPixel(600));
        // coordinate (40,40) for vertex 2
        texCoords.put(getUForPixel(800)).put(getVForPixel(600));
        // coordinate (40,0) for vertex 3
        texCoords.put(getUForPixel(800)).put(getVForPixel(0));
        // assign texture coordinates to the quad
        hudQuad.setTextureCoords(new TexCoords(texCoords));
        // apply the texture state to the quad
        hudQuad.setRenderState(ts);
        
        // to handle texture transparency:
        // create a blend state
//        final BlendState bs = display.getRenderer().createBlendState();
//        // activate blending
//        bs.setBlendEnabled(true);
//        // set the source function
//        bs.setSourceFunctionAlpha(BlendState.SourceFunction.SourceAlpha);
//        // set the destination function
//        bs.setDestinationFunctionAlpha(BlendState.DestinationFunction.OneMinusSourceAlpha);
//        // set the blend equation between source and destination
//        bs.setBlendEquation(BlendState.BlendEquation.Subtract);
//        bs.setTestEnabled(false);
//        // activate the blend state
//        bs.setEnabled(true);
        // assign the blender state to the quad
//        hudQuad.setRenderState(bs);
           
        
        hudQuad.setRenderQueueMode(Renderer.QUEUE_ORTHO);        

        hudQuad.setLocalTranslation(new Vector3f(DisplaySystem.getDisplaySystem().getWidth()/2,DisplaySystem.getDisplaySystem().getHeight()/2,0));

        /* does not work to disable light under v0.10 */
        // LightState ls = display.getRenderer().createLightState();
        // ls.setEnabled(false);
        // hudQuad.setRenderState(ls);

        hudQuad.setLightCombineMode(Spatial.LightCombineMode.Off);
        hudQuad.updateRenderState();

		HudManager.getInstance().addHud(hudQuad);
		HudManager.getInstance().update();
	}
    private float getUForPixel(int xPixel) {
        return (float) xPixel / textureWidth;
    }

    private float getVForPixel(int yPixel) {
        return 1f - (float) yPixel / textureHeight;
    }

	@Override
	public void initializeState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateState(float interpolation) {
		// TODO Auto-generated method stub
		
	}


	public WorldGameState getWorld() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void updateCamera(Vector3f direction) {}
}
