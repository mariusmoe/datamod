package controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import dagbokapplication.UserApplication;
import db.TrainingFetch;
import db.ExerciseForTrainingFetch;
import helper.MyEnterEventHandler;
import helper.MyEnterStringTableRow;
import helper.MyEventHandler;
import helper.MyIntegerTableCell;
import helper.MyStringTableCell;
import helper.TrainingRow;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
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
	@SuppressWarnings("unchecked")
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
		performanceColumn.setCellFactory(stringCellFactory);
		
		goalColumn.setCellValueFactory(new PropertyValueFactory<TrainingRow, Integer>("maal"));
		goalColumn.setCellFactory(stringCellFactory);
	
		
		ObservableList<TrainingRow> observablelist = FXCollections.observableArrayList(dataset);
		
		trainingTable.setItems(observablelist);	
		

		trainingTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    
			System.out.println("AAAAAAAAAAAA: "+newSelection.getId());
			
			trainingTable.setOnKeyPressed(new EventHandler<KeyEvent>() {
			    @Override
			    public void handle(KeyEvent keyEvent) {
			        if (keyEvent.getCode() == KeyCode.ENTER)  {
			            System.out.println("ppppppppppp");
			            popup(newSelection.getTrueTime(), newSelection.getId());
			        }
			    }
			});
			 
		});
		
		
		         

		

		
//        Callback<TableColumn, TableCell> stringEnterCellFactory =
//                new Callback<TableColumn, TableCell>() {
//            @Override
//            public TableCell call(TableColumn p) {
//            	MyEnterStringTableRow cell = new MyEnterStringTableRow();
//                cell.addEventFilter(KeyCode.ENTER, new MyEnterEventHandler());
//                return cell;
//            }
        //};
	}
	
	
	public void popup(String s, String id){
		
		// call to a db function -> what kind if exercises was carried out during this training?
		ExerciseForTrainingFetch eftf = new ExerciseForTrainingFetch();
		try {
			eftf.readDataBase(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<ArrayList> allOvelser = eftf.getExForTraining();
		
		StringBuilder bStr = new StringBuilder();
		for (int i=0;i<allOvelser.get(0).size();i++){
			bStr.append("Ovelse: " + allOvelser.get(0).get(i) + " Belastning: " +allOvelser.get(1).get(i) +
					" Sett: " + allOvelser.get(2).get(i) + " Repetisjoner: " + allOvelser.get(3).get(i) + " Beskrivelse: " + allOvelser.get(4).get(i) + 
					" Kategori: " + allOvelser.get(5).get(i) + "\n" + "-----------------\n");
		}
		System.out.println(bStr);
		
		
		Group root = new Group();
		Scene dialogScene = new Scene(root, 400, 300);
		Label label3 = new Label(bStr.toString());
		label3.setWrapText(true);
		label3.setFont(new Font("Arial", 12));
		label3.setMaxSize(380, 300);
		
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(UserApplication.primaryStage);
        //VBox dialogVbox = new VBox(20);
        //dialogVbox.getChildren().add(new Text("This is a Dialog: "+ s));
        //VBox dialogVbox2 = new VBox(40);
        //dialogVbox2.getChildren().add(new Text(bStr.toString()));
        Button cancel = new Button("Cancel");
        cancel.setCancelButton(true);
        cancel.setOnAction((actionEvent) -> dialog.close());
        GridPane gridpane = new GridPane();
        gridpane.setMaxSize(400, 300);
        gridpane.setHgap(10); //horizontal gap in pixels => that's what you are asking for
        gridpane.setVgap(10); //vertical gap in pixels
        gridpane.setPadding(new Insets(10, 10, 10, 10)); //margins around the whole grid
                                                     //(top/right/bottom/left)
        ScrollPane s1 = new ScrollPane();
        s1.setPrefSize(380, 300);
        s1.setHbarPolicy(ScrollBarPolicy.NEVER);
        Pane p1 = new Pane();
        //p1.setMaxSize(380, 300);
        //p1.addAll(s1);
        s1.setContent(label3);
        gridpane.add(cancel, 0, 3); // column=2 row=1
        gridpane.add(s1, 0, 1);  // column=3 row=1
        gridpane.setMaxSize(400, 300);
        root.getChildren().addAll(gridpane);
        
        
        dialog.setScene(dialogScene);
        dialog.show();            
         
	}
	

	


}
