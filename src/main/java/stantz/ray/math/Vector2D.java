package stantz.ray.math;

public final class Vector2D {

  public final double x;
  public final double y;

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

  public Vector2D minPerCoordinate(Vector2D v) {
    double minx = Math.min(x, v.x);
    double miny = Math.min(y, v.y);
    return new Vector2D(minx, miny);
  }

  public Vector2D maxPerCoordinate(Vector2D v) {
	double maxx = Math.max(x, v.x);
	double maxy = Math.max(y, v.y);
	return new Vector2D(maxx, maxy);
  }


}
