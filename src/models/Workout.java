package models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Workout {

	public Workout(LocalDate date, LocalTime time, int duration, int form, int achievement, String note){

	}

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
