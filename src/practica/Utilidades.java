package practica;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilidades
{
	public static void guardarLog(int tipoUsuario, String mensaje)
	{
		String usuario2;
		if(tipoUsuario == 0)
		{
			usuario2 = "Administrador";
		}
		else
		{
			usuario2 = "Básico";
		}
		Date fecha = new Date();
		String pattern = "dd/MM/YYYY HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try
		{
			FileWriter fw = new FileWriter("historico.log", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);

			salida.println("[" + simpleDateFormat.format(fecha) + "][" + usuario2 + "][" + mensaje + "]");

			salida.close();
			bw.close();
			fw.close();
		}
		catch(IOException ioe)
		{
			System.out.println("Error:" + ioe.getMessage());
		}
	}
	
	public static void guardarLogAltaCiudad(String nombre, int tipoUsuario)
	{
		String usuario2;
		if(tipoUsuario == 0)
		{
			usuario2 = "Administrador";
		}
		else
		{
			usuario2 = "Básico";
		}
		Date fecha = new Date();
		String pattern = "dd/MM/YYYY HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try
		{
			FileWriter fw = new FileWriter("historico.log", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);

			salida.println("[" + simpleDateFormat.format(fecha) + "][" + usuario2 + "][Alta][Ciudades][" + nombre + "]");

			salida.close();
			bw.close();
			fw.close();
		}
		catch(IOException ioe)
		{
			System.out.println("Error:" + ioe.getMessage());
		}
	}
	
	public static void guardarLogConsultaCiudad(int tipoUsuario)
	{
		String usuario2;
		if(tipoUsuario == 0)
		{
			usuario2 = "Administrador";
		}
		else
		{
			usuario2 = "Básico";
		}
		Date fecha = new Date();
		String pattern = "dd/MM/YYYY HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try
		{
			FileWriter fw = new FileWriter("historico.log", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);

			salida.println("[" + simpleDateFormat.format(fecha) + "][" + usuario2 + "][Consulta][Ciudades]");

			salida.close();
			bw.close();
			fw.close();
		}
		catch(IOException ioe)
		{
			System.out.println("Error:" + ioe.getMessage());
		}
	}
	
	public static void guardarLogExportacionCiudad(int tipoUsuario)
	{
		String usuario2;
		if(tipoUsuario == 0)
		{
			usuario2 = "Administrador";
		}
		else
		{
			usuario2 = "Básico";
		}
		Date fecha = new Date();
		String pattern = "dd/MM/YYYY HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try
		{
			FileWriter fw = new FileWriter("historico.log", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);

			salida.println("[" + simpleDateFormat.format(fecha) + "][" + usuario2 + "][Consulta][Exportación][Ciudades]");

			salida.close();
			bw.close();
			fw.close();
		}
		catch(IOException ioe)
		{
			System.out.println("Error:" + ioe.getMessage());
		}
	}
	
	public static void guardarLogModificacionCiudad(String ciudad, String nombre, int tipoUsuario)
	{
		String usuario2;
		if(tipoUsuario == 0)
		{
			usuario2 = "Administrador";
		}
		else
		{
			usuario2 = "Básico";
		}
		Date fecha = new Date();
		String pattern = "dd/MM/YYYY HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try
		{
			FileWriter fw = new FileWriter("historico.log", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);

			salida.println("[" + simpleDateFormat.format(fecha) + "][" + usuario2 + "][Modificación][Ciudades][" + ciudad + "][" + nombre + "]");

			salida.close();
			bw.close();
			fw.close();
		}
		catch(IOException ioe)
		{
			System.out.println("Error:" + ioe.getMessage());
		}
	}
	
	public static void guardaLogBajaCiudad(String ciudad, int tipoUsuario)
	{
		String usuario2;
		if(tipoUsuario == 0)
		{
			usuario2 = "Administrador";
		}
		else
		{
			usuario2 = "Básico";
		}
		Date fecha = new Date();
		String pattern = "dd/MM/YYYY HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try
		{
			FileWriter fw = new FileWriter("historico.log", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);

			salida.println("[" + simpleDateFormat.format(fecha) + "][" + usuario2 + "][Baja][Ciudades][" + ciudad + "]");

			salida.close();
			bw.close();
			fw.close();
		}
		catch(IOException ioe)
		{
			System.out.println("Error:" + ioe.getMessage());
		}
	}
	
	public static void guardarLogAltaEquipo(String nombre, String elementoSeleccionado, int tipoUsuario)
	{
		String usuario2;
		if(tipoUsuario == 0)
		{
			usuario2 = "Administrador";
		}
		else
		{
			usuario2 = "Básico";
		}
		Date fecha = new Date();
		String pattern = "dd/MM/YYYY HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try
		{
			FileWriter fw = new FileWriter("historico.log", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);

			salida.println("[" + simpleDateFormat.format(fecha) + "][" + usuario2 + "][Alta][Equipos][" + nombre + ", " + elementoSeleccionado + "]");

			salida.close();
			bw.close();
			fw.close();
		}
		catch(IOException ioe)
		{
			System.out.println("Error:" + ioe.getMessage());
		}
	}
	
	public static void guardarLogConsultaEquipo(int tipoUsuario)
	{
		String usuario2;
		if(tipoUsuario == 0)
		{
			usuario2 = "Administrador";
		}
		else
		{
			usuario2 = "Básico";
		}
		Date fecha = new Date();
		String pattern = "dd/MM/YYYY HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try
		{
			FileWriter fw = new FileWriter("historico.log", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);

			salida.println("[" + simpleDateFormat.format(fecha) + "][" + usuario2 + "][Consulta][Equipos]");

			salida.close();
			bw.close();
			fw.close();
		}
		catch(IOException ioe)
		{
			System.out.println("Error:" + ioe.getMessage());
		}
	}
	
	public static void guardarLogExportacionEquipo(int tipoUsuario)
	{
		String usuario2;
		if(tipoUsuario == 0)
		{
			usuario2 = "Administrador";
		}
		else
		{
			usuario2 = "Básico";
		}
		Date fecha = new Date();
		String pattern = "dd/MM/YYYY HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try
		{
			FileWriter fw = new FileWriter("historico.log", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);

			salida.println("[" + simpleDateFormat.format(fecha) + "][" + usuario2 + "][Consulta][Exportación][Equipos]");

			salida.close();
			bw.close();
			fw.close();
		}
		catch(IOException ioe)
		{
			System.out.println("Error:" + ioe.getMessage());
		}
	}
	
	public static void guardarLogModificacionEquipo(String equipo, String nombre, String ciudad, int tipoUsuario)
	{
		String usuario2;
		if(tipoUsuario == 0)
		{
			usuario2 = "Administrador";
		}
		else
		{
			usuario2 = "Básico";
		}
		Date fecha = new Date();
		String pattern = "dd/MM/YYYY HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try
		{
			FileWriter fw = new FileWriter("historico.log", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);

			salida.println("[" + simpleDateFormat.format(fecha) + "][" + usuario2 + "][Modificación][Equipos][" + equipo + "][" + nombre + ", " + ciudad +"]");

			salida.close();
			bw.close();
			fw.close();
		}
		catch(IOException ioe)
		{
			System.out.println("Error:" + ioe.getMessage());
		}
	}
	
	public static void guardaLogBajaEquipo(String equipo, int tipoUsuario)
	{
		String usuario2;
		if(tipoUsuario == 0)
		{
			usuario2 = "Administrador";
		}
		else
		{
			usuario2 = "Básico";
		}
		Date fecha = new Date();
		String pattern = "dd/MM/YYYY HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try
		{
			FileWriter fw = new FileWriter("historico.log", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);

			salida.println("[" + simpleDateFormat.format(fecha) + "][" + usuario2 + "][Baja][Equipos][" + equipo + "]");

			salida.close();
			bw.close();
			fw.close();
		}
		catch(IOException ioe)
		{
			System.out.println("Error:" + ioe.getMessage());
		}
	}
	
	public static void guardarLogAltaJugador(String nombre, String apellidos, String edad, String posicion, String nacionalidad, int tipoUsuario)
	{
		String usuario2;
		if(tipoUsuario == 0)
		{
			usuario2 = "Administrador";
		}
		else
		{
			usuario2 = "Básico";
		}
		Date fecha = new Date();
		String pattern = "dd/MM/YYYY HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try
		{
			FileWriter fw = new FileWriter("historico.log", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);

			salida.println("[" + simpleDateFormat.format(fecha) + "][" + usuario2 + "][Alta][Jugadores][" + nombre + " " + apellidos + ", " + edad + " años, " + posicion + ", " + nacionalidad + "]");

			salida.close();
			bw.close();
			fw.close();
		}
		catch(IOException ioe)
		{
			System.out.println("Error:" + ioe.getMessage());
		}
	}
	
	public static void guardarLogConsultaJugador(int tipoUsuario)
	{
		String usuario2;
		if(tipoUsuario == 0)
		{
			usuario2 = "Administrador";
		}
		else
		{
			usuario2 = "Básico";
		}
		Date fecha = new Date();
		String pattern = "dd/MM/YYYY HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try
		{
			FileWriter fw = new FileWriter("historico.log", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);

			salida.println("[" + simpleDateFormat.format(fecha) + "][" + usuario2 + "][Consulta][Jugadores]");

			salida.close();
			bw.close();
			fw.close();
		}
		catch(IOException ioe)
		{
			System.out.println("Error:" + ioe.getMessage());
		}
	}
	
	public static void guardarLogExportacionJugador(int tipoUsuario)
	{
		String usuario2;
		if(tipoUsuario == 0)
		{
			usuario2 = "Administrador";
		}
		else
		{
			usuario2 = "Básico";
		}
		Date fecha = new Date();
		String pattern = "dd/MM/YYYY HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try
		{
			FileWriter fw = new FileWriter("historico.log", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);

			salida.println("[" + simpleDateFormat.format(fecha) + "][" + usuario2 + "][Consulta][Exportación][Jugadores]");

			salida.close();
			bw.close();
			fw.close();
		}
		catch(IOException ioe)
		{
			System.out.println("Error:" + ioe.getMessage());
		}
	}
	
	public static void guardarLogModificacionJugador(String jugador, String nombre, String apellidos, String edad, String posicion, String nacionalidad, int tipoUsuario)
	{
		String usuario2;
		if(tipoUsuario == 0)
		{
			usuario2 = "Administrador";
		}
		else
		{
			usuario2 = "Básico";
		}
		Date fecha = new Date();
		String pattern = "dd/MM/YYYY HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try
		{
			FileWriter fw = new FileWriter("historico.log", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);

			salida.println("[" + simpleDateFormat.format(fecha) + "][" + usuario2 + "][Modificación][Jugadores][" + jugador + "][" + nombre + ", " + apellidos + ", " + edad + ", " + posicion + ", " + nacionalidad + "]");

			salida.close();
			bw.close();
			fw.close();
		}
		catch(IOException ioe)
		{
			System.out.println("Error:" + ioe.getMessage());
		}
	}
	
	public static void guardaLogBajaJugador(String jugador, int tipoUsuario)
	{
		String usuario2;
		if(tipoUsuario == 0)
		{
			usuario2 = "Administrador";
		}
		else
		{
			usuario2 = "Básico";
		}
		Date fecha = new Date();
		String pattern = "dd/MM/YYYY HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try
		{
			FileWriter fw = new FileWriter("historico.log", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);

			salida.println("[" + simpleDateFormat.format(fecha) + "][" + usuario2 + "][Baja][Jugadores][" + jugador + "]");

			salida.close();
			bw.close();
			fw.close();
		}
		catch(IOException ioe)
		{
			System.out.println("Error:" + ioe.getMessage());
		}
	}
	
	public static void guardarLogAltaFichaje(String fechaFichaje, BigDecimal precio, String elementoSeleccionado1, String elementoSeleccionado2, int tipoUsuario)
	{
		String usuario2;
		if(tipoUsuario == 0)
		{
			usuario2 = "Administrador";
		}
		else
		{
			usuario2 = "Básico";
		}
		Date fecha = new Date();
		String pattern = "dd/MM/YYYY HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try
		{
			FileWriter fw = new FileWriter("historico.log", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);

			salida.println("[" + simpleDateFormat.format(fecha) + "][" + usuario2 + "][Alta][Fichajes][" + fechaFichaje + ", " + precio + ", " + elementoSeleccionado1 + " | " + elementoSeleccionado2 + "]");

			salida.close();
			bw.close();
			fw.close();
		}
		catch(IOException ioe)
		{
			System.out.println("Error:" + ioe.getMessage());
		}
	}
	
	public static void guardarLogConsultaFichaje(int tipoUsuario)
	{
		String usuario2;
		if(tipoUsuario == 0)
		{
			usuario2 = "Administrador";
		}
		else
		{
			usuario2 = "Básico";
		}
		Date fecha = new Date();
		String pattern = "dd/MM/YYYY HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try
		{
			FileWriter fw = new FileWriter("historico.log", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);

			salida.println("[" + simpleDateFormat.format(fecha) + "][" + usuario2 + "][Consulta][Fichajes]");

			salida.close();
			bw.close();
			fw.close();
		}
		catch(IOException ioe)
		{
			System.out.println("Error:" + ioe.getMessage());
		}
	}
	
	public static void guardarLogExportaciónFichaje(int tipoUsuario)
	{
		String usuario2;
		if(tipoUsuario == 0)
		{
			usuario2 = "Administrador";
		}
		else
		{
			usuario2 = "Básico";
		}
		Date fecha = new Date();
		String pattern = "dd/MM/YYYY HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try
		{
			FileWriter fw = new FileWriter("historico.log", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);

			salida.println("[" + simpleDateFormat.format(fecha) + "][" + usuario2 + "][Consulta][Exportación][Fichajes]");

			salida.close();
			bw.close();
			fw.close();
		}
		catch(IOException ioe)
		{
			System.out.println("Error:" + ioe.getMessage());
		}
	}
	
	public static void guardarLogModificacionFichaje(String fechaFichaje, BigDecimal precio, String equipo, String jugador, int tipoUsuario)
	{
		String usuario2;
		if(tipoUsuario == 0)
		{
			usuario2 = "Administrador";
		}
		else
		{
			usuario2 = "Básico";
		}
		Date fecha = new Date();
		String pattern = "dd/MM/YYYY HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try
		{
			FileWriter fw = new FileWriter("historico.log", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);

			salida.println("[" + simpleDateFormat.format(fecha) + "][" + usuario2 + "][Modificación][Fichajes][" + equipo + " | " + jugador + "][" + fechaFichaje + ", " + precio + ", " + equipo + ", " + jugador + "]");

			salida.close();
			bw.close();
			fw.close();
		}
		catch(IOException ioe)
		{
			System.out.println("Error:" + ioe.getMessage());
		}
	}
	
	public static void guardaLogBajaFichaje(String fichaje, int tipoUsuario)
	{
		String usuario2;
		if(tipoUsuario == 0)
		{
			usuario2 = "Administrador";
		}
		else
		{
			usuario2 = "Básico";
		}
		Date fecha = new Date();
		String pattern = "dd/MM/YYYY HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try
		{
			FileWriter fw = new FileWriter("historico.log", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);

			salida.println("[" + simpleDateFormat.format(fecha) + "][" + usuario2 + "][Baja][Fichajes][" + fichaje + "]");

			salida.close();
			bw.close();
			fw.close();
		}
		catch(IOException ioe)
		{
			System.out.println("Error:" + ioe.getMessage());
		}
	}
}
