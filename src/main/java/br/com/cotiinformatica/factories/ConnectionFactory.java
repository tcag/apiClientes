package br.com.cotiinformatica.factories;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	/*
	 * Método estático para abrir conexão com o banco de dados
	 * e retornar esta conexão..
	 */
	public static Connection getConnection() throws Exception {
		
		//parâmetros para conexão com o banco de dados
		String driver = "org.postgresql.Driver";
		String url = "jdbc:postgresql://localhost:5432/bd_apiClientes";
		String user = "postgres";
		String password = "coti";
		
		//abrir e retornar a conexão
		Class.forName(driver);
		return DriverManager.getConnection(url, user, password);
	}
}
