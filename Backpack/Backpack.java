package Backpack;

import Encoding.Encoding;

import java.util.ArrayList;

public class Backpack {

    private ArrayList<Item> items;

    private int constraint;

    public Backpack(int[][] items, int constraint) {
        this.items = new ArrayList<Item>();
        this.constraint = constraint;
        for(int[] item : items) {
            this.items.add(new Item(item[0], item[1]));
        }
    }

    public int getLength() {
        return this.items.size();
    }

    public boolean test(Encoding solution) {
        return this.test(solution.getValue());
    }

    public int getFitness(Encoding encoding) {
        return this.getPrice(encoding.getValue());
    }

    public boolean test(int solution) {
        boolean result = false;

        if(this.getWeight(solution) <= this.constraint) {
            result = true;
        }

        return result;
    }

    /**
     * @param solution решение
     * @return номера предметов
     */
    public ArrayList<Integer> items(int solution) {
        int degree;
        ArrayList<Integer> items = new ArrayList<Integer>();
        for(int i = 0; i < this.items.size(); i++) {
            degree = (int)Math.pow(2, this.items.size() - i - 1);
            if((solution & degree) == degree) {
                items.add(i);
            }
        }
        return items;
    }

    /**
     * @param solution решение
     * @return цена рюкзака
     */
    public int getPrice(int solution) {
        int total = 0;
        for(int i : this.items(solution)) {
            total += this.items.get(i).getPrice();
        }
        return total;
    }

    /**
     * @param solution решение
     * @return вес рюкзака
     */
    public int getWeight(int solution) {
        int total = 0;
        for(int i : this.items(solution)) {
            total += this.items.get(i).getWeight();
        }
        return total;
    }

}
