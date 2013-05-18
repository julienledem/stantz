package stantz.ray;

public class Color {
  public static final Color black = new Color(0,0,0);

  public float red;
  public float green;
  public float blue;

  public Color(float red, float green, float blue) {
    super();
    this.red = plateau(red);
    this.green = plateau(green);
    this.blue = plateau(blue);
  }

  public Color(Color c) {
    red = c.red;
    green = c.green;
    blue = c.blue;
  }

  private float plateau(float value) {
    return value>1?1:(value<0?0:value);
  }

  public Color add(Color c) {
    return new Color(red+c.red, green+c.green, blue+c.blue);
  }

  public Color dim(float scalar) {
    return new Color(red*scalar, green*scalar, blue*scalar);
  }

  public Color filter(Color color) {
    return new Color(red*color.red,green*color.green,blue*color.blue);
  }

  @Override
  public String toString() {
    return "Color("+red+", "+green+", "+blue+")";
  }

  public Color bangadd(Color c) {
    red = plateau(red + c.red);
    green = plateau(green + c.green);
    blue = plateau(blue + c.blue);

    return this;
  }

  public Color bangfilter(Color c) {
    red = red* c.red;
    green = green * c.green;
    blue = blue * c.blue;

    return this;
  }

  public int toInt() {
    int r = (int) (red*255+0.5);
    int g = (int) (green*255+0.5);
    int b = (int) (blue*255+0.5);

    return
        ((255 & 0xFF) << 24) |
        ((r & 0xFF) << 16) |
        ((g & 0xFF) << 8) |
        ((b & 0xFF) << 0);
  }

  public Color bangaddDimmed(Color c, float coef) {
    red = plateau(red + c.red*coef);
    green = plateau(green + c.green*coef);
    blue = plateau(blue + c.blue*coef);

    return this;
  }

  public void bangInvert() {
    red = 1-red;
    green = 1-green;
    blue = 1-blue;
  }

}
