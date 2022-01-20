package view.dialogs;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JTextArea;

public class AboutDialog extends JDialog{
	
	public AboutDialog(Frame parent, String title, boolean modal){
		super(parent, title, modal);
		
		setSize(450,500);
		setLocationRelativeTo(parent);
		setLayout(new BorderLayout());
		
		JTextArea aboutTxt = new JTextArea();
		aboutTxt.setText("Aplikacija 'Studentska sluzba' je projekat u okviru predmeta\n Osnovni informacionih sistema i softverskog inzenjerstva.\n Ona predstavlja model informacionog sistema studentske"
				+ " sluzbe\n Fakulteta tehnickih nauka i namenjena je za upotrebu od strane referenta\n studentske sluzbe."
				+ "\n\n"
				+ "Autori:"
				+ "\n"
				+ "\nIsidora Poznanovic,"
				+ "\nrodjena 02.08.2000. u Kragujevcu. Zavrsila Prvu kragujevacku gimnaziju,\n specijalno matematicki smer. Trenutno je student trece godine Fakulteta\n tehnickih nauka"
				+ " u Novom Sadu, smer Racunarstvo i automatika,\n studijski program Primenjene racunarske nauke i informatika.\n Broj indeksa RA 163/2019."
				+ "\n\n"
				+ "Jelena Petric,"
				+ "\nrodjena 01.05.2000. u Novom Sadu. Zavrsila Gimnaziju u Backoj Palanci,\n opsti smer. Trenutno je student trece godine Fakulteta tehnickih nauka\n u Novom Sadu,"
				+ " smer Racunarstvo i automatika, studijski program\n Primenjene racunarske nauke i informatika.Broj indeksa RA 183/2019. ");
		aboutTxt.setEditable(false);
		aboutTxt.setLineWrap(true);
		
		add(aboutTxt);
	}

}
