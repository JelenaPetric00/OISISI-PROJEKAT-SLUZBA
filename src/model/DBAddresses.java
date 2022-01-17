package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DBAddresses {
		private static DBAddresses instance = null;
		
		public static DBAddresses getInstance() {
			if(instance == null) {
				instance = new DBAddresses();
			}
			return instance;
		}
		
		private List<Address> addresses;
		
		private DBAddresses() {
			initAddresses();
		}
		
		private void initAddresses(){
			this.addresses = new ArrayList<Address>();
			try {
				//FileReader fr = new FileReader("data" + File.separator + "Addresses.txt");
				//BufferedReader br = new BufferedReader(fr);
				File fileDir = new File("data" + File.separator + "Addresses.txt");
				BufferedReader br = new BufferedReader(
			            new InputStreamReader(
			                       new FileInputStream(fileDir), "UTF8"));
				String str;
				while((str = br.readLine()) != null) {
					String[] strings1 = str.split("[|]");
					
					addresses.add(new Address(strings1[0], strings1[1], strings1[2], strings1[3]));
				}
				br.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public List<Address> getAddresses() {
			return addresses;
		}

		public void setAddresses(List<Address> addresses) {
			this.addresses = addresses;
		}

	}
