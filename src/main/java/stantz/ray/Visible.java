package stantz.ray;

import stantz.ray.math.Box2D;

public interface Visible {

  Incidence intersection(Ray ray);

  Box2D getBoundingBox(Screen screen);

}
