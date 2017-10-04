public class FactoryMethod {
    public static void main(String[] args) {
        MyFactory1 myFactory = new MyFactory1();
        Car1 s = myFactory.createCar("Sports");
        Car1 f = myFactory.createCar("Family");
        s.toStr();
        f.toStr();
    }
}

class Car1 {
    String name;
    String maxSpeed;
    
    Car1() {
        name = null;
        maxSpeed = null;
    }
    
    void toStr() {
        System.out.println("Name is " + name + ", maxSpeed is " + maxSpeed);
    }
}

class FamilyCar1 extends Car1 {
    FamilyCar1() {
        this.name = "Familiar";
        this.maxSpeed = "150 Km/hr";
    }
}

class SportsCar1 extends Car1 {
    SportsCar1() {
        this.name = "Deportivo";
        this.maxSpeed = "250 Km/hr";
    }
}

class MyFactory1 {
    Car1 createCar(String carType) {
        Car1 car = null;
        switch (carType) {
            case "Sports":
                car = new SportsCar1();
                break;
            case "Family":
                car = new FamilyCar1();
                break;
            default:
                System.out.println("Car type " + carType + " is not defined");
                break;
        }
        return car;
    }
    
    void doSomething(Car1 car) {
        car.toStr();
    }
}
