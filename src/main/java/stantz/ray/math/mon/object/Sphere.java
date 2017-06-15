package stantz.ray.math.mon.object;

import stantz.ray.Color;
import stantz.ray.Incidence;
import stantz.ray.Screen;
import stantz.ray.math.Box2D;
import stantz.ray.math.Vector2D;
import stantz.ray.math.Vector3D;
import stantz.ray.math.mon.Ray;
import stantz.ray.math.mon.Scalar;
import stantz.ray.math.mon.Vector;

public class Sphere {

  Vector center;

  Scalar radius;

  Color color;

  public Sphere(Vector center, Scalar radius, Color color) {
    //super();
    this.center = center;
    this.radius = radius;
    this.color = color;
  }

  public Incidence intersection(Ray ray) {
    // ray destination referential
    Vector c = center.minus(ray.toPoint);
    Vector l = ray.minusFromDirection;
    Scalar r = radius;
    Scalar lc = l.scalar(c);
    Scalar discriminant = lc.mul(lc).minus(c.scalar(c)).plus(r.mul(r));
    if (discriminant<0) {
      return null;
    } else {
      Scalar sqrt = discriminant.sqrt();
      // we want the smallest d positive
      Scalar d;
      d = lc.minus(sqrt);
      if (d<=0.01) { // must be "positive" with computation error
        d = lc + sqrt;
      }
      if (d<=0.01) {
        return null;
      }
      Vector intersection = l.multiply(d).plus(ray.toPoint);
      Vector normal = intersection.minus(center).normalize();

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
