package hotel.habitacion;

import java.util.Objects;

import hotel.dinero.Precios;

public class Suite extends Habitacion {

	private String nombre;
	private int numPlazas;
	private String serExtra;

	public Suite(int numero, String descripcion, String nombre, int numPlazas, String serExtra) {
		super(numero, Precios.PRECIO_UNA_SUITE, descripcion);
		this.nombre = nombre;
		this.numPlazas = numPlazas;
		this.serExtra = serExtra;

	}

	@Override
	public String toString() {
		return "Suite [nombre=" + nombre + ", numPlazas=" + numPlazas + ", serExtra=" + serExtra + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(nombre, numPlazas, serExtra);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Suite other = (Suite) obj;
		return Objects.equals(nombre, other.nombre) && numPlazas == other.numPlazas
				&& Objects.equals(serExtra, other.serExtra);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumPlazas() {
		return numPlazas;
	}

	public void setNumPlazas(int numPlazas) {
		this.numPlazas = numPlazas;
	}

	public String getSerExtra() {
		return serExtra;
	}

	public void setSerExtra(String serExtra) {
		this.serExtra = serExtra;
	}

}
