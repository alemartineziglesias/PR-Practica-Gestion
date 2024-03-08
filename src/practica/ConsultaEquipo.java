package practica;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ConsultaEquipo implements ActionListener, WindowListener
{
	Frame ventana = new Frame("Listado equipos");
	TextArea listado = new TextArea(5, 40);
	Button btnVolver = new Button("Volver");
	Datos datos = new Datos();
	
	ConsultaEquipo()
	{
		ventana.setLayout(new FlowLayout());
		datos.conectar();
		listado.append(datos.dameEquipos());
		datos.desconectar();
		
		ventana.addWindowListener(this);
		ventana.add(listado);
		ventana.add(btnVolver);
		btnVolver.addActionListener(this);
		ventana.setSize(350, 200);
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
		ventana.setVisible(false);
		
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
		ventana.setVisible(false);
		
	}
	
}
