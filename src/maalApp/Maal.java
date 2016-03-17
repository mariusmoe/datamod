package maalApp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.sql.Date;

public class Maal {
	
	public int id;
	public LocalDate fraDato;
	public LocalDate oppnaaddDato;
	public String maal;
	public LocalDate tilDato;
	
	public String toString(){
		return this.id+": Fra: "+this.fraDato+" Til: "+this.tilDato+" Oppnaadd: "+this.oppnaaddDato+" Maal: "+this.maal;
	}
	
	public static ArrayList<Maal> fromResultSet(ResultSet resultSet) throws SQLException{
		ArrayList<Maal> maalList = new ArrayList<Maal>();
		
		while (resultSet.next()){
			maalList.add(fromSingleResultSetRow(resultSet));			
		}
		
		return maalList;
	}
	private static Maal fromSingleResultSetRow(ResultSet resultSet) throws SQLException{
		Maal maal = new Maal();
		
		maal.id = resultSet.getInt("maal_id");
		
		Date sqlFraDato = resultSet.getDate("fra_dato");
		Date sqlOppnaaddDato = resultSet.getDate("oppnaadd_dato");
		Date sqlTilDato = resultSet.getDate("til_dato");
		
		maal.fraDato = sqlFraDato == null ? null : sqlFraDato.toLocalDate();
		maal.oppnaaddDato = sqlOppnaaddDato == null ? null : sqlOppnaaddDato.toLocalDate();
		maal.maal = resultSet.getString("maal");
		maal.tilDato = sqlTilDato == null ? null : sqlTilDato.toLocalDate();
		
		return maal;
	}
}
