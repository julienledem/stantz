package stantz.ray;

import java.util.List;

public final class Scene {

  final Screen screen;
  final List<LightSource> lightSources;
  final Color ambientColor;
  final Color background;
  final List<Visible> objects;

  public Scene(Screen screen, List<LightSource> lightSources,
      Color ambientColor, Color background, List<Visible> objects) {
    super();
    this.screen = screen;
    this.lightSources = lightSources;
    this.ambientColor = ambientColor;
    this.background = background;
    this.objects = objects;
  }



}
