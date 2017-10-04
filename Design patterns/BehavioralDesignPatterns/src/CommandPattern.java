public class CommandPattern {
    public static void main(String[] args) {
        LightSwitch client = new LightSwitch();
        client.operation("ON");
        System.out.println("Testing on command");
        client.operation("OFF");
        System.out.println("Testing off command");
    }
}

//Interface
interface Command {
    void execute();
}

//Commando especifico para ON
class OnCommand implements Command {
    Light light;

    public OnCommand(Light light) {
        this.light = light;
    }
    
    @Override
    public void execute() {
        this.light.turnOn();
    }
}

//Commando especifico para OFF
class OffCommand implements Command {
    Light light;

    public OffCommand(Light light) {
        this.light = light;
    }
    
    @Override
    public void execute() {
        this.light.turnOff();
    }
}

//Clase receiver
class Light {
    void turnOn() {
        System.out.println("Foco encendido");
    }
    
    void turnOff() {
        System.out.println("Foco apagado");
    }
}

//Clase cliente
class LightSwitch {
    Light foco;
    OnCommand switchUp;
    OffCommand switchDown;
    Switch switch1;

    public LightSwitch() {
        this.foco = new Light();
        this.switchUp = new OnCommand(foco);
        this.switchDown = new OffCommand(foco);
        this.switch1 = new Switch(switchUp, switchDown);
    }
    
    public void operation(String cmd) {
        switch (cmd) {
            case "ON":
                switch1.on();
                break;
            case "OFF":
                switch1.off();
                break;
            default:
                System.out.println("Operación inválida");
                break;
        }
    }
}

//Clase invoker
class Switch {
    Command onCommand;
    Command offCommand;
    
    Switch(Command onCommand, Command offCommand) {
        this.onCommand = onCommand;
        this.offCommand = offCommand;
    }
    
    void on() {
        this.onCommand.execute();
    }
    
    void off() {
        this.offCommand.execute();
    }
}
