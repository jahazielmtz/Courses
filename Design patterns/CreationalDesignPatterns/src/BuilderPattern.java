public class BuilderPattern {
    public static void main(String[] args) {
        Director director = new Director();
        CarBuilder carBuilder = new CarBuilder();
        Car car = director.contruct(carBuilder);
        car.doSomething();
    }
}

class Car {
    int doors;

    void addDoors(int doors) {
        this.doors = doors;
    }
    
    void doSomething() {
        System.out.println("Tengo " + doors + " puertas");
    }
}

class CarBuilder {
    Car car;
    
    void step1() {
        car = new Car();
    }

    void step2() {
        car.addDoors(4);
    }
    
    Car getResult() {
        return car;
    }
}

class Director {
    Car contruct(CarBuilder builder) {
        builder.step1();
        builder.step2();
        return builder.getResult();
    }
}
