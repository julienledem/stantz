package stantz.ray.math.mon;

public class ScalarBinaryOp extends Scalar {

  enum BinaryOperator { MUL, ADD };

  private final BinaryOperator op;
  private final Scalar s1;
  private final Scalar s2;

  public ScalarBinaryOp(BinaryOperator op, Scalar s1, Scalar s2) {
    this.op = op;
    this.s1 = s1;
    this.s2 = s2;
  }

}
