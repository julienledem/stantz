package stantz.ray.math.mon;

import static stantz.ray.math.mon.ScalarBinaryOp.BinaryOperator.ADD;
import static stantz.ray.math.mon.ScalarBinaryOp.BinaryOperator.MUL;
import static stantz.ray.math.mon.ScalarUnaryOp.UnaryOperator.INVERSE;
import static stantz.ray.math.mon.ScalarUnaryOp.UnaryOperator.SQRT;

public class Scalar {

  public Scalar sqrt() {
    return new ScalarUnaryOp(SQRT, this);
  }

  public Scalar inverse() {
    return new ScalarUnaryOp(INVERSE, this);
  }

  public Scalar mul(Scalar other) {
    return new ScalarBinaryOp(MUL, this, other);
  }

  public Scalar minus(Scalar scalar) {
    return plus(scalar.mul(new ScalarLiteral(-1)));
  }

  public Scalar plus(Scalar other) {
    return new ScalarBinaryOp(ADD, this, other);
  }

}
