package chr;

import javafx.application.Application;
import javafx.stage.Stage;

public class Maal extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
	
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("Maal.fxml"));
	    Parent root = loader.load();
	
	    MyController myController = loader.getController();
	
	    Scene scene = new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	
	    //Set Data to FXML through controller
	    MaalController.fillMaal();
		}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		launch(args);
		
	}
}