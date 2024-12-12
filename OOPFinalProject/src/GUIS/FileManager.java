package GUIS;

import java.util.Scanner;
import java.util.Vector;
import java.io.*;
public class FileManager{
	private String file_name;
	
	public FileManager(String value) {
        this.file_name = value;
    }
	
	public Vector<String> read(){
		Vector<String> data=new Vector<String>();
		try {
			Scanner s=new Scanner(new File(file_name));
			while(s.hasNext()) {
				data.add(s.nextLine());
			}
			s.close();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		return data;
	}
	public void save(Vector<String> list_of_passwords) {
		try {
			FileWriter f=new FileWriter(new File(file_name));
			for(int i=0; i<list_of_passwords.size();i++) {
				f.write(list_of_passwords.get(i));
				f.write("\n");
			}
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
