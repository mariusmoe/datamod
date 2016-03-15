package Model;

import java.time.LocalDate;
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

	private List<String> exerciseIds = new ArrayList<>();

	

}
