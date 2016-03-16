package chr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Maal.fxml"));
		primaryStage.setTitle("Maal");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
		
	    MaalController controll= FXMLLoader.getController();

	
	    //Set Data to FXML through controller
	    MaalController.fillMaal();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}