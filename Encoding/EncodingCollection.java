package Encoding;

import Backpack.Backpack;
import Encoding.Encoding;

import java.util.ArrayList;

public class EncodingCollection {

    private ArrayList<Encoding> collection;

    private int length;

    public EncodingCollection(Backpack backpack, int length) {
        this.length = length;
        this.collection = new ArrayList<Encoding>();
        for (int i = 0; i < Math.pow(2, length); i++) {
            this.collection.add(new Encoding(i));
        }
    }

    public EncodingCollection(ArrayList<Encoding> collection, int length) {
        this.length = length;
        this.collection = collection;
    }

    public int getLength() {
        return this.collection.size();
    }

    public ArrayList<Encoding> asList() {
        return this.collection;
    }
}
