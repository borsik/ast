import java.io.*;
import java.util.Scanner;

/**
 * Created by laptop on 03.09.2016.
 */
public class Main {
    public static void main (String [] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Parser parser = new Parser(input);
        Expression expression = parser.parse();

        long result = expression.calculate();
        String serialized = expression.toXML();
        try {
            PrintWriter writer = new PrintWriter("serialized.xml", "UTF-8");
            writer.println(serialized);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }
}
