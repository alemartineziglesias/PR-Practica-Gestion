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

public class ModificacionFichaje implements WindowListener, ActionListener
{
	Frame ventana = new Frame("Modificación fichaje");
	Label lblModificacion = new Label("Elija el dato que quiera modificar:");
	Choice chofichajes = new Choice();
	Button editar = new Button("Editar");

	Dialog dlgModificacion = new Dialog(ventana, "Modificación fichaje", true);
	Dialog dlgNotificacion = new Dialog(ventana, "Notificacion", true);
	Label lblFecha = new Label("   Fecha:");
	TextField fecha = new TextField(20);
	Label lblPrecio = new Label("   Precio:");
	TextField precio = new TextField(20);
	Label lblJugador = new Label("   Jugador:");
	Choice jugador = new Choice();
	Label lblEquipo = new Label("   Equipo:");
	Choice equipo = new Choice();
	Button aceptar = new Button("Aceptar");
	Button cancelar = new Button("Cancelar");
	Label notificacion = new Label("");

	Datos datos = new Datos();

	String elementoSeleccionado;
	String fichajeSeleccionado;
	int tipoUsuario;

	public ModificacionFichaje(int tipoUsuario)
	{
		ventana.setLayout(new FlowLayout());
		ventana.addWindowListener(this);
		ventana.add(lblModificacion);

		datos.conectar();
		String[] elementos = datos.rellenarChoiceFichajes();
		for(String elemento: elementos)
		{
			chofichajes.add(elemento);
		}
		ventana.add(chofichajes);
		editar.addActionListener(this);
		ventana.add(editar);
		ventana.setSize(300,150);
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
			if(chofichajes.getSelectedIndex() != 0) 
			{
				dlgModificacion.setLayout(new FlowLayout());
				dlgModificacion.addWindowListener(this);
				dlgModificacion.setSize(250, 300);
				dlgModificacion.add(lblFecha);
				dlgModificacion.add(fecha);
				dlgModificacion.add(lblPrecio);
				dlgModificacion.add(precio);
				dlgModificacion.add(lblJugador);
				String[] elementos = datos.rellenarChoiceJugadores();
				for (String elemento : elementos) 
				{
					jugador.add(elemento);
				}
				dlgModificacion.add(jugador);
				dlgModificacion.add(lblEquipo);
				String[] elementos2 = datos.rellenarChoiceEquipos();
				for (String elemento2 : elementos2) 
				{
					equipo.add(elemento2);
				}
				dlgModificacion.add(equipo);
				dlgModificacion.add(aceptar);
				dlgModificacion.add(cancelar);
				aceptar.addActionListener(this);
				cancelar.addActionListener(this);
				dlgModificacion.setResizable(false);
				dlgModificacion.setLocationRelativeTo(null);
				elementoSeleccionado = chofichajes.getSelectedItem().split("-")[0];
				String fundamentos = datos.seleccionModificacionFichajes(elementoSeleccionado);
				fecha.setText(fundamentos.split("-")[0]);
				precio.setText(fundamentos.split("-")[1]);
				equipo.select(fundamentos.split("-")[2] + "-" + fundamentos.split("-")[3]);
				jugador.select(fundamentos.split("-")[4] + "-" + fundamentos.split("-")[5]);
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
			try
			{
			if(precio.getText().equals("")) 
			{
				dlgNotificacion.setLayout(new FlowLayout());
				dlgNotificacion.addWindowListener(this);
				dlgNotificacion.setResizable(false);
				dlgNotificacion.setSize(300, 70);
				dlgNotificacion.setLocationRelativeTo(null);
				dlgNotificacion.add(notificacion);
				notificacion.setText("No puede dejar los campos vacios");
				dlgNotificacion.setVisible(true);
			} 
			else if(equipo.getSelectedIndex() != 0 & jugador.getSelectedIndex() != 0) 
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
						dlgNotificacion.setLayout(new FlowLayout());
						dlgNotificacion.addWindowListener(this);
						dlgNotificacion.setSize(250, 70);
						dlgNotificacion.setResizable(false);
						dlgNotificacion.setLocationRelativeTo(null);
						notificacion.setText("La fecha es incorrecta");
						dlgNotificacion.add(notificacion);
						dlgNotificacion.setVisible(true);
					}
					else if(Datos.fechaCorrecta(Integer.parseInt(fecha.getText().split("/")[0]), Integer.parseInt(fecha.getText().split("/")[1]), Integer.parseInt(fecha.getText().split("/")[2])) == false)
					{
						dlgNotificacion.setLayout(new FlowLayout());
						dlgNotificacion.addWindowListener(this);
						dlgNotificacion.setSize(250, 70);
						dlgNotificacion.setResizable(false);
						dlgNotificacion.setLocationRelativeTo(null);
						notificacion.setText("La fecha es incorrecta");
						dlgNotificacion.add(notificacion);
						dlgNotificacion.setVisible(true);
					}
					BigDecimal precioCorrecto = new BigDecimal(Float.parseFloat(precio.getText()));
					precioCorrecto = precioCorrecto.setScale(2, RoundingMode.DOWN);
					boolean modificacionCorrecta = datos.actualizacionFichaje(fecha.getText(), precioCorrecto, equipo.getSelectedItem().split("-")[0], jugador.getSelectedItem().split("-")[0], elementoSeleccionado);
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
						Utilidades.guardarLogModificacionFichaje(fecha.getText(), precioCorrecto, equipo.getSelectedItem().split("-")[1], jugador.getSelectedItem().split("-")[1], tipoUsuario);
						notificacion.setText("Modificacion realizada");
					}
					dlgNotificacion.add(notificacion);
					dlgNotificacion.setVisible(true);
				}
				catch(NumberFormatException nfe)
				{
					dlgNotificacion.setLayout(new FlowLayout());
					dlgNotificacion.addWindowListener(this);
					dlgNotificacion.setResizable(false);
					dlgNotificacion.setSize(300, 70);
					dlgNotificacion.setLocationRelativeTo(null);
					dlgNotificacion.add(notificacion);
					notificacion.setText("El precio debe ser un número decimal");
					dlgNotificacion.setVisible(true);
				}
			} 
			else 
			{
				dlgNotificacion.setLayout(new FlowLayout());
				dlgNotificacion.addWindowListener(this);
				dlgNotificacion.setResizable(false);
				dlgNotificacion.setSize(250, 70);
				dlgNotificacion.setLocationRelativeTo(null);
				dlgNotificacion.add(notificacion);
				notificacion.setText("Elija el equipo y el jugador del fichaje");
				dlgNotificacion.setVisible(true);
			}
			}
			catch(NumberFormatException nfe)
			{
				dlgNotificacion.setLayout(new FlowLayout());
				dlgNotificacion.addWindowListener(this);
				dlgNotificacion.setSize(250, 70);
				dlgNotificacion.setResizable(false);
				dlgNotificacion.setLocationRelativeTo(null);
				notificacion.setText("La fecha es incorrecta");
				dlgNotificacion.add(notificacion);
				dlgNotificacion.setVisible(true);
			}
			catch(ArrayIndexOutOfBoundsException aiobe)
			{
				dlgNotificacion.setLayout(new FlowLayout());
				dlgNotificacion.addWindowListener(this);
				dlgNotificacion.setSize(250, 70);
				dlgNotificacion.setResizable(false);
				dlgNotificacion.setLocationRelativeTo(null);
				notificacion.setText("La fecha es incorrecta");
				dlgNotificacion.add(notificacion);
				dlgNotificacion.setVisible(true);
			}
		} 
		else if(actionEvent.getSource().equals(cancelar)) 
		{
			dlgModificacion.setVisible(false);
		}
	}

}
