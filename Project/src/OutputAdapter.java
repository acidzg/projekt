public interface OutputAdapter {

	public void setupConfig(Configuration config);

	public boolean storeEvents(List<Event> batch); // batch -> partia, pakiet

}
