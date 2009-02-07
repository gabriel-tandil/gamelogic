/**
 *<code>CollisionManager</code> is responsible
 * for processing all collision detection tasks.
 */
package client.manager;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

import com.jme.intersection.CollisionResults;
import com.jme.intersection.PickData;
import com.jme.intersection.PickResults;
import com.jme.intersection.TrianglePickResults;
import com.jme.math.Ray;
import com.jme.math.Vector3f;
import com.jme.scene.Node;
import com.jme.scene.Spatial;
import com.jme.scene.TriMesh;

import client.game.state.IAccessPoint;
import client.game.state.AccessPoint;

/** 
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
	
	public void addAccessPoint(String id, IAccessPoint ap)
	{
		iaccesspoint.put(id, ap);
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
			Node abuelo = node.getParent().getParent();
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
            	if(results.getPickData(i).getDistance()<=2)
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
        //generate Ray for intersection detection
        Ray moveRay = new Ray(origin, direction);
        
        //calculate the intersection between the move ray and the spatial
        Vector3f hitPoint = getIntersection(moveRay, spatial, false);
        
        //if there are no obstacles, return the destination directly
        if(hitPoint == null) {
        	//if there are no hit point, return the destination directly
            spatial.worldToLocal(destination, destination);
            return destination;
        }
        else {
        	//if there are hit point, calcule the new destination
            Vector3f newDestination = hitPoint.subtractLocal(direction);
            spatial.worldToLocal(newDestination, newDestination);
            return newDestination;
        }
	}
}