package stantz.ray.object;

import stantz.ray.Color;
import stantz.ray.Incidence;
import stantz.ray.Ray;
import stantz.ray.Screen;
import stantz.ray.Visible;
import stantz.ray.math.Box2D;
import stantz.ray.math.Vector2D;
import stantz.ray.math.Vector3D;

public class Sphere implements Visible {

  Vector3D center;

  double radius;

  Color color;

  public Sphere(Vector3D center, double radius, Color color) {
    //super();
    this.center = center;
    this.radius = radius;
    this.color = color;
  }

  public Incidence intersection(Ray ray) {
    double d;
    // ray destination referential
    Vector3D c = center.minus(ray.toPoint);
    Vector3D l = ray.minusFromDirection;
    double r = radius;
    double lc = l.scalar(c);
    double discriminant = lc*lc - c.scalar(c) + r*r;
    if (discriminant<0) {
      return null;
    } else {
      double sqrt = Math.sqrt(discriminant);
      // we want the smallest d positive
      d = lc - sqrt;
      if (d<=0.01) { // must be "positive" with computation error
        d = lc + sqrt;
      }
      if (d<=0.01) {
        return null;
      }
      Vector3D intersection = l.multiply(d).bangplus(ray.toPoint);
      Vector3D normal = intersection.minus(center).bangnormalize();

      return new Incidence(d,normal,color, this);
    }

  }

  public Box2D getBoundingBox(Screen screen) {
    Vector3D topLeftF = center.plus(screen.referential.bangToAbsolute(new Vector3D(-radius, -radius, -radius)));
    Vector3D bottomRightF = center.plus(screen.referential.bangToAbsolute(new Vector3D(radius, radius, -radius)));
    Vector3D topLeftB = center.plus(screen.referential.bangToAbsolute(new Vector3D(-radius, -radius, radius)));
    Vector3D bottomRightB =  center.plus(screen.referential.bangToAbsolute(new Vector3D(radius, radius, radius)));

    Vector2D topLeft2DF = screen.project(topLeftF);
    Vector2D bottomRight2DF = screen.project(bottomRightF);
    Vector2D topLeft2DB = screen.project(topLeftB);
    Vector2D bottomRight2DB = screen.project(bottomRightB);

    Vector2D topLeft = topLeft2DB.bangMinPerCoordinate(topLeft2DF);
    Vector2D bottomRigth = bottomRight2DB.bangMaxPerCoordinate(bottomRight2DF);
    return new Box2D(topLeft, bottomRigth);
  }


  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  @Override
  public String toString() {
    return "Sphere: "+center+" "+radius;
  }

}
