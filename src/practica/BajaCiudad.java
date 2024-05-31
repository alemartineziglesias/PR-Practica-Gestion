package practica;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class BajaCiudad implements WindowListener, ActionListener
{
	Frame ventana = new Frame("Baja Ciudad");
	Label lblBaja = new Label("Elija la Ciudad a Borrar:");
	Choice choCiudades = new Choice();
	Button btnAceptar = new Button("Aceptar");

	Dialog dlgSeguro = new Dialog(ventana, "¿Segur@?", true);
	Label lblMensaje = new Label("¿Seguro de borrar?");
	Button btnSi = new Button("Sí");
	Button btnNo = new Button("No");
	Dialog error = new Dialog(ventana, "Error", true);
	Label lblError = new Label("La ciudad esta siendo usada por un equipo, borre el equipo antes");

	Datos datos = new Datos();

	int tipoUsuario;

	public BajaCiudad(int tipoUsuario)
	{
		ventana.setLayout(new FlowLayout());
		ventana.addWindowListener(this);
		ventana.add(lblBaja);

		datos.conectar();
		String[] elementos = datos.rellenarChoiceCiudades();
		datos.desconectar();
		for(String elemento: elementos)
		{
			choCiudades.add(elemento);
		}
		ventana.add(choCiudades);
		btnAceptar.addActionListener(this);
		btnSi.addActionListener(this);
		btnNo.addActionListener(this);
		ventana.add(btnAceptar);
		ventana.setSize(250,150);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
		this.tipoUsuario = tipoUsuario;
	}

	public void windowActivated(WindowEvent windowEvent)
	{

	}

	public void windowClosed(WindowEvent windowEvent) 
	{

	}

	public void windowClosing(WindowEvent windowEvent)
	{
		if(dlgSeguro.isActive())
		{
			dlgSeguro.setVisible(false);
		}
		else
		{
			ventana.setVisible(false);
		}
	}

	public void windowDeactivated(WindowEvent windowEvent) 
	{

	}

	public void windowDeiconified(WindowEvent windowEvent) 
	{

	}

	public void windowIconified(WindowEvent windowEvent) 
	{

	}

	public void windowOpened(WindowEvent windowEvent) 
	{

	}

	public void actionPerformed(ActionEvent actionEvent)
	{
		if(actionEvent.getSource().equals(btnAceptar))
		{
			if(choCiudades.getSelectedIndex()!= 0)
			{
				dlgSeguro.setLayout(new FlowLayout());
				dlgSeguro.addWindowListener(this);
				dlgSeguro.setSize(250,70);
				dlgSeguro.setResizable(false);
				dlgSeguro.setLocationRelativeTo(null);
				dlgSeguro.add(lblMensaje);
				dlgSeguro.add(btnSi);
				dlgSeguro.add(btnNo);
				dlgSeguro.setVisible(true);
			}
		}
		else if(actionEvent.getSource().equals(btnSi))
		{

			datos.conectar();
			String elementoSeleccionado = choCiudades.getSelectedItem().split("-")[0];
			Utilidades.guardaLogBajaCiudad(choCiudades.getSelectedItem().split("-")[1], tipoUsuario);
			datos.bajaCiudad(elementoSeleccionado);
			if(datos.bajaCiudad(elementoSeleccionado) == true)
			{
				error.setLayout(new FlowLayout());
				error.addWindowListener(this);
				error.setSize(400, 70);
				error.setResizable(false);
				error.setLocationRelativeTo(null);
				error.add(lblError);
				error.setVisible(true);
			}
			datos.desconectar();
			dlgSeguro.setVisible(false);
			ventana.setVisible(false);
		}
		else if(actionEvent.getSource().equals(btnNo))
		{
			dlgSeguro.setVisible(false);
		}
	}
}