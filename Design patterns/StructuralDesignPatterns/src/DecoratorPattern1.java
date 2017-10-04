public class DecoratorPattern1 {
    public static void main(String[] args) {
        Circle circle = new Circle();
        ColorRedDecorator redCircle = new ColorRedDecorator(new Circle());
        circle.draw();
        System.out.println("---");
        redCircle.draw();
    }
}

abstract class Forma {
    abstract void draw(); 
}

class Circle extends Forma {
    @Override
    public void draw() {
        System.out.println("circulo");
    }
}

class ShapeDecorator extends Forma {
    Forma forma;
    
    ShapeDecorator(Forma forma) {
        this.forma = forma;
    }
    
    @Override
    public void draw() {
        forma.draw();
    }
    
    public void doSomethingElse(){}
}

class ColorRedDecorator extends ShapeDecorator {
    public ColorRedDecorator(Forma forma) {
        super(forma);
    }
    
    @Override
    public void draw() {
        forma.draw();
        this.doSomethingElse();
    }
    
    @Override
    public void doSomethingElse() {
        System.out.println("rojo");
    }
}