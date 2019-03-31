package dataLayer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.Auto;
import models.Transmision;

public class AutosEntity implements IDatabase {
	File file = new File("/home/emmanuel/Documents/autos.db");
	public AutosEntity()
	{
		if(!file.exists())
		{
			try {
				file.createNewFile();
			} catch (IOException e)
			{
				e.printStackTrace();
				return;
			}
		}
	}
	public ArrayList<Auto> getAutos(){
		Scanner ent;
		ArrayList<Auto> returnValue = new ArrayList<Auto>();
		try {
			ent = new Scanner(file);
			while(ent.hasNextLine())
			{
				String linea = ent.nextLine();
				if(!linea.isEmpty())
				{
					String[] array = linea.split("\\|"); 
					Auto auto = new Auto();
					auto.id = Integer.parseInt(array[0]);
					auto.brand = array[1];
					auto.doors = Integer.parseInt(array[2]);
					auto.extColor = array[3];
					auto.intColor = array[4];
					auto.km = Integer.parseInt(array[5]);
					auto.liters = Float.parseFloat(array[6]);
					auto.model = array[7];
					auto.price = Float.parseFloat(array[8]);
					auto.trans = Integer.parseInt(array[9]) == 0 ? Transmision.auto : Transmision.manual;
					auto.year = Integer.parseInt(array[10]);
					returnValue.add(auto);
				}
			}
			ent.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return returnValue;
	}
	/**
	 * Inserta un auto
	 * @param auto
	 */
	public void insertAuto(Auto auto) {
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(file, true));
			writer.append(
					Integer.toString(auto.id) + "|" + 
					auto.brand + "|" +
					auto.doors + "|" +
					auto.extColor + "|" +
					auto.intColor + "|" +
					auto.km + "|" +
					auto.liters + "|" +
					auto.model + "|" +
					auto.price + "|" +
					(auto.trans == Transmision.auto ? 0 : 1) + "|" +
					auto.year +
					"\n\r");
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void updateAuto(Auto auto) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteAuto(Auto auto) {
		// TODO Auto-generated method stub
		
	}
}
