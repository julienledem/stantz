package stantz.ray.math.mon;

public class VectorMultiply extends Vector {

  private final Vector vector;
  private final Scalar scalar;

  public VectorMultiply(Vector vector, Scalar scalar) {
    this.vector = vector;
    this.scalar = scalar;
  }

}
