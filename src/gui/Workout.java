package gui;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Workout {

	private LocalDate date;
	private LocalTime time;
	private int duration;
	private int form;
	private int achievement;
	private String note;

	//Outdoor fields
	private int temp;
	private String weather;

	//Indoor fields
	private int spectatorsNum;
	private int airquality;

	/*Constructor which initializes only the fields that are common for both indoor and outdoor workouts. These other fields are set in separate methods that caller must use*/
	public Workout(LocalDate date, LocalTime time, int duration, int form, int achievement, String note){
		this.date = date;
		this.time = time;
		this.duration = duration;
		this.form = form;
		this.achievement = achievement;
		this.note = note;

	}

	public Timestamp getDateAsTimestamp(){
		LocalDateTime datetime = getDate().atTime(getTime());

		Timestamp timestamp = Timestamp.valueOf(datetime);
		return timestamp;
	}
	public LocalDate getDate() {
		return date;
	}

	public String getNote() {
		return note;
	}

	public int getAchievement() {
		return achievement;
	}

	public int getForm() {
		return form;
	}

	public int getDuration() {
		return duration;
	}

	public LocalTime getTime() {
		return time;
	}

	public List<String> getExerciseIds() {
		return exerciseIds;
	}


	public int getTemp() {
		return temp;
	}

	public String getWeather() {
		return weather;
	}


	public int getAirquality() {
		return airquality;
	}

	public int getSpectatorsNum() {
		return spectatorsNum;
	}


	private List<String> exerciseIds = new ArrayList<>();

	public void setIndoorFields(int spectatorsNum, int airquality){
		this.spectatorsNum = spectatorsNum;
		this.airquality = airquality;
	}
	public void setOutdoorFields(int temp, String weather){
		this.temp = temp;
		this.weather = weather;
	}


	

}
