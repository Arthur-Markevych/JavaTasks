package ua.khpi.markevich.Practice6.part3;

/**
 * The model of car.
 * 
 */
public class Car {

	/**
	 * Car model.
	 */
	private String model;

	/**
	 * Car number.
	 */
	private String number;

	/**
	 * Constructor creates new instance.
	 * 
	 * @param model
	 *            name of the car
	 * @param number
	 *            car number
	 */
	public Car(final String model, final String number) {
		this.model = model;
		this.number = number;
	}

	/**
	 * Print Car instance to console.
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return model + " [" + number + "]";
	}

}
