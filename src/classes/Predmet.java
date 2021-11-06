package classes;

import java.util.ArrayList;

public class Predmet {
	
	private String sifra;
	private String naziv;
	private Semestar semestar;
	private byte godinaStudija;
	private Profesor profesor;
	private byte espb;
	private ArrayList<Student> studentiPolozili;
	private ArrayList<Student> studentiNisuPolozili;
	
	public enum Semestar {LETNJI, ZIMSKI}

	public Predmet() {}

	public Predmet(String sifra, String naziv, Semestar semestar, byte godinaStudija, Profesor profesor, byte espb,
			ArrayList<Student> studentiPolozili, ArrayList<Student> studentiNisuPolozili) {
		super();
		this.sifra = sifra;
		this.naziv = naziv;
		this.semestar = semestar;
		this.godinaStudija = godinaStudija;
		this.profesor = profesor;
		this.espb = espb;
		this.studentiPolozili = studentiPolozili;
		this.studentiNisuPolozili = studentiNisuPolozili;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Semestar getSemestar() {
		return semestar;
	}

	public void setSemestar(Semestar semestar) {
		this.semestar = semestar;
	}

	public byte getGodinaStudija() {
		return godinaStudija;
	}

	public void setGodinaStudija(byte godinaStudija) {
		this.godinaStudija = godinaStudija;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public byte getEspb() {
		return espb;
	}

	public void setEspb(byte espb) {
		this.espb = espb;
	}

	public ArrayList<Student> getStudentiPolozili() {
		return studentiPolozili;
	}

	public void setStudentiPolozili(ArrayList<Student> studentiPolozili) {
		this.studentiPolozili = studentiPolozili;
	}

	public ArrayList<Student> getStudentiNisuPolozili() {
		return studentiNisuPolozili;
	}

	public void setStudentiNisuPolozili(ArrayList<Student> studentiNisuPolozili) {
		this.studentiNisuPolozili = studentiNisuPolozili;
	}
	
	
}
