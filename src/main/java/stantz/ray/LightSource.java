package stantz.ray;

import stantz.ray.math.Vector3D;


public final class LightSource {

  public final Color color;
  public final Vector3D position;

  public LightSource(Color color, Vector3D position) {
    super();
    this.color = color;
    this.position = position;
  }

}
