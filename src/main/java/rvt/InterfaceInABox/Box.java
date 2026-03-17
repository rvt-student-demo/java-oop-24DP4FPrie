package rvt.InterfaceInABox;

import java.util.ArrayList;

public class Box implements Packable {
    private double capacity;
    private ArrayList<Packable> items;

    public Box(double capacity) {
        this.capacity = capacity;
        this.items = new ArrayList<>();
    }

    public void add(Packable item) {
        if (this.weight() + item.weight() <= capacity) {
            this.items.add(item);
        }
    }

    public double weight() {
        double total = 0;
        for (Packable item : items) {
            total += item.weight();
        }
        return total;
    }

    public String toString() {
        return "Box: " + items.size() + " items, total weight " + this.weight() + " kg";
    }
}