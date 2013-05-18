package stantz.ray.math;

public class Vector3D {

  public static final Vector3D ZERO = new Vector3D(0,0,0);

  protected double x;
  protected double y;
  protected double z;

  public Vector3D(double x, double y, double z) {
    super();
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public double norm() {
    return Math.sqrt(scalar(this));
  }

  public Vector3D normalize() {
    double norm = norm();
    return new Vector3D(x/norm,y/norm,z/norm);
  }

  public double scalar(Vector3D v) {
    return v.x*x+v.y*y+v.z*z;
  }

  public Vector3D minus(Vector3D v) {
    return new Vector3D(x-v.x,y-v.y,z-v.z);
  }

  public Vector3D plus(Vector3D v) {
    return new Vector3D(x+v.x,y+v.y,z+v.z);
  }

  public Vector3D multiply(double scalar) {
    return new Vector3D(x*scalar,y*scalar,z*scalar);
  }

  @Override
  public String toString() {
    return "("+x+", "+y+", "+z+")";
  }

  public Vector3D bangplus(Vector3D v) {
    x += v.x;
    y += v.y;
    z += v.z;
    return this;
  }

  public Vector3D bangplusMultiplied(Vector3D v, double scalar) {
    x += v.x * scalar;
    y += v.y * scalar;
    z += v.z * scalar;
    return this;
  }

  public Vector3D bangnormalize() {
    double norm = norm();
    x = x/norm;
    y = y/norm;
    z = z/norm;
    return this;
  }

  public Vector3D bangmultiply(double scalar) {
    x = x * scalar;
    y = y * scalar;
    z = z * scalar;
    return this;
  }

  public Vector3D bangminus(Vector3D v) {
    x -= v.x;
    y -= v.y;
    z -= v.z;
    return this;
  }

  public Vector3D cross(Vector3D v) {
    return new Vector3D(
        y*v.z-z*v.y,
        z*v.x-x*v.z,
        x*v.y-y*v.x
        );
  }

  public Vector3D plusMultiplied(Vector3D v, double scalar) {
    return new Vector3D(x + v.x * scalar,y + v.y * scalar,z + v.z * scalar);
  }
}
