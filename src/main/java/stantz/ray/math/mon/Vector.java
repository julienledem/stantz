package stantz.ray.math.mon;

public abstract class Vector {

  protected Vector() {
  }

  public Scalar norm() {
    return scalar(this).sqrt();
  }

  public Vector normalize() {
    return multiply(norm().inverse());
  }

  public Scalar scalar(Vector v) {
    return new VectorScalar(this, v);
  }

  public Vector minus(Vector v) {
    return plus(v.negate());
  }

  private Vector negate() {
    return new VectorMultiply(this, new ScalarLiteral(-1));
  }

  public Vector plus(Vector v) {
    return new VectorAdd(this, v);
  }

  public Vector multiply(Scalar scalar) {
    return new VectorMultiply(this, scalar);
  }

  public Vector cross(Vector v) {
    return new VectorCross(this, v);
  }

}
