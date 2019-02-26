package Crossover;

import Backpack.Backpack;
import Encoding.Encoding;

import java.util.ArrayList;
import java.util.Random;

public class PanmixiaStrategy implements CrossoverStrategyInterface {

    private Random random;

    private Backpack backpack;

    public PanmixiaStrategy(Backpack backpack) {
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

            index = this.random.nextInt(population.size());
            Encoding mother = population.get(index);

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
}
