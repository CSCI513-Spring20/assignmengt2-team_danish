//This class sets up the game and extends Application to
//implement the GUI of the game and controls the flow of the
//game
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import java.awt.*;
import java.io.FileInputStream;
import java.util.Random;

public class OceanExplorer extends Application {

    //setting up variables
    final int dimensions = 10;
    final int scale = 50;
    private Scene scene;
    private OceanMap oceanMap;
    private ImageView shipImageView;
    private ImageView shipPirateImageView1;
    private ImageView shipPirateImageView2;
    private ImagePattern islandImagePattern;
    private Image islandImage;
    private Ship ship;
    private PirateShip pirateShip1;
    private PirateShip pirateShip2;
    private AnchorPane root;
    private int[][] oceanGrid;

    @Override
    public void start(Stage oceanStage) throws Exception {
        oceanMap = new OceanMap();
        oceanGrid = oceanMap.getMap();
        ship = new Ship();
        pirateShip1 = new PirateShip(new Point(0, 0), oceanGrid);
        pirateShip2 = new PirateShip(new Point(1, 1), oceanGrid);
        root = new AnchorPane();
        ship.addObserver(pirateShip1);
        ship.addObserver(pirateShip2);

        islandImage = new Image(new FileInputStream("Assignment2/island.jpg"), 50, 50, true, true);
        setupOcean();

        Image shipImage = new Image(new FileInputStream("Assignment2/ship.png"), 50, 50, true, true);

        shipImageView = new ImageView(shipImage);
        setupColumbusShip();
        shipImageView.setX(ship.getShipLocation().x * scale);
        shipImageView.setY(ship.getShipLocation().y * scale);
        root.getChildren().add(shipImageView);

        Image pirateImage = new Image(new FileInputStream("Assignment2/pirateShip.png"), 50, 50, true, true);

        shipPirateImageView1 = new ImageView(pirateImage);
        shipPirateImageView2 = new ImageView(pirateImage);

        setupPirateShip(pirateShip1);
        shipPirateImageView1.setX(pirateShip1.getCurrentLocation().x * scale);
        shipPirateImageView1.setY(pirateShip1.getCurrentLocation().y * scale);

        root.getChildren().add(shipPirateImageView1);

        setupPirateShip(pirateShip2);
        shipPirateImageView2.setX(pirateShip2.getCurrentLocation().x * scale);
        shipPirateImageView2.setY(pirateShip2.getCurrentLocation().y * scale);

        root.getChildren().add(shipPirateImageView2);

        scene = new Scene(root, 500, 500);

        oceanStage.setTitle("Christopher Columbus Sails the Ocean Blue 2!");
        oceanStage.setScene(scene);
        oceanStage.show();
        startSailing(oceanStage);
    }

    private void setupOcean() { //sets up the ocean with water and islands
        Random rng = new Random();
        for (int x = 0; x < dimensions; x++) {
            for (int y = 0; y < dimensions; y++) {
                double rand = rng.nextDouble();
                Rectangle rect = new Rectangle(x * scale, y * scale, scale, scale);
                if (rand < .1) {
                    islandImagePattern = new ImagePattern(islandImage); //fills island space with image
                    rect.setStroke(Color.BLACK);
                    rect.setFill(islandImagePattern);
                    root.getChildren().add(rect);
                    oceanGrid[x][y] = 1;
                    continue;
                }
                rect.setStroke(Color.BLACK);
                rect.setFill(Color.PALETURQUOISE);
                oceanGrid[x][y] = 0;
                root.getChildren().add(rect);
            }
        }
    }

    private void setupColumbusShip() {
        ship.setCurrentLocation(getRandomOceanSpot());
        oceanGrid[ship.getShipLocation().x][ship.getShipLocation().y] = 2;
    }

    private void setupPirateShip(PirateShip pirateShip) {
        pirateShip.setCurrentLocation(getRandomOceanSpot());
        oceanGrid[pirateShip.getCurrentLocation().x][pirateShip.getCurrentLocation().y] = 3;
    }

    private Point getRandomOceanSpot() {    //removes duplicate code and gets a random point with water
        Random rng = new Random();
        while(true) {
            int x = rng.nextInt(10);
            int y = rng.nextInt(10);
            if(oceanGrid[x][y] == 0)
                return new Point(x, y);
        }
    }

    private void startSailing(Stage stage) {    //contains the eventhandler that controls the ship movements
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {    //using the arrow keys on the keyboard
            @Override
            public void handle(KeyEvent ke) {
                switch (ke.getCode()) {
                    case RIGHT: //checks which key is pressed
                        if(ship.getShipLocation().x != 9) { //avoids array out of bound index
                            if(oceanGrid[ship.getShipLocation().x + 1][ship.getShipLocation().y] == 0) {    //checks if the requested location is water
                                oceanGrid[ship.getShipLocation().x][ship.getShipLocation().y] = 0;
                                oceanGrid[ship.getShipLocation().x + 1][ship.getShipLocation().y] = 2;
                                ship.goEast();
                            }
                        }
                        break;
                    case LEFT:
                        if(ship.getShipLocation().x != 0) {
                            if(oceanGrid[ship.getShipLocation().x - 1][ship.getShipLocation().y] == 0) {
                                oceanGrid[ship.getShipLocation().x][ship.getShipLocation().y] = 0;
                                oceanGrid[ship.getShipLocation().x - 1][ship.getShipLocation().y] = 2;
                                ship.goWest();
                            }
                        }
                        break;
                    case UP:
                        if(ship.getShipLocation().y != 0) {
                            if (oceanGrid[ship.getShipLocation().x][ship.getShipLocation().y - 1] == 0) {
                                oceanGrid[ship.getShipLocation().x][ship.getShipLocation().y] = 0;
                                oceanGrid[ship.getShipLocation().x][ship.getShipLocation().y - 1] = 2;
                                ship.goNorth();
                            }
                        }
                        break;
                    case DOWN:
                        if(ship.getShipLocation().y != 9) {
                            if (oceanGrid[ship.getShipLocation().x][ship.getShipLocation().y + 1] == 0) {
                                oceanGrid[ship.getShipLocation().x][ship.getShipLocation().y] = 0;
                                oceanGrid[ship.getShipLocation().x][ship.getShipLocation().y + 1] = 2;
                                ship.goSouth();
                            }
                        }
                        break;
                    default:
                        break;
                }
                shipImageView.setX(ship.getShipLocation().x * scale);       //moves the ships on the GUI
                shipImageView.setY(ship.getShipLocation().y * scale);
                shipPirateImageView1.setX(pirateShip1.getCurrentLocation().x * scale);
                shipPirateImageView1.setY(pirateShip1.getCurrentLocation().y * scale);
                shipPirateImageView2.setX(pirateShip2.getCurrentLocation().x * scale);
                shipPirateImageView2.setY(pirateShip2.getCurrentLocation().y * scale);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

}
