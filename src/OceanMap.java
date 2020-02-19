//class to set up a new playable grid
public class OceanMap {
    private int[][] grid;

    public OceanMap() {
        grid = new int[10][10];
    }

    public int[][] getMap() {
        return grid;
    }
}
