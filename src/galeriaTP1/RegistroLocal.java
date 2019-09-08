package galeriaTP1;

public class RegistroLocal {
	private String apellido;
	private String nombre;
	private int local;
	private String rubro;
	private int facturacion;
	
	public RegistroLocal (String data[]) {
		this.setApellido(data[0]);
		this.setNombre(data[1]);
		this.setLocal(data[2]);
		this.setRubro(data[3]);
		this.setFacturacion(data[4]);
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public int getLocal() {
		return local;
	}
	
	public void setLocal(int local) {
		this.local = local;
	}
	
	public void setLocal(String local) {
		this.local = Integer.parseInt(local);
	}
	
	public String getRubro() {
		return rubro;
	}
	
	public void setRubro(String rubro) {
		this.rubro = rubro;
	}
	
	public int getFacturacion() {
		return facturacion;
	}
	
	public void setFacturacion(int facturacion) {
		this.facturacion = facturacion;
	}
	
	public void setFacturacion(String facturacion) {
		this.facturacion = Integer.parseInt(facturacion);
	}
	
	@Override
	public String toString() {
		return this.apellido + "\t" + this.nombre + "\t" + this.local + "\t" + this.rubro + "\t" + this.facturacion;
	}
}
