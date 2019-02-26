package Selection;

import Encoding.Encoding;

import java.util.ArrayList;

public interface SelectionStrategyInterface {
    ArrayList<Encoding> run(ArrayList<Encoding> population, int selectionSize);
}
