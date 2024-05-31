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

public class ModificacionJugador implements WindowListener, ActionListener
{
	Frame ventana = new Frame("Modificación jugador");
	Label lblModificacion = new Label("Elija el dato que quiera modificar:");
	Choice chojugadores = new Choice();
	Button editar = new Button("Editar");

	Dialog dlgModificacion = new Dialog(ventana, "Modificación jugador", true);
	Dialog dlgNotificacion = new Dialog(ventana, "Notificacion", true);
	Label lblNombre = new Label("   Nombre:");
	TextField nombre = new TextField(20);
	Label lblApellidos = new Label("   Apellidos:");
	TextField apellidos = new TextField(20);
	Label lblEdad = new Label("   Edad:");
	TextField edad = new TextField(20);
	Label lblPosicion = new Label("   Posicion:");
	TextField posicion = new TextField(20);
	Label lblNacionalidad = new Label("   Nacionalidad:");
	TextField nacionalidad = new TextField(20);
	Button aceptar = new Button("Aceptar");
	Button cancelar = new Button("Cancelar");
	Label notificacion = new Label("");

	Datos datos = new Datos();

	String elementoSeleccionado;
	String jugadorSeleccionado;
	int tipoUsuario;

	public ModificacionJugador(int tipoUsuario)
	{
		ventana.setLayout(new FlowLayout());
		ventana.addWindowListener(this);
		ventana.add(lblModificacion);

		datos.conectar();
		String[] elementos = datos.rellenarChoiceJugadores();
		for(String elemento: elementos)
		{
			chojugadores.add(elemento);
		}
		ventana.add(chojugadores);
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
			if(chojugadores.getSelectedIndex() != 0) 
			{
				dlgModificacion.setLayout(new FlowLayout());
				dlgModificacion.addWindowListener(this);
				dlgModificacion.setSize(210, 400);
				dlgModificacion.add(lblNombre);
				dlgModificacion.add(nombre);
				dlgModificacion.add(lblApellidos);
				dlgModificacion.add(apellidos);
				dlgModificacion.add(lblEdad);
				dlgModificacion.add(edad);
				dlgModificacion.add(lblPosicion);
				dlgModificacion.add(posicion);
				dlgModificacion.add(lblNacionalidad);
				dlgModificacion.add(nacionalidad);
				dlgModificacion.add(aceptar);
				dlgModificacion.add(cancelar);
				aceptar.addActionListener(this);
				cancelar.addActionListener(this);
				dlgModificacion.setResizable(false);
				dlgModificacion.setLocationRelativeTo(null);
				elementoSeleccionado = chojugadores.getSelectedItem().split("-")[0];
				jugadorSeleccionado = chojugadores.getSelectedItem().split("-")[1];
				String fundamentos = datos.seleccionModificacionJugadores(elementoSeleccionado);
				nombre.setText(fundamentos.split("-")[0]);
				apellidos.setText(fundamentos.split("-")[1]);
				edad.setText(fundamentos.split("-")[2]);
				posicion.setText(fundamentos.split("-")[3]);
				nacionalidad.setText(fundamentos.split("-")[4]);
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
			if(nombre.getText().equals("") | apellidos.getText().equals("") | edad.getText().equals("") | posicion.getText().equals("") | nacionalidad.getText().equals("")) 
			{
				dlgModificacion.setLayout(new FlowLayout());
				dlgModificacion.addWindowListener(this);
				dlgModificacion.setResizable(false);
				dlgModificacion.setSize(250, 70);
				dlgModificacion.setLocationRelativeTo(null);
				dlgModificacion.add(notificacion);
				notificacion.setText("No puede dejar ningún campo vacío");
				dlgModificacion.setVisible(true);
			}
			else
			{
				try
				{
					boolean modificacionCorrecta = datos.actualizacionJugador(nombre.getText(), apellidos.getText(), Integer.parseInt(edad.getText()), posicion.getText(), nacionalidad.getText(), elementoSeleccionado);
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
						Utilidades.guardarLogModificacionJugador(jugadorSeleccionado, nombre.getText(), apellidos.getText(), edad.getText(), posicion.getText(), nacionalidad.getText(), tipoUsuario);
						notificacion.setText("Modificacion realizada");
					}
					dlgNotificacion.add(notificacion);
					dlgNotificacion.setVisible(true);
					datos.desconectar();
				}
				catch(NumberFormatException nfe)
				{
					dlgModificacion.setLayout(new FlowLayout());
					dlgModificacion.addWindowListener(this);
					dlgModificacion.setResizable(false);
					dlgModificacion.setSize(250, 70);
					dlgModificacion.setLocationRelativeTo(null);
					dlgModificacion.add(notificacion);
					notificacion.setText("La edad debe ser un número entero");
					dlgModificacion.setVisible(true);
				}
			} 
		} 
		else if(actionEvent.getSource().equals(cancelar)) 
		{
			dlgModificacion.setVisible(false);
		}
	}
}