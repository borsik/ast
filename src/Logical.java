import jdk.nashorn.api.scripting.JSObject;
import jdk.nashorn.internal.ir.debug.JSONWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.Collection;
import java.util.Set;

/**
 * Created by oaitkaliyev on 03.09.2016.
 */
public class Logical extends Expression {
    enum Opcode {
        and,
        or,
        xor,
        none
    }
    Opcode op;

    Expression left, right;

    @Override
    public long calculate(){
        long r1 = left.calculate();
        long r2 = right.calculate();
        switch (op) {
            case and:
                return ((r1 > 0) && (r2 > 0)) ? 1 : 0;
            case or:
                return ((r1 > 0) || (r2 > 0)) ? 1 : 0;
            case xor:
                return ((r1 > 0) && (r2 > 0)) || ((r1 < 0) && (r2 < 0)) ? 0 : 1;
            default:
                System.out.println("Logical operator missing");
                return -1;
        }
    }

    @Override
    public String toXML() {
        /*
        String line = "{" + System.lineSeparator();
        line += '"' + "op: " + '"' + op.toString() + '"' + ',' + System.lineSeparator();
        line += "left: " + '"' + left.ToJSON() + '"' + ',' + System.lineSeparator();
        line += "right: " + '"' + right.ToJSON() + System.lineSeparator();
        line += "}" + System.lineSeparator();
        return line;
        */
        String xml = "<logical>" + System.lineSeparator();
        xml += "<op>" + op.toString() + "</op>" + System.lineSeparator();
        xml += "<left>" + left.toXML() + "</left>" + System.lineSeparator();
        xml += "<right>" + right.toXML() + "</right>" + System.lineSeparator();
        xml += "</logical>" + System.lineSeparator();
        return xml;

    }

    public Logical(Opcode op, Expression left, Expression right) {
        this.op = op;
        this.left = left;
        this.right = right;
        System.out.println("Logical " + left + " " + op + " " + right + " created");
    }

}
