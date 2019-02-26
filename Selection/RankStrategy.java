package Selection;

import Backpack.Backpack;
import Encoding.Encoding;
import Encoding.EncodingComparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class RankStrategy implements SelectionStrategyInterface {

    private Random random;

    private Backpack backpack;

    public RankStrategy(Backpack backpack) {
        this.random = new Random();
        this.backpack = backpack;
    }

    public ArrayList<Encoding> run(ArrayList<Encoding> population, int selectionSize) {
        ArrayList<Encoding> result = new ArrayList<Encoding>();
        population.sort(new EncodingComparator(this.backpack));

        double[] wheel = new double[population.size()];
        double probability , a = this.random.nextDouble() + 1, b = 2 - a;
        for(int i = 0; i < population.size(); i++) {
            probability = (a - (a - b) * ((double)i / (population.size() - 1))) / population.size();
            if(0 != i) {
                wheel[i] = wheel[i - 1] + probability;
            } else {
                wheel[0] = probability;
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
