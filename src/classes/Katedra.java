package classes;

import java.util.ArrayList;

public class Katedra {
	
	private String sifraKatedre;
	private String nazivKatedre;
	private Professor sefKatedre;
	private ArrayList<Professor> predavaci;
	
	public Katedra() {}

	public Katedra(String sifraKatedre, String nazivKatedre, Professor sefKatedre, ArrayList<Professor> predavaci) {
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

	public Professor getSefKatedre() {
		return sefKatedre;
	}

	public void setSefKatedre(Professor sefKatedre) {
		this.sefKatedre = sefKatedre;
	}

	public ArrayList<Professor> getPredavaci() {
		return predavaci;
	}

	public void setPredavaci(ArrayList<Professor> predavaci) {
		this.predavaci = predavaci;
	}

	
	
	

}
