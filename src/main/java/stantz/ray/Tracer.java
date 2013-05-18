package stantz.ray;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import stantz.ray.math.Box2D;
import stantz.ray.math.Vector2D;
import stantz.ray.math.Vector3D;



public class Tracer {

  public static class CachedVisible implements Visible {
    Visible object;
    Box2D boundingBox;

    private CachedVisible(Visible object, Box2D boundingBox) {
      this.object = object;
      this.boundingBox = boundingBox;
    }

    public Box2D getBoundingBox(Screen screen) {
      return object.getBoundingBox(screen);
    }

    public Incidence intersection(Ray ray) {
      return object.intersection(ray);
    }

    public Box2D getBoundingBox() {
      return boundingBox;
    }
  }

  private static ExecutorService executorService;
  static {
    int availableProcessors = Runtime.getRuntime().availableProcessors();
    System.out.println(availableProcessors+" available processors, creating the same number of threads");
    executorService = Executors.newFixedThreadPool(availableProcessors);
  }

  Scene scene;
  public List<CachedVisible> cacheObjects;
  public Visible objectAtMouse = null;

  public Tracer(Scene scene) {
    super();
    this.scene = scene;
  }

  public Color[][] trace(Vector2D mouse) {

    //		long t0 = System.currentTimeMillis();
    cacheObjects = cache(scene.objects);
    //		long t1 = System.currentTimeMillis();
    Color[][] result = new Color[scene.screen.widthInPixels][];

    Ray ray = scene.screen.generateRay(mouse);
    Incidence nearestObject = nearestObject(ray, mouse);
    if (nearestObject!=null) {
      objectAtMouse = nearestObject.object;
    }

    //		long t2 = System.currentTimeMillis();
    Future<Color[]>[] futures = new Future[scene.screen.widthInPixels];
    for (int i = 0; i < scene.screen.widthInPixels; i++) {
      final int ii = i;
      futures[i] = executorService.submit(new Callable<Color[]>() {

        public Color[] call() throws Exception {
          Color[] line = new Color[scene.screen.heightInPixels];
          Vector2D pixel = new Vector2D(0,0);
          for (int j = 0; j < scene.screen.heightInPixels; j++) {
            pixel.bangupdate(ii,j);
            Ray ray = scene.screen.generateRay(pixel);
            Color c = trace(ray, pixel);
            line[j]=c;
          }
          return line;
        }

      });
    }
    //		long t3 = System.currentTimeMillis();
    for (int i = 0; i < scene.screen.widthInPixels; i++) {
      try {
        result[i] = futures[i].get();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      } catch (ExecutionException e) {
        throw new RuntimeException(e);
      }
    }
    //		long t4 = System.currentTimeMillis();

    //System.out.println("  trace: "+(t4-t0)+" = "+(t1-t0)+" + "+(t2-t1)+" + "+(t3-t2)+" + "+(t4-t3));
    return result;
  }

  public Color trace(Ray ray, Vector2D screenPoint) {
    if (ray.ttl==0) {
      return scene.background;
    }
    Incidence nearestObject = nearestObject(ray, screenPoint);
    if (nearestObject == null) {
      return scene.background;
    } else {
      Vector3D reflectionDirection = nearestObject.normal.multiply(2*nearestObject.normal.scalar(ray.minusFromDirection)).bangminus(ray.minusFromDirection).bangmultiply(-1).bangnormalize();
      Vector3D intersection = ray.minusFromDirection.multiply(nearestObject.distance).bangplus(ray.toPoint);
      Ray reflected = ray.commingFrom(intersection, reflectionDirection);
      Color reflectedColor = trace(reflected, null);

      //			Vector refractedDirection = l.minus(normal).bangmultiply(-0.1).bangplus(l).bangnormalize();
      //			Ray refracted = ray.commingFrom(intersection, refractedDirection);
      //			Color refractedColor = trace(refracted).dim(0.6f);

      Color diffusedColor = new Color(Color.black);
      for (LightSource lightSource : scene.lightSources) {
        Vector3D lightSourceDirection = lightSource.position.minus(intersection).bangmultiply(-1).bangnormalize();
        if (nearestObject(ray.commingFrom(intersection, lightSourceDirection), null)==null) {
          float coef = (float)positive(lightSourceDirection.scalar(reflectionDirection));
          diffusedColor = diffusedColor.bangaddDimmed(lightSource.color,coef);
        }
      }

      Color result = diffusedColor.bangadd(reflectedColor).bangadd(scene.ambientColor)./*bangadd(refractedColor).*/bangfilter(nearestObject.color);
      if (nearestObject.object == objectAtMouse) {
        result.bangInvert();
      }
      return result;
    }

  }

  private double positive(double scalar) {

    return scalar<0?0:scalar;
  }

  public Incidence nearestObject(Ray ray, Vector2D screenPoint) {
    Incidence nearestObject = null;
    for (CachedVisible object : cacheObjects) {
      Box2D boundingBox = object.getBoundingBox();
      if (screenPoint==null || boundingBox.contains(screenPoint)) {
        Incidence d = object.intersection(ray);
        if (d!=null && (nearestObject == null || d.distance<nearestObject.distance)) {
          nearestObject = d;
        }
      }
    }
    return nearestObject;
  }

  public List<CachedVisible> cache(List<Visible> nonCacheObjects) {
    List<CachedVisible> cacheObjects = new ArrayList<CachedVisible>(nonCacheObjects.size());
    for (Visible object : nonCacheObjects) {
      Box2D boundingBox = object.getBoundingBox(scene.screen);
      cacheObjects.add(new CachedVisible(object, boundingBox));
    }
    return cacheObjects;
  }

}
