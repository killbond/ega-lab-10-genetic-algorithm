import Backpack.Backpack;
import Encoding.Encoding;
import dnl.utils.text.table.TextTable;

import java.util.ArrayList;

public class TableAdapter {

    private Backpack backpack;

    TableAdapter(Backpack backpack) {
        this.backpack = backpack;
    }

    public TextTable toTable(Encoding encoding) {
        ArrayList<Encoding> population = new ArrayList<Encoding>();
        population.add(encoding);
        return this.toTable(population);
    }

    public TextTable toTable(ArrayList<Encoding> population) {
        int value;
        String[][] data = new String[population.size()][5];
        for(int i = 0; i < population.size(); i++) {
            value = population.get(i).getValue();
            data[i][0] = Integer.toString(value);
            data[i][1] = population.get(i).toString(this.backpack.getLength());
            data[i][2] = Integer.toString(this.backpack.getWeight(value));
            data[i][3] = Integer.toString(this.backpack.getPrice(value));

            StringBuilder items = new StringBuilder();
            for(int j : this.backpack.items(value)) {
                items.append(j + 1).append(",");
            }

            if (items.length() > 0) {
                items.deleteCharAt(items.length() - 1);
            }

            data[i][4] = items.toString();
        }

        TextTable table = new TextTable(this.columns(), data);
        table.setAddRowNumbering(true);
        return table;
    }

    private String[] columns() {
        return new String[]{
                "S10",
                "S2",
                "W (вес)",
                "P (цена)",
                "Предметы",
        };
    }
}
