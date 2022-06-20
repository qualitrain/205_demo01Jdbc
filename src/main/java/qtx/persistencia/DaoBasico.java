package qtx.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import qtx.dto.Articulo;
import qtx.dto.Persona;

public class DaoBasico {
	public static final String SQL_LEER_TODOS_ART = "Select * from articulo";
	public static final String BD = "ejemplosJDBC";
	public Connection getConexion() throws SQLException {
		 Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/"
					+ BD
					+ "?serverTimezone=UTC", "root", "root");
		return con;
	}
	
	public List<Articulo> getArticulosTodos(){
		List<Articulo> listArticulos = new ArrayList<Articulo>();
		try (Connection con = getConexion()){
			 Statement statement = con.createStatement();
			 ResultSet resultset = statement.executeQuery(SQL_LEER_TODOS_ART);
			 while(resultset.next()){
				 String cveArt = resultset.getString("cve_articulo");
				 String descArt = resultset.getString("descripcion");
				 double costoProv1 = resultset.getDouble("costo_prov_1");
				 double precioLista = resultset.getDouble("precio_lista");
				 listArticulos.add(new Articulo(cveArt,descArt, costoProv1, precioLista));
			 }
			
		} 
		catch (SQLException sqlex) {
			PersistenciaException pex = 
					ManejadorExcepciones.crearExcepcion(sqlex, 
							              SQL_LEER_TODOS_ART,
							              BD,
							              "getArticulosTodos()",
							              "",
							              "error reportado por la base de datos");
			throw pex;
		} 
		catch (Exception ex) {
			PersistenciaException pex = 
			ManejadorExcepciones.crearExcepcion(ex, 
		              SQL_LEER_TODOS_ART,
		              BD,
		              "getArticulosTodos()",
		              "revise instrucciones java en el método",
		              "error al intentar leer artículos NO provocado por la base de datos");
			throw pex;
		}
		return listArticulos;
	}
	public Articulo getArticuloXID(String cveArticulo) {
		final String SENTENCIA_SQL = "SELECT * FROM articulo "
								   + "WHERE cve_articulo ='" + cveArticulo + "'";
		Articulo articulo = null;
		try (Connection conBD = getConexion() ){
			Statement statementBD = conBD.createStatement();
			statementBD.execute(SENTENCIA_SQL);
			ResultSet resultSet = statementBD.getResultSet();
			if(resultSet.next()){
				articulo = new Articulo();
				articulo.setCveArticulo( resultSet.getString("cve_articulo") );
				articulo.setDescripcion( resultSet.getString("descripcion"));
				articulo.setCostoProv1( resultSet.getFloat("costo_prov_1") );
				articulo.setPrecioLista( resultSet.getFloat("precio_lista") );
			}
		}
		catch (SQLException sqlex) {
			PersistenciaException pex = 
					ManejadorExcepciones.crearExcepcion(sqlex, 
										  SENTENCIA_SQL,
							              BD,
							              "getArticuloXID(" + cveArticulo + ")",
							              "",
							              "error reportado por la base de datos");
			throw pex;
		} 
		catch (Exception ex) {
			PersistenciaException pex = 
			ManejadorExcepciones.crearExcepcion(ex, 
					  SENTENCIA_SQL,
		              BD,
		              "getArticuloXID(" + cveArticulo + ")",
		              "revise instrucciones java en el método",
		              "error al intentar insertar artículo, NO provocado por la base de datos");
			throw pex;
		}
		return articulo;
	}
	public Persona getPersonaXID(int idPersona) {
		final String SENTENCIA_SQL = "SELECT * FROM persona "
								   + "WHERE id_persona =" + idPersona + "";
		Persona persona = null;

		try (Connection conBD = getConexion()){
			Statement statementBD = conBD.createStatement();
			statementBD.execute(SENTENCIA_SQL);
			ResultSet resultSet = statementBD.getResultSet();
			if(resultSet.next()){
				String nombre = resultSet.getString("nombre");			
				String direccion = resultSet.getString("direccion");
				Date fechaNacimiento = resultSet.getDate("fecha_nacimiento");

				persona = new Persona(idPersona,nombre,direccion,fechaNacimiento);
			}
		} 
		catch (SQLException sqlex) {
			PersistenciaException pex = 
					ManejadorExcepciones.crearExcepcion(sqlex, 
										  SENTENCIA_SQL,
							              BD,
							              "getPersonaXID(" + idPersona + ")",
							              "",
							              "error reportado por la base de datos");
			throw pex;
		} 
		catch (Exception ex) {
			PersistenciaException pex = 
			ManejadorExcepciones.crearExcepcion(ex, 
					  SENTENCIA_SQL,
		              BD,
		              "getPersonaXID(" + idPersona + ")",
		              "revise instrucciones java en el método",
		              "error al intentar insertar artículo, NO provocado por la base de datos");
			throw pex;
		}
		return persona;
	}
	public int insertarPersona(Persona persona) {
		int numAfectacionesBD = 0;
		
		GregorianCalendar fechaNac = new GregorianCalendar();
		fechaNac.setTime(persona.getFechaNacimiento());
		int dia = fechaNac.get(GregorianCalendar.DAY_OF_MONTH);
		int mes = fechaNac.get(GregorianCalendar.MONTH) + 1;
		int anio = fechaNac.get(GregorianCalendar.YEAR);
		
		final String INSERT_SQL = "INSERT INTO persona "
				+ "(id_persona, nombre, direccion, fecha_nacimiento) "
				+ "VALUES ("
				+ persona.getIdPersona() + ",'"
				+ persona.getNombre() + "','"
				+ persona.getDireccion() + "','"
				+ anio + "-" + mes + "-" + dia 
				+ "')";
		
		try (Connection conBD = getConexion()){
			Statement stmt = conBD.createStatement();
			numAfectacionesBD = stmt.executeUpdate( INSERT_SQL );
			
		} 
		catch (SQLException sqlex) {
			PersistenciaException pex = 
					ManejadorExcepciones.crearExcepcion(sqlex, 
										  INSERT_SQL,
							              BD,
							              "insertarPersona(" + persona + ")",
							              "",
							              "error reportado por la base de datos");
			throw pex;
		} 
		catch (Exception ex) {
			PersistenciaException pex = 
			ManejadorExcepciones.crearExcepcion(ex, 
					  INSERT_SQL,
		              BD,
		              "insertarPersona(" + persona + ")",
		              "revise instrucciones java en el método",
		              "error al intentar insertar persona, NO provocado por la base de datos");
			throw pex;
		}
		return numAfectacionesBD;
	}

}
