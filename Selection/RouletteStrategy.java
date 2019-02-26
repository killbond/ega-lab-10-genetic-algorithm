package Selection;

import Backpack.Backpack;
import Encoding.Encoding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class RouletteStrategy implements SelectionStrategyInterface {

    private Random random;

    private Backpack backpack;

    public RouletteStrategy(Backpack backpack) {
        this.random = new Random();
        this.backpack = backpack;
    }

    public ArrayList<Encoding> run(ArrayList<Encoding> population, int selectionSize) {
        ArrayList<Encoding> result = new ArrayList<Encoding>();

        int totalFitness = 0;
        for(Encoding item : population) {
            totalFitness += Math.abs(this.backpack.getFitness(item));
        }

        double probability;
        double[] wheel = new double[population.size()];
        for(int i = 0; i < population.size(); i++) {
            probability = ((double)Math.abs(this.backpack.getFitness(population.get(i))) / totalFitness);
            if(0 != i) {
                wheel[i] = wheel[i - 1] + probability;
            } else {
                wheel[i] = probability;
            }
        }

        int selection = 0;
        while(selection < selectionSize) {
            double pointer = this.random.nextDouble();
            int index = Arrays.binarySearch(wheel, pointer);
            result.add(population.get(Math.abs(index + 1)));
            selection++;
        }

        return result;
    }
}
