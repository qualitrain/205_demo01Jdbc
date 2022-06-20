package qtx.test;

import java.util.Date;
import java.util.List;

import qtx.dto.Articulo;
import qtx.dto.Persona;
import qtx.persistencia.DaoBasico;
import qtx.persistencia.ManejadorExcepciones;
import qtx.persistencia.PersistenciaException;

public class TestDAO {

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
		DaoBasico dao = new DaoBasico();
		Persona persona = new Persona(8,"Karla Jara De la Mora","Av. Torres 125-J", new Date());
		int nIns = dao.insertarPersona(persona);
		System.out.println("Se insertó " + nIns + " personas");
	}
	
	public static void testGetArticulosTodos() {
		DaoBasico dao = new DaoBasico();
		List<Articulo> articulos = dao.getArticulosTodos();
		for(Articulo artI: articulos) {
			System.out.println(artI);
		}
	}
	public static void testGetArticuloXID() {
		DaoBasico dao = new DaoBasico();
		Articulo art = dao.getArticuloXID("A-23");
		System.out.println(art);
	}

}
