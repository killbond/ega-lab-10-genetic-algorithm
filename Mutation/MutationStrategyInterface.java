package Mutation;

import Encoding.Encoding;

import java.util.ArrayList;

public interface MutationStrategyInterface {

    double MUTATION_PROBABILITY = 0.005;

    int MUTATIONS_NUMBER = 4;

    ArrayList<Encoding> run(ArrayList<Encoding> population);
}
