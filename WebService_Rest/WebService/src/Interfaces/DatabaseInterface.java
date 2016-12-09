package Interfaces;

import java.sql.Connection;

import org.jvnet.hk2.annotations.Contract;

@Contract
public interface DatabaseInterface {
	Connection conectar();
}
