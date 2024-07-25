package Metier;

public class Voiture {
	public int id;
	private String model;
	private String marque;
	
	public Voiture() {
		
	}
	
	public Voiture(String marque, String model) {
		this.setModel(model);
		this.setMarque(marque);
	}
	
	public Voiture(int id, String marque, String model) {
		this.id = id;
		this.setModel(model);
		this.setMarque(marque);
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}
	
	@Override public String toString() {
		return this.getMarque() + " de model " + this.getModel();
	}
}
