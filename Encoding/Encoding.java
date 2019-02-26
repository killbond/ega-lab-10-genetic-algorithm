package Encoding;

public class Encoding {

    private int value;

    public Encoding(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public String toString(int length) {
        return this.toString(length, 8);
    }

    public String toString(int length, int groupSize) {
        StringBuilder result = new StringBuilder();

        for(int i = length - 1; i >= 0 ; i--) {
            int mask = 1 << i;
            result.append((this.getValue() & mask) != 0 ? "1" : "0");

            if (i % groupSize == 0)
                result.append(" ");
        }
        result.replace(result.length() - 1, result.length(), "");

        return result.toString();
    }
}
