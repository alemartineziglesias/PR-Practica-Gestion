package practica;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ModificacionCiudad implements WindowListener, ActionListener
{
	Frame ventana = new Frame("Modificación ciudad");
	Label lblModificacion = new Label("Elija el dato que quiera modificar:");
	Choice chociudades = new Choice();
	Button editar = new Button("Editar");

	Dialog dlgModificacion = new Dialog(ventana, "Modificación ciudad", true);
	Dialog dlgNotificacion = new Dialog(ventana, "Notificacion", true);
	Label lblNombre = new Label("   Nombre:");
	TextField nombre = new TextField(20);
	Button aceptar = new Button("Aceptar");
	Button cancelar = new Button("Cancelar");
	Label notificacion = new Label("");

	Datos datos = new Datos();

	String elementoSeleccionado;
	String ciudadSeleccionada;
	int tipoUsuario;

	public ModificacionCiudad(int tipoUsuario)
	{
		ventana.setLayout(new FlowLayout());
		ventana.addWindowListener(this);
		ventana.add(lblModificacion);

		datos.conectar();
		String[] elementos = datos.rellenarChoiceCiudades();
		for(String elemento: elementos)
		{
			chociudades.add(elemento);
		}
		ventana.add(chociudades);
		editar.addActionListener(this);
		ventana.add(editar);
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
		if(dlgModificacion.isActive())
		{
			dlgModificacion.setVisible(false);
		}
		else if(dlgNotificacion.isActive())
		{
			dlgNotificacion.setVisible(false);
		}
		else if(dlgNotificacion.isActive() & dlgModificacion.isActive())
		{
			dlgNotificacion.setVisible(false);
			dlgModificacion.setVisible(false);
		}
		else
		{
			ventana.setVisible(false);
			datos.desconectar();
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
		if(actionEvent.getSource().equals(editar)) 
		{
			if(chociudades.getSelectedIndex() != 0) 
			{
				dlgModificacion.setLayout(new FlowLayout());
				dlgModificacion.addWindowListener(this);
				dlgModificacion.setSize(210, 200);
				dlgModificacion.add(lblNombre);
				dlgModificacion.add(nombre);
				dlgModificacion.add(aceptar);
				dlgModificacion.add(cancelar);
				aceptar.addActionListener(this);
				cancelar.addActionListener(this);
				dlgModificacion.setResizable(false);
				dlgModificacion.setLocationRelativeTo(null);
				elementoSeleccionado = chociudades.getSelectedItem().split("-")[0];
				ciudadSeleccionada = chociudades.getSelectedItem().split("-")[1];
				String fundamentos = datos.seleccionModificacionCiudades(elementoSeleccionado);
				nombre.setText(fundamentos.split("-")[1]);
				dlgModificacion.setVisible(true); 
			} 
			else 
			{
				dlgNotificacion.setLayout(new FlowLayout());
				dlgNotificacion.addWindowListener(this);
				dlgNotificacion.setResizable(false);
				dlgNotificacion.setSize(250, 70);
				dlgNotificacion.setLocationRelativeTo(null);
				dlgNotificacion.add(notificacion);
				notificacion.setText("Elija el dato que quiera modificar");
				dlgNotificacion.setVisible(true);
			}
		} 
		else if(actionEvent.getSource().equals(aceptar)) 
		{
			if(nombre.getText().equals("")) 
			{
				dlgNotificacion.setLayout(new FlowLayout());
				dlgNotificacion.addWindowListener(this);
				dlgNotificacion.setResizable(false);
				dlgNotificacion.setSize(300, 70);
				dlgNotificacion.setLocationRelativeTo(null);
				dlgNotificacion.add(notificacion);
				notificacion.setText("No puede dejar vacío el nombre de la ciudad");
				dlgNotificacion.setVisible(true);
			}
			else
			{
				boolean modificacionCorrecta = datos.actualizacionCiudad(nombre.getText(), elementoSeleccionado);
				dlgNotificacion.setLayout(new FlowLayout());
				dlgNotificacion.addWindowListener(this);
				dlgNotificacion.setResizable(false);
				dlgNotificacion.setSize(250, 70);
				dlgNotificacion.setLocationRelativeTo(null);
				if(modificacionCorrecta == false) 
				{
					notificacion.setText("Ha habido un problema");
				} 
				else 
				{
					Utilidades.guardarLogModificacionCiudad(ciudadSeleccionada, nombre.getText(), tipoUsuario);
					notificacion.setText("Modificacion realizada");
				}
				dlgNotificacion.add(notificacion);
				dlgNotificacion.setVisible(true);
				
			} 
		} 
		else if(actionEvent.getSource().equals(cancelar)) 
		{
			dlgModificacion.setVisible(false);
		}
	}
}
