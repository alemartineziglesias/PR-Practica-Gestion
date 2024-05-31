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

public class AltaEquipo implements ActionListener, WindowListener
{
	Frame ventana = new Frame("Nuevo equipo");
	Label lblNombre = new Label("   Nombre:");
	TextField nombre = new TextField(20);
	Label lblCiudad = new Label("   Ciudad:");
	Choice ciudad = new Choice();
	Button aceptar = new Button("Aceptar");
	Button cancelar = new Button("Cancelar");
	Dialog mensaje = new Dialog(ventana, "mensaje", true);
	Label lblMensaje = new Label("Equipo creado correctamente");
	Datos datos = new Datos();
	String portapapeles;
	private int tipoUsuario;
	
	AltaEquipo(int tipoUsuario)
	{
		ventana.setLayout(new FlowLayout());
		datos.conectar();
		ventana.add(lblNombre);
		ventana.add(nombre);
		ventana.add(lblCiudad);
		String[] elementos = datos.rellenarChoiceCiudades();
		for(String elemento: elementos)
		{
			ciudad.add(elemento);
		}
		ventana.add(ciudad);
		ventana.add(aceptar);
		aceptar.addActionListener(this);
		ventana.add(cancelar);
		cancelar.addActionListener(this);
		ventana.addWindowListener(this);
		ventana.setSize(200, 200);
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
			if(nombre.getText().equals(""))
			{
				mensaje.setLayout(new FlowLayout());
				mensaje.addWindowListener(this);
				mensaje.setSize(250, 70);
				mensaje.setResizable(false);
				mensaje.setLocationRelativeTo(null);
				lblMensaje.setText("Escriba el nombre del equipo");
				mensaje.add(lblMensaje);
				mensaje.setVisible(true);
			}
			else
			{
				if(ciudad.getSelectedIndex() != 0)
				{
					boolean altaCorrecta = datos.altaEquipo(nombre.getText(), ciudad.getSelectedItem().split("-")[0]);
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
						lblMensaje.setText("Equipo creado correctamente");
						Utilidades.guardarLogAltaEquipo(nombre.getText(), ciudad.getSelectedItem().split("-")[1], tipoUsuario);
					}
					mensaje.add(lblMensaje);
					mensaje.setVisible(true);
				}
				else
				{
					mensaje.setLayout(new FlowLayout());
					mensaje.addWindowListener(this);
					mensaje.setSize(250, 70);
					mensaje.setResizable(false);
					mensaje.setLocationRelativeTo(null);
					lblMensaje.setText("Selecciona la ciudad local del equipo");
					mensaje.add(lblMensaje);
					mensaje.setVisible(true);
					nombre.setText(portapapeles);
				}
			}
		}
		else if(e.getSource().equals(cancelar))
		{
			ventana.setVisible(false);
		}
	}
}
