/**
 * 
 */
package client.manager;

import java.nio.FloatBuffer;

import com.jme.image.Texture;
import com.jme.math.Vector3f;
import com.jme.renderer.Renderer;
import com.jme.scene.Node;
import com.jme.scene.Spatial;
import com.jme.scene.TexCoords;
import com.jme.scene.shape.Quad;
import com.jme.scene.state.BlendState;
import com.jme.scene.state.TextureState;
import com.jme.system.DisplaySystem;
import com.jme.util.TextureManager;
import com.jme.util.geom.BufferUtils;

/**
 * Esta clase se encarga de cargar el mapa del lugar. Además se encarga de reflejar los
 * cambios en el mapa cuando ocurre un movimiento.
 * 
 * @author Sebastian Sampaoli
 */
public class Map{

	private Node hudMap;
    private Quad map;
    private Quad borderMap;
    private Quad myPosition;
    
    private int mapWidth;
    private int mapHeight;
	private float factor;
	private float desplazamientoX;
	private float rotacionMundo;
	
	public float getFactor() {
		return factor;
	}

	public void setFactor(float factor) {
		this.factor = factor;
	}

	public float getDesplazamientoX() {
		return desplazamientoX;
	}

	public void setDesplazamientoX(float desplazamientoX) {
		this.desplazamientoX = desplazamientoX;
	}

	public float getDesplazamientoY() {
		return desplazamientoY;
	}

	public void setDesplazamientoY(float desplazamientoY) {
		this.desplazamientoY = desplazamientoY;
	}

	private float desplazamientoY;
	private float x=221;
	private float y=181;
	private float rotacion;
    
   	public Map(){
		hudMap = new Node("hudMap");
        borderMap = new Quad("borderMap", 203f, 143f);
        map = new Quad ("map", 188f, 124f );
        myPosition = new Quad ("myPosition", 30f, 30f);
        
        hudMap.setRenderQueueMode(Renderer.QUEUE_ORTHO);    
	}
   	
    /*@Override
	protected void simpleInitGame() {
		Node n = this.loadMap();
		rootNode.attachChild(n);
	}*/
	
