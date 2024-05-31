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

public class ModificacionEquipo implements WindowListener, ActionListener
{
	Frame ventana = new Frame("Modificación equipo");
	Label lblModificacion = new Label("Elija el dato que quiera modificar:");
	Choice choequipos = new Choice();
	Button editar = new Button("Editar");

	Dialog dlgModificacion = new Dialog(ventana, "Modificación equipo", true);
	Dialog dlgNotificacion = new Dialog(ventana, "Notificacion", true);
	Label lblNombre = new Label("   Nombre:");
	TextField nombre = new TextField(20);
	Label lblCiudad = new Label("   Ciudad:");
	Choice ciudad = new Choice();
	Button aceptar = new Button("Aceptar");
	Button cancelar = new Button("Cancelar");
	Label notificacion = new Label("");

	Datos datos = new Datos();

	String elementoSeleccionado;
	String equipoSeleccionado;
	int tipoUsuario;

	public ModificacionEquipo(int tipoUsuario)
	{
		ventana.setLayout(new FlowLayout());
		ventana.addWindowListener(this);
		ventana.add(lblModificacion);

		datos.conectar();
		String[] elementos = datos.rellenarChoiceEquipos();
		for(String elemento: elementos)
		{
			choequipos.add(elemento);
		}
		ventana.add(choequipos);
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
			if(choequipos.getSelectedIndex() != 0) 
			{
				dlgModificacion.setLayout(new FlowLayout());
				dlgModificacion.addWindowListener(this);
				dlgModificacion.setSize(200, 200);
				dlgModificacion.add(lblNombre);
				dlgModificacion.add(nombre);
				dlgModificacion.add(lblCiudad);
				String[] elementos = datos.rellenarChoiceCiudades();
				for (String elemento : elementos) 
				{
					ciudad.add(elemento);
				}
				dlgModificacion.add(ciudad);
				dlgModificacion.add(aceptar);
				dlgModificacion.add(cancelar);
				aceptar.addActionListener(this);
				cancelar.addActionListener(this);
				dlgModificacion.setResizable(false);
				dlgModificacion.setLocationRelativeTo(null);
				elementoSeleccionado = choequipos.getSelectedItem().split("-")[0];
				equipoSeleccionado = choequipos.getSelectedItem().split("-")[1];
				String fundamentos = datos.seleccionModificacionEquipos(elementoSeleccionado);
				nombre.setText(fundamentos.split("-")[0]);
				ciudad.select(fundamentos.split("-")[1] + "-" + fundamentos.split("-")[2]);
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
				notificacion.setText("No puede dejar el nombre del equipo vacío");
				dlgNotificacion.setVisible(true);
			} 
			else if(ciudad.getSelectedIndex() != 0) 
			{
				boolean modificacionCorrecta = datos.actualizacionEquipo(nombre.getText(), ciudad.getSelectedItem().split("-")[0], elementoSeleccionado);
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
					Utilidades.guardarLogModificacionEquipo(equipoSeleccionado, nombre.getText(), ciudad.getSelectedItem().split("-")[1], tipoUsuario);
					notificacion.setText("Modificacion realizada");
				}
				dlgNotificacion.add(notificacion);
				dlgNotificacion.setVisible(true);
				datos.desconectar();
			} 
			else 
			{
				dlgNotificacion.setLayout(new FlowLayout());
				dlgNotificacion.addWindowListener(this);
				dlgNotificacion.setResizable(false);
				dlgNotificacion.setSize(250, 70);
				dlgNotificacion.setLocationRelativeTo(null);
				dlgNotificacion.add(notificacion);
				notificacion.setText("Elija la ciudad del equipo");
				dlgNotificacion.setVisible(true);
			}
		} 
		else if(actionEvent.getSource().equals(cancelar)) 
		{
			dlgModificacion.setVisible(false);
		}
	}

}
