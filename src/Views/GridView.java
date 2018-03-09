/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Models.Agent;
import Models.Grille;
import com.jfoenix.controls.JFXButton;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Epulapp
 */
public class GridView extends Application implements Observer {

    Grille modelgrd;
    GridPane gridPane;
    ArrayList<JFXButton> lstBtn;
    int gridsize = 5;

    @Override
    public void start(Stage primaryStage) {

        lstBtn = new ArrayList<JFXButton>();
        StackPane root = new StackPane();

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));

        gridPane = new GridPane();

        gridPane.setStyle("-fx-background-fill: black, white ;\n"
                + "-fx-background-insets: 10, 10 ;");
        JFXButton button2 = new JFXButton();
        button2.setText("reset");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Reset();
            }
        });
        button2.setStyle("-fx-background-fill: black, white ;\n"
                + "-fx-background-insets: 10, 10 ;"+"-fx-background-color: #EEEEEE;");
        button2.setMinSize(120, 120);
        button2.setMaxSize(120, 120);
        gridPane.add(button2,gridsize+1,0,1,1);
        
        
        root.getChildren().add(gridPane);
        
        Scene scene = new Scene(root, 120 * gridsize+ 100, 120 * gridsize );
        primaryStage.setTitle("Taquin");
        primaryStage.setScene(scene);
        primaryStage.show();

        PopulateGrid(gridsize, 5);
        modelgrd.startAgent();
    }

    public void Reset() {
        modelgrd.service.shutdownNow();
        gridPane.getChildren().clear();
        modelgrd.lstAgent.clear();
        lstBtn.clear();
        modelgrd.clear();

        JFXButton button2 = new JFXButton();
        button2.setText("reset");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Reset();
            }
        });
        button2.setStyle("-fx-background-fill: black, white ;\n"
                + "-fx-background-insets: 10, 10 ;"+"-fx-background-color: #AAAAAA;");
        button2.setMinSize(120, 120);
        button2.setMaxSize(120, 120);
        gridPane.add(button2,gridsize+1,0,1,1);
        
        PopulateGrid(gridsize, 5);
        modelgrd.startAgent();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void update(Observable o, Object arg) {

        Agent a = (Agent) o;
        Platform.runLater(()
                -> {
            clearTextGrid(a.getId());
        });
        Node ob = getNodeByRowColumnIndex(a.getCurrentCase().getPosX(), a.getCurrentCase().getPosY(), gridPane);
        Platform.runLater(()
                -> {

            ((Button) ob).setText(a.getId() + ":" + a.GetIsIenb().toString());

        });
    }

    public void PopulateGrid(int size, int nbagent) {

        String style = " -fx-border-color: white; " + "-fx-border-width: 3;" + "-fx-background-color: #61E79E;" + "-fx-spacing: 5px;" + "-fx-text-fill: white;";
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JFXButton button1 = new JFXButton();
                lstBtn.add(button1);
                button1.setMinSize(120, 120);
                button1.setMaxSize(120, 120);
                button1.setStyle(style);
                gridPane.add(button1, i, j, 1, 1);
            }
        }
        modelgrd = new Grille(nbagent, this);
        modelgrd.Generate(size, nbagent);
    }

    public void clearTextGrid(String idAgent) {

        if (!gridPane.getChildren().stream().anyMatch(b -> ((Button) b).getText().contains(idAgent))) {
        } else {

            Iterator it = gridPane.getChildren().stream().filter(b -> ((Button) b).getText().contains(idAgent)).iterator();
            while (it.hasNext()) {
                ((Button) it.next()).setText("--");

            }
        }

    }

    public Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if (gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
    }
}
