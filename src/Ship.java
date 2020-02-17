import java.util.Random;

public class Ship {
    private Point currentLocation;

    Ship() {
        Random rand = new Random();
        currentLocation = new Point(rand.nextInt(10), rand.nextInt(10));
    }

    public Point getShipLocation() {
        return currentLocation;
    }

    public void goEast() {
        if(currentLocation.x != 9)
            currentLocation.x++;
    }

    public void goWest() {
        if(currentLocation.x != 0)
            currentLocation.x--;
    }

    public void goNorth() {
        if(currentLocation.y != 0)
            currentLocation.y--;
    }

    public void goSouth() {
        if(currentLocation.y != 9)
            currentLocation.y++;
    }
}
