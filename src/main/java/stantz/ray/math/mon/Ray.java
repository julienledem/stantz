package stantz.ray.math.mon;

public class Ray {

  public final Vector toPoint;

  public final Vector minusFromDirection;

  public Ray(Vector toPoint, Vector fromDirection) {
    super();
    this.toPoint = toPoint;
    this.minusFromDirection = fromDirection;
  }

  @Override
  public String toString() {
    return "toPoint: "+toPoint+" fromDir: "+minusFromDirection;
  }

}
