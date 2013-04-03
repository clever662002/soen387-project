package ts;

import app.FrontController;

public abstract class DatabaseSetup {

	public static void main(String[] args){
		
		//setupLogging();
		FrontController.prepareDbRegistry();
		FrontController.setupUoW();
		//dropAllTables();
		//createAllTables();
		
		//TODO : finish this
		
	}
	
	public static void setup(){
		
	}
	
}
