package sprint5;

public interface IObserver {
    /**
     * Called when the observed subject notifies this observer.
     * @param message the notification message from the subject
     */
    public void updateObserver(String message);

}