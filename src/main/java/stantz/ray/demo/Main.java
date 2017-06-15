package stantz.ray.demo;

import stantz.ray.display.Display;

public class Main {

  public static void main(String[] args) {
    System.out.println("start");

    new Display(new SceneControler()).init(600,600);

    System.out.println("end");
  }
}
