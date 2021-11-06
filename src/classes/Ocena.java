package classes;

import java.time.LocalDate;

public class Ocena {
	
	private Student student;
	private Predmet predmet;
	private byte ocena;
	private LocalDate datumPolaganja;
	
	public Ocena() {}

	public Ocena(Student student, Predmet predmet, byte ocena, LocalDate datumPolaganja) {

		this.student = student;
		this.predmet = predmet;
		this.ocena = ocena;
		this.datumPolaganja = datumPolaganja;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Predmet getPredmet() {
		return predmet;
	}

	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}

	public byte getOcena() {
		return ocena;
	}

	public void setOcena(byte ocena) {
		this.ocena = ocena;
	}

	public LocalDate getDatumPolaganja() {
		return datumPolaganja;
	}

	public void setDatumPolaganja(LocalDate datumPolaganja) {
		this.datumPolaganja = datumPolaganja;
	}
	
	
	
}
