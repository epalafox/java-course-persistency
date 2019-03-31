package logic;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dataLayer.MongoDB;
import models.Auto;
import models.Transmision;

public class CreateNewAuto extends JFrame {
	MainVentana mainVentana;
	JTextField jTextBrand;
	JLabel jLabelBrand;
	JTextField jTextModel;
	JLabel jLabelModel;
	JTextField jTextDoors;
	JLabel jLabelDoors;
	JTextField jTextYear;
	JLabel jLabelYear;
	JTextField jTextExtColor;
	JLabel jLabelExtColor;
	JTextField jTextIntColor;
	JLabel jLabelIntColor;
	JTextField jTextKM;
	JLabel jLabelKM;
	JTextField jTextLiters;
	JLabel jLabelLiters;
	JTextField jTextPrice;
	JLabel jLabelPrice;
	JTextField jTextTransmision;
	JLabel jLabelTransmision;
	
	JButton jButtonCancel;
	JButton jButtonSave;
	
	public CreateNewAuto(MainVentana parent)
	{
		mainVentana = parent;
		configurarVentana();
		inicializarComponentes();		
	}
	private void inicializarComponentes() {
		jTextBrand = new JTextField();
		jLabelBrand = new JLabel("Brand");
		jTextModel = new JTextField();
		jLabelModel = new JLabel("Model");
		jTextDoors = new JTextField();
		jLabelDoors = new JLabel("Doors");
		jTextYear = new JTextField();
		jLabelYear = new JLabel("Year");
		jTextExtColor = new JTextField();
		jLabelExtColor = new JLabel("Ext. Color");
		jTextIntColor = new JTextField("Int. Color");
		jLabelIntColor = new JLabel("Int Color");
		jTextKM = new JTextField();
		jLabelKM = new JLabel("KM");
		jTextLiters = new JTextField();
		jLabelLiters = new JLabel("Liters");
		jTextPrice = new JTextField();
		jLabelPrice = new JLabel("Price");
		jTextTransmision = new JTextField();
		jLabelTransmision = new JLabel("Transmision");
		jButtonCancel = new JButton("Cancel");
		jButtonCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CreateNewAuto.this.dispose();				
			}
		});
		jButtonSave = new JButton("Save");
		jButtonSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Auto newAuto = new Auto();
				newAuto.brand = jTextBrand.getText();
				newAuto.model = jTextModel.getText();
				newAuto.year = Integer.parseInt(jTextYear.getText());
				newAuto.liters = Float.parseFloat(jTextLiters.getText());
				newAuto.extColor = jTextExtColor.getText();
				newAuto.intColor = jTextIntColor.getText();
				newAuto.km = Integer.parseInt(jTextKM.getText());
				newAuto.price = Float.parseFloat(jTextPrice.getText());
				newAuto.trans = jTextTransmision.getText() == "0" ? Transmision.auto : Transmision.manual;
				newAuto.doors = Integer.parseInt(jTextDoors.getText());
				
				MongoDB db = new MongoDB();
				db.insertAuto(newAuto);
				mainVentana.getAutos("");
				CreateNewAuto.this.dispose();
			
			}
		});
		
		this.add(jLabelBrand);
		this.add(jTextBrand);
		this.add(jLabelModel);
		this.add(jTextModel);
		this.add(jLabelDoors);
		this.add(jTextDoors);
		this.add(jLabelYear);
		this.add(jTextYear);
		this.add(jLabelExtColor);
		this.add(jTextExtColor);
		this.add(jLabelIntColor);
		this.add(jTextIntColor);
		this.add(jLabelKM);
		this.add(jTextKM);
		this.add(jLabelPrice);
		this.add(jTextPrice);
		this.add(jLabelLiters);
		this.add(jTextLiters);
		this.add(jLabelTransmision);
		this.add(jTextTransmision);
		this.add(jButtonCancel);
		this.add(jButtonSave);
		
	}
	private void configurarVentana() {

		this.setTitle("AutoCar");
		this.setSize(500, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		GridLayout grid = new GridLayout(11, 2);
		this.setLayout(grid);
		this.setDefaultCloseOperation(JFrame. DISPOSE_ON_CLOSE);
		
	}
}
