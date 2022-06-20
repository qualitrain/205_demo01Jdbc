package qtx.dto;

public class Articulo {
	private String cveArticulo;
	private String descripcion;
	private double costoProv1;
	private double precioLista;
	
	public Articulo(String cveArticulo, String descripcion, double costoProv1, double precioLista) {
		super();
		this.cveArticulo = cveArticulo;
		this.descripcion = descripcion;
		this.costoProv1 = costoProv1;
		this.precioLista = precioLista;
	}
	public Articulo() {
		super();
	}
	public String getCveArticulo() {
		return cveArticulo;
	}
	public void setCveArticulo(String cveArticulo) {
		this.cveArticulo = cveArticulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getCostoProv1() {
		return costoProv1;
	}
	public void setCostoProv1(double costoProv1) {
		this.costoProv1 = costoProv1;
	}
	public double getPrecioLista() {
		return precioLista;
	}
	public void setPrecioLista(double precioLista) {
		this.precioLista = precioLista;
	}
	@Override
	public String toString() {
		return "Articulo [cveArticulo=" + cveArticulo + ", descripcion=" + descripcion + ", costoProv1=" + costoProv1
				+ ", precioLista=" + precioLista + "]";
	}
	
}
