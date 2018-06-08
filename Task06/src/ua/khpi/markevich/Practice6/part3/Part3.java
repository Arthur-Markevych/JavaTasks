package ua.khpi.markevich.Practice6.part3;

/**
 * Class demonstrate work with Parking application.
 *
 */
public final class Part3 {

	/**
	 * Default count of parking places.
	 */
	private static final int PLACES = 10;

	/**
	 * Application start point.
	 * 
	 * @param args
	 *            ignored by application
	 */
	public static void main(final String[] args) {
		Parking parking = new Parking(PLACES);
		Car car1 = new Car("Honda", "A040E");
		Car car2 = new Car("Lada", "B0002");
		Car car3 = new Car("Marcedes", "G00T1");
		Car car4 = new Car("Jaguar", "0Y34R");
		Car car5 = new Car("Mocquich", "Y0F01");

		parking.findPlace(car1);
		parking.findPlace(car2);
		parking.findPlace(car3);
		parking.findPlace(car4);
		parking.findPlace(car5);
		parking.leavePlace(car2);
		parking.leavePlace(car4);
		parking.findPlace(car4);
		System.out.println();
		parking.getParkingState();
	}

	/**
	 * Private constructor.
	 */
	private Part3() {
		// TODO Auto-generated constructor stub
	}
}
