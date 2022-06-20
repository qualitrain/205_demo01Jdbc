package qtx.persistencia;

import java.util.Date;

public class PersistenciaException extends RuntimeException {
	private String sentenciaSql;
	private String baseDatos;
	private String metodo;
	private String recomendacion;
	private Date fechaError;
	
	public PersistenciaException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}
	public String getSentenciaSql() {
		return sentenciaSql;
	}
	public void setSentenciaSql(String sentenciaSql) {
		this.sentenciaSql = sentenciaSql;
	}
	public String getBaseDatos() {
		return baseDatos;
	}
	public void setBaseDatos(String baseDatos) {
		this.baseDatos = baseDatos;
	}
	public String getMetodo() {
		return metodo;
	}
	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}
	public String getRecomendacion() {
		return recomendacion;
	}
	public void setRecomendacion(String recomendacion) {
		this.recomendacion = recomendacion;
	}
	public Date getFechaError() {
		return fechaError;
	}
	public void setFechaError(Date fechaError) {
		this.fechaError = fechaError;
	}
	
}
