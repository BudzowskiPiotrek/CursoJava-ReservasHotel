package hotel;

import java.time.LocalDate;

public interface LasFechas {

	static boolean overlaps(LocalDate start1, LocalDate stop1, LocalDate start2, LocalDate stop2) {
		return (start1.isBefore(stop2) && (start2.isBefore(stop1)));

	}
}
