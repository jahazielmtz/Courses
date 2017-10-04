public class StrategyPattern {
    public static void main(String[] args) {
        MyFinder myFinder = new MyFinder();
        OtherFinder otherFinder = new OtherFinder();
        
        System.out.println(myFinder.find("Texto A"));
        System.out.println(otherFinder.find("Texto B"));
    }
}

interface TextFinder {
    String find(String text);
}

class MyFinder implements TextFinder {
    @Override
    public String find(String text) {
        return (text + " fue encontrado");
    }
}

class OtherFinder implements TextFinder {
    @Override
    public String find(String text) {
        return (text + " was found");
    }
}