
package client.manager;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import client.game.state.AccessPoint;
import client.game.state.IAccessPoint;

import com.jme.intersection.PickData;
import com.jme.intersection.PickResults;
import com.jme.intersection.TrianglePickResults;
import com.jme.math.Ray;
import com.jme.math.Vector3f;
import com.jme.scene.Node;
import com.jme.scene.Spatial;
import com.jme.scene.TriMesh;

/**
 *<code>CollisionManager</code> es responsable de
 * procesar todas las tareas de deteccion de colisiones
 * @author Santiago Michielotto, Guillermo Fiorini (Javadoc)
 * @version Created: 19-11-2008
 */
public class CollisionManager {

	/**
	 * La instancia unica del <code>CollisionManager</code>.
	 */
	protected static CollisionManager instance;
	
	/** 
	 * <code>iaccesspoint</code> es un conjunto de puntos de acceso del modelo.
	 * Un punto de acceso puede ser una puerta, una ventana o otro objeto 
	 * generador de una tarea.
	 * La tarea puede ser, por ejemplo, ingresar al isistan.
	 */
	private Hashtable<String,IAccessPoint> iaccesspoint;
	/** 
	 * Obtiene el conjunto de <code>iaccesspoint</code> del modelo. 
	 * @return el conjunto de puntos de acceso del modelo. 
	 */
	public Hashtable<String,IAccessPoint> getIaccesspoint() {
		return iaccesspoint;
	}
	/** 
	 * Inicializa la estructura interna de los puntos de acceso
	 */
	public void init()
	{
		iaccesspoint=new Hashtable<String,IAccessPoint>();
	}
	/**
	 * Agrega un punto de acceso al conjunto
	 * @param id <code>String</code> El identificador unico del punto de acceso
	 * @param ap <code>IAccessPoint</code> El punto de acceso a agregar
	 */
	public void addAccessPoint(String id, IAccessPoint ap)
	{
		iaccesspoint.put(id, ap);
	}
	
	/**
	 * Quita lodos los puntos de acceso almacenados
	 */
	public void removeAccessPoints()
	{
		iaccesspoint.clear();
	}
	
	/** 
	 * Cambia el conjunto de puntos de acceso de este <code>CollisionManager</code>.
	 * @param theIaccesspoint <code>Hashtable<code> conjunto de puntos de acceso a utilizar
	 */
	public void setIaccesspoint(Hashtable<String,IAccessPoint> theIaccesspoint) {
		iaccesspoint = theIaccesspoint;
	}

	/**
     * Obtiene la instancia unica del <code>CollisionManager</code>.
     * @return <code>CollisionManager</code> La instancia unica.
     */
	public static CollisionManager getInstace() {
		if (CollisionManager.instance == null) {
            CollisionManager.instance = new CollisionManager();
        }
        return CollisionManager.instance;
	}

	/** 
	 * Verifica si existe una colision con un punto de acceso
	 * @return <code>boolean</code> verdadero si se colisiona con un punto de acceso, en caso contrario falso
	 */
	public boolean checkOverAccessPoint(Node node) {
		Enumeration e = iaccesspoint.elements();
		boolean cond = false; 
		AccessPoint temp=null;
		while( e. hasMoreElements() && !cond)
		{
			temp=(AccessPoint)e.nextElement();
			Node abuelo = node;
			
			Node aux = abuelo;
			Node aux1 = abuelo.getParent();
			String num= "";
			while (aux1 != null)
			{
				num += " -- Numero: " +aux1.getChildIndex(aux);
				aux = aux1;
				aux1 = aux.getParent(); 
			}
			System.out.println("El accespoint puede ser uno de estos numeros: " + num);
			
			while (abuelo!=null && cond==false)
			{
			if (temp.getNodo().equals(abuelo))
				{cond=true;
				temp.dialogoIngresar();
				}
			else
				abuelo=abuelo.getParent();
			}
		}
		return cond;
	}

