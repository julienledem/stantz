package stantz.ray;

public final class Color {
  public static final Color black = new Color(0,0,0);

  public final float red;
  public final float green;
  public final float blue;

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
    return value > 1 ? 1 : (value < 0 ? 0 : value);
  }

  public Color add(Color c) {
    return new Color(red + c.red, green + c.green, blue + c.blue);
  }

  public Color dim(float scalar) {
    return new Color(red * scalar, green * scalar, blue * scalar);
  }

  public Color filter(Color color) {
    return new Color(red * color.red, green * color.green, blue * color.blue);
  }

  public int toInt() {
    int r = (int) (red * 255 + 0.5);
    int g = (int) (green * 255 + 0.5);
    int b = (int) (blue * 255 + 0.5);

    return
        ((255 & 0xFF) << 24) |
        ((r & 0xFF) << 16) |
        ((g & 0xFF) << 8) |
        ((b & 0xFF) << 0);
  }

  public Color addDimmed(Color c, float coef) {
    return new Color(red + c.red * coef, green + c.green * coef, blue + c.blue * coef);
  }

  public Color invert() {
	return new Color(1 - red, 1 - green, 1 - blue);
  }

  @Override
  public String toString() {
    return "Color(" + red + ", " + green + ", " + blue + ")";
  }
  
}
