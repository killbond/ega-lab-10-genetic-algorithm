package Backpack;

public class Item {

    private int price;

    private int weight;

    public Item(int price, int weight) {
        this.price = price;
        this.weight = weight;
    }

    public int getPrice() {
        return this.price;
    }

    public int getWeight() {
        return this.weight;
    }
}
