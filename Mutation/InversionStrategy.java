package Mutation;

import Backpack.Backpack;
import Encoding.Encoding;

import java.util.ArrayList;
import java.util.Random;

public class InversionStrategy implements MutationStrategyInterface {

    private Random random;

    private Backpack backpack;

    public InversionStrategy(Backpack backpack) {
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

        int a = this.random.nextInt(this.backpack.getLength() - 2) + 1,
            b = this.random.nextInt(this.backpack.getLength() - 2) + 1;

        if (b < a) {
            a += (b - (b = a));
        }

        StringBuilder builder = new StringBuilder(
            encoding.toString(this.backpack.getLength()).replaceAll("\\s+","")
        );

        for (; a <= b; a++) {
            if ('0' == builder.charAt(a)) {
                builder.setCharAt(a,'1');
            } else {
                builder.setCharAt(a,'0');
            }
        }

        int result = Integer.parseInt(builder.toString(), 2);
        return new Encoding(result);
    }
}
