package stantz.ray;

import stantz.ray.math.Referential;
import stantz.ray.math.Vector2D;
import stantz.ray.math.Vector3D;

public class Screen {

  public Vector3D eye;

  public Vector3D center;
  public Vector3D x;
  public Vector3D y;

  public int widthInPixels;
  public int heightInPixels;

  // plan
  public Vector3D n;
  public double d;

  public Referential referential;

  public double width;
  public double height;
  public double pixelWidth;
  public double pixelHeight;

  final Vector3D topLeftCorner;


  public Screen(Vector3D eye, Vector3D screenCenter, Vector3D screenX, Vector3D screenY, int widthInPixels, int heightInPixels) {
    super();
    this.eye = eye;
    this.center = screenCenter;
    this.x = screenX;
    this.y = screenY;
    this.widthInPixels = widthInPixels;
    this.heightInPixels = heightInPixels;
    // plan
    n = x.cross(y);
    d = -center.scalar(n);

    width = x.norm();
    height = y.norm();

    pixelWidth = width/widthInPixels;
    pixelHeight = height/heightInPixels;

    referential = new Referential(x.normalize(), y.normalize(), n.normalize());

    topLeftCorner = center.plusMultiplied(x,-0.5).bangplusMultiplied(y,-0.5);
  }

  public Vector2D project(Vector3D point) {
    // intersection with line
    Vector3D eyeToPoint = point.minus(eye);
    double t = (-d-eye.scalar(n))/(eyeToPoint.scalar(n));
    Vector3D intersection = eyeToPoint.bangmultiply(t).bangplus(eye);

    Vector2D intersection2D = new Vector2D((referential.i.scalar(intersection)+width/2)/pixelWidth, (referential.j.scalar(intersection)+height/2)/pixelHeight);

    return intersection2D;
  }

  public Ray generateRay(Vector2D pixel) {
    Vector3D toPoint = toSpace(pixel);
    Vector3D fromDirection = toPoint.minus(eye).bangnormalize();
    return new Ray(toPoint, fromDirection, 3);
  }

  public Vector3D toSpace(Vector2D pixel) {
    return topLeftCorner.plusMultiplied(referential.i, pixel.x*pixelWidth).bangplusMultiplied(referential.j, pixel.y*pixelHeight);
  }

}
