package dataLayer;

import java.util.ArrayList;

import models.Auto;

public interface IDatabase {
	ArrayList<Auto> getAutos();
	void insertAuto(Auto auto);
	void updateAuto(Auto auto);
	void deleteAuto(Auto auto);
}
