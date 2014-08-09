import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
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

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class App extends Application {

  private static final String APP_FXML = "App.fxml";

  private Stage primaryStage;
  private MenuBar menuBar;
  private ImageView imageView;
  private Button generateButton;


  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    this.primaryStage = primaryStage;
    this.primaryStage.setTitle("City Street Generator");
    initLayout();
    initComponents();
  }

  private void initComponents() {
    // Menu bar
    menuBar = (MenuBar) findNodeById("#menuBar");
    MenuItem openFileMenuItem = menuBar.getMenus().get(0).getItems().get(0);
    MenuItem exitMenuItem = menuBar.getMenus().get(0).getItems().get(2);

    openFileMenuItem.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        Image img = showFileChooser();

        if (img != null) {
          showImage(img);
          processImg(img);
        }
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

  private void processImg(Image img) {
    BufferedImage inputImg = SwingFXUtils.fromFXImage(img, null);

    // TODO Add a function call to generate the streets hereunder.

  }

  private Image showFileChooser() {
    FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter jpgExtentionFilter = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg");
    FileChooser.ExtensionFilter pngExtentionFilter = new FileChooser.ExtensionFilter("PGN file (*.png)", "*.png");
    fileChooser.getExtensionFilters().addAll(jpgExtentionFilter, pngExtentionFilter);
    File chosenFile = fileChooser.showOpenDialog(getPrimaryStage());

    if (chosenFile != null) {
      System.out.println("[INFO] Opening file " + chosenFile.getPath());
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