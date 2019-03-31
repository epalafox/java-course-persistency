package dataLayer;

import java.sql.*;
import java.util.ArrayList;

import models.Auto;
import models.Transmision;
public class MariaDB implements IDatabase {
	public MariaDB()
	{
		connectDatabase();
	}
	Connection connection;
    /**
     * We establish the connection with the database <b>customerdb</b>.
     * Establecemos la conexión con la base de datos <b>customerdb</b>.
     */
    private void connectDatabase() {
        try {
            connection = null;
            // Database connect
            // Conectamos con la base de datos
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/autocar",
                    "root", "");
        } catch (java.sql.SQLException sqle) {
            System.out.println("Error: " + sqle);
        }
    }
	@Override
    public ArrayList<Auto> getAutos()
    {
    	Statement s;
    	ArrayList<Auto> returnValue = new ArrayList<Auto>();
		try {
			s = connection.createStatement();
	    	ResultSet rs = s.executeQuery("select * from Autos");
	    	while(rs.next())
	    	{
	    		Auto auto = new Auto();
	    		auto.id = rs.getInt("id");
				auto.brand = rs.getString("brand");
				auto.doors = rs.getInt("doors");
				auto.extColor = rs.getString("extColor");
				auto.intColor = rs.getString("intColor");
				auto.km = rs.getInt("km");
				auto.liters = rs.getFloat("liters");
				auto.model = rs.getString("model");
				auto.price = rs.getFloat("price");
				auto.trans = rs.getInt("trans") == 0 ? Transmision.auto : Transmision.manual;
				auto.year = rs.getInt("year");
				returnValue.add(auto);
	    	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnValue;
    }

	@Override
    public void insertAuto(Auto auto)
    {
    	try {
			Statement s = connection.createStatement();
			s.execute("INSERT INTO `Autos` "
					+ "(`year`, `brand`, `model`, `price`, `km`, `extColor`, `intColor`, `trans`, `liters`, `doors`) "
					+ "VALUES"
					+ " (" + auto.year + ", '" + auto.brand + "', '" + auto.model + "', '" + auto.price
					+ "', '" + auto.km + "', '" + auto.extColor + "', '" + auto.intColor + "', '" + 
					(auto.trans == Transmision.auto ? 0 : 1)
					+ "', '" + auto.liters + "', '" + auto.doors + "')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	@Override
    public void updateAuto(Auto auto)
    {

    	try {
			Statement s = connection.createStatement();
			s.execute("Update `Autos` set "
					+ "(`year`= " + auto.year
					+ ", `brand` = '" + auto.brand + "'" 
					+ ", `model` = '" + auto.model + "'" 
					+ ", `price` = "+ auto.price
					+ ", `km` = " + auto.km 
					+ ", `extColor` = '" + auto.extColor + "'"
					+ ", `intColor` = '" + auto.intColor + "'"
					+ ", `trans` = " + (auto.trans == Transmision.auto ? 0 : 1)
					+ ", `liters` = " + auto.liters 
					+ ", `doors` = " + auto.doors);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	@Override
	public void deleteAuto(Auto auto) {
		// TODO Auto-generated method stub
		
	}
    /**
     * Method to connect to the database by passing parameters.
     * Método para establecer la conexión a la base de datos mediante el paso de parámetros.
     * 
     * @param host <code>String</code> host name or ip. Nombre del host o ip.
     * @param port <code>String</code> listening database port. Puerto en el que escucha la base de datos.
     * @param database <code>String</code> database name for the connection. Nombre de la base de datos para la conexión.
     * @param user <code>String</code> user name. Nombre de usuario.
     * @param password  <code>String</code> user password. Password del usuario.
     */
    public void connectDatabase(String host, String port, String database,
            String user, String password) {
        String url = null;
        try {
            // We register the MySQL driver
            // Registramos el driver de MySQL
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de MySQL: " + ex);
            }
            Connection connection = null;
            url ="jdbc:mysql://" + host + ":" + port + "/" + database;
            // Database connect
            // Conectamos con la base de datos
            connection = DriverManager.getConnection(
                    url,
                    user, password);           
            boolean valid = connection.isValid(50000);
            System.out.println(valid ? "TEST OK" : "TEST FAIL");
        } catch (java.sql.SQLException sqle) {
            System.out.println("Error al conectar con la base de datos de MySQL (" + url + "): " + sqle);
        }
    }
}