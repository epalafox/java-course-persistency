package dataLayer;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import models.Auto;
import models.Transmision;

public class MongoDB implements IDatabase {
	
	private MongoClient mongoClient;
	private MongoDatabase database;
	private MongoCollection<Document> collection;
	
	public MongoDB()
	{
		mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
		database = mongoClient.getDatabase("autocar");
		collection = database.getCollection("autos");
	}
	@Override
	public ArrayList<Auto> getAutos() {
		ArrayList<Auto> autos = new ArrayList<Auto>();
		FindIterable<Document> autosDocuments = collection.find();
		for(Document auto : autosDocuments)
		{
			Auto a = new Auto();
			a._id = auto.getObjectId("_id");
			a.year = auto.getInteger("year");
			a.brand = auto.getString("brand");
			a.model = auto.getString("model");
			a.doors = auto.getInteger("doors");
			a.extColor = auto.getString("extColor");
			a.intColor = auto.getString("intColor");
			a.km = auto.getInteger("km");
			a.liters = Float.parseFloat(auto.get("liters").toString());
			a.price = Float.parseFloat(auto.get("price").toString());
			a.trans = auto.getInteger("trans") == 0 ? Transmision.auto : Transmision.manual;
			autos.add(a);
		}
		return autos;
	}

	@Override
	public void insertAuto(Auto auto) {
		Document document = new Document();
		document.put("year", auto.year);
		document.put("brand", auto.brand);
		document.put("model", auto.model);
		document.put("doors", auto.doors);
		document.put("extColor", auto.extColor);
		document.put("intColor", auto.intColor);
		document.put("km", auto.km);
		document.put("liters", auto.liters);
		document.put("price", auto.price);
		document.put("trans", auto.trans == Transmision.auto ? 0 : 1);
		collection.insertOne(document);
	}

	@Override
	public void updateAuto(Auto auto) {
		Bson filter = Filters.eq("_id", auto._id);
		Bson query = Updates.combine(
				Updates.set("year", auto.year),
				Updates.set("brand", auto.brand),
				Updates.set("model", auto.model),
				Updates.set("doors", auto.doors),
				Updates.set("extColor", auto.extColor),
				Updates.set("intColor", auto.intColor),
				Updates.set("km", auto.km),
				Updates.set("liters", auto.liters),
				Updates.set("price", auto.price),
				Updates.set("trans", auto.trans == Transmision.auto ? 0 : 1)
				);
		
		collection.updateOne(filter, query);
	}

	@Override
	public void deleteAuto(Auto auto) {
		Bson filter = Filters.eq("_id", auto._id);
		collection.deleteOne(filter);
	}

}
