package edu.weit.project;

import java.io.IOException;
import java.util.logging.Level;

public class App {

	public static void main(String[] args) throws IOException {
		
		Model model = new Model();
		
		View console = new ConsoleView();
		
		Logger logger = new FileLogger(Level.WARNING);
		
		AppCore appCore = new AppCore(console);
		
		appCore.addView(logger);

		appCore.someException();

	}

}
