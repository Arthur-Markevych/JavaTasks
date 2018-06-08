package ua.khpi.markevich.SummaryTask3.model;

/**
 * Knife model.
 * 
 */
public class Knife {

	private String model;
	private String handy;
	private String type;
	private String origin;
	private boolean value;
	private Visuals visuals;

	public Knife() {
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHandy() {
		return handy;
	}

	public void setHandy(String handy) {
		this.handy = handy;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public boolean isValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	public Visuals getVisuals() {
		return visuals;
	}

	public void setVisuals(Visuals visuals) {
		this.visuals = visuals;
	}

	@Override
	public String toString() {
		return "~~~~~~~~~~~~~~~~~~~~~" + "\nmodel: " + model + "\n\t handy: " + handy + "\n\t collectible: "
				+ (value ? "yes" : "no") + visuals.toString();

	}

}
