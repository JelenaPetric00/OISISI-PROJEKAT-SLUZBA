package view.dialogs;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class HelpDialog extends JDialog{
	
	public HelpDialog(Frame parent, String title, boolean modal){
		super(parent, title, modal);
		setSize(450,500);
		setLocationRelativeTo(parent);
		setLayout(new BorderLayout());
		
		JTextArea helpTxt = new JTextArea();
				helpTxt.setText("Aplikacija Studentske sluzbe pruza mogucnost dodavanja, izmene i brisanja\n entiteta iz sistema. Rukovanje entitetima podrazumeva rukovanje bazom\n studenata, "
				+ "profesora, predmeta i katedri."
				+ "\n\n "
				+ "PRIKAZ ENTITETA SISTEMA"
				+ "\nPrilikom otvaranja aplikacije, prikazace se glavni prozor koji sadrzi\n MenuBar, ToolBar, tabove sa tabelema entiteta i StatusBar.\n"
				+ "Klikom na odredjeni tab ili koriscenjem MenuBar-a\n(File -> Open -> Zeljeni tab) korisnik vrsi pozicioniranje koje mu omogucava dalji rad u aplikaciji. "
				+ "Informacije o trenutno aktivnom tabu su\n istaknute na levom donjem uglu status bara."
				+ "\n\n"
				+ "U nastavku se nalaze precice(akceleratori) koji mogu olaksati\n rad u aplikaciji:"
				+ "\n\tCTRL + N  Dodavanje novog entiteta u sistem"
				+ "\n\tCTRL + S  Sacuva tekuce stanje aplikacije"
				+ "\n\tCTRL + C Zatvaranje aplikacije"
				+ "\n\tCTRL + E Izmena postojeceg entiteta"
				+ "\n\tCTRL + D Brisanje postojeceg entiteta"
				+ "\n\tCTRL + H Detaljan opis nacina koriscenja aplikacije"
				+ "\n\tCTRL + A Informacije o aplikaciji"
				+ "\n\n"
				+ "DODAVANJE NOVOG ENTITETA U SISTEM"
				+ "\nDodavanje novog entiteta u sistem se vrsi izborom odgovarajuceg taba\n u glavnom prozoru. Nakon toga, korisniku je ostavljen nacin\n izbora dodavanja "
				+ "koji moze da se realizuje na neki od sledecih nacina:"
				+ "\n1. Pritiskom Meni dugmeta u ToolBar-u, nakon cega ce se ukazati tri nova\n dugmeta od kojih je prvi predstavlja opciju dodavnja\n (slede ga dume za izmenu i brisanje)"
				+ "\n2. Izborom stavke Menija FILE -> NEW"
				+ "\n3. Upotrebom odgovarajuceg mnemonika"
				+ "\n4. Upotrebom odgovarajuceg akceleratora"
				+ "\nNakon preduzete akcije, korisniku se prikazuje dijalog za dodavanje\n novog entiteta, koji sadrzi formu za unos informacija izabranog entiteta.\nPored praznih polja, na dnu forme"
				+ "se nalaze i dva dugmeta.\n'Potvrdi' za potvrdjivanje novog unosa i 'Odustani' dugme ukoliko\n korisnik ne zeli da nastavi sa zapocetim unosom. Treba napomenuti da se proces unosa"
				+ " ne moze obaviti ukoliko nisu sva potrebna polja\n u dijalogu pravilno popunjena. Ukoliko korisnik u polje sa Identifikatorom\n datog tipa unese vec postojeci ID unos nece biti omogucen"
				+ "i pojavice\n se dijalog upozorenja. U suprotnom ako je sve proslo validaciju,\n nakon pritiska 'Potvrdi' bice prikazan auzuriran sadrzaj tabele izabranog tipa entiteta."
				+ "\n\n"
				+ "IZMENA ENTITETA"
				+ "\nDa bi korisnik izmenio informacije o nekom postojecem entitetu,\n potrebno je da izabere odgovarajuci tab. Nakon toga neophodno je\n da oznaci odgovarajuci red tabele, koji sadrzi prikaz"
				+ " samo odredjenih\n informacja o konkretnom entitetu. Zatim, izborom neke od gore navedene\n funkcionalnosti (koja se odnosi na editovanje), prikazace se dijalog\n za izmenu entiteta."
				+ " Moguca je izmena svih informacija dokle god su\n nove unesene vrednosti validne. Nakon zavrsetka rada, nacinjene izmene\n se primenjuju pritiskom na dugme 'Potvrdi' cime se "
				+ "auzurira prikaz\n odabranog entiteta u tabeli"
				+ "\n\n"
				+ "BRISANJE ENTITETA"
				+ "\nBrisanje entiteta se vrsi na slican nacin kao i izmena. U pocetnom\n prozoru korisnik vrsi odabir zeljenog taba, nakon cega se opredeljuje\n za pojavu entiteta koju zeli da"
				+ "ukloni iz sistema. Selektovanjem zeljene\n kolone i odabirom neke od funkcionalnosti brisanja(dugme toolbara,\n stavke menija...) pojavice se dijalog sa porukom u kom ce se traziti potvrda"
				+ "akcije brisanja, nakon koje se izabrani entitet uklanja iz tabele."
				+ "\n\n"
				+ "PRIKAZ POLOZENIH I NEPOLOZENIH ENTITETA"
				+ "\nDijalog za izmenu Studenta pored taba sa osnovnim informacijama o\n studentu sadrzi i tabove sa prikazom polozenih i nepolozenih predmeta.\n Odabirom taba 'Polozeni' u panelu"
				+ " taba se prikazuje tabela sa polozenim\n ispitima konkretnog studenta. Pritiskom dugmeta 'Ponisti ocenu'\n referent brise predmet iz tabele polozenih i automatski ga prebacuje\n u tabelu"
				+ " sa nepolozenim predmetima. U donjem desnu uglu panela nalaze se informacije o ukupnom broju ESBP bodova koje je student osvojio\n u toku studija i informacije o prosecnoj oceni. "
				+ "\nOdabirom taba 'Nepolozeni' u panelu se prikazuje tabela sa nepolozenim\n predmetima odabranog studenta. Dugmad 'Dodaj' i 'Obrisi' nude\n mogucnost dodavanja odnosno brisanja "
				+ "odabranog studenta na predmet.\n Pored njih postoji i dugme 'Polaganje' koje omogucuje upis ocene za\n oznaceni predmet. Nakon potvrde unosa predmet se prebacuje u tabelu sa polozenim i brise se iz tabele nepolozenih."
				+ "\n\n"
				+ "SORTIRANJE"
				+ "\nUkoliko referent zeli pregledniji prikaz u tabelama, dovoljno je da klikne\n na zeljenu kolonu nakon cega ce se izvrsiti sortiranje po toj koloni i pred\n njim ce se pojaviti "
				+ "novi auzurirani prikaz u tabeli."
				+ "\n\n"
				+ "DODAVANJE I UKLANJANJE PREDMETA PROFESORU"
				+ "\nOdabirom taba 'Profesori' i otvaranjem dijaloga za izmenu profesora,\n korisniku se, izborom taba 'Predmeti' pruza i uvid u predmete koje izabrani profesor predaje. Iznad tabele nalaze"
				+ " se dva dugmeta. Klikom na dugme\n 'Dodaj predmet' pojavljuje se dijalog sa listom predmeta koje dati profesor ne predaje. Moguce je oznaciti vise predmeta i nakon njihove selekcije\n i"
				+ " pritiskom dugmeta za potvrdu, tabela se auzurira. "
				+ "Odabirom jednog\n predmeta iz tabele i klikom na dugme 'Ukloni predmet' javlja se dijalog\n u kom referent treba da potvrdi nastavak akcije brisanja nakon cega\n ce se auzurirarti prikaz u tabeli. "
				+ "\n\n"
				+ "PRETRAGA"
				+ "\n\n"
				+ "DODAVANJE I UKLANJANJE PROFESORA SA PREDMETA"
				+ "\n\n"
				+ "POSTAVLJANJE SEFA KATEDRE"
				+ "\n\n"
				+ "LOKALIZACIJA"
				+ "\nUkoliko korisnik zeli da promeni jezik aplikacije, odabirom dugmeta\n 'Administracija' u MeniBaru pojavice se padajuci meni sa opcijama\n za prebacivanje na srpski i engleski jezik.");
		helpTxt.setLineWrap(true);
		helpTxt.setEditable(false);
		JScrollPane scrollP = new JScrollPane(helpTxt);
		
		add(scrollP);
		
	}

}
