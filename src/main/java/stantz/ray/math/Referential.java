package stantz.ray.math;

public class Referential {

  public Vector3D i;
  public Vector3D j;
  public Vector3D k;

  public Referential(Vector3D i, Vector3D j, Vector3D k) {
    super();
    this.i = i;
    this.j = j;
    this.k = k;
  }

  public Vector3D toAbsolute(Vector3D v) {
    return v.plusMultiplied(i, v.x).bangplusMultiplied(j, v.y).bangplusMultiplied(k, v.z);
  }

  public Vector3D bangToAbsolute(Vector3D v) {
    return v.bangplusMultiplied(i, v.x).bangplusMultiplied(j, v.y).bangplusMultiplied(k, v.z);
  }

  public Vector3D fromAbsolute(Vector3D v) {
    return new Vector3D(i.scalar(v),j.scalar(v),k.scalar(v));
  }

}
