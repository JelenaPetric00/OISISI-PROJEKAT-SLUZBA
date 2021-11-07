package classes;

import java.time.LocalDate;
import java.util.ArrayList;

public class Student {
	
	private String ime;
	private String prezime;
	private LocalDate datumRodjenja;
	private String adresa;
	private String telefon;
	private String email;
	private String brojIndeksa;
	private short godinaUpisa;
	private byte trenutnaGodinaStudija;
	private Status status;
	private float prosecnaOcena;
	private ArrayList<Ocena> spisakPolozenih;
	private ArrayList<Predmet> spisakNepolozenih;
	
	public enum Status  {B, S}

	
	public Student() {}
	
	public Student(String ime, String prezime, LocalDate datumRodjenja, String adresa, String telefon, String email,
			String brojIndeksa, short godinaUpisa, byte trenutnaGodinaStudija, Status status, float prosecnaOcena,
			ArrayList<Ocena> spisakPolozenih, ArrayList<Predmet> spisakNepolozenih) {
		
		this.ime = ime;
		this.prezime = prezime;
		this.datumRodjenja = datumRodjenja;
		this.adresa = adresa;
		this.telefon = telefon;
		this.email = email;
		this.brojIndeksa = brojIndeksa;
		this.godinaUpisa = godinaUpisa;
		this.trenutnaGodinaStudija = trenutnaGodinaStudija;
		this.status = status;
		this.prosecnaOcena = prosecnaOcena;
		this.spisakPolozenih = spisakPolozenih;
		this.spisakNepolozenih = spisakNepolozenih;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public LocalDate getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(LocalDate datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBrojIndeksa() {
		return brojIndeksa;
	}

	public void setBrojIndeksa(String brojIndeksa) {
		this.brojIndeksa = brojIndeksa;
	}

	public short getGodinaUpisa() {
		return godinaUpisa;
	}

	public void setGodinaUpisa(short godinaUpisa) {
		this.godinaUpisa = godinaUpisa;
	}

	public byte getTrenutnaGodinaStudija() {
		return trenutnaGodinaStudija;
	}

	public void setTrenutnaGodinaStudija(byte trenutnaGodinaStudija) {
		this.trenutnaGodinaStudija = trenutnaGodinaStudija;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public float getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(float prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

	public ArrayList<Ocena> getSpisakPolozenih() {
		return spisakPolozenih;
	}

	public void setSpisakPolozenih(ArrayList<Ocena> spisakPolozenih) {
		this.spisakPolozenih = spisakPolozenih;
	}

	public ArrayList<Predmet> getSpisakNepolozenih() {
		return spisakNepolozenih;
	}

	public void setSpisakNepolozenih(ArrayList<Predmet> spisakNepolozenih) {
		this.spisakNepolozenih = spisakNepolozenih;
	};
	
	
}