	/**
	 * Retorna el punto de interseccion con el rayo y el spatial dados por
	 * parametro, ya sea en cordenadas locales o globales. La interseccion resultante
	 * se almacena en un <code>Vector3f</code>
     * @param ray El <code>Ray</code> contra el cual se checkea.
     * @param parent El <code>Spatial</code> padre contra el cual se checkea.
     * @param local verdadero si la interseccion debe convertirse al sistemas de cordenadas local del padre.
     * @return Si hay colision, el <code>Vector3f</code> de la interseccion es retornado. Caso contrario <code>null</code> es retornado.
     */	
	public Vector3f getIntersection(Ray ray, Spatial parent, boolean local) {
		//Intersection vector
		Vector3f store = new Vector3f();
        TrianglePickResults results = new TrianglePickResults();
        results.setCheckDistance(true);
        Vector3f[] vertices = new Vector3f[3];
        //Tests a ray against this spatial, and stores the results in the results.
        parent.findPick(ray, results);
        boolean hit = false;
        if (results.getNumber() > 0) {
            PickData data = results.getPickData(0);
            ArrayList<Integer> triangles = data.getTargetTris();
            if (!triangles.isEmpty()) {
                TriMesh mesh = (TriMesh) data.getTargetMesh();
                mesh.getTriangle(triangles.get(0).intValue(), vertices);
                for (int j = 0; j < vertices.length; j++) {
                	mesh.localToWorld(vertices[j], vertices[j]);
                }
                //verify intersection
                hit = ray.intersectWhere(vertices[0], vertices[1], vertices[2], store);
                if (hit && local) {
                	//intersection found
                    parent.worldToLocal(store, store);
                    return store;
                } else if (hit && !local) {
                	//intersection found
                    return store;
                }
            }
        }
      //intersection found
        return null;
    }
	/**
	 * Retorna el <code>Spatial</code> con el cual colisiona el rayo <code>Ray</code>
	 * utilizando un <code>Spatial</code> de referencia
     * @param ray El <code>Ray</code> contra el cual se checkea.
	 * @param root El <code>Node</code> raiz.
     * @param reference El <code>Spatial</code> de referencia.
     * @param iterate verdadero si se desea iterar en busca del resultado
     * @return Si hay colision, el <code>Spatial</code> de la interseccion es retornado. Caso contrario <code>null</code> es retornado.
     */	
	public Spatial getIntersectObject(Ray ray, Node root, Class<? extends Spatial> reference, boolean iterate) {
        PickResults results = new TrianglePickResults();
        results.setCheckDistance(true);
        root.findPick(ray, results);
        if (iterate) {
            for (int i = 0; i < results.getNumber(); i++) {
            	if(results.getPickData(i).getDistance()<=10)
            	{
	            	Spatial collision = this.validateClass(root, results.getPickData(i).getTargetMesh(), reference);
	                if (collision != null) 
	                	return collision;
            	}
            }
        } else if (results.getNumber() > 0) {
            return this.validateClass(root, results.getPickData(0).getTargetMesh(), reference);
        }
        return null;
    }
	/**
	 * Valida si el <code>Spatial</code> de referencia corresponde a una clase valida
	 * para el calculo de colision.
	 * @param root El <code>Node</code> raiz.
     * @param reference El <code>Spatial</code> de referencia.
     * @param spatial El <code>Spatial</code> contra el que se compara
     * @return Si es valido, el <code>Spatial</code> valido encontrado. Caso contrario <code>null</code> es retornado.
     */	
	private Spatial validateClass(Node root, Spatial spatial, Class<? extends Spatial> reference) {
        if (spatial.getClass().equals(reference)) {
            return spatial;
        } else {
            while (spatial.getParent() != null) {
                spatial = spatial.getParent();
                if (spatial == root) {
                    return null; // TODO Should throw an exception here saying reached parent.
                } else if (spatial.getClass().equals(reference)) {
                    return spatial;
                }
            }
        // TODO Should throw an exception here saying that cannot find the referencing class.
        }
        return null;
    }

	/**
     * Obtiene el punto destino valido basandose en el valor de las coordenadas 
	 * pasado por parametro
     * @param origin <code>Vector3f</code> El punto de origen.
     * @param destination <code>Vector3f</code> La posicion final.
     * Estas cordenadas son locales.
     * @param spatial El <code>Spatial</code> contra el que se checkea.
     * @return La posicion destino <code>Vector3f</code> valida.
     */
	public Vector3f getDestination(Vector3f origin, Vector3f destination, Spatial spatial){

		System.out.println("POSICION ACTUAL!!!!: " + origin);
		//convert start point to world coordinate system
        spatial.localToWorld(origin, origin);
        //convert destination point to world coordinate system
        spatial.localToWorld(destination, destination);
        
        System.out.println("POSICION ACTUAL!!!!: RELOCATE:  " + origin);
        
        //build the direction Vector3f
        Vector3f direction = destination.subtract(origin).normalizeLocal();
       
        
        Vector3f directionL = new Vector3f(-direction.z,direction.y,direction.x).normalize();
        Vector3f directionL2 = direction.add(directionL).normalize();
        Vector3f directionR = new Vector3f(direction.z,direction.y,-direction.x).normalize();
        Vector3f directionR2 = direction.add(directionR).normalize(); 
        
        Vector<Vector3f> hitPoints = new Vector<Vector3f>();
        
        hitPoints.add(getIntersection(new Ray(origin, direction),spatial, false));        	
        hitPoints.add(getIntersection(new Ray(origin, directionL),spatial, false));
        hitPoints.add(getIntersection(new Ray(origin, directionL2),spatial, false));
        hitPoints.add(getIntersection(new Ray(origin, directionR),spatial, false));
        hitPoints.add(getIntersection(new Ray(origin, directionR2),spatial, false));
        
        Vector3f origin2 = new Vector3f(origin.x,origin.y + 0.9f,origin.z);        
        Vector3f destination2 = new Vector3f(destination.x,destination.y + 0.9f,destination.z);
        
        //build the direction2 Vector3f
        Vector3f direction2 = destination2.subtract(origin2).normalizeLocal();
       
        // 5 rays was added in order to handle collisions at heights different
        Vector3f direction2L = new Vector3f(-direction2.z,direction2.y,direction2.x).normalize();
        Vector3f direction2L2 = direction2.add(direction2L).normalize();
        Vector3f direction2R = new Vector3f(direction2.z,direction2.y,-direction2.x).normalize();
        Vector3f direction2R2 = direction2.add(direction2R).normalize(); 
        
        hitPoints.add(getIntersection(new Ray(origin2, direction2),spatial, false));        	
        hitPoints.add(getIntersection(new Ray(origin2, direction2L),spatial, false));
        hitPoints.add(getIntersection(new Ray(origin2, direction2L2),spatial, false));
        hitPoints.add(getIntersection(new Ray(origin2, direction2R),spatial, false));
        hitPoints.add(getIntersection(new Ray(origin2, direction2R2),spatial, false));
        
        for (int i= 5;i<10;i++)
         	if (hitPoints.get(i) != null)
         		if (hitPoints.get(i).distance(destination2) < 5f)
          			return origin;
        
        

       for (int i= 0;i<5;i++)
          	if (hitPoints.get(i) != null)
          		if (hitPoints.get(i).distance(destination) < 5f)
           			return origin;
       return destination;
	}
}