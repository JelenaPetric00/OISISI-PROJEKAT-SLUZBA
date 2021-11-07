package classes;

import java.util.ArrayList;

public class Katedra {
	
	private String sifraKatedre;
	private String nazivKatedre;
	private Profesor sefKatedre;
	private ArrayList<Profesor> predavaci;
	
	public Katedra() {}

	public Katedra(String sifraKatedre, String nazivKatedre, Profesor sefKatedre, ArrayList<Profesor> predavaci) {
		super();
		this.sifraKatedre = sifraKatedre;
		this.nazivKatedre = nazivKatedre;
		this.sefKatedre = sefKatedre;
		this.predavaci = predavaci;
	}

	public String getSifraKatedre() {
		return sifraKatedre;
	}

	public void setSifraKatedre(String sifraKatedre) {
		this.sifraKatedre = sifraKatedre;
	}

	public String getNazivKatedre() {
		return nazivKatedre;
	}

	public void setNazivKatedre(String nazivKatedre) {
		this.nazivKatedre = nazivKatedre;
	}

	public Profesor getSefKatedre() {
		return sefKatedre;
	}

	public void setSefKatedre(Profesor sefKatedre) {
		this.sefKatedre = sefKatedre;
	}

	public ArrayList<Profesor> getPredavaci() {
		return predavaci;
	}

	public void setPredavaci(ArrayList<Profesor> predavaci) {
		this.predavaci = predavaci;
	}

	
	
	

}