	public Node loadMap(){
        //Aplica la textura al borde del mapa y su ubicación
        this.setTexture(borderMap, "/HUD/textures/map_frame.png");
        borderMap.setLocalTranslation(new Vector3f((DisplaySystem
				.getDisplaySystem().getWidth() - 105) , DisplaySystem
				.getDisplaySystem().getHeight() - 75, 0));
        /*borderMap.setLocalTranslation(new Vector3f((86.8f*DisplaySystem
				.getDisplaySystem().getWidth()/100) , 87.5f*DisplaySystem
				.getDisplaySystem().getHeight()/100, 0));*/
        
        //Aplica la textura al mapa y su ubicación
        this.setTexture(map, "/HUD/textures/MAPA.gif");
        this.mapWidth = this.getTextureWidth("/HUD/textures/MAPA.gif");
        this.mapHeight = this.getTextureHeight("/HUD/textures/MAPA.gif");
        map.setLocalTranslation(new Vector3f((DisplaySystem
				.getDisplaySystem().getWidth() - 105) , DisplaySystem
				.getDisplaySystem().getHeight() - 75, 0));
        /*map.setLocalTranslation(new Vector3f((86.8f*DisplaySystem
				.getDisplaySystem().getWidth()/100) , 87.5f*DisplaySystem
				.getDisplaySystem().getHeight()/100, 0));*/
        
        //Aplica la textura a la posicion actual
        this.setTexture(myPosition, "/HUD/textures/map_myposition.png");
        myPosition.setLocalTranslation(new Vector3f((DisplaySystem
				.getDisplaySystem().getWidth() - 105) , DisplaySystem
				.getDisplaySystem().getHeight() - 75, 0));
        /*myPosition.setLocalTranslation(new Vector3f((86.8f*DisplaySystem
				.getDisplaySystem().getWidth()/100) , 87.5f*DisplaySystem
				.getDisplaySystem().getHeight()/100, 0));*/
        //Aplica transparencia a la posicion actual
        final BlendState alphaState = DisplaySystem.getDisplaySystem().getRenderer().createBlendState();
        alphaState.setBlendEnabled(true);
        alphaState.setSourceFunction(BlendState.SourceFunction.SourceAlpha);
        alphaState.setDestinationFunction(BlendState.DestinationFunction.OneMinusSourceAlpha);
        alphaState.setTestEnabled(true);
        alphaState.setTestFunction(BlendState.TestFunction.GreaterThan);
        alphaState.setEnabled(true);

        myPosition.setRenderState(alphaState);
        myPosition.updateRenderState();
        //Aplica transparencia al borde del mapa
        borderMap.setRenderState(alphaState); 
        borderMap.updateRenderState();
        
        hudMap.setLightCombineMode(Spatial.LightCombineMode.Off);
        hudMap.attachChild(borderMap);
        hudMap.attachChild(map);
        hudMap.attachChild(myPosition);
      
        hudMap.updateRenderState();
        return hudMap;
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public Node getHudMap() {
		return hudMap;
	}

	/**
	 * Retorna la altura de una imagen.
	 * @param image
	 * @return la altura de la imagen
	 */
	private int getTextureHeight(String image) {
		Texture texture = TextureManager.loadTexture(
                getClass().getResource(image),
                Texture.MinificationFilter.Trilinear, // of no use for the quad
                Texture.MagnificationFilter.Bilinear, // of no use for the quad
                1.0f,
                true);
		return texture.getImage().getHeight();
	}

	/**
	 * Retorna el ancho de una imagen.
	 * @param image
	 * @return el ancho de la imagen
	 */
	private int getTextureWidth(String image) {
		Texture texture = TextureManager.loadTexture(
                getClass().getResource(image),
                Texture.MinificationFilter.Trilinear, // of no use for the quad
                Texture.MagnificationFilter.Bilinear, // of no use for the quad
                1.0f,
                true);
		return texture.getImage().getWidth();	
	}
	
	/**
	 * Transforma la coordenada x de una imagen en la coordenada x de la textura coorespondiente. Esto es necesario
	 * ya que OpenGL utiliza un sistema de coordenadas diferentes al de los editores gráficos.
	 * @param xPixel
	 * @return la coordenada de la textura
	 */
	private float getUForPixel(int xPixel) {
        return (float) xPixel / mapWidth;
    }

    /**
     * Transforma la coordenada x de una imagen en la coordenada x de la textura coorespondiente. Esto es necesario
	 * ya que OpenGL utiliza un sistema de coordenadas diferentes al de los editores gráficos. 
     * @param yPixel
     * @return la coordenada de la textura
     */
	private float getVForPixel(int yPixel) {
        return 1f - (float) yPixel / mapHeight;
    }
	
    /**
     * Setea una textura a un nodo.
     * @param quad el nodo al que se le seteara la textura
     * @param path la textura
     */
	private void setTexture (Quad quad, String path){        
        TextureState textureState = DisplaySystem
		.getDisplaySystem().getRenderer().createTextureState();
        Texture texture = TextureManager.loadTexture(
                getClass().getResource(path),
                Texture.MinificationFilter.Trilinear, // of no use for the quad
                Texture.MagnificationFilter.Bilinear, // of no use for the quad
                1.0f,
                true);
        textureState.setTexture(texture);
        textureState.setEnabled(true);
        quad.setRenderState(textureState);
	}
	
	/**
	 * Mueve la textura del mapa para reflejar el movimiento realizado por el jugador.
	 * @param x la nueva coordenada en x a la que se movio el jugador
	 * @param y la nueva coordenada en y a la que se movio el jugador
	 */
	public void redrawMap(){
		System.out.print("Posicion "+x+" "+y+"  ->  ");
		float x=this.x*factor+desplazamientoX;
		float y=this.y*factor+desplazamientoY;
		System.out.println(x+" "+y);

		final FloatBuffer texCoords = BufferUtils.createVector2Buffer(4);
		int x1=xRotar((x - this.map.getWidth()/2),(y - this.map.getHeight()/2),x,y);
		int y1=yRotar((x - this.map.getWidth()/2),(y - this.map.getHeight()/2),x,y);
		System.out.println(x1+" "+y1);
		texCoords.put(getUForPixel(x1)).put(getVForPixel(y1));
		x1=xRotar((x - this.map.getWidth()/2),(y + this.map.getHeight()/2),x,y);
		y1=yRotar((x - this.map.getWidth()/2),(y + this.map.getHeight()/2),x,y);
		System.out.println(x1+" "+y1);

		texCoords.put(getUForPixel(x1)).put(getVForPixel(y1));
		x1=xRotar((x + this.map.getWidth()/2),(y + this.map.getHeight()/2),x,y);
		y1=yRotar((x + this.map.getWidth()/2),(y + this.map.getHeight()/2),x,y);
		System.out.println(x1+" "+y1);

		texCoords.put(getUForPixel(x1)).put(getVForPixel(y1));
		x1=xRotar((x + this.map.getWidth()/2),(y - this.map.getHeight()/2),x,y);
		y1=yRotar((x + this.map.getWidth()/2),(int)(y - this.map.getHeight()/2),x,y);
		System.out.println(x1+" "+y1);

		texCoords.put(getUForPixel(x1)).put(getVForPixel(y1));
        // assign texture coordinates to the quad
        map.setTextureCoords(new TexCoords(texCoords));
        // apply the texture state to the quad
        map.updateRenderState();
	}

	private int xRotar(float x, float y,float cx,float cy) {
		return (int) (-Math.sin(rotacion+rotacionMundo)*y+Math.cos(rotacion+rotacionMundo)*x+cx-
				Math.cos(rotacion+rotacionMundo)*cx+Math.sin(rotacion+rotacionMundo)*cy);
	}
	private int yRotar(float x, float y,float cx,float cy) {
		return  (int) (Math.sin(rotacion+rotacionMundo)*x+Math.cos(rotacion+rotacionMundo)*y+cy-
				Math.sin(rotacion+rotacionMundo)*cx-Math.cos(rotacion+rotacionMundo)*cy);
	}	

	
	public float getRotacion() {
		return rotacion;
	}

	public void setRotacion(float rotacion) {
		this.rotacion = rotacion;
	}

	/**
	 * Setea el mapa del lugar.
	 * @param mapa el mapa
	 */
	public void setMap (String mapa){
		this.setTexture(map, mapa);
		map.updateRenderState();
	}

	public void setRotacionMundo(float rotacionMundo) {
		this.rotacionMundo = rotacionMundo;
	}

	public float getRotacionMundo() {
		return rotacionMundo;
	}
    
    /*public static void main(String[] args) {
        Map app = new Map();
        app.setConfigShowMode(SimpleGame.ConfigShowMode.AlwaysShow);
        app.start();
    }*/

}
