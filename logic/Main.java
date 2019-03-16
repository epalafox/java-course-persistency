package logic;

import models.*;

import java.util.ArrayList;
import dataLayer.AutosEntity;
import dataLayer.MariaDB;

public class Main {

	public static void main(String[] args) {
		/*Auto a = new Auto();
		a.id = 1;
		a.year = 2017;
		a.brand = "Volkswagen";
		a.model = "Vento Comfortline";
		a.doors = 4;
		a.extColor = "Grafito";
		a.intColor = "Gris";
		a.km = 76000;
		a.liters = 1.6f;
		a.price = 173900;
		a.trans = Transmision.manual;
		//AutosEntity db = new AutosEntity();
		//db.insertAuto(a);
		/*ArrayList<Auto> autos = db.getAutos();
		for(Auto a : autos)
		{
			System.out.println(a.getNombre());
		}*/
		MariaDB db = new MariaDB();
		db.connectDatabase();
		//db.insertAuto(a);
		ArrayList<Auto> autos = db.getAutos();
		for(Auto auto : autos)
		{
			System.out.println(auto.getNombre());
		}
		
	}

}
