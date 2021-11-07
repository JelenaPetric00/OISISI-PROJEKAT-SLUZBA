package classes;

import java.time.LocalDate;
import java.util.ArrayList;

public class Profesor {
	
	private String prezime;
	private String ime;
	private LocalDate datumRodjenja;
	private String adresaStanovanja;
	private String kontaktTelefon;
	private String email;
    private String adresaKancelarije;
    private String brojlicne;
    private String zvanje;
    private short godineStaza;
    private ArrayList<Predmet> spisakDrziPredmet;
    
    public Profesor() {}
    
    

	public Profesor(String prezime, String ime, LocalDate datumRodjenja, String adresaStanovanja, String kontaktTelefon,
			String email, String adresaKancelarije, String brojlicne, String zvanje, short godineStaza,
			ArrayList<Predmet> spisakDrziPredmet) {
		
		this.prezime = prezime;
		this.ime = ime;
		this.datumRodjenja = datumRodjenja;
		this.adresaStanovanja = adresaStanovanja;
		this.kontaktTelefon = kontaktTelefon;
		this.email = email;
		this.adresaKancelarije = adresaKancelarije;
		this.brojlicne = brojlicne;
		this.zvanje = zvanje;
		this.godineStaza = godineStaza;
		this.spisakDrziPredmet = spisakDrziPredmet;
	}



	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public LocalDate getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(LocalDate datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public String getAdresaStanovanja() {
		return adresaStanovanja;
	}

	public void setAdresaStanovanja(String adresaStanovanja) {
		this.adresaStanovanja = adresaStanovanja;
	}

	public String getKontaktTelefon() {
		return kontaktTelefon;
	}

	public void setKontaktTelefon(String kontaktTelefon) {
		this.kontaktTelefon = kontaktTelefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresaKancelarije() {
		return adresaKancelarije;
	}

	public void setAdresaKancelarije(String adresaKancelarije) {
		this.adresaKancelarije = adresaKancelarije;
	}

	public String getBrojlicne() {
		return brojlicne;
	}

	public void setBrojlicne(String brojlicne) {
		this.brojlicne = brojlicne;
	}

	public String getZvanje() {
		return zvanje;
	}

	public void setZvanje(String zvanje) {
		this.zvanje = zvanje;
	}

	public short getGodineStaza() {
		return godineStaza;
	}

	public void setGodineStaza(short godineStaza) {
		this.godineStaza = godineStaza;
	}

	public ArrayList<Predmet> getSpisakDrziPredmet() {
		return spisakDrziPredmet;
	}

	public void setSpisakDrziPredmet(ArrayList<Predmet> spisakDrziPredmet) {
		this.spisakDrziPredmet = spisakDrziPredmet;
	}
    
    
    
}
