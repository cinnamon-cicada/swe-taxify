package sprint3;

public class Observer implements IObserver {
    @Override
    public void updateObserver(String message) {
        System.out.println("Observer received update: " + message);
    }
}