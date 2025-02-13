package hotel.gestiones;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import hotel.LasFechas;
import hotel.dinero.Reservas;
import hotel.habitacion.Habitacion;
import hotel.habitacion.HabitacionDoble;
import hotel.habitacion.Suite;
import hotel.personas.Cliente;
import hotel.personas.Huesped;

public class Hotel {

	private Habitacion[] habitacion;
	private Reservas[] reservas;
	private int cantidadReservas;

	public Hotel() {
		habitacion = new Habitacion[10];
		reservas = new Reservas[100];
		cantidadReservas = 0;
		inicializacion();

	}

	private void inicializacion() {
		for (int i = 0; i < 8; i++) {
			int numero = i + 1;
			String descripcion = "Habitacion doble";
			habitacion[i] = new HabitacionDoble(numero, descripcion);
		}

		String[] nomSuite = { "bla bla bla", "fuck fuck fuck" };

		for (int i = 0; i < 2; i++) {
			int numero = i + 9;
			String descripcion = "Habitacion Suite";
			String nombre = "Suite" + nomSuite[i];
			int plazas = 4;
			String serExtra = "algo mas";
			habitacion[i + 8] = new Suite(numero, descripcion, nombre, plazas, serExtra);
		}

	}

	public Reservas agregarReserva(Cliente cliente, Huesped[] huespedes, LocalDate fechaStart, int numDias,
			Habitacion habitacion) {
		if (cantidadReservas >= reservas.length) {
			reservas = Arrays.copyOf(reservas, cantidadReservas + 10);
		}
		Reservas r = new Reservas(fechaStart, numDias, cliente, huespedes, habitacion);
		reservas[cantidadReservas++] = r;
		return r;

	}

	public void mostrarReserva() {

		System.out.println("reservas del hotel");
		System.out.println("------------------");
		System.out.println();

		if (cantidadReservas == 0) {
			System.out.println("no hay reservas");
		}

		for (int i = 0; i < cantidadReservas; i++) {
			Reservas r = reservas[i];
			System.out.println("cliente " + r.getCliente().getNombre());
			System.out.println("Dni " + r.getCliente().getDni());
			System.out.println("fecha " + r.getFechaStart().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			System.out.println("salida " + r.getFechaStop().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			System.out.println("importe " + r.getImporte());
			System.out.println();
		}
		System.out.println();
	}

	public boolean habitacionDisponible(int numero, LocalDate fecha, int numeroDias) {
		boolean disponible = true;
		int i = 0;
		while (i < cantidadReservas && disponible) {
			Reservas r = reservas[i];
			if (r.getHabitacion().getNumero() == numero)
				disponible = !LasFechas.overlaps(r.getFechaStart(), r.getFechaStop(), fecha,
						fecha.plusDays(numeroDias));
			i++;
		}
		return disponible;
	}

	public Habitacion[] getHabitacionDisponible(String tipo, LocalDate fecha, int numDias) {
		Habitacion[] result = new Habitacion[10];
		int cantidad = 0;
		for (Habitacion h : habitacion) {
			if (this.habitacionDisponible(h.getNumero(), fecha, numDias))
				if (tipo.toUpperCase() == "DOBLE") {
					if (h instanceof HabitacionDoble)
						result[cantidad++] = h;
				} else {
					if (h instanceof Suite)
						result[cantidad++] = h;
				}
		}
		return Arrays.copyOf(result, cantidad);

	}

	public Habitacion[] getHabitaciones() {
		return habitacion.clone();
	}

	public void mostrarHabitaciones() {
		System.out.println("Habitaciones");
		System.out.println("------------");
		System.out.println();

		for (Habitacion h : habitacion) {
			String tipo = (h instanceof HabitacionDoble) ? "Habitacion doble" : "Suite";

			System.out.println("habitacion nº " + h.getNumero());
			System.out.println("Tipo:  " + tipo);
			System.out.println("Precio por noche: %.2f".formatted(h.getPrecio()));
			System.out.println("descripcion: " + h.getDescripcion());

			if (h instanceof Suite s) {
				System.out.println("Nombre: " + s.getNombre());
				System.out.println("Numero: " + s.getNumPlazas());
			}

			String disponibilidad = (this.habitacionDisponible(h.getNumero(), LocalDate.now(), 1)) ? "si" : "no";
			System.out.println("Disponible hoy: " + disponibilidad);
			System.out.println("");
		}

	}

	public Reservas agregarReserva(Cliente cliente, Huesped[] huespedes, LocalDate fechaStart, int numDias,
			String tipoHabitacion) {

		// buscamos si hay una habitacion disponible para nosotros hoy
		Habitacion[] disponible = getHabitacionDisponible(tipoHabitacion, fechaStart, numDias);
		Reservas r = null;

		if (disponible.length > 0) {
			// resivamos si el numero de huespedes se puede alojar
			if (tipoHabitacion.toUpperCase() == "DOBLE")
				if (huespedes.length > 1) {
					System.out.println("no se puede alojar mas de 2 en una doble");
					return r;
				} else {
					r = agregarReserva(cliente, huespedes, fechaStart, numDias, disponible[0]);
				}
			else {
				// si es suite comprobamos si camen los huespedes habiatacion a habitacion
				for (int i = 0; i < disponible.length && r == null; i++) {
					Suite s = (Suite) disponible[i];
					if (s.getNumPlazas() >= huespedes.length + 1) {
						r = agregarReserva(cliente, huespedes, fechaStart, numDias, s);
					}
				}
				if (r == null) {
					System.out.println("no hay suite con esa capacidad");
				}

			}
		} else {
			System.out.println("No hay habitacion");
		}

		return r;

	}
}
