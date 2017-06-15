package stantz.ray.math.mon;

public class ScalarUnaryOp extends Scalar {

  enum UnaryOperator { SQRT, INVERSE };

  private final UnaryOperator operator;

  private final Scalar scalar;

  public ScalarUnaryOp(UnaryOperator operator, Scalar scalar) {
    this.operator = operator;
    this.scalar = scalar;
  }

}
