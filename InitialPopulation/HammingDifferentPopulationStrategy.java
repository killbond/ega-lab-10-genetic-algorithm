package InitialPopulation;

import Backpack.Backpack;
import Encoding.Encoding;
import java.util.ArrayList;
import java.util.Random;

public class HammingDifferentPopulationStrategy implements InitialPopulationStrategyInterface {

    private Random random;

    private Backpack backpack;

    public HammingDifferentPopulationStrategy(Backpack backpack) {
        this.random = new Random();
        this.backpack = backpack;
    }

    public ArrayList<Encoding> generate(int size) {
        int i, constraint = (int)Math.pow(2, this.backpack.getLength());
        ArrayList<Encoding> population = new ArrayList<Encoding>();
        Encoding applicant;
        while (population.size() < size) {
            i = this.random.nextInt(constraint);
            applicant = new Encoding(i);
            if (this.isDifferent(population, applicant) && this.backpack.test(i)) {
                population.add(applicant);
            }
        }
        return population;
    }

    private boolean isDifferent(ArrayList<Encoding> set, Encoding encoding) {
        int length = this.backpack.getLength();
        for (Encoding item : set) {
            if (0 == this.hammingDistance(item.toString(length), encoding.toString(length))) {
                return false;
            }
        }

        return true;
    }

    private int hammingDistance(String right, String left) {
        if (left == null || right == null) {
            throw new IllegalArgumentException("Strings must not be null");
        }

        if (left.length() != right.length()) {
            throw new IllegalArgumentException("Strings must have the same length");
        }

        int distance = 0;

        for (int i = 0; i < left.length(); i++) {
            if (left.charAt(i) != right.charAt(i)) {
                distance++;
            }
        }

        return distance;
    }
}
