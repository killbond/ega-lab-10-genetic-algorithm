package Crossover;

import Backpack.Backpack;
import Encoding.Encoding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class AutobreedingStrategy implements CrossoverStrategyInterface {

    private Random random;

    private Backpack backpack;

    public AutobreedingStrategy(Backpack backpack) {
        this.random = new Random();
        this.backpack = backpack;
    }

    @Override
    public ArrayList<Encoding> run(ArrayList<Encoding> population) {

        ArrayList<Encoding> crossovers = new ArrayList<Encoding>();

        int index;
        for(int i = 0; i < CROSSOVERS_NUMBER; i++) {
            index = this.random.nextInt(population.size());
            Encoding father = population.get(index);

            int maxDistance = 0, distance;
            Encoding mother = population.get(0);
            for(Encoding encoding : population) {
                distance = this.hammingDistance(
                    father.toString(this.backpack.getLength()),
                    encoding.toString(this.backpack.getLength())
                );

                if(distance > maxDistance) {
                    maxDistance = distance;
                    mother = encoding;
                }
            }

            if (this.random.nextDouble() < CROSSOVER_PROBABILITY) {
                Encoding child = this.crossing(father, mother);
                crossovers.add(child);
            }
        }
        return crossovers;
    }

    private Encoding crossing(Encoding firstParent, Encoding secondParent) {
        int backpackLength = this.backpack.getLength();
        int L = this.random.nextInt(backpackLength - 2) + 1;
        String child = firstParent.toString(backpackLength).substring(0, L) +
                secondParent.toString(backpackLength).substring(L);

        child = child.replace(" ", "");
        return new Encoding(Integer.parseInt(child, 2));
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
