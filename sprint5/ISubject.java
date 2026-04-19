package sprint5;

public interface ISubject {
    /**
     * Adds an observer to this subject.
     * @param observer the IObserver to add
     */
    public void addObserver(IObserver observer);
    
    /**
     * Notifies all observers with a message.
     * @param message the message to send to observers
     */
    public void notifyObserver(String message);
    
}