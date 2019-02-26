package Mutation;

import Backpack.Backpack;
import Encoding.Encoding;

import java.util.ArrayList;
import java.util.Random;

public class AdditionStrategy implements MutationStrategyInterface {

    private Random random;

    private Backpack backpack;

    public AdditionStrategy(Backpack backpack) {
        this.random = new Random();
        this.backpack = backpack;
    }

    @Override
    public ArrayList<Encoding> run(ArrayList<Encoding> population) {

        int index;
        ArrayList<Encoding> mutants = new ArrayList<Encoding>();

        while (mutants.size() < MUTATIONS_NUMBER) {
            index = this.random.nextInt(population.size());
            Encoding applicant = population.get(index);

            if (this.random.nextDouble() < MUTATION_PROBABILITY) {
                Encoding mutant = this.mutate(applicant);
                mutants.add(mutant);
            }
        }

        return mutants;
    }

    private Encoding mutate(Encoding encoding) {
        StringBuilder builder = new StringBuilder();
        for (char c : encoding.toString(this.backpack.getLength()).toCharArray()) {
            if (c == '1') {
                builder.append('0');
            }
            if (c == '0') {
                builder.append('1');
            }
        }
        int result = Integer.parseInt(builder.toString(), 2);
        return new Encoding(result);
    }
}
