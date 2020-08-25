package fr.portolio.Tracker.models;

public class LocationStats {

	private String state;
	private String country;
	private int lastestTotalCases;
	private int diffFromPreviousDay;
	
	
	public int getDiffFromPreviousDay() {
		return diffFromPreviousDay;
	}

	public void setDiffFromPreviousDay(int diffFromPreviousDay) {
		this.diffFromPreviousDay = diffFromPreviousDay;
	}

	@Override
	public String toString() {
		return "LocationStats [state=" + state + ", country=" + country + ", lastestTotalCases=" + lastestTotalCases
				+ "]";
	}

	public LocationStats() {
		super();
	}
	
	public LocationStats(String state, String country, int lastestTotalCases) {
		super();
		this.state = state;
		this.country = country;
		this.lastestTotalCases = lastestTotalCases;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getLastestTotalCases() {
		return lastestTotalCases;
	}
	public void setLastestTotalCases(int lastestTotalCases) {
		this.lastestTotalCases = lastestTotalCases;
	}
	
	
	
}
