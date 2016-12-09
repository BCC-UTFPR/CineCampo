package Interfaces;

import java.sql.ResultSet;

import org.jvnet.hk2.annotations.Contract;

@Contract
public interface JSONInterface {
	String getJSON(ResultSet r, String tipo);
}
