import Backpack.Backpack;
import Crossover.CrossoverStrategyInterface;
import Encoding.Encoding;
import InitialPopulation.*;
import Mutation.MutationStrategyInterface;
import Selection.SelectionStrategyInterface;

import java.util.ArrayList;

public class GeneticEngine {

    public int generation;

    public ArrayList<Encoding> population;

    public Backpack backpack;

    private int populationSize;

    private SelectionStrategyInterface selection;

    private CrossoverStrategyInterface crossover;

    private MutationStrategyInterface mutation;

    public GeneticEngine(Backpack backpack, int populationSize) {
        this.generation = 0;
        this.backpack = backpack;
        this.population = new ArrayList<Encoding>();
        this.populationSize = populationSize;
    }

    public void init(InitialPopulationStrategyInterface algorithm) {
        this.population = algorithm.generate(this.populationSize);
    }

    public void setSelectionStrategy(SelectionStrategyInterface algorithm) {
        this.selection = algorithm;
    }

    public void setCrossoverStrategy(CrossoverStrategyInterface algorithm) {
        this.crossover = algorithm;
    }

    public void setMutationStrategy(MutationStrategyInterface algorithm) {
        this.mutation = algorithm;
    }

    public void evolve() {
        ArrayList<Encoding> reproduction = this.selection.run(this.population, this.populationSize);

        Encoding best = this.best();
        this.population.clear();
        this.population.addAll(this.crossover.run(reproduction));
        this.population.addAll(this.mutation.run(reproduction));
        this.population.add(best);

        this.population.removeIf((Encoding e) -> !this.backpack.test(e));

        this.generation++;
    }

    /**
     * @return лучшая в текущей популяции особь
     */
    public Encoding best() {
        int maxS = 0, adaptability; 
        Encoding max = this.population.get(0);
        for (Encoding item : this.population) {
            adaptability = this.backpack.getFitness(item);
            if (maxS < adaptability) {
                max = item;
                maxS = adaptability;
            }
        }

        return max;
    }

}
