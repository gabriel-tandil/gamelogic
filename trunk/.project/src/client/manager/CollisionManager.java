/**
 *<code>CollisionManager</code> is responsible
 * for processing all collision detection tasks.
 */
package client.manager;

import java.util.Set;
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
	private HashTable<Integer,IAccessPoint> iaccesspoint;
	/** 
	 * Retrieve the <code>iaccesspoint</code> Set of the model. 
	 * @return the Access point Set of the model. 
	 */
	public HashTable<Integer,IAccessPoint> getIaccesspoint() {
		return iaccesspoint;
	}

	/** 
	 * Apply a <code>Set</code> iaccesspoint to this <code>CollisionManager</code>.
	 * @param theIaccesspoint <code>Set<code> iaccesspoint to aplly
	 */
	public void setIaccesspoint(HashTable<Integer,IAccessPoint> theIaccesspoint) {
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
	public AccessPoint checkOverAccessPoint() {
		for(IAccessPoint accesspoint : this.iaccesspoint) 
		{
			//chequeo
		}
		return null;
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

	/**
     * Retrieve the valid destination point based on the given coordinate values.
     * @param xInicio The x coordinate of the starting position.
     * @param yInicio The y coordinate of the starting position.
     * @param zInicio The z coordinate of the starting position.
     * @param xFin The x coordinate of the clicking position.
     * @param yFin The y coordinate of the starting position.
     * @param zFin The z coordinate of the clicking position.
     * This coordinates are locals.
     * @param spatial The <code>Spatial</code> instance to check against.
     * @return 
     * @return The valid <code>Vector3f</code> destination.
     */
	public Vector3f getDestination(float xInicio, float yInicio, float zInicio, float xFin, float yFin, float zFin, Spatial spatial){
		//start point
		Vector3f start= new Vector3f(xInicio,yInicio,zInicio);
        //destination point
        Vector3f destination = new Vector3f(xFin, yFin, xFin);
        //convert start point to world coordinate system
        spatial.localToWorld(start, start);
        //convert destination point to world coordinate system
        spatial.localToWorld(destination, destination);
        
        //build the direction Vector3f
        Vector3f direction = destination.subtract(start).normalizeLocal();
        //generate Ray for intersection detection
        Ray moveRay = new Ray(start, direction);
        
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