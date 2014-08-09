import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class App extends Application {

  private static final String APP_FXML = "App.fxml";

  Stage primaryStage;
  MenuBar menuBar;
  ImageView imageView;
  Button generateButton;


  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    this.primaryStage = primaryStage;
    this.primaryStage.setTitle("City Street Generator");
    initLayout();
    initComponenets();
  }

  private void initComponenets() {
    // Menu bar
    menuBar = (MenuBar) findNodeById("#menuBar");
    MenuItem openFileMenuItem = menuBar.getMenus().get(0).getItems().get(0);
    MenuItem exitMenuItem = menuBar.getMenus().get(0).getItems().get(2);

    openFileMenuItem.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        Image img = openImage();
        showImage(img);

      }
    });

    exitMenuItem.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        System.exit(0);
      }
    });

    // Image view
    imageView = (ImageView) findNodeById("#imageView");

    // Generate button
    generateButton = (Button) findNodeById("#generateButton");
    generateButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        System.out.println("[INFO] Generating streets...");
      }
    });
  }

  private void showImage(Image img) {
    imageView.setImage(img);
  }

  private Image openImage() {
    FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter jpgExtentionFilter = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg");
    FileChooser.ExtensionFilter pngExtentionFilter = new FileChooser.ExtensionFilter("PGN file (*.png)", "*.png");
    fileChooser.getExtensionFilters().addAll(jpgExtentionFilter, pngExtentionFilter);
    File chosenFile = fileChooser.showOpenDialog(getPrimaryStage());

    if (chosenFile != null) {
      System.out.println(chosenFile.getPath());
      return new Image(new File(chosenFile.getPath()).toURI().toString());
    }

    return null;
  }


  private void initLayout() {
    FXMLLoader loader = new FXMLLoader();

    try {
      loader.setLocation(getClass().getResource(APP_FXML));
      BorderPane pane = (BorderPane) loader.load();
      Scene scene = new Scene(pane);
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private Stage getPrimaryStage() {
    return primaryStage;
  }

  private Node findNodeById(String id) {
    return getPrimaryStage().getScene().lookup(id);
  }

}