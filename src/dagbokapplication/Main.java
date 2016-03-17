package dagbokapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {

			Parent root = FXMLLoader.load((Main.class.getResource("../gui/RootLayout.fxml")));
			Scene scene = new Scene(root);
			primaryStage.getIcons().add(new Image("/dagbokapplication/RunningMan.ico"));
			primaryStage.setScene(scene);
			primaryStage.setTitle("Registrer trening");
			primaryStage.setMinHeight(1000);
			primaryStage.setMinWidth(950);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

