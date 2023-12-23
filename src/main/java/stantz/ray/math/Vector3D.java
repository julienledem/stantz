package stantz.ray.math;

public final class Vector3D {

  public static final Vector3D ZERO = new Vector3D(0,0,0);

  protected final double x;
  protected final double y;
  protected final double z;

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
    return new Vector3D(x / norm, y / norm, z / norm);
  }

  public double scalar(Vector3D v) {
    return v.x * x + v.y * y + v.z * z;
  }

  public Vector3D minus(Vector3D v) {
    return new Vector3D(x - v.x, y - v.y, z - v.z);
  }

  public Vector3D plus(Vector3D v) {
    return new Vector3D(x + v.x, y + v.y, z + v.z);
  }

  public Vector3D multiply(double scalar) {
    return new Vector3D( x * scalar, y * scalar, z * scalar);
  }

  public Vector3D cross(Vector3D v) {
    return new Vector3D(
        y * v.z - z * v.y,
        z * v.x - x * v.z,
        x * v.y - y * v.x
        );
  }
  
  @Override
  public String toString() {
    return "("+x+", "+y+", "+z+")";
  }

}
