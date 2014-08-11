package app;

import model.City;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class App extends Application {

  private static final String APP_FXML = "../App.fxml";

  private ObservableList<City> cities;
  private Image imgOriginal;

  private Stage primaryStage;
  private MenuBar menuBar;
  private ImageView imageView;
  private TableView cityTableView;
  private Button addCityButton;
  private Button removeCityButton;
  private Button generateButton;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    this.primaryStage = primaryStage;
    this.primaryStage.setTitle("model.City Street Generator");

    initLayout();
    initComponents();
  }

  private void initComponents() {

    findNodes();

    initMenuBar();
    initImageView();
    initCityTable();
    initAddCityButton();
    initRemoveCityButton();
    initGenerateButton();
  }

  private void findNodes() {
    menuBar = (MenuBar) findNodeById("#menuBar");
    imageView = (ImageView) findNodeById("#imageView");
    cityTableView = (TableView) findNodeById("#cityTableView");
    addCityButton = (Button) findNodeById("#addCityButton");
    removeCityButton = (Button) findNodeById("#removeCityButton");
    generateButton = (Button) findNodeById("#generateButton");
  }

  private void initMenuBar() {
    MenuItem openFileMenuItem = menuBar.getMenus().get(0).getItems().get(0);
    MenuItem exitMenuItem = menuBar.getMenus().get(0).getItems().get(2);

    openFileMenuItem.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        Image img = showFileChooser();

        if (img != null) {
          showImage(img);
        }
      }
    });

    exitMenuItem.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        System.exit(0);
      }
    });
  }

  private void initImageView() {

  }

  private void initCityTable() {
    cityTableView.setEditable(true);

    List<TableColumn> columns = cityTableView.getColumns();
    TableColumn citySize = columns.get(0);
    TableColumn xCol = columns.get(1);
    TableColumn yCol = columns.get(2);

    Callback<TableColumn, TableCell> cellFactory =
            new Callback<TableColumn, TableCell>() {
              public TableCell call(TableColumn p) {
                return new EditingCell();
              }
            };

    citySize.setEditable(true);
    citySize.setCellValueFactory(new PropertyValueFactory<City, Integer>("size"));
    citySize.setCellFactory(cellFactory);
    citySize.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<City, Integer>>() {
              @Override
              public void handle(TableColumn.CellEditEvent<City, Integer> t) {
                City city = t.getTableView().getItems().get(t.getTablePosition().getRow());
                city.setSize(t.getNewValue());
                redrawCities();
              }
            }
    );

    xCol.setEditable(true);
    xCol.setCellValueFactory(new PropertyValueFactory<City, Integer>("x"));
    xCol.setCellFactory(cellFactory);
    xCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<City, Integer>>() {
              @Override
              public void handle(TableColumn.CellEditEvent<City, Integer> t) {
                City city = t.getTableView().getItems().get(t.getTablePosition().getRow());
                city.setX(t.getNewValue());
                redrawCities();
              }
            }
    );


    yCol.setEditable(true);
    yCol.setCellValueFactory(new PropertyValueFactory<City, Integer>("y"));
    yCol.setCellFactory(cellFactory);
    yCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<City, Integer>>() {
              @Override
              public void handle(TableColumn.CellEditEvent<City, Integer> t) {
                City city = t.getTableView().getItems().get(t.getTablePosition().getRow());
                city.setY(t.getNewValue());
                redrawCities();
              }
            }
    );

    cityTableView.setItems(cities);
  }

  private void initAddCityButton() {
    addCityButton.setDisable(true); // enabled after loading an image
    addCityButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        if (cities == null) cities = FXCollections.observableArrayList();
        cities.add(new City(0, 0, 0)); // default values for city size, x and y positions are all set to 0
        cityTableView.setItems(cities);
      }
    });
  }

  private void initRemoveCityButton() {
    removeCityButton.setDisable(true); // enabled after loading an image
    removeCityButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        City citySelected = (City) cityTableView.getSelectionModel().getSelectedItem();
        if (citySelected != null) {
          cities.remove(citySelected);
          redrawCities();
        }
      }
    });
  }

  private void initGenerateButton() {
    generateButton.setDisable(true); // enabled after loading an image
    generateButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        generateStreets();
      }
    });
  }

  private void generateStreets() {
    // Console logging
    Image img = imageView.getImage();
    double width = img.getWidth();
    double height = img.getHeight();

    System.out.println("[INFO] Map\tWidth: " + width + "px\tHeight: " + height + "px.");

    for (int i = 0; i < cities.size(); i++) {
      City city = cities.get(i);
      System.out.println("[INFO] City " + (i + 1) + "\tSize: " + city.getSize() + "\tX: " + city.getX() + "\tY: " + city.getY() + ".");
    }

    System.out.println("[INFO] Generating streets...");

    // TODO Instantiate necessary classes and generate the streets.

  }

  private void showImage(Image img) {
    if (imgOriginal == null) {
      imgOriginal = img;
    }

    BufferedImage bImg  = SwingFXUtils.fromFXImage(img, null);

    drawBufferedImageOnImageView(bImg);
  }

  private void drawBufferedImageOnImageView(BufferedImage bufferedImg) {
    imageView.setImage(SwingFXUtils.toFXImage(bufferedImg, null));
  }

  private void redrawCities() {
    BufferedImage bufferedImg = SwingFXUtils.fromFXImage(imgOriginal, null);

    for (City c : cities) {
      drawCityOnBufferedImage(bufferedImg, c);
    }

    drawBufferedImageOnImageView(bufferedImg);
  }

  private void drawCityOnBufferedImage(BufferedImage bufferedImg, City c) {
    if (bufferedImg != null) {
      int x = c.getX();
      int y = c.getY();
      int size = c.getSize();
      Graphics2D g2d = bufferedImg.createGraphics();
      g2d.setColor(Color.RED); // cities are colored red

      if (size != 0) {
        int width = size / 2;
        int height = size / 2;
        g2d.fillArc(x, y, width, height, 0, 360);
      }

      g2d.dispose();
    }
  }

  private Image showFileChooser() {
    FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter jpgExtentionFilter = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg");
    FileChooser.ExtensionFilter pngExtentionFilter = new FileChooser.ExtensionFilter("PGN file (*.png)", "*.png");
    fileChooser.getExtensionFilters().addAll(jpgExtentionFilter, pngExtentionFilter);
    File chosenFile = fileChooser.showOpenDialog(getPrimaryStage());

    if (chosenFile != null) {
      System.out.println("[INFO] Opening file " + chosenFile.getPath());

      // Enable buttons after successfully opening image
      addCityButton.setDisable(false);
      generateButton.setDisable(false);
      removeCityButton.setDisable(false);

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
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private Node findNodeById(String id) {
    return getPrimaryStage().getScene().lookup(id);
  }

  private Stage getPrimaryStage() {
    return primaryStage;
  }
}