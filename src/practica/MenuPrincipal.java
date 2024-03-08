package practica;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MenuPrincipal implements ActionListener, WindowListener
{
	Frame ventana = new Frame("Menú");
	MenuBar barraMenu = new MenuBar();
	Menu muCiudades = new Menu("Ciudades");
	Menu muEquipos = new Menu("Equipos");
	Menu muJugadores = new Menu("Jugadores");
	Menu muFichajes = new Menu("Fichajes");
	MenuItem miConsultaCiudad = new MenuItem("Consulta");
	MenuItem miAltaCiudad = new MenuItem("Alta");
	MenuItem miBajaCiudad = new MenuItem("Baja");
	MenuItem miModificacionCiudad = new MenuItem("Modificación");
	MenuItem miConsultaEquipo = new MenuItem("Consulta");
	MenuItem miAltaEquipo = new MenuItem("Alta");
	MenuItem miBajaEquipo = new MenuItem("Baja");
	MenuItem miModificacionEquipo = new MenuItem("Modificación");
	MenuItem miConsultaJugador = new MenuItem("Consulta");
	MenuItem miAltaJugador = new MenuItem("Alta");
	MenuItem miBajaJugador = new MenuItem("Baja");
	MenuItem miModificacionJugador = new MenuItem("Modificación");
	MenuItem miConsultaFichaje = new MenuItem("Consulta");
	MenuItem miAltaFichaje = new MenuItem("Alta");
	MenuItem miBajaFichaje = new MenuItem("Baja");
	MenuItem miModificacionFichaje = new MenuItem("Modificación");
	
	MenuPrincipal(int tipoUsuario)
	{
		ventana.setLayout(new FlowLayout());
		ventana.setMenuBar(barraMenu);
		
		muCiudades.add(miAltaCiudad);
		muEquipos.add(miAltaEquipo);
		muJugadores.add(miAltaJugador);
		muFichajes.add(miAltaFichaje);
		
		if(tipoUsuario == 0)
		{
			muCiudades.add(miConsultaCiudad);
			muCiudades.add(miModificacionCiudad);
			muCiudades.add(miBajaCiudad);
		}
		
		miAltaCiudad.addActionListener(this);
		
		if(tipoUsuario == 0)
		{
			miConsultaCiudad.addActionListener(this);
		}
		
		if(tipoUsuario == 0)
		{
			muEquipos.add(miConsultaEquipo);
			muEquipos.add(miModificacionEquipo);
			muEquipos.add(miBajaEquipo);
		}
		
		miAltaEquipo.addActionListener(this);
		
		if(tipoUsuario == 0)
		{
			miConsultaEquipo.addActionListener(this);
		}
		
		if(tipoUsuario == 0)
		{
			muJugadores.add(miConsultaJugador);
			muJugadores.add(miModificacionJugador);
			muJugadores.add(miBajaJugador);
		}
		
		miAltaJugador.addActionListener(this);
		
		if(tipoUsuario == 0)
		{
			miConsultaJugador.addActionListener(this);
		}
		
		if(tipoUsuario == 0)
		{
			muFichajes.add(miConsultaFichaje);
			muFichajes.add(miModificacionFichaje);
			muFichajes.add(miBajaFichaje);
		}
		
		miAltaFichaje.addActionListener(this);
		
		if(tipoUsuario == 0)
		{
			miConsultaFichaje.addActionListener(this);
		}
		
		barraMenu.add(muCiudades);
		barraMenu.add(muEquipos);
		barraMenu.add(muJugadores);
		barraMenu.add(muFichajes);
		
		ventana.addWindowListener(this);
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
		System.exit(0);
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
		if(e.getSource().equals(miConsultaCiudad))
		{
			new ConsultaCiudad();
		}
		else if(e.getSource().equals(miAltaCiudad))
		{
			new AltaCiudad();
		}
		else if(e.getSource().equals(miConsultaEquipo))
		{
			new ConsultaEquipo();
		}
		else if(e.getSource().equals(miAltaEquipo))
		{
			new AltaEquipo();
		}
		else if(e.getSource().equals(miConsultaJugador))
		{
			new ConsultaJugador();
		}
		else if(e.getSource().equals(miAltaJugador))
		{
			new AltaJugador();
		}
		else if(e.getSource().equals(miConsultaFichaje))
		{
			new ConsultaFichaje();
		}
		else if(e.getSource().equals(miAltaFichaje))
		{
			new AltaFichaje();
		}
	}
}
