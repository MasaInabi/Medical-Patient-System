package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


import javafx.application.Application;
import javafx.stage.Stage;

//Masa Ahmad 1231024


public class Main extends Application {
	@Override
	public void start(Stage steege) {
		
		MainWindow m=new MainWindow();
		m.show();
	}
	
	public static void main(String[] args) {
		System.out.println("Medical");
		 launch( args);  
	}
}

