package practica;

import java.awt.Button;
import java.awt.Desktop;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

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
	Button ayuda = new Button("Ayuda");
	Dialog mensaje = new Dialog(ventana, "Error", true);
	Label lblMensaje = new Label("");
	private int tipoUsuario;
	public static final String DEST = "manualAyuda.pdf";

	MenuPrincipal(int tipoUsuario)
	{
		this.tipoUsuario = tipoUsuario;
		ventana.setLayout(new FlowLayout());
		ventana.setMenuBar(barraMenu);
		ventana.add(ayuda);
		ayuda.addActionListener(this);

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
			miModificacionCiudad.addActionListener(this);
			miBajaCiudad.addActionListener(this);
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
			miModificacionEquipo.addActionListener(this);
			miBajaEquipo.addActionListener(this);
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
			miModificacionJugador.addActionListener(this);
			miBajaJugador.addActionListener(this);
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
			miModificacionFichaje.addActionListener(this);
			miBajaFichaje.addActionListener(this);
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
			new ConsultaCiudad(tipoUsuario);
			Utilidades.guardarLogConsultaCiudad(tipoUsuario);
		}
		else if(e.getSource().equals(miAltaCiudad))
		{
			new AltaCiudad(tipoUsuario);
		}
		else if(e.getSource().equals(miModificacionCiudad))
		{
			new ModificacionCiudad(tipoUsuario);
		}
		else if(e.getSource().equals(miBajaCiudad))
		{
			new BajaCiudad(tipoUsuario);
		}
		else if(e.getSource().equals(miConsultaEquipo))
		{
			new ConsultaEquipo(tipoUsuario);
			Utilidades.guardarLogConsultaEquipo(tipoUsuario);
		}
		else if(e.getSource().equals(miAltaEquipo))
		{
			new AltaEquipo(tipoUsuario);
		}
		else if(e.getSource().equals(miModificacionEquipo))
		{
			new ModificacionEquipo(tipoUsuario);
		}
		else if(e.getSource().equals(miBajaEquipo))
		{
			new BajaEquipo(tipoUsuario);
		}
		else if(e.getSource().equals(miConsultaJugador))
		{
			new ConsultaJugador(tipoUsuario);
			Utilidades.guardarLogConsultaJugador(tipoUsuario);
		}
		else if(e.getSource().equals(miAltaJugador))
		{
			new AltaJugador(tipoUsuario);
		}
		else if(e.getSource().equals(miModificacionJugador))
		{
			new ModificacionJugador(tipoUsuario);
		}
		else if (e.getSource().equals(miBajaJugador))
		{
			new BajaJugador(tipoUsuario);
		}
		else if(e.getSource().equals(miConsultaFichaje))
		{
			new ConsultaFichaje(tipoUsuario);
			Utilidades.guardarLogConsultaFichaje(tipoUsuario);
		}
		else if(e.getSource().equals(miAltaFichaje))
		{
			new AltaFichaje(tipoUsuario);
		}
		else if(e.getSource().equals(miModificacionFichaje))
		{
			new ModificacionFichaje(tipoUsuario);
		}
		else if(e.getSource().equals(miBajaFichaje))
		{
			new BajaFichaje(tipoUsuario);
		}
		else if(e.getSource().equals(ayuda))
		{
			try
			{
				File pdf = new File("manualAyuda.pdf");
				Desktop.getDesktop().open(pdf);
			} 
			catch (IOException e1)
			{
				mensaje.setLayout(new FlowLayout());
				mensaje.addWindowListener(this);
				mensaje.setSize(300, 70);
				mensaje.setResizable(false);
				mensaje.setLocationRelativeTo(null);
				lblMensaje.setText("Ha habido un problema");
				mensaje.add(lblMensaje);
				mensaje.setVisible(true);
			}
			catch(IllegalArgumentException iae)
			{
				mensaje.setLayout(new FlowLayout());
				mensaje.addWindowListener(this);
				mensaje.setSize(300, 70);
				mensaje.setResizable(false);
				mensaje.setLocationRelativeTo(null);
				lblMensaje.setText("El archivo que busca no existe o no se ha encontrado");
				mensaje.add(lblMensaje);
				mensaje.setVisible(true);
			}
		}
	}
}
