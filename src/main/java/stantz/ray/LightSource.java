package stantz.ray;

import stantz.ray.math.Vector3D;


public class LightSource {

  public Color color;
  public Vector3D position;

  public LightSource(Color color, Vector3D position) {
    super();
    this.color = color;
    this.position = position;
  }

}
