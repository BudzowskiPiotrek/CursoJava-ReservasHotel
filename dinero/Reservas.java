package hotel.dinero;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

import hotel.habitacion.Habitacion;
import hotel.personas.Cliente;
import hotel.personas.Huesped;

public class Reservas implements Cobrable {

	private LocalDate fechaStart;
	private int numDias;
	private Cliente cliente;
	private Huesped[] huesped;
	private Habitacion habitacion;

	public Reservas(LocalDate fechaStart, int numDias, Cliente cliente, Huesped[] huesped, Habitacion habitacion) {
		this.fechaStart = fechaStart;
		this.numDias = numDias;
		this.cliente = cliente;
		this.huesped = huesped;
		this.habitacion = habitacion;
	}

	public LocalDate getFechaStart() {
		return fechaStart;
	}

	public void setFechaStart(LocalDate fechaStart) {
		this.fechaStart = fechaStart;
	}

	public int getNumDias() {
		return numDias;
	}

	public void setNumDias(int numDias) {
		this.numDias = numDias;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Huesped[] getHuesped() {
		return huesped;
	}

	public void setHuesped(Huesped[] huesped) {
		this.huesped = huesped;
	}

	public Habitacion getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}

	public LocalDate getFechaStop() {
		return fechaStart.plusDays(numDias);
	}

	@Override
	public double getImporte() {
		// TODO Auto-generated method stub
		return habitacion.getPrecio() * numDias;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(huesped);
		result = prime * result + Objects.hash(cliente, fechaStart, habitacion, numDias);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservas other = (Reservas) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(fechaStart, other.fechaStart)
				&& Objects.equals(habitacion, other.habitacion) && Arrays.equals(huesped, other.huesped)
				&& numDias == other.numDias;
	}

	@Override
	public String toString() {
		return "ReservaPapi [fechaStart=" + fechaStart + ", numDias=" + numDias + ", cliente=" + cliente + ", huesped="
				+ Arrays.toString(huesped) + ", habitacion=" + habitacion + "]";
	}

}
