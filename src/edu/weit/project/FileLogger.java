package edu.weit.project;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

public class FileLogger extends Logger {
	public static final String logFilePatch = "log.txt";
	private File file;
	private FileOutputStream fileOutputStream;

	public FileLogger(Level level) throws IOException {
		super(level);
		initialize();
		appendHeadline();
	}

	private void initialize() throws IOException {
		file = new File(logFilePatch);
		fileOutputStream = new FileOutputStream(file, true);
	}

	@Override
	protected void appendHeadline() throws IOException {
		log("\n\n>> START @ " + getDate() + "\n");

		log("\tJava runtime info:");
		log("\t\tName: " + System.getProperty("java.runtime.name"));
		log("\t\tVersion: " + System.getProperty("java.runtime.version"));

		log("\tOS info:");
		log("\t\tArch: " + System.getProperty("os.arch"));
		log("\t\tName: " + System.getProperty("os.name"));

		log("\tUser info:");
		log("\t\tName: " + System.getProperty("user.name"));

		log("\n");
	}

	protected void log(String message) {
		try {
			fileOutputStream.write(message.getBytes());
			fileOutputStream.write("\n".getBytes());
			fileOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void show(String text) {
		if (level == Level.ALL) {
			log(text);
		}
	}

	public void handle(Exception ex) {
		if (level != Level.OFF) {
			log(getDate() + " @ " + ex.toString());
		}
	}

	private String getDate() {
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"dd-MM-yyyy HH:mm");
		return simpleDateFormat.format(date, new StringBuffer(),
				new FieldPosition(0)).toString();
	}

	@Override
	protected void finalize() throws Throwable {
		fileOutputStream.close();
		super.finalize();
	}
}
