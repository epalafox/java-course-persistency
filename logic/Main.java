package logic;

import models.*;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import dataLayer.AutosEntity;
import dataLayer.IDatabase;
import dataLayer.MariaDB;
import dataLayer.MongoDB;

public class Main {
	private static IDatabase db = new MongoDB();
	public static void main(String[] args) {
		/*Auto a = new Auto();
		//a.id = 1;
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
		//db.insertAuto(a);
		/*ArrayList<Auto> autos = db.getAutos();
		for(Auto auto : autos)
		{
			System.out.println(auto.getNombre());
		}*/
		
	}

}
