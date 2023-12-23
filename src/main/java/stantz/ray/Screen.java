package stantz.ray;

import stantz.ray.math.Line;
import stantz.ray.math.Referential;
import stantz.ray.math.Vector2D;
import stantz.ray.math.Vector3D;

public final class Screen {

  public final Vector3D eye;

  public final Vector3D center;
  public final Vector3D x;
  public final Vector3D y;

  public final int widthInPixels;
  public final int heightInPixels;

  public final Referential referential;

  public final double width;
  public final double height;
  public final double pixelWidth;
  public final double pixelHeight;

  final Vector3D topLeftCorner;


  public Screen(Vector3D eye, Vector3D screenCenter, Vector3D screenX, Vector3D screenY, int widthInPixels, int heightInPixels) {
    super();
    this.eye = eye;
    this.center = screenCenter;
    
    this.x = screenX;
    this.y = screenY;
    Vector3D z = x.cross(y);
    
    this.widthInPixels = widthInPixels;
    this.heightInPixels = heightInPixels;
    
    // plan

    width = x.norm();
    height = y.norm();

    pixelWidth = width / widthInPixels;
    pixelHeight = height / heightInPixels;

    referential = new Referential(x.normalize(), y.normalize(), z.normalize());

    topLeftCorner = center.plus(x.multiply(-0.5)).plus(y.multiply(-0.5));
  }

  public Vector2D project(Vector3D point) {
	Line toPoint = Line.fromTwoPointsOriginOnFirst(eye, point);
	Vector3D intersection = toPoint.intersectionWithPlan(center, referential.k);

    Vector2D intersection2D = new Vector2D(
    		( referential.i.scalar(intersection) + width / 2 ) / pixelWidth, 
    		( referential.j.scalar(intersection) + height / 2 ) / pixelHeight
    		);

    return intersection2D;
  }

  public Ray generateRay(Vector2D pixel) {
    Vector3D origin = toSpace(pixel);
    return new Ray(Line.fromTwoPointsOriginOnSecond(eye, origin), 3);
  }

  public Vector3D toSpace(Vector2D pixel) {
    return topLeftCorner
    		.plus(referential.i.multiply(pixel.x * pixelWidth))
    		.plus(referential.j.multiply(pixel.y * pixelHeight));
  }

}
