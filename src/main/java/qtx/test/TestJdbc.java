package qtx.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import qtx.dto.Articulo;
import qtx.dto.Persona;
import qtx.persistencia.GestorBD;
import qtx.persistencia.ManejadorExcepciones;
import qtx.persistencia.PersistenciaException;

public class TestJdbc {

	public static void main(String[] args)  {
		try {
			testGetArticulosTodos();
			testGetArticuloXID();
			testInsertarPersona();
		}
		catch(PersistenciaException pex) {
			System.out.println(ManejadorExcepciones.toString(pex));
		}
	}
	private static void testInsertarPersona() {
		Persona persona = new Persona(7,"Ramiro Sosa Matus","San Luis 99", new Date());
		int nIns = GestorBD.insertarPersona(persona);
		System.out.println("Se insertó " + nIns + " personas");
	}
	public static void testGetArticulosTodos() {
		List<Articulo> articulos = GestorBD.getArticulosTodos();
		for(Articulo artI: articulos) {
			System.out.println(artI);
		}
	}
	public static void testGetArticuloXID() {
		Articulo art = GestorBD.getArticuloXID("A-23");
		System.out.println(art);
	}
	private static void testConsultaBasica() throws SQLException {
		Connection con = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/ejemplosJDBC?serverTimezone=UTC", "root", "root");
		 Statement statement = con.createStatement();
		 ResultSet resultset = statement.executeQuery("Select * from articulo");
		 while(resultset.next()){
			 System.out.println("cve_articulo: " + resultset.getString("cve_articulo"));
		 }
		 statement.close();
		 con.close();
	}

}
