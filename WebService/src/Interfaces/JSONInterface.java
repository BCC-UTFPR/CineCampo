package Interfaces;

import java.sql.ResultSet;

public interface JSONInterface {
	String getJSON(ResultSet r, String tipo);
}
