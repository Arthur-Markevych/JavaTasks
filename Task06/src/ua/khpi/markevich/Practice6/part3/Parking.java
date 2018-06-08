package ua.khpi.markevich.Practice6.part3;

/**
 * The class represent car parking.
 * 
 * @author Arthur Markevich
 *
 */
public class Parking {

	/**
	 * Size of parking places.
	 */
	private Car[] places;

	/**
	 * Create new parking with special size.
	 * 
	 * @param placesCount
	 *            parking size
	 */
	public Parking(final int placesCount) {
		places = new Car[placesCount];
	}

	/**
	 * Find available parking place and take it.
	 * 
	 * @param car
	 *            to place to the parking
	 */
	public void findPlace(final Car car) {
		System.out.print(car + " : ");
		for (int i = 0; i < places.length; i++) {
			if (places[i] == null) {
				places[i] = car;
				System.out.println("[ " + (i + 1) + " place found] ");
				break;
			} else {
				System.out.print("[ " + (i + 1) + " ] ");
			}
		}
	}

	/**
	 * The car leaves self parking place and the parking get one more available
	 * place.
	 * 
	 * @param car
	 *            which will leave the parking.
	 */
	public void leavePlace(final Car car) {
		for (int i = 0; i < places.length; i++) {
			if (places[i] != null && places[i].equals(car)) {
				places[i] = null;
				System.out.println("Car " + car + " left " + (i + 1) + " place");
				break;
			}
		}
	}

	/**
	 * The method display parking state.
	 */
	public void getParkingState() {
		for (int i = 0; i < places.length; i++) {
			if (places[i] != null) {
				System.out.println("[ " + (i + 1) + ": " + places[i] + " ] ");
			} else {
				System.out.println("[ " + (i + 1) + ": " + "is free ] ");
			}
		}
	}

}
