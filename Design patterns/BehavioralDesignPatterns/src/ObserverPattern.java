import java.util.ArrayList;
import java.util.List;

public class ObserverPattern {
    public static void main(String[] args) {
        Forum foro = new Forum();
        UserA user1 = new UserA();
        UserB user2 = new UserB();
        
        foro.addObserver(user1);
        foro.addObserver(user2);
        
        foro.writePost("Mi primer post al foro");
        
        foro.removeObserver(user2);
        
        foro.writePost("Mi segundo post al foro");
    }
}

//Interfaz observable
interface Publisher {
    void addObserver(Suscriber observer);
    void removeObserver(Suscriber observer);
    void notifyAllUsers();
}

class Forum implements Publisher {
    List<Suscriber> userList;
    String post;
    
    public Forum() {
        userList = new ArrayList();
    }
    
    @Override
    public void addObserver(Suscriber observer) {
        if (! userList.contains(observer)) {
            userList.add(observer);
        }
    }

    @Override
    public void removeObserver(Suscriber observer) {
        if (userList.contains(observer)) {
            userList.remove(observer);
        }
    }

    @Override
    public void notifyAllUsers() {
        for (Suscriber observer : userList) {
            observer.notifyUser(post);
        }
    }
    
    public void writePost(String text) {
        this.post = text;
        notifyAllUsers();
    }
}

interface Suscriber {
    void notifyUser(String post);
}

class UserA implements Suscriber {
    @Override
    public void notifyUser(String post) {
        System.out.println("UserA ha sido notificado " + post);
    }
}

class UserB implements Suscriber {
    @Override
    public void notifyUser(String post) {
        System.out.println("UserB ha sido notificado " + post);
    }
}