package stantz.ray.math;

public class Box2D {

  public Vector2D topLeft;
  public Vector2D bottomRight;

  public Box2D(Vector2D topLeft, Vector2D bottomRight) {
    super();
    this.topLeft = topLeft;
    this.bottomRight = bottomRight;
  }

  public boolean contains(Vector2D point) {
    return inBetween(point.x, topLeft.x, bottomRight.x) && inBetween(point.y, topLeft.y, bottomRight.y);
  }

  private boolean inBetween(double x, double a, double b) {
    return x>a && x<b;
  }


}
