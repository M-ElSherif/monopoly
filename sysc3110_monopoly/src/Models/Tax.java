package Models;

public class Tax extends Property{

    public Tax(String name,
               int position,
               double cost,
               Player owner) {
        super(name, position, cost, null);
        this.rentCost = 200;
    }
}
