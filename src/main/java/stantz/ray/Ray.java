package stantz.ray;

import stantz.ray.math.Line;

public final class Ray {

  public final Line line;

  public final int ttl;

  public Ray(Line line, int ttl) {
    super();
    this.line = line;
    this.ttl = ttl;
  }

  public Ray commingFrom(Line line) {
    return new Ray(line, ttl - 1);
  }

  @Override
  public String toString() {
    return "Ray [line=" + line + ", ttl=" + ttl + "]";
  }

}
