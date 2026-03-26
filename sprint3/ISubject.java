package sprint3;

public interface ISubject {
    //Outline for subject which shall be implemented by ITaxiCompany
    public void addObserver(IObserver observer);
    public void notifyObserver(String message);
    
}