package edu.weit.project;

public class ConsoleView implements View {
	
	@Override
	public void show(String text) {
		System.out.println(text);
	}
	
	@Override
	public void handle(Exception ex) {
		System.err.println(ex);
	}
}
