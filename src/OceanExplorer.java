import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import java.io.FileInputStream;
import java.util.Random;

public class OceanExplorer extends Application {

    final int dimensions = 10;
    final int scale = 50;
    private Scene scene;
    private OceanMap oceanMap;
    private ImageView shipImageView;
    private Ship ship;

    @Override
    public void start(Stage oceanStage) throws Exception {
        ship = new Ship();
        oceanMap = new OceanMap();
        boolean[][] oceanGrid = oceanMap.getMap();


        AnchorPane root = new AnchorPane();

        Random rng = new Random();
        for (int x = 0; x < dimensions; x++) {
            for (int y = 0; y < dimensions; y++) {
                double rand = rng.nextDouble();

                Rectangle rect = new Rectangle(x * scale, y * scale, scale, scale);
                if (rand < .1) {
                    rect.setStroke(Color.BLACK);
                    rect.setFill(Color.GREEN);
                    root.getChildren().add(rect);
                    continue;
                }
                rect.setStroke(Color.BLACK);
                rect.setFill(Color.PALETURQUOISE);
                root.getChildren().add(rect);
            }
        }




        Image shipImage = new Image(new FileInputStream("Lab2/ship.png"), 50, 50, true, true);

        shipImageView = new ImageView(shipImage);

        shipImageView.setX(ship.getShipLocation().x * scale);
        shipImageView.setY(ship.getShipLocation().y * scale);

        root.getChildren().add(shipImageView);

        scene = new Scene(root, 500, 500);

        oceanStage.setTitle("Christopher Columbus Sails the Ocean Blue!");
        oceanStage.setScene(scene);
        oceanStage.show();
        startSailing();
    }

    private void startSailing() {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                switch (ke.getCode()) {
                    case RIGHT:
                        ship.goEast();
                        break;
                    case LEFT:
                        ship.goWest();
                        break;
                    case UP:
                        ship.goNorth();
                        break;
                    case DOWN:
                        ship.goSouth();
                        break;
                    default:
                        break;
                }
                shipImageView.setX(ship.getShipLocation().x * scale);
                shipImageView.setY(ship.getShipLocation().y * scale);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

}
