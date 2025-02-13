package hotel.dinero;

public class Servicio implements Cobrable {

	private String nombre;
	private double precio;

	public Servicio(String nombre, double precio) {
		this.nombre = nombre;
		this.precio = precio;
	}

	@Override
	public double getImporte() {
		// TODO Auto-generated method stub
		return precio;
	}

}
