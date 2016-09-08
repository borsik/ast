/**
 * Created by laptop on 06.09.2016.
 */
public class Term extends Expression {
    enum Opcode {
        plus,
        minus,
        none
    }

    Opcode op;
    Expression left, right;

    @Override
    public long calculate() {
        long r1 = left.calculate();
        long r2 = right.calculate();
        switch (op) {
            case plus:
                return r1 + r2;

            case minus:
                return r1 - r2;

            default:
                System.out.println("Term operator missing");
                return -1;
        }
    }

    @Override
    public String toXML() {
        String xml = "<relation>" + System.lineSeparator();
        xml += "<op>" + op.toString() + "</op>" + System.lineSeparator();
        xml += "<left>" + left.toXML() + "</left>" + System.lineSeparator();
        xml += "<right>" + right.toXML() + "</right>" + System.lineSeparator();
        xml += "</relation>" + System.lineSeparator();
        return xml;
    }

    public Term(Opcode op, Expression left, Expression right) {
        this.op = op;
        this.left = left;
        this.right = right;
        System.out.println("Term " + left + " " + op + " " + right + " created");
    }
}
