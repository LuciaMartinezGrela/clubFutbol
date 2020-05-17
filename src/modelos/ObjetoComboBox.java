package modelos;

public class ObjetoComboBox {
	String nombre;
	int id;
	
	//constructor
	public ObjetoComboBox(String nombre, int id) {
		super();
		this.nombre = nombre;
		this.id = id;
	}
	
	
	//getters and setters
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	//al aplicar el metodo toString al combobox que nos interese mostrará solo el string y no la id asociada en la base de datos
	@Override
	public String toString() {
		return nombre;
	}
	
	

}
