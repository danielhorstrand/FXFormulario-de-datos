package application;

public class Persona {
	

	private String nombre;
	private String apellido;
	private String email;
	private Character sexo;
	private boolean soltero;
	
	
	public Persona(String nombre, String apellido, String email, Character sexo, boolean soltero) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.sexo = sexo;
		this.soltero = soltero;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Character getSexo() {
		return sexo;
	}
	public void setSexo(Character sexo) {
		this.sexo = sexo;
	}
	public boolean isSoltero() {
		return soltero;
	}
	public void setSoltero(boolean soltero) {
		this.soltero = soltero;
	}

	public String toString() {
		return "Persona [nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", sexo=" + sexo
				+ ", soltero=" + soltero + "]";
	}
	
	

}
