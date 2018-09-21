//Created by Shreyas C S, under the guidance of Dr. Jing Zhang 

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map; 
import java.util.HashMap; 
import java.util.Collections; 

public class OptimizePrint {

	public static void main(String[] args) {
		//CSV file is kept in the project directory
		String path = System.getProperty("user.dir");
		String csvFile = path+"/Data_Sheet.csv";
		String line = ""; //this variable contains the entire record while parsing through CSV file
		String csvSplitBy = ","; //CSV file will be split using comma as the delimiter
		boolean check = new File(csvFile).exists(); //checking if CSV file exists
		
		//ArrayList variables to hold the information in CSV file
		List<Double> slNo = new ArrayList<Double>();
		List<Double> dlWater = new ArrayList<Double>();
		List<Double> pvp = new ArrayList<Double>();
		List<Double> darvan = new ArrayList<Double>();
		List<Double> zircon = new ArrayList<Double>();
		List<Double> nozzelDia = new ArrayList<Double>();
		List<Double> tempC = new ArrayList<Double>();
		List<Double> exMul = new ArrayList<Double>();
		List<Double> qlty = new ArrayList<Double>();
		
		//Double variables to hold the max and min values of the constituents
		double min_dlWater = Double.MAX_VALUE, max_dlWater = Double.MIN_VALUE;
		double min_pvp = Double.MAX_VALUE, max_pvp = Double.MIN_VALUE;
		double min_darvan = Double.MAX_VALUE, max_darvan = Double.MIN_VALUE;
		double min_zircon = Double.MAX_VALUE, max_zircon = Double.MIN_VALUE;
		double min_nozzelDia = Double.MAX_VALUE, max_nozzelDia = Double.MIN_VALUE;
		double min_tempC = Double.MAX_VALUE, max_tempC = Double.MIN_VALUE;
		double min_exMul = Double.MAX_VALUE, max_exMul = Double.MIN_VALUE;
		double min_qlty = Double.MAX_VALUE, max_qlty = Double.MIN_VALUE;
		
		int itr = 0; //number of records in the CSV file
		
		if(check) { //Checking if CSV file is present in the project folder
			try(BufferedReader br = new BufferedReader(new FileReader(csvFile))) { //buffer to read through CSV file
				while ((line = br.readLine()) != null) { //reading one record at a time
					if(itr>0) { //omitting the column names
						
						String[] cols = line.split(csvSplitBy);// use comma as separator
						
						slNo.add(Double.parseDouble(cols[0])); //collects the serial number
						
						dlWater.add(Double.parseDouble(cols[1])); //Stores Distilled Water quantity and calculates the min and max values
						if(Double.parseDouble(cols[1]) < min_dlWater)
							min_dlWater = Double.parseDouble(cols[1]);
						if(Double.parseDouble(cols[1]) > max_dlWater)
							max_dlWater = Double.parseDouble(cols[1]);
						
						pvp.add(Double.parseDouble(cols[2]));//Stores PVP quantity and calculates the min and max values
						if(Double.parseDouble(cols[2]) < min_pvp)
							min_pvp = Double.parseDouble(cols[2]);
						if(Double.parseDouble(cols[2]) > max_pvp)
							max_pvp = Double.parseDouble(cols[2]);
						
						darvan.add(Double.parseDouble(cols[3]));//Stores Darvan quantity and calculates the min and max values
						if(Double.parseDouble(cols[3]) < min_darvan)
							min_darvan = Double.parseDouble(cols[3]);
						if(Double.parseDouble(cols[3]) > max_darvan)
							max_darvan = Double.parseDouble(cols[3]);
						
						zircon.add(Double.parseDouble(cols[4]));//Stores Zircon quantity and calculates the min and max values
						if(Double.parseDouble(cols[4]) < min_zircon)
							min_zircon = Double.parseDouble(cols[4]);
						if(Double.parseDouble(cols[4]) > max_zircon)
							max_zircon = Double.parseDouble(cols[4]);
						
						nozzelDia.add(Double.parseDouble(cols[5]));//Stores Nozzel Diameter value and calculates the min and max values
						if(Double.parseDouble(cols[5]) < min_nozzelDia)
							min_nozzelDia = Double.parseDouble(cols[5]);
						if(Double.parseDouble(cols[5]) > max_nozzelDia)
							max_nozzelDia = Double.parseDouble(cols[5]);
						
						tempC.add(Double.parseDouble(cols[6]));//Stores Temperature value and calculates the min and max values
						if(Double.parseDouble(cols[6]) < min_tempC)
							min_tempC = Double.parseDouble(cols[6]);
						if(Double.parseDouble(cols[6]) > max_tempC)
							max_tempC = Double.parseDouble(cols[6]);
						
						exMul.add(Double.parseDouble(cols[7]));//Stores Extrusion multiplier value quantity and calculates the min and max values
						if(Double.parseDouble(cols[7]) < min_exMul)
							min_exMul = Double.parseDouble(cols[7]);
						if(Double.parseDouble(cols[7]) > max_exMul)
							max_exMul = Double.parseDouble(cols[7]);
						
						qlty.add(Double.parseDouble(cols[8]));//Stores Quality and calculates the min and max values
						if(Double.parseDouble(cols[8]) < min_qlty)
							min_qlty = Double.parseDouble(cols[8]);
						if(Double.parseDouble(cols[8]) > max_qlty)
							max_qlty = Double.parseDouble(cols[8]);
					}
					itr++; //holds the number of records in the CSV file
				}
			}
			catch(Exception e){ //if CSV file is missing, program will throw an exception 
				System.out.println("Exception: Main function " + e.getMessage());
				e.printStackTrace();
				}
		}

		System.out.println("Finished parsing through the CSV file.");
		System.out.println("Number of records = "+(itr-1));
		
		System.out.println("\nPlease enter the details below to predict the print quality:"); //Taking user input to predict the quality
		boolean ch = true;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("\nDistilled water quantity in grams- ");
		double new_dlWater = 0;
		while(ch) { //making sure user enters values in double format
			while (!sc.hasNextDouble() ) {
			System.out.print("Please enter the value in double format only - ");
			sc.next();
			}
			new_dlWater = sc.nextDouble();
			if(new_dlWater > min_dlWater &&  new_dlWater < max_dlWater)
				ch = false;
			else
				System.out.print("\nPlease keep the range between "+min_dlWater+" and "+max_dlWater+"- ");
		}
		
		ch = true;
		System.out.print("\nPVP quantity in grams- ");
		double new_pvp = 0;
		while(ch) {//making sure user enters values in double format
			sc = new Scanner(System.in);
			while (!sc.hasNextDouble()) {
				System.out.print("\nPlease enter the value in double format- ");
				sc.next();
			}
			new_pvp = sc.nextDouble();
			if(new_pvp > min_pvp &&  new_pvp < max_pvp) //making sure the entered value is in pre-determined range
				ch = false;
			else
				System.out.print("\nPlease keep the range between "+min_pvp+" and "+max_pvp+"- ");
		}
		
		ch = true;
		System.out.print("\nDarvan quantity in grams- ");
		double new_darvan = 0;
		while(ch) {//making sure user enters values in double format
			sc = new Scanner(System.in);
			while (!sc.hasNextDouble()) {
				System.out.print("\nPlease enter the value in double format only- ");
				sc.next();
			}
			new_darvan = sc.nextDouble();
			if(new_darvan > min_darvan &&  new_darvan < max_darvan)//making sure the entered value is in pre-determined range
				ch = false;
			else
				System.out.print("\nPlease keep the range between "+min_darvan+" and "+max_darvan+"- ");
		}
		
		ch = true;
		System.out.print("\nZircon quantity in grams- ");
		double new_zircon = 0;
		while(ch) {//making sure user enters values in double format
			sc = new Scanner(System.in);
			while (!sc.hasNextDouble()) {
				System.out.print("\nPlease enter the value in double format only- ");
				sc.next();
			}
			new_zircon = sc.nextDouble();
			if(new_zircon > min_zircon &&  new_zircon < max_zircon)//making sure the entered value is in pre-determined range
				ch = false;
			else
				System.out.print("\nPlease keep the range between "+min_zircon+" and "+max_zircon+"- ");
		}
		
		ch = true;
		System.out.print("\nNozzle diameter in millimeter- ");
		double new_nozzelDia = 0;
		while(ch) {//making sure user enters values in double format
			sc = new Scanner(System.in);
			while (!sc.hasNextDouble()) {
				System.out.print("\nPlease enter the value in double format only- ");
				sc.next();
			}
			new_nozzelDia = sc.nextDouble();
			if(new_nozzelDia > min_nozzelDia &&  new_nozzelDia < max_nozzelDia)//making sure the entered value is in pre-determined range
				ch = false;
			else
				System.out.print("\nPlease keep the range between "+min_nozzelDia+" and "+max_nozzelDia+"- ");
		}
		
		ch = true;
		System.out.print("\nTemperature in degree centigrade- ");
		double new_tempC = 0;
		while(ch) {//making sure user enters values in double format
			sc = new Scanner(System.in);
			while (!sc.hasNextDouble()) {
				System.out.print("\nPlease enter the value in double format only- ");
				sc.next();
			}
			new_tempC = sc.nextDouble();
			if(new_tempC > min_tempC &&  new_tempC < max_tempC)//making sure the entered value is in pre-determined range
				ch = false;
			else
				System.out.print("\nPlease keep the range between "+min_tempC+" and "+max_tempC+"- ");
		}
		
		sc.close(); 

		int idx[] = new int[6]; //variable to hold the quality which comes close to the entered input
		
		double diff_dlWater = Double.MAX_VALUE;
		double diff_pvp = Double.MAX_VALUE;
		double diff_darvan = Double.MAX_VALUE;
		double diff_zircon = Double.MAX_VALUE;
		double diff_nozzelDia = Double.MAX_VALUE;
		double diff_tempC = Double.MAX_VALUE;
		
		
		for(int i = 0; i < itr-1; i++) { //predicting the quality which comes close to the value in CSV file
			if(Math.abs(new_dlWater - dlWater.get(i)) < diff_dlWater) {
				diff_dlWater = Math.abs(new_dlWater - dlWater.get(i));
				idx[0]= i;
			}
			if(Math.abs(new_pvp - pvp.get(i)) < diff_pvp) {
				diff_pvp = Math.abs(new_pvp - pvp.get(i));
				idx[1] = i;
			}
			if(Math.abs(new_darvan - darvan.get(i)) < diff_darvan) {
				diff_darvan = Math.abs(new_darvan - darvan.get(i));
				idx[2] = i;
			}
			if(Math.abs(new_zircon - zircon.get(i)) < diff_zircon) {
				diff_zircon = Math.abs(new_zircon - zircon.get(i));
				idx[3] = i;
			}
			if(Math.abs(new_nozzelDia - nozzelDia.get(i)) < diff_nozzelDia) {
				diff_nozzelDia = Math.abs(new_nozzelDia - nozzelDia.get(i));
				idx[4] = i;
			}
			if(Math.abs(new_tempC - tempC.get(i)) < diff_tempC) {
				diff_tempC = Math.abs(new_dlWater - tempC.get(i));
				idx[5] = i;
			}
		}
	
		Map<Integer,Integer> m = new HashMap<>();
		
		for(int i : idx) { //hold which record in CSV file matches close to the input value
			if(m.containsKey(i))
				m.put(i, m.get(i) + 1);
			else
				m.put(i,1);
		}
		
		int maxValueInMap=(Collections.max(m.values()));  // This will return max value in the Hashmap
        for (Map.Entry<Integer, Integer> entry : m.entrySet())  // Iterate through hashmap
            if (entry.getValue()==maxValueInMap) //printing the quality of the record which is repeated the most
                System.out.println("Comparing the input values to the data in CSV file, the predicted quality of the print is "+qlty.get((int)entry.getKey()));     // Print the key with max value
	}

}
