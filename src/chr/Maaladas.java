package chr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class Maaladas extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
	
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("gui.Maal.fxml"));
	    Parent root = loader.load();
	
	    MaalController myController = loader.getController();
	
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