package stantz.ray.math;

public class Line {

	public final Vector3D origin;
	public final Vector3D direction;
	
	public Line(Vector3D origin, Vector3D direction) {
		super();
		this.origin = origin;
		this.direction = direction;
	}
	
	public Vector3D intersectionWithPlan(Vector3D point, Vector3D normal) {
		double d = (point.minus(origin)).scalar(normal) / direction.scalar(normal);
	    return pointOnLine(d);
	}

	public Vector3D pointOnLine(double distanceFromOrigin) {
		return origin.plus(direction.multiply(distanceFromOrigin));
	}
	
	public static Line fromTwoPointsOriginOnFirst(Vector3D origin, Vector3D other) {
		return new Line(origin, other.minus(origin).normalize());
	}
	
	public static Line fromTwoPointsOriginOnSecond(Vector3D other, Vector3D origin) {
		return new Line(origin, origin.minus(other).normalize());
	}
	
	
}
