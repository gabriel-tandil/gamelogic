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
 * <code>Map</code> es responsable de cargar el mapa del lugar. Además
 * se encarga de reflejar los cambios en el mapa cuando ocurre un movimiento.
 * <code>Map</code> permite obtener el nodo que contiene el mapa del lugar. 
 * Este mapa puede ser cambiado para reflejar otro mundo.
 * Los movimientos realizados por el jugador se ven reflejados en el mapa. Esto
 * se realiza "moviendo" la textura que contiene el mapa para reflejar dichos
 * movimientos y dejando la textura de posicion (en realidad el quad) en el centro,
 * es decir en un lugar fijo. Para poder mover la textura del mapa para reflejar el
 * movimiento correctamente, se utiliza un factor de escala, un desplazamiento en x, 
 * un desplazamiento en y, y una rotacion. Estos parámetros se utilizan para 
 * manejar el mapeo entre el mundo real y el mapa.
 * <code>Map</code> solo es accedida por la clase <code>HudManager</code>.
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

	/**
	 * Setea el factor de escala.
	 * @param factor
	 */
	public void setFactor(float factor) {
		this.factor = factor;
	}

	/**
	 * Devuelve el desplazamiento en la coordenada x.
	 * @return el desplazamiento en la coordenada x.
	 */
	public float getDesplazamientoX() {
		return desplazamientoX;
	}

	/**
	 * Setea el desplazamiento en la coordenada x.
	 * @param desplazamientoX
	 */
	public void setDesplazamientoX(float desplazamientoX) {
		this.desplazamientoX = desplazamientoX;
	}

	/**
	 * Devuelve el desplazamiento en la coordenada y.
	 * @return el desplazamiento en la coordenada y.
	 */
	public float getDesplazamientoY() {
		return desplazamientoY;
	}

	/**
	 * Setea el desplazamiento en la coordenada y.
	 * @param desplazamientoY
	 */
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
	
	/**
	 * Inicializa el nodo que contiene el mapa del lugar (con aspectos
	 * tales como la textura del mapa, la textura de la posicion actual y
	 * la textura del borde del mapa) y devuelve dicho nodo.
	 * @return el nodo que contiene el mapa
	 */
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
	
	/**
	 * Devuelve el valor de x.
	 * @return el valor de x
	 */
   	public float getX() {
		return x;
	}

	/**
	 * Setea el valor de x.
	 * @param x
	 */
   	public void setX(float x) {
		this.y = x;
	}

   	/**
	 * Devuelve el valor de y.
	 * @return el valor de y
	 */
   	public float getY() {
		return y;
	}

   	/**
	 * Setea el valor de y.
	 * @param y
	 */
   	public void setY(float y) {
		this.x = y;
	}

	/**
	 * Devuelve el nodo que contiene el mapa.
	 * @return el nodo que contiene el mapa
	 */
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
	 */
	public void redrawMap(){
		float x=this.x*factor+desplazamientoX;
		float y=this.y*factor+desplazamientoY;
		x= x;
		y= 800 - y;
		
		final FloatBuffer texCoords = BufferUtils.createVector2Buffer(4);
		int x1=xRotar((x - this.map.getWidth()/2),(y - this.map.getHeight()/2),x,y);
		int y1=yRotar((x - this.map.getWidth()/2),(y - this.map.getHeight()/2),x,y);
		texCoords.put(getUForPixel(x1)).put(getVForPixel(y1));
		x1=xRotar((x - this.map.getWidth()/2),(y + this.map.getHeight()/2),x,y);
		y1=yRotar((x - this.map.getWidth()/2),(y + this.map.getHeight()/2),x,y);

		texCoords.put(getUForPixel(x1)).put(getVForPixel(y1));
		x1=xRotar((x + this.map.getWidth()/2),(y + this.map.getHeight()/2),x,y);
		y1=yRotar((x + this.map.getWidth()/2),(y + this.map.getHeight()/2),x,y);

		texCoords.put(getUForPixel(x1)).put(getVForPixel(y1));
		x1=xRotar((x + this.map.getWidth()/2),(y - this.map.getHeight()/2),x,y);
		y1=yRotar((x + this.map.getWidth()/2),(int)(y - this.map.getHeight()/2),x,y);

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

	
	/**
	 * Devuelve la rotacion (el parámetro rotacion que indica cuanto roto el jugador)
	 * @return la rotacion
	 */
	public float getRotacion() {
		return rotacion;
	}

	/**
	 * Setea la rotacion.
	 * @param rotacion
	 */
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

	/**
	 * Setea la rotacion del mundo.
	 * @param la rotacion del mundo
	 */
	public void setRotacionMundo(float rotacionMundo) {
		this.rotacionMundo = rotacionMundo;
	}

	/**
	 * Devuelve la rotacion del mundo
	 * @return la rotacion del mundo
	 */
	public float getRotacionMundo() {
		return rotacionMundo;
	}
    

}
