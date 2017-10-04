public class AbstractFactory {
    public static void main(String[] args) throws Exception {
        Factory2 myFactory = Factory2.getFactory("VW");
        myFactory.toStr();
        Car2 s = myFactory.createCar("Sports");
        Car2 f = myFactory.createCar("Family");
        s.toStr();
        f.toStr();
    }
}

abstract class Car2 {
    String name;
    String maxSpeed;
    
    Car2() {
        name = null;
        maxSpeed = null;
    }
    
    void toStr() {
        System.out.println("Name is " + name + ", maxSpeed is " + maxSpeed);
    }
}

class FamilyCar2 extends Car2 {
    FamilyCar2() {
        this.name = "Familiar";
        this.maxSpeed = "150 Km/hr";
    }
}

class SportsCar2 extends Car2{
    SportsCar2() {
        this.name = "Deportivo";
        this.maxSpeed = "250 Km/hr";
    }
}

abstract class Factory2 {
    String manufacturer;
    
    Factory2() {
        manufacturer = null;
    }
    
    abstract Car2 createCar(String carType);
    
    static VWFactory getFactory(String factoryName) throws Exception {
        switch (factoryName) {
            case "VW":
                return new VWFactory();
            default:
                throw new Exception("Unknown factory");
        }
    }
    
    void toStr() {
        System.out.println("Manufacturer is " + manufacturer);
    }    
}

class VWFactory extends Factory2 {
    VWFactory() {
        manufacturer = "VW";
    }
    
    @Override
    Car2 createCar(String carType) {
        Car2 car = null;
        switch (carType) {
            case "Sports":
                car = new SportsCar2();
                break;
            case "Family":
                car = new FamilyCar2();
                break;
            default:
                System.out.println("Car type " + carType + " is not defined");
                break;
        }
        return car;
    }
    
    void doSomething(Car2 car) {
        car.toStr();
    }
}
