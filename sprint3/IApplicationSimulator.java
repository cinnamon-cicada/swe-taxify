package sprint3;

public interface IApplicationSimulator {
    //Outlines ApplicationSimulator methods to be @Overriden
    public void show();
    public void showStatistics();
    public void update();
    public void requestService();
    public int getTotalServices();
    
}