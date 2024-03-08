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

public class AltaFichaje implements ActionListener, WindowListener
{
	Frame ventana = new Frame("Nuevo fichaje");
	Label lblFecha = new Label("   Fecha:");
	TextField fecha = new TextField(20);
	Label lblPrecio = new Label("   Precio:");
	TextField precio = new TextField(20);
	Button aceptar = new Button("Aceptar");
	Button cancelar = new Button("Cancelar");
	Dialog mensaje = new Dialog(ventana, "mensaje", true);
	Label lblMensaje = new Label("fichaje creada correctamente");
	Datos datos = new Datos();
	
	AltaFichaje()
	{
		ventana.setLayout(new FlowLayout());
		datos.conectar();
		ventana.add(lblFecha);
		ventana.add(fecha);
		ventana.add(lblPrecio);
		ventana.add(precio);
		ventana.add(aceptar);
		aceptar.addActionListener(this);
		ventana.add(cancelar);
		cancelar.addActionListener(this);
		ventana.addWindowListener(this);
		ventana.setSize(210, 200);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
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
			fecha.setText("");
			fecha.requestFocus();	
		}
		else
		{
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
			boolean altaCorrecta = datos.altaFichaje(fecha.getText(), precio.getText());
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
				lblMensaje.setText("fichaje creado correctamente");
			}
			mensaje.add(lblMensaje);
			mensaje.setVisible(true);
			datos.desconectar();
			
		}
		else if(e.getSource().equals(cancelar))
		{
			ventana.setVisible(false);
		}
		
	}
}
