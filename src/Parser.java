import java.lang.*;

/**
 * Created by laptop on 03.09.2016.
 */
public class Parser {
    private String input;
    private String temp;
    private int pos;

    public Parser(String s) {
        input = s;
        pos = 0;
    }

    public Expression parse() {
        return parseLogical();
    }

    public char getNextChar() {
        if (pos < input.length()) {
            return input.charAt(pos);
        } else {
            return '\0';
        }
    }

    public void skipNextChar() {
        pos++;
    }




    private Expression parseLogical() {
        Expression result = parseRelation();
        while (true) {
            Logical.Opcode opcode = parseLogOperator();
            if (opcode != Logical.Opcode.none) {
                Expression right = parseRelation();
                result = new Logical(opcode, result, right);
            } else {
                break;
            }

        }
        return result;
    }

    private Logical.Opcode parseLogOperator() {
        temp = "";
        while(Character.isLetter(getNextChar())) {
            temp += Character.toString(getNextChar());
            skipNextChar();
        }

        switch(temp) {
            case "and":
                return Logical.Opcode.and;

            case "or":
                return Logical.Opcode.or;

            case "xor":
                return Logical.Opcode.xor;

            default:
                return Logical.Opcode.none;
        }


    }

    private Expression parseRelation() {
        Expression result = parseTerm();
        while (true) {
            Relation.Opcode opcode = parseRelOperator();
            if (opcode != Relation.Opcode.none) {
                Expression right = parseTerm();
                result = new Relation(opcode, result, right);
            } else {
                break;
            }
        }
        return result;
    }

    private Relation.Opcode parseRelOperator() {
        temp = "";
        while(getNextChar() == '<' || getNextChar() == '>' || getNextChar() == '=' || getNextChar() == '/') {
            temp += Character.toString(getNextChar());
            skipNextChar();
        }
        switch(temp) {
            case "<":
                return Relation.Opcode.less;

            case "<=":
                return Relation.Opcode.less_eq;

            case ">":
                return Relation.Opcode.greater;

            case ">=":
                return Relation.Opcode.greater_eq;

            case "=":
                return Relation.Opcode.equal;

            case "/=":
                return Relation.Opcode.not_eq;

            default:
                return Relation.Opcode.none;
        }
    }

    private Expression parseTerm() {
        Expression result = parseFactor();
        while (true) {
            Term.Opcode opcode = parseTermOperator();
            if (opcode != Term.Opcode.none) {
                Expression right = parseFactor();
                result = new Term(opcode, result, right);
            } else {
                break;
            }

        }
        return result;
    }

    private Term.Opcode parseTermOperator() {
        temp = "";
        while(getNextChar() == '-' || getNextChar() == '+') {
            temp += Character.toString(getNextChar());
            skipNextChar();
        }
        switch (temp) {
            case "-":
                return Term.Opcode.minus;

            case "+":
                return Term.Opcode.plus;

            default:
                return Term.Opcode.none;
        }
    }

    private Expression parseFactor() {
        Expression result = parsePrimary();
        while (true) {
            Factor.Opcode opcode = parseFacOperator();
            if(opcode != Factor.Opcode.none) {
                Expression right = parsePrimary();
                result = new Factor(opcode, result, right);
            } else {
                break;
            }

        }
        return result;
    }

    private Factor.Opcode parseFacOperator() {
        temp = "";
        while(getNextChar() == '/' || getNextChar() == '*') {
            temp += Character.toString(getNextChar());
            skipNextChar();
        }
        switch (temp) {
            case "*":
                return Factor.Opcode.mult;

            case "/":
                return Factor.Opcode.div;

            default:
                return Factor.Opcode.none;
        }
    }


    private Expression parsePrimary() {
        Expression result = null;
        if (Character.isDigit(getNextChar())) {
            result = parseInteger();
        } else if (getNextChar() == '(') {
            skipNextChar();
            result = parseLogical();
            skipNextChar();
        } else {
            System.out.println("Error");
        }
        return result;
    }

    private Expression parseInteger() {
        temp = "";
        while (Character.isDigit(getNextChar())) {
            temp += Character.toString(getNextChar());
            skipNextChar();
        }
        Expression expression = new Integer(java.lang.Integer.parseInt(temp));
        return expression;
    }


}
