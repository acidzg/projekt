package edu.weit.project;

import java.util.ArrayList;
import java.util.Collection;

public abstract class Controller {
	protected Collection<View> views;
	
	public Controller(View inView) {
		this.views = new ArrayList<View>(1);
		this.views.add(inView);
	}

	public Controller(Collection<View> inViews) {
		this.views = new ArrayList<View>(inViews);
	}
	
	public void addView(View view) {
		this.views.add(view);
	}
	
	protected void handle(Exception ex) {
		for(View view : views) {
			view.handle(ex);
		}
	}

}
