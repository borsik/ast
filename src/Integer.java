/**
 * Created by laptop on 03.09.2016.
 */
public class Integer extends Primary {
    private long value;

    @Override
    public long calculate() {
        return value;
    }

    @Override
    public String toXML() {
        return String.valueOf(value);
    }

    public Integer(long value) {
        this.value = value;
    }



}
