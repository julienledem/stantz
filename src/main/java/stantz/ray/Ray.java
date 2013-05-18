package stantz.ray;

import stantz.ray.math.Vector3D;


public class Ray {

  public final Vector3D toPoint;

  public final Vector3D minusFromDirection;

  int ttl;

  public Ray(Vector3D toPoint, Vector3D fromDirection, int ttl) {
    super();
    this.toPoint = toPoint;
    this.minusFromDirection = fromDirection;
    this.ttl = ttl;
  }


  public Ray commingFrom(Vector3D intersection, Vector3D from) {
    return new Ray(intersection, from, ttl-1);
  }

  @Override
  public String toString() {
    return "toPoint: "+toPoint+" fromDir: "+minusFromDirection;
  }

}
