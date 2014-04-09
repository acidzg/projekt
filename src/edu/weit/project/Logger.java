package edu.weit.project;

import java.io.IOException;
import java.util.logging.Level;

public abstract class Logger implements View {
	public final Level level;
	
	public Logger(Level level) {
		this.level = level;
	}
	
	protected abstract void appendHeadline() throws IOException;
	protected abstract void log(String message);
}
