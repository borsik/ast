/**
 * Created by laptop on 03.09.2016.
 */
public class Relation extends Expression {
    enum Opcode {
        less,
        less_eq,
        greater,
        greater_eq,
        equal,
        not_eq,
        none
    }

    Opcode op;
    Expression left, right;

    @Override
    public long calculate() {
        long r1 = left.calculate();
        long r2 = right.calculate();

        switch (op) {
            case less:
                return r1 < r2 ? 1 : 0;

            case less_eq:
                return r1 <= r2 ? 1 : 0;

            case greater:
                return r1 > r2 ? 1 : 0;

            case greater_eq:
                return r1 >= r2 ? 1 : 0;

            case equal:
                return r1 == r2 ? 1 : 0;

            case not_eq:
                return r1 != r2 ? 1 : 0;

            default:
                System.out.println("Relation operator missing");
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

    public Relation(Opcode op, Expression left, Expression right) {
        this.op = op;
        this.left = left;
        this.right = right;
        System.out.println("Relation " + left + " " + op + " " + right + " created");
    }
}
