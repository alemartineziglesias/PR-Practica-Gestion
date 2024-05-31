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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AltaFichaje implements ActionListener, WindowListener {
	Frame ventana = new Frame("Nuevo fichaje");
	Label lblFecha = new Label("   Fecha:");
	TextField fecha = new TextField(20);
	LocalDate fechaActual = LocalDate.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	String hoy = fechaActual.format(formatter);
	Label lblPrecio = new Label("   Precio:");
	TextField precio = new TextField(20);
	Label lblJugador = new Label("   Jugador:");
	Choice jugador = new Choice();
	Label lblEquipo = new Label("   Equipo:");
	Choice equipo = new Choice();
	Button aceptar = new Button("Aceptar");
	Button cancelar = new Button("Cancelar");
	Dialog mensaje = new Dialog(ventana, "mensaje", true);
	Label lblMensaje = new Label("fichaje creado correctamente");
	Datos datos = new Datos();
	private int tipoUsuario;

	AltaFichaje(int tipoUsuario) 
	{
		ventana.setLayout(new FlowLayout());
		datos.conectar();
		ventana.add(lblFecha);
		ventana.add(fecha);
		fecha.setText(hoy);
		ventana.add(lblPrecio);
		ventana.add(precio);
		precio.setText("0.00");
		ventana.add(lblJugador);
		String[] elementos = datos.rellenarChoiceJugadores();
		for (String elemento : elementos) 
		{
			jugador.add(elemento);
		}
		ventana.add(jugador);
		ventana.add(lblEquipo);
		elementos = datos.rellenarChoiceEquipos();
		for (String elemento : elementos) 
		{
			equipo.add(elemento);
		}
		ventana.add(equipo);
		ventana.add(aceptar);
		aceptar.addActionListener(this);
		ventana.add(cancelar);
		cancelar.addActionListener(this);
		ventana.addWindowListener(this);
		ventana.setSize(250, 300);
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
			fecha.requestFocus();	
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
			try
			{
				int contador = 0;
				for(int i = 0; i < fecha.getText().length(); i++)
				{
					if(fecha.getText().charAt(i) == '/')
					{
						contador++;
					}
				}
				if(contador != 2)
				{
					mensaje.setLayout(new FlowLayout());
					mensaje.addWindowListener(this);
					mensaje.setSize(250, 70);
					mensaje.setResizable(false);
					mensaje.setLocationRelativeTo(null);
					lblMensaje.setText("La fecha es incorrecta");
					mensaje.add(lblMensaje);
					mensaje.setVisible(true);
					fecha.setText(hoy);
				}
				else if(precio.getText().equals(""))
				{
					mensaje.setLayout(new FlowLayout());
					mensaje.addWindowListener(this);
					mensaje.setSize(250, 70);
					mensaje.setResizable(false);
					mensaje.setLocationRelativeTo(null);
					lblMensaje.setText("Escriba el precio del fichaje");
					mensaje.add(lblMensaje);
					mensaje.setVisible(true);
				}
				else if(jugador.getSelectedIndex() != 0 & equipo.getSelectedIndex() != 0)
				{
					String[] comprobacion = null;
					for(int i = 0; i < 3; i++)
					{
						comprobacion = fecha.getText().split("/");
					}
					int dia = Integer.parseInt(comprobacion[0]);
					int mes = Integer.parseInt(comprobacion[1]);
					int anio = Integer.parseInt(comprobacion[2]);
					boolean fechaCorrecta = Datos.fechaCorrecta(dia, mes, anio);
					if(fechaCorrecta == false)
					{
						mensaje.setLayout(new FlowLayout());
						mensaje.addWindowListener(this);
						mensaje.setSize(250, 70);
						mensaje.setResizable(false);
						mensaje.setLocationRelativeTo(null);
						lblMensaje.setText("La fecha es incorrecta");
						mensaje.add(lblMensaje);
						mensaje.setVisible(true);
						fecha.setText(hoy);
					}
					else
					{
						try
						{
							BigDecimal precioCorrecto = new BigDecimal(Float.parseFloat(precio.getText()));
							precioCorrecto = precioCorrecto.setScale(2, RoundingMode.DOWN);
							boolean altaCorrecta = datos.altaFichaje(fecha.getText(), precioCorrecto, equipo.getSelectedItem().split("-")[0], jugador.getSelectedItem().split("-")[0]);
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
								Utilidades.guardarLogAltaFichaje(fecha.getText(), precioCorrecto, jugador.getSelectedItem().split("-")[1], equipo.getSelectedItem().split("-")[1], tipoUsuario);
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
							lblMensaje.setText("El precio es incorrecto");
							mensaje.add(lblMensaje);
							mensaje.setVisible(true);
						}

					}

				}
				else
				{
					mensaje.setLayout(new FlowLayout());
					mensaje.addWindowListener(this);
					mensaje.setSize(250, 70);
					mensaje.setResizable(false);
					mensaje.setLocationRelativeTo(null);
					lblMensaje.setText("Falta seleccionar un jugador/equipo");
					mensaje.add(lblMensaje);
					mensaje.setVisible(true);
				}	
			}
			catch(NumberFormatException nfe)
			{
				mensaje.setLayout(new FlowLayout());
				mensaje.addWindowListener(this);
				mensaje.setSize(250, 70);
				mensaje.setResizable(false);
				mensaje.setLocationRelativeTo(null);
				lblMensaje.setText("La fecha es incorrecta");
				mensaje.add(lblMensaje);
				mensaje.setVisible(true);
				fecha.setText(hoy);
			}
			catch(ArrayIndexOutOfBoundsException aiobe)
			{
				mensaje.setLayout(new FlowLayout());
				mensaje.addWindowListener(this);
				mensaje.setSize(250, 70);
				mensaje.setResizable(false);
				mensaje.setLocationRelativeTo(null);
				lblMensaje.setText("La fecha es incorrecta");
				mensaje.add(lblMensaje);
				mensaje.setVisible(true);
				fecha.setText(hoy);
			}
		}
		else if(e.getSource().equals(cancelar))
		{
			ventana.setVisible(false);
		}

	}
}
