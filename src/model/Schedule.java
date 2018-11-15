package model;
import java.util.*;

public class Schedule {
	private ArrayList<String> days = new ArrayList<String>();
	private Date startTime;
	private Date endTime;

	public ArrayList<String> getDays() {
		return days;
	}
	public void setDays(ArrayList<String> days) {
		this.days = days;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
