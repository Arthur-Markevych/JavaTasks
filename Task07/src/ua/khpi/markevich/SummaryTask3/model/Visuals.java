package ua.khpi.markevich.SummaryTask3.model;

/**
 * Visuals model.
 *
 */
public class Visuals {

	private int length;
	private int width;
	private String bladeMaterial;
	private boolean isWoodenHandler;
	private String handleMaterial;
	private boolean bloodsucker;

	@Override
	public String toString() {
		return "\n\t length: " + length + " cm" + "\n\t width: " + width + "mm" + "\n\t blade: " + bladeMaterial
				+ "\n\t handle: " + (isWoodenHandler ? "wooden (" + handleMaterial + ")" : handleMaterial + "")
				+ "\n\t bloodsink: " + bloodsucker;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getBladeMaterial() {
		return bladeMaterial;
	}

	public void setBladeMaterial(String bladeMaterial) {
		this.bladeMaterial = bladeMaterial;
	}

	public boolean isWoodenHandler() {
		return isWoodenHandler;
	}

	public void setWoodenHandler(boolean isWoodenHandler) {
		this.isWoodenHandler = isWoodenHandler;
	}

	public String getHandleMaterial() {
		return handleMaterial;
	}

	public void setHandleMaterial(String handleMaterial) {
		this.handleMaterial = handleMaterial;
	}

	public boolean isBloodsucker() {
		return bloodsucker;
	}

	public void setBloodsucker(boolean bloodsucker) {
		this.bloodsucker = bloodsucker;
	}

}
