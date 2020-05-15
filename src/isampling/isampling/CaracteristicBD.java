package isampling.isampling;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class CaracteristicBD {
	private Integer tailleMoyenneTrans;
	private Integer bdSize;
    final Pattern separator = Pattern.compile("[\t , /]");
	
    public CaracteristicBD(String pathData) throws IOException {
		this.tailleMoyenneTrans = 0;
		this.bdSize = 0;
		BufferedReader lecteurAvecBuffer = null;
		try{
			lecteurAvecBuffer = new BufferedReader(
					new FileReader(pathData));
		}
		catch(FileNotFoundException exc){
			System.out.println("Erreur d'ouverture"+exc.toString());
		}
		
		String ligne;
		while ((ligne = lecteurAvecBuffer.readLine()) != null){
			String [] items= separator.split(ligne);
			this.bdSize++;
			this.tailleMoyenneTrans+= items.length;
		}
		this.tailleMoyenneTrans = this.tailleMoyenneTrans/this.bdSize;
	}

	public Integer getTailleMoyenneTrans() {
		return tailleMoyenneTrans;
	}

	public Integer getBdSize() {
		return bdSize;
	}	
}
