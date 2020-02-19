//Ship class is the player class and extends observable
//to notify its movements to the pirateship class
//it contains methods to control its movements.
import java.util.Observable;
import java.awt.Point;

public class Ship extends Observable {
    private Point currentLocation;

    public Point getShipLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Point currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void goEast() {
        currentLocation.x++;
        setChanged();
        notifyObservers();
    }

    public void goWest() {
        currentLocation.x--;
        setChanged();
        notifyObservers();
    }

    public void goNorth() {
        currentLocation.y--;
        setChanged();
        notifyObservers();
    }

    public void goSouth() {
        currentLocation.y++;
        setChanged();
        notifyObservers();
    }
}
