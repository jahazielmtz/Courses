public class SingletonPattern {
    public static void main(String[] args) {
        Long a = Singleton.getInstance();
        Long b = Singleton.getInstance();
        
        System.out.println(a);
        System.out.println(b);
    }
}

class Singleton {
    private static Long randomNumber;
    
    private Singleton() {
        randomNumber = Math.round(Math.random() * 1000);
    }
    
    public static Long getInstance() {
        if (randomNumber == null) {
            randomNumber = Math.round(Math.random() * 1000);
        }
        return randomNumber;
    }
}
