package stantz.ray.object;

import stantz.ray.Color;
import stantz.ray.Incidence;
import stantz.ray.Ray;
import stantz.ray.Screen;
import stantz.ray.Visible;
import stantz.ray.math.Box2D;
import stantz.ray.math.Line;
import stantz.ray.math.Vector2D;
import stantz.ray.math.Vector3D;

public final class Sphere implements Visible {

  final Vector3D center;

  final double radius;

  final Color color;

  public Sphere(Vector3D center, double radius, Color color) {
    this.center = center;
    this.radius = radius;
    this.color = color;
  }

  public Incidence intersection(Ray ray) {
    double d;
    Vector3D o_minus_c = ray.line.origin.minus(center);
    double uomc = ray.line.direction.scalar(o_minus_c);
    double discriminant = uomc * uomc - (o_minus_c.scalar(o_minus_c) - radius * radius);
    if (discriminant < 0) {
      return null;
    } else {
      double sqrt_d = Math.sqrt(discriminant);
      // we want the smallest d positive
      d = - uomc - sqrt_d;
//      if (d < 0) {
       // the inside is invisible because we sometime start from the inside because of rounding error
//    	  d = - uomc + sqrt_d;
//      }
      if (d < 0) {
        return null;
      }
      Vector3D intersection = ray.line.pointOnLine(d);
      Vector3D normal = intersection.minus(center).normalize();

      return new Incidence(d, normal, color, this);
    }

  }

  public Box2D getBoundingBox(Screen screen) {
    Vector3D topLeftF = new Vector3D(-radius, -radius, -radius).plus(center);
	Vector3D bottomRightF = new Vector3D(radius, radius, -radius).plus(center);
	Vector3D topLeftB = new Vector3D(-radius, -radius, radius).plus(center);
	Vector3D bottomRightB = new Vector3D(radius, radius, radius).plus(center);
	  
    Vector2D topLeft2DF = screen.project(topLeftF);
    Vector2D bottomRight2DF = screen.project(bottomRightF);
    Vector2D topLeft2DB = screen.project(topLeftB);
    Vector2D bottomRight2DB = screen.project(bottomRightB);

    Vector2D topLeft = topLeft2DB.minPerCoordinate(topLeft2DF);
    Vector2D bottomRigth = bottomRight2DB.maxPerCoordinate(bottomRight2DF);
    return new Box2D(topLeft, bottomRigth);
  }


  public Color getColor() {
    return color;
  }

  @Override
  public String toString() {
    return "Sphere: "+center+" "+radius;
  }

}
