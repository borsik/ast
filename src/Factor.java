/**
 * Created by laptop on 03.09.2016.
 */
public class Factor extends Expression {
    enum Opcode {
        mult,
        div,
        none
    }

    Opcode op;
    Expression left, right;

    @Override
    public long calculate() {
        long r1 = left.calculate();
        long r2 = right.calculate();
        switch (op) {
            case mult:
                return r1 * r2;

            case div:
                return r1 / r2;

            default:
                System.out.println("Factor operator missing");
                return -1;
        }
    }

    @Override
    public String toXML() {
        String xml = "<factor>" + System.lineSeparator();
        xml += "<op>" + op.toString() + "</op>" + System.lineSeparator();
        xml += "<left>" + left.toXML() + "</left>" + System.lineSeparator();
        xml += "<right>" + right.toXML() + "</right>" + System.lineSeparator();
        xml += "</factor>" + System.lineSeparator();
        return xml;
    }

    public Factor(Opcode op, Expression left, Expression right) {
        this.op = op;
        this.left = left;
        this.right = right;
        System.out.println("Factor " + left + " " + op + " " + right + " created");
    }
}
