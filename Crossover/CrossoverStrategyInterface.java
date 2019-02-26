package Crossover;

import Encoding.Encoding;

import java.util.ArrayList;

public interface CrossoverStrategyInterface {

    double CROSSOVER_PROBABILITY = 0.7;

    int CROSSOVERS_NUMBER = 7;

    ArrayList<Encoding> run(ArrayList<Encoding> population);

}
