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

public class AltaCiudad implements ActionListener, WindowListener
{
	Frame ventana = new Frame("Nueva ciudad");
	Label lblNombre = new Label("   Nombre:");
	TextField nombre = new TextField(20);
	Button aceptar = new Button("Aceptar");
	Button cancelar = new Button("Cancelar");
	Dialog mensaje = new Dialog(ventana, "mensaje", true);
	Label lblMensaje = new Label("Ciudad creada correctamente");
	Datos datos = new Datos();
	private int tipoUsuario;

	AltaCiudad(int tipoUsuario)
	{
		ventana.setLayout(new FlowLayout());
		datos.conectar();
		ventana.add(lblNombre);
		ventana.add(nombre);
		ventana.add(aceptar);
		aceptar.addActionListener(this);
		ventana.add(cancelar);
		cancelar.addActionListener(this);
		ventana.addWindowListener(this);
		ventana.setSize(210, 200);
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
			datos.desconectar();
			ventana.setVisible(false);
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
			if(nombre.getText().equals(""))
			{
				mensaje.setLayout(new FlowLayout());
				mensaje.addWindowListener(this);
				mensaje.setSize(250, 70);
				mensaje.setResizable(false);
				mensaje.setLocationRelativeTo(null);
				lblMensaje.setText("Escriba el nombre de una ciudad");
				mensaje.add(lblMensaje);
				mensaje.setVisible(true);
			}
			else
			{
				boolean altaCorrecta = datos.altaCiudad(nombre.getText());
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
					lblMensaje.setText("Ciudad creada correctamente");
					Utilidades.guardarLogAltaCiudad(nombre.getText(), tipoUsuario);
				}
				mensaje.add(lblMensaje);
				mensaje.setVisible(true);
			}
		}
		else if(e.getSource().equals(cancelar))
		{
			ventana.setVisible(false);
		}
	}
}
