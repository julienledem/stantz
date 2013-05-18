package stantz.ray.math;

public class Vector2D {

  public double x;
  public double y;

  public Vector2D(double x, double y) {
    super();
    this.x = x;
    this.y = y;
  }

  public Vector2D minus(Vector2D v) {
    return new Vector2D(x-v.x,y-v.y);
  }

  public Vector2D plus(Vector2D v) {
    return new Vector2D(x+v.x,y+v.y);
  }

  public Vector2D bangMinPerCoordinate(Vector2D v) {
    this.x = x < v.x ? x : v.x;
    this.y = y < v.y ? y : v.y;
    return this;
  }

  public Vector2D bangMaxPerCoordinate(Vector2D v) {
    this.x = x > v.x ? x : v.x;
    this.y = y > v.y ? y : v.y;
    return this;
  }

  public Vector2D bangupdate(double x, double y) {
    this.x = x;
    this.y = y;
    return this;
  }

}
