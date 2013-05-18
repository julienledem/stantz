package stantz.ray.demo;

import java.util.ArrayList;
import java.util.List;

import stantz.ray.Color;
import stantz.ray.LightSource;
import stantz.ray.Scene;
import stantz.ray.Screen;
import stantz.ray.Visible;
import stantz.ray.math.Vector3D;
import stantz.ray.object.Sphere;



public class SceneControler implements stantz.ray.SceneControler {

  public Scene getCurrentScene(int width, int height) {
    int total = 10000;
    int step = (int)System.currentTimeMillis() % total;
    step = step % total;
    Vector3D eye = new Vector3D(0,0,0);
    Vector3D screenCenter = new Vector3D(0,0,50);
    Vector3D screenx = new Vector3D(100,0,0);
    Vector3D screeny = new Vector3D(0,100,0);
    Screen screen = new Screen(eye, screenCenter, screenx, screeny, width, height);

    List<Visible> objects = new ArrayList<Visible>();
    double cos = Math.cos(step*2*Math.PI/total);
    double sin = Math.sin(step*2*Math.PI/total);
    objects.add(new Sphere(new Vector3D(0*cos,0*sin,100), 25+5*cos, new Color(0.5f+0.5f*(float)cos,0.5f+0.5f*(float)sin,0.2f)));
    objects.add(new Sphere(new Vector3D(50*cos,50*sin,80), 20, new Color(0.5f,0.5f,1f)));
    objects.add(new Sphere(new Vector3D(-30*cos,-30*sin,120), 20, new Color(0.2f,1f,0.2f)));
    objects.add(new Sphere(new Vector3D(50*cos,-50*sin,100), 20, new Color(0.2f,1f,1f)));
    objects.add(new Sphere(new Vector3D(-60,50*cos,100+10*sin), 20, new Color(1f,1f,0.2f)));

    List<LightSource> lightSources = new ArrayList<LightSource>();
    //		lightSources.add(new Vector(100,100,0));
    //		lightSources.add(new Vector(-100,100,0));
    lightSources.add(new LightSource(new Color(0.1f,0.5f,0.5f), new Vector3D(0,1000,1000)));
    lightSources.add(new LightSource(new Color(0.5f,0.1f,0.1f), new Vector3D(150*cos,-150*sin,100)));


    return new Scene(screen, lightSources, new Color(0.15f,0.15f,0.15f), new Color(0.15f,0.15f,0.15f), objects);

  }

}
