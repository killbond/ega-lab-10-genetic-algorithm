package InitialPopulation;

import Encoding.Encoding;
import java.util.ArrayList;

public interface InitialPopulationStrategyInterface {
    ArrayList<Encoding> generate(int size);
}
