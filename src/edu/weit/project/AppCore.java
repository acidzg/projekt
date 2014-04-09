package edu.weit.project;

public class AppCore extends Controller {
	
	public AppCore(View inView) {
		super(inView);
	}
	
	public void someException() {
		handle(new Exception("Error"));
	}

}
