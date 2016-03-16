package helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;

import controllers.GetTrainingController;
import javafx.beans.property.SimpleStringProperty;

/**
 * This class represent a row of information in the table-view {@link GetTrainingController}.
 * @author moe
 *
 */
public final class TrainingRow {
	 
    private final SimpleStringProperty time;
    private final SimpleStringProperty trueTime;
    private final SimpleStringProperty duration;
    private final SimpleStringProperty personal;
    //private final SimpleStringProperty maal;
    private final SimpleStringProperty performance;
    private final SimpleStringProperty maal;
    private final SimpleStringProperty id;
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public TrainingRow(LocalDateTime time, ArrayList col) {
        this.time = new SimpleStringProperty((String) time.format(formatter)); 
        this.trueTime = new SimpleStringProperty((String) time.toString());//.format(formatter)); 
        this.duration = new SimpleStringProperty(col.get(0).toString());
        this.personal = new SimpleStringProperty(col.get(1).toString());
        //this.maal = new SimpleStringProperty(lName);
        this.performance = new SimpleStringProperty(col.get(2).toString());
        this.maal = new SimpleStringProperty(col.get(3).toString());
        this.id = new SimpleStringProperty(col.get(4).toString());
    }

    public String getTime() {
        return time.get();
    }

    public void setTime(LocalDateTime t) {
        time.set(t.toString());
    }
    
    public String getTrueTime() {
        return trueTime.get();
    }

    public void setTrueTime(LocalDateTime t) {
    	trueTime.set(t.toString());
    }

    public String getDuration() {
        return duration.get();
    }

    public void setDuration(Integer i) {
        duration.set(i.toString());
    }

    public String getPersonal() {
        return personal.get();
    }

    public void setPersonal(Integer i) {
        personal.set(i.toString());
    }
    
    public String getPerformance() {
        return performance.get();
    }

    public void setPerformance(Integer i) {
    	performance.set(i.toString());
    }
    
    public String getMaal() {
        return maal.get();
    }

    public void setMaal(Integer i) {
    	maal.set(i.toString());
    }
    
    public String getId() {
        return id.get();
    }

    public void setId(Integer i) {
    	id.set(i.toString());
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TrainingRow [time=" + time + ", duration=" + duration + ", personal=" + personal + ", performance="
				+ performance + ", maal="
						+ maal + "]";
	}
    
}
