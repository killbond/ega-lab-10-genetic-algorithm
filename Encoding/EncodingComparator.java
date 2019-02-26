package Encoding;

import Backpack.Backpack;

import java.util.Comparator;

public class EncodingComparator implements Comparator<Encoding> {

    private Backpack backpack;

    public EncodingComparator(Backpack backpack) {
        this.backpack = backpack;
    }

    @Override
    public int compare(Encoding o1, Encoding o2) {
        int f1 = this.backpack.getFitness(o1),
            f2 = this.backpack.getFitness(o2);
        return Integer.compare(f2, f1);
    }
}
