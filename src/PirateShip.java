//This is the observer class and contains code for
//chasing the player ship on the map, its prompted when
//the player class changes position and it tries to chase it
//down
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class PirateShip implements Observer {
    private Point currentLocation;
    private Point columbusLocation;
    private int[][] grid;

    public PirateShip(Point currentLocation, int[][]grid) {
        this.currentLocation = currentLocation;
        this.grid = grid;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Ship) {
            columbusLocation = ((Ship) o).getShipLocation();
            chase();
        }
    }

    public Point getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Point point) {
        this.currentLocation = point;
    }

    public void chase() {   //determines the right direction to move
        if (Math.abs(columbusLocation.getLocation().x - currentLocation.getLocation().x) >= Math.abs(columbusLocation.getLocation().y - currentLocation.getLocation().y)) {
            if (columbusLocation.getLocation().x > currentLocation.getLocation().x) {
                if ((grid[currentLocation.x+1][currentLocation.y] == 0 || grid[currentLocation.x+1][currentLocation.y] == 2) && currentLocation.getLocation().x < grid.length-1) {
                    grid[currentLocation.x][currentLocation.y] = 0; //rewrites the grid to reflect
                    currentLocation.x = currentLocation.x + 1;
                    grid[currentLocation.x][currentLocation.y] = 3; //its new location and the old location is reverted to water
                }
                else {
                    if (columbusLocation.getLocation().y > currentLocation.getLocation().y && (grid[currentLocation.x][currentLocation.y+1] == 0 || grid[currentLocation.x][currentLocation.y+1] == 2) && currentLocation.getLocation().y < grid.length-1) {
                        grid[currentLocation.x][currentLocation.y] = 0;
                        currentLocation.y = currentLocation.y + 1;
                        grid[currentLocation.x][currentLocation.y] = 3;
                    }
                    else if (columbusLocation.getLocation().y < currentLocation.getLocation().y && (grid[currentLocation.x][currentLocation.y-1] == 0 || grid[currentLocation.x][currentLocation.y-1] == 2) && currentLocation.getLocation().y > 0) {
                        grid[currentLocation.x][currentLocation.y] = 0;
                        currentLocation.y = currentLocation.y - 1;
                        grid[currentLocation.x][currentLocation.y] = 3;
                    }
                }

            }
            else {
                if ((grid[currentLocation.x-1][currentLocation.y] == 0 || grid[currentLocation.x-1][currentLocation.y] == 2)) {
                    grid[currentLocation.x][currentLocation.y] = 0;
                    currentLocation.x = currentLocation.x - 1;
                    grid[currentLocation.x][currentLocation.y] = 3;
                }
                else {
                    if (columbusLocation.getLocation().y > currentLocation.getLocation().y && (grid[currentLocation.x][currentLocation.y+1] == 0 || grid[currentLocation.x][currentLocation.y+1] == 2) && currentLocation.getLocation().y < grid.length-1) {
                        grid[currentLocation.x][currentLocation.y] = 0;
                        currentLocation.y = currentLocation.y + 1;
                        grid[currentLocation.x][currentLocation.y] = 3;
                    }
                    else if (columbusLocation.getLocation().y < currentLocation.getLocation().y && (grid[currentLocation.x][currentLocation.y-1] == 0 || grid[currentLocation.x][currentLocation.y-1] == 2) && currentLocation.getLocation().y > 0) {
                        grid[currentLocation.x][currentLocation.y] = 0;
                        currentLocation.y = currentLocation.y - 1;
                        grid[currentLocation.x][currentLocation.y] = 3;
                    }
                }
            }
        }
        else {
            if (columbusLocation.getLocation().y > currentLocation.getLocation().y && currentLocation.getLocation().y < grid.length-1) {
                if (grid[currentLocation.x][currentLocation.y+1] == 0 || grid[currentLocation.x][currentLocation.y+1] == 2) {
                    grid[currentLocation.x][currentLocation.y] = 0;
                    currentLocation.y = currentLocation.y + 1;
                    grid[currentLocation.x][currentLocation.y] = 3;
                }
                else {
                    if (columbusLocation.getLocation().x > currentLocation.getLocation().x && (grid[currentLocation.x+1][currentLocation.y] == 0 || grid[currentLocation.x+1][currentLocation.y] == 2) && currentLocation.getLocation().x < grid.length-1) {
                        grid[currentLocation.x][currentLocation.y] = 0;
                        currentLocation.x = currentLocation.x + 1;
                        grid[currentLocation.x][currentLocation.y] = 3;
                    }
                    else if (columbusLocation.getLocation().x < currentLocation.getLocation().x && (grid[currentLocation.x-1][currentLocation.y] == 0 || grid[currentLocation.x-1][currentLocation.y] == 2) && currentLocation.getLocation().x > 0) {
                        grid[currentLocation.x][currentLocation.y] = 0;
                        currentLocation.x = currentLocation.x - 1;
                        grid[currentLocation.x][currentLocation.y] = 3;
                    }
                }
            }
            else {
                if ((grid[currentLocation.x][currentLocation.y-1] == 0 || grid[currentLocation.x][currentLocation.y-1] == 2) && currentLocation.getLocation().y > 0) {
                    grid[currentLocation.x][currentLocation.y] = 0;
                    currentLocation.y = currentLocation.y - 1;
                    grid[currentLocation.x][currentLocation.y] = 3;
                }
                else {
                    if (columbusLocation.getLocation().x > currentLocation.getLocation().x && (grid[currentLocation.x+1][currentLocation.y] == 0 || grid[currentLocation.x+1][currentLocation.y] == 2) && currentLocation.getLocation().x < grid.length-1) {
                        grid[currentLocation.x][currentLocation.y] = 0;
                        currentLocation.x = currentLocation.x + 1;
                        grid[currentLocation.x][currentLocation.y] = 3;
                    }
                    else if (columbusLocation.getLocation().x < currentLocation.getLocation().x && (grid[currentLocation.x-1][currentLocation.y] == 0 || grid[currentLocation.x-1][currentLocation.y] == 2) && currentLocation.getLocation().x > 0) {
                        grid[currentLocation.x][currentLocation.y] = 0;
                        currentLocation.x = currentLocation.x - 1;
                        grid[currentLocation.x][currentLocation.y] = 3;
                    }
                }
            }
        }
    }

}
