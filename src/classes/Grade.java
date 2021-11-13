package classes;

import java.time.LocalDate;

public class Grade {
	
	private Student student;
	private Subject subject;
	private byte grade;
	private LocalDate date;
	
	public Grade() {}

	public Grade(Student student, Subject subject, byte grade, LocalDate date) {

		this.student = student;
		this.subject = subject;
		this.grade = grade;
		this.date = date;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Subject getsubject() {
		return subject;
	}

	public void setsubject(Subject subject) {
		this.subject = subject;
	}

	public byte getgrade() {
		return grade;
	}

	public void setgrade(byte grade) {
		this.grade = grade;
	}

	public LocalDate getdate() {
		return date;
	}

	public void setdate(LocalDate date) {
		this.date = date;
	}
	
	
	
}
