package InitialPopulation;

import Backpack.Backpack;
import Encoding.Encoding;

import java.util.ArrayList;
import java.util.Random;

public class RandomPopulationStrategy implements InitialPopulationStrategyInterface {

    private Backpack backpack;

    private Random random;

    public RandomPopulationStrategy(Backpack backpack) {
        this.backpack = backpack;
        this.random = new Random();
    }

    public ArrayList<Encoding> generate(int size) {
        int i;
        ArrayList<Encoding> population = new ArrayList<Encoding>();
        Encoding applicant;
        while (population.size() < size) {
            i = this.random.nextInt((int)Math.pow(2, this.backpack.getLength()));
            applicant = new Encoding(i);
            if (this.backpack.test(i)) {
                population.add(applicant);
            }
        }
        return population;
    }
}
