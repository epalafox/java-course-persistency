package logic;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListModel;

import dataLayer.IDatabase;
import dataLayer.MongoDB;
import models.Auto;

public class MainVentana extends JFrame {
	private static IDatabase db = new MongoDB();
	JList jListAutos;
	JButton jButtonUpdate;
	JTextField jBuscar;
	ArrayList autos;
	
	GridBagLayout grid;
	public MainVentana() {
		super();
		autos = new ArrayList<Auto>();
		configurarVentana();
		inicializarComponentes();
		getAutos("");
	}
	private void configurarVentana()
	{
		this.setTitle("AutoCar");
		this.setSize(500, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void getAutos(String filter)
	{
		autos.clear();
		DefaultListModel model = new DefaultListModel();
		ArrayList<Auto> autosDB = db.getAutos();
		for(Auto a : autosDB)
		{
			if(a.model.toLowerCase().contains(filter.toLowerCase()) || a.brand.contains(filter.toLowerCase()) || 
					Integer.toString(a.year).contains(filter));
				{
					autos.add(a);
					model.addElement(a.getNombre());
				}
		}
		jListAutos.setModel(model);
	}
	private void inicializarComponentes()
	{		
		grid = new GridBagLayout();
		this.setLayout(grid);
	
		jBuscar = new JTextField();
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		constraints.fill = GridBagConstraints.BOTH;
		this.add(jBuscar, constraints);
		
		jListAutos = new JList();
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weighty = 10.0;
		this.add(jListAutos, constraints);
		
		jButtonUpdate = new JButton();
		jButtonUpdate.setText("Create New");
		jButtonUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CreateNewAuto create = new CreateNewAuto(MainVentana.this);
				create.setVisible(true);				
			}
		});
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 2;
		constraints.weighty = 2.0;
		
		this.add(jButtonUpdate, constraints);
	}
	public static void main(String[] args) {
		MainVentana v = new MainVentana();
		v.setVisible(true);
	}

}
