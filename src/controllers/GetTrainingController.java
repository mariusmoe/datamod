package controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import dagbokapplication.UserApplication;
import db.TrainingFetch;
import helper.MyEventHandler;
import helper.MyIntegerTableCell;
import helper.MyStringTableCell;
import helper.TrainingRow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class GetTrainingController {

    @FXML
    private TableColumn performanceColumn;

    @FXML
    private ChoiceBox trainingBox;

    @FXML
    private TableView<TrainingRow> trainingTable;

    @FXML
    private TableColumn goalColumn;

    @FXML
    private TableColumn dateColumn;

    @FXML
    private TableColumn durationColumn;

    @FXML
    private TableColumn howColumn;
    
    public static List<TrainingRow> list;
    TrainingFetch tf = new TrainingFetch();
    
    /**
	 * Initialize data
	 */
	@FXML
	public void initialize(){
		System.out.println("controller start!");
		
		try {
			tf.readDataBase("4");
		} catch (Exception e) {
			
		}
		

		Callback<TableColumn, TableCell> integerCellFactory =
                new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn p) {
                MyIntegerTableCell cell = new MyIntegerTableCell();
                cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
                return cell;
            }
        };
 
        Callback<TableColumn, TableCell> stringCellFactory =
                new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn p) {
                MyStringTableCell cell = new MyStringTableCell();
                cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
                return cell;
            }
        };
		
		
		
		trainingTable.setEditable(false);
		System.out.println("got this far ---------->");
		HashMap<LocalDateTime, ArrayList> data = tf.getTrainingMap();
        Collection<TrainingRow> dataset = data.entrySet()
        		.stream()
        		.map(entry -> {
        			return new TrainingRow(entry.getKey(), entry.getValue());
        		}).collect(Collectors.toList());
        System.out.println("here____");
		dataset.forEach(System.out::println);
		System.out.println(tf.toString());
		
		//Put collection in a list so index makes sense(used when row clicked)
		if (dataset instanceof List)
		  list = (List)dataset;
		else
		  list = new ArrayList(dataset);
		
		dateColumn.setCellValueFactory(new PropertyValueFactory<TrainingRow, String>("time"));
		dateColumn.setCellFactory(stringCellFactory);
		
		durationColumn.setCellValueFactory(new PropertyValueFactory<TrainingRow, Integer>("duration"));
		durationColumn.setCellFactory(stringCellFactory);
		
		howColumn.setCellValueFactory(new PropertyValueFactory<TrainingRow, Integer>("personal"));
		howColumn.setCellFactory(stringCellFactory);
		
		performanceColumn.setCellValueFactory(new PropertyValueFactory<TrainingRow, Integer>("performance"));
		//performanceColumn.setCellFactory(integerCellFactory);
		performanceColumn.setCellFactory(stringCellFactory);
		
		goalColumn.setCellValueFactory(new PropertyValueFactory<TrainingRow, Integer>("maal"));
		goalColumn.setCellFactory(stringCellFactory);
		
		ObservableList<TrainingRow> observablelist = FXCollections.observableArrayList(dataset);
		
		trainingTable.setItems(observablelist);		
	}
	
	
	public void popup(String s){
		
		// call to a db function -> what kind if exercises was carried out during this training?
		
		
        
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(UserApplication.primaryStage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text("This is a Dialog: "+ s));
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
            
         
	}


}
