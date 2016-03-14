package dagbokapplication;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main application
 *
 */
public class UserApplication extends Application{
	
	/**
	 * Start application!
	 */
	public static Stage primaryStage;
	@Override
	public void start(Stage primaryStage) throws Exception{
		this.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/UserMainFrame.fxml"));
        primaryStage.getIcons().add(new Image("/dagbokapplication/RunningMan.ico"));
        primaryStage.setTitle("Show training");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMinHeight(625);
        primaryStage.setMinWidth(650);
        primaryStage.show();
        
        
        
        
    }

	/**
	 * Launch
	 * @param args None needed
	 */
    public static void main(String[] args) {
        launch(args);
    }
}
