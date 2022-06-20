package qtx.persistencia;

import java.sql.SQLException;
import java.util.Date;

public class ManejadorExcepciones {

	public static PersistenciaException crearExcepcion(Exception sqlex, String sql, 
			String bd, 	String metodo, String recomendacion, String mensaje) {
		PersistenciaException pex = new PersistenciaException(mensaje, sqlex);
		pex.setBaseDatos(bd);
		pex.setFechaError(new Date());
		pex.setSentenciaSql(sql);
		pex.setMetodo(metodo);
		pex.setRecomendacion(recomendacion);
		return pex;
	}
	public static String toString(PersistenciaException pex) {
		StringBuilder sb = new StringBuilder();
		sb.append("Error:");
		sb.append(pex.getMessage());
		sb.append("\nfecha:")
		  .append(pex.getFechaError())
		  .append("\nmétodo:")
		  .append(pex.getMetodo())
		  .append("\nbase de datos:")
		  .append(pex.getBaseDatos())
		  .append("\nsentencia sql:")
		  .append(pex.getSentenciaSql())
		  .append("\nrecomendación:")
		  .append(pex.getRecomendacion());
		
		Throwable causaI = pex.getCause();
		while(causaI != null) {
			sb.append("\nCausa subyacente:")
			  .append(causaI.getClass().getName())
			  .append("\nmensaje:")
			  .append(causaI.getMessage());
			if (causaI instanceof SQLException) {
				SQLException sqlex = (SQLException) causaI;
				sb.append("\nSQLState:")
				  .append(sqlex.getSQLState())
				  .append("\nError code:" + sqlex.getErrorCode());
			}
			causaI = causaI.getCause();
		}
		return sb.toString();
	}
}
