/**
 *<code>CollisionManager</code> Es responsable
 * de procesar todas las tareas de detecci�n de colisiones
 */
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
  * Es responsable de procesar todas las tareas de detecci�n de colisiones
 * @author Santiago Michielotto
 * @version Created: 19-11-2008
 */
public class CollisionManager {

	/**
	 * The <code>CollisionManager</code> instance.
	 */
	protected static CollisionManager instance;
	
	/** 
	 * <code>iaccesspoint</code> is a Set of access point of the model.
	 * A access point can be a door, a window or other object generator of a task.
	 * The task can be enter to the isistan for example.
	 */
	private Hashtable<String,IAccessPoint> iaccesspoint;
	/** 
	 * Retrieve the <code>iaccesspoint</code> Set of the model. 
	 * @return the Access point Set of the model. 
	 */
	public Hashtable<String,IAccessPoint> getIaccesspoint() {
		return iaccesspoint;
	}

	public void init()
	{
		iaccesspoint=new Hashtable<String,IAccessPoint>();
	}
	/**
	 * Add Access Points to the Hashtable
	 * @param id
	 * @param ap
	 */
	
	
	public void addAccessPoint(String id, IAccessPoint ap)
	{
		iaccesspoint.put(id, ap);
	}
	
	/**
	 * Remove all the Access Point than exist in the Hashtable
	 */
	
	public void removeAccessPoints()
	{
		iaccesspoint.clear();
	}
	
	/** 
	 * Apply a <code>Set</code> iaccesspoint to this <code>CollisionManager</code>.
	 * @param theIaccesspoint <code>Set<code> iaccesspoint to aplly
	 */
	public void setIaccesspoint(Hashtable<String,IAccessPoint> theIaccesspoint) {
		iaccesspoint = theIaccesspoint;
	}

	/**
     * Retrieve the <code>CollisionManager</code> instance.
     * @return The <code>CollisionManager</code> instance.
     */
	public static CollisionManager getInstace() {
		if (CollisionManager.instance == null) {
            CollisionManager.instance = new CollisionManager();
        }
        return CollisionManager.instance;
	}

	/** 
	 * Retrieve the collision AccessPoint if exist a collision or null if not exists.
	 * @return the collision AccessPoint if exist a collision or null if not exists.
	 */
	public boolean checkOverAccessPoint(Node node) {
		Enumeration e = iaccesspoint.elements();
		boolean cond = false; 
		AccessPoint temp=null;
		while( e. hasMoreElements() && !cond)
		{
			temp=(AccessPoint)e.nextElement();
			//Node abuelo = node.getParent().getParent();
			Node abuelo = node;
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
     * Retrieve the intersection point with the given ray and spatial in either
     * world coordinate system or local coordinate system of the given spatial
     * based on the given flag value. The intersection result is stored in the
     * given vector and returned. If the given store is null, a new vector instance
     * is created and returned with the intersection result.
     * @param ray The <code>Ray</code> to check with.
     * @param parent The parent <code>Spatial</code> to check against.
     * @param local True if the intersection should be converted to local coordinate system of the parent.
     * @return If hit, the <code>Vector3f</code> intersection is returned. Otherwise <code>null</code> is returned.
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
     * Retrieve the valid destination point based on the given coordinate values.
     * @param origin the starting position.
     * @param destination the end position.
     * This coordinates are locals.
     * @param spatial The <code>Spatial</code> instance to check against.
     * @return 
     * @return The valid <code>Vector3f</code> destination.
     */
	public Vector3f getDestination(Vector3f origin, Vector3f destination, Spatial spatial){
		
		
		//convert start point to world coordinate system
        spatial.localToWorld(origin, origin);
        //convert destination point to world coordinate system
        spatial.localToWorld(destination, destination);
        
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