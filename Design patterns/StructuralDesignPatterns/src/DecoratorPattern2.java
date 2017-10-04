public class DecoratorPattern2 {
    public static void main(String[] args) {
        ICoffee coffeeWithMilk = new CoffeeWithMilk(new Coffee());
        System.out.println(coffeeWithMilk.getCost());
    }
}

interface ICoffee {
    public double getCost();
}

class Coffee implements ICoffee {
    @Override
    public double getCost() {
        return 1;
    }
}

class CoffeeWithMilk implements ICoffee {
    private ICoffee coffee;

    public CoffeeWithMilk(ICoffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public double getCost() {
        return this.coffee.getCost() + 0.25;
    }
}