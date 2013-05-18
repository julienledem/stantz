package stantz.ray;

import stantz.ray.math.Vector3D;


public final class Incidence {

  public final double distance;

  public final Vector3D normal;

  public final Color color;

  public final Visible object;

  public Incidence(double distance, Vector3D normal, Color color, Visible object) {
    super();
    this.distance = distance;
    this.normal = normal;
    this.color = color;
    this.object = object;
  }

}
