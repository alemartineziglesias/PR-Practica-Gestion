package practica;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class AltaJugador implements ActionListener, WindowListener
{
	Frame ventana = new Frame("Nuevo jugador");
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
	Dialog mensaje = new Dialog(ventana, "mensaje", true);
	Label lblMensaje = new Label("Jugador creado correctamente");
	Datos datos = new Datos();
	String portapapeles;
	private int tipoUsuario;
	
	AltaJugador(int tipoUsuario)
	{
		ventana.setLayout(new FlowLayout());
		datos.conectar();
		ventana.add(lblNombre);
		ventana.add(nombre);
		ventana.add(lblApellidos);
		ventana.add(apellidos);
		ventana.add(lblEdad);
		ventana.add(edad);
		ventana.add(lblPosicion);
		ventana.add(posicion);
		ventana.add(lblNacionalidad);
		ventana.add(nacionalidad);
		ventana.add(aceptar);
		aceptar.addActionListener(this);
		ventana.add(cancelar);
		cancelar.addActionListener(this);
		ventana.addWindowListener(this);
		ventana.setSize(210, 400);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
		this.tipoUsuario = tipoUsuario;
	}

	@Override
	public void windowOpened(WindowEvent e)
	{
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		if(mensaje.isActive())
		{
			mensaje.setVisible(false);
			nombre.setText("");
			nombre.requestFocus();	
		}
		else
		{
			ventana.setVisible(false);
			datos.desconectar();
		}
	}

	@Override
	public void windowClosed(WindowEvent e)
	{
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void windowIconified(WindowEvent e)
	{
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void windowDeiconified(WindowEvent e)
	{
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void windowActivated(WindowEvent e)
	{
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void windowDeactivated(WindowEvent e)
	{
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(aceptar))
		{
			String portapapeles = nombre.getText();
			if(nombre.getText().equals("") | apellidos.getText().equals("") | edad.getText().equals("") | posicion.getText().equals("") | nacionalidad.getText().equals(""))
			{
				mensaje.setLayout(new FlowLayout());
				mensaje.addWindowListener(this);
				mensaje.setSize(250, 70);
				mensaje.setResizable(false);
				mensaje.setLocationRelativeTo(null);
				lblMensaje.setText("Por favor, rellene todos los campos");
				mensaje.add(lblMensaje);
				mensaje.setVisible(true);
				nombre.setText(portapapeles);
			}
			else
			{
				try
				{
					boolean altaCorrecta = datos.altaJugador(nombre.getText(), apellidos.getText(), Integer.parseInt(edad.getText()), posicion.getText(), nacionalidad.getText());
					mensaje.setLayout(new FlowLayout());
					mensaje.addWindowListener(this);
					mensaje.setSize(250, 70);
					mensaje.setResizable(false);
					mensaje.setLocationRelativeTo(null);
					if(altaCorrecta == false)
					{
						lblMensaje.setText("Se ha producido un error");
					}
					else
					{
						lblMensaje.setText("Jugador creado correctamente");
						Utilidades.guardarLogAltaJugador(nombre.getText(), apellidos.getText(), edad.getText(), posicion.getText(), nacionalidad.getText(), tipoUsuario);
					}
					mensaje.add(lblMensaje);
					mensaje.setVisible(true);
				}
				catch(NumberFormatException nfe)
				{
					mensaje.setLayout(new FlowLayout());
					mensaje.addWindowListener(this);
					mensaje.setSize(250, 70);
					mensaje.setResizable(false);
					mensaje.setLocationRelativeTo(null);
					lblMensaje.setText("Solo se admiten números en la edad");
					mensaje.add(lblMensaje);
					mensaje.setVisible(true);
					nombre.setText(portapapeles);
					edad.setText("");
					edad.requestFocus();
				}
				catch(NullPointerException npe)
				{
					
				}
			}
		}
		else if(e.getSource().equals(cancelar))
		{
			ventana.setVisible(false);
		}
	}
}
