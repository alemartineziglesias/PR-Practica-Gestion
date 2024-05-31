package practica;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class Datos
{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/practica_gestion";
	String login = "root";
	String password = "Mr9+agar";

	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;

	Datos() 
	{

	}

	public boolean conectar()
	{
		boolean conexionCorrecta = true;

		try
		{
			Class.forName(driver);
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Se ha producido un error al cargar el Driver");
			conexionCorrecta = false;
		}

		try
		{
			connection = DriverManager.getConnection(url, login, password);
		}
		catch(SQLException e)
		{
			System.out.println("Se produjo un error al conectar a la Base de Datos");
			conexionCorrecta = false;
		}
		return conexionCorrecta;
	}

	public boolean comprobarCredenciales(String usuario, String clave)
	{
		boolean credencialesCorrectas = true;

		String sentencia = "SELECT * FROM usuarios " 
				+ "WHERE nombreUsuario='" + usuario + "' " 
				+ "AND claveUsuario = SHA2('" + clave + "', 256);";

		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);

			if(!rs.next())
			{
				credencialesCorrectas = false;
			}
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}
		return credencialesCorrectas;
	}

	public int dameTipo(String usuario)
	{
		String sentencia = "SELECT tipoUsuario FROM usuarios WHERE nombreUsuario = '" + usuario + "';";

		try 
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

			rs = statement.executeQuery(sentencia);

			if(rs.next())
			{
				if(rs.getInt("tipoUsuario") == 0)
				{
					return 0;
				}
			}
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}
		return 1;
	}

	public void desconectar()
	{
		try
		{
			statement.close();
			connection.close();
		}
		catch(SQLException e)
		{
			System.out.println("Error al cerrar " + e.toString());
		}
		catch(NullPointerException npe)
		{

		}
	}

	public String dameCiudades()
	{
		String contenido = "";
		String sentencia = "SELECT * FROM ciudades;";

		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

			rs = statement.executeQuery(sentencia);

			while(rs.next())
			{
				contenido = contenido + "ID: " + rs.getInt("idCiudad") + " | Nombre: " 

				+ rs.getString("nombreCiudad") + "\n";
			}
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL: " + e.toString());
		}
		return contenido;
	}

	public boolean altaCiudad(String nombre)
	{
		boolean altaCorrecta = true;

		if(nombre.contains("*") | nombre.contains("-"))
		{
			return false;
		}
		String sentencia = "INSERT INTO ciudades VALUES (null, '" + nombre + "');";
		System.out.println(sentencia);
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
			altaCorrecta = false;
		}
		return altaCorrecta;
	}

	public String[] rellenarChoiceCiudades()
	{
		String elementosCadena = "Elija una ciudad...*";
		String sentencia = "SELECT * FROM ciudades";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);

			while(rs.next())
			{
				elementosCadena = elementosCadena + rs.getString("idCiudad") + "-" + rs.getString("nombreCiudad") + "*";
			}
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}
		return elementosCadena.split("\\*");
	}

	public boolean bajaCiudad(String elementoSeleccionado)
	{
		boolean errorSQL = false;
		String sentencia = "DELETE FROM ciudades WHERE idCiudad = " + elementoSeleccionado;
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
		}
		catch(SQLException e)
		{
			errorSQL = true;
		}
		return errorSQL;
	}

	public String rellenarExcelCiudades()
	{
		String elementosCadena = "";
		String sentencia = "SELECT * FROM ciudades";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);

			while(rs.next())
			{
				elementosCadena = elementosCadena + rs.getString("idCiudad") + "-" + rs.getString("nombreCiudad") + "*";
			}
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}
		return elementosCadena;
	}

	public String seleccionModificacionCiudades(String elementoSeleccionado)
	{
		String elementosCadena = "";
		String sentencia = "SELECT * FROM ciudades WHERE idCiudad = " + elementoSeleccionado + ";";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);

			while(rs.next())
			{
				elementosCadena = elementosCadena + rs.getString("idCiudad") + "-" + rs.getString("nombreCiudad");
			}
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}
		return elementosCadena;
	}

	public boolean actualizacionCiudad(String nombre, String elementoSeleccionado) 
	{
		boolean modificacionCorrecta = true;

		if (nombre.contains("*") | nombre.contains("-")) 
		{
			return false;
		}
		String sentencia = "UPDATE ciudades SET nombreCiudad = '" + nombre + "' WHERE idCiudad = " + elementoSeleccionado + ";";
		try 
		{
			statement = connection.createStatement();
			int filasAfectadas = statement.executeUpdate(sentencia);
			if (filasAfectadas == 0) 
			{
				modificacionCorrecta = false;
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
			modificacionCorrecta = false;
		}
		return modificacionCorrecta;
	}

	public String dameEquipos()
	{
		String contenido = "";
		String sentencia = "SELECT * FROM equipos JOIN ciudades ON equipos.idCiudadFK = ciudades.idCiudad;";

		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

			rs = statement.executeQuery(sentencia);

			while(rs.next())
			{
				contenido = contenido + "ID: " + rs.getInt("idEquipo") + " | Nombre: " 

				+ rs.getString("nombreEquipo") + " | Ciudad: " 

				+ rs.getString("nombreCiudad") + "\n";
			}
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL: " + e.toString());
		}
		return contenido;
	}

	public boolean altaEquipo(String nombre, String elementoSeleccionado)
	{
		boolean altaCorrecta = true;

		if(nombre.contains("*") | nombre.contains("-"))
		{
			return false;
		}
		String sentencia = "INSERT INTO equipos VALUES (null, '" + nombre + "', '" + elementoSeleccionado + "');";
		System.out.println(sentencia);
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
			altaCorrecta = false;
		}
		return altaCorrecta;
	}

	public String[] rellenarChoiceEquipos()
	{
		String elementosCadena = "Elija un equipo...*";
		String sentencia = "SELECT * FROM equipos";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);

			while(rs.next())
			{
				elementosCadena = elementosCadena + rs.getString("idEquipo") + "-" + rs.getString("nombreEquipo") + "*";
			}
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}
		return elementosCadena.split("\\*");
	}

	public String seleccionModificacionEquipos(String elementoSeleccionado)
	{
		String elementosCadena = "";
		String sentencia = "SELECT * FROM equipos JOIN ciudades ON equipos.idCiudadFK = ciudades.idCiudad WHERE idEquipo = " + elementoSeleccionado + ";";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);

			while(rs.next())
			{
				elementosCadena = elementosCadena + rs.getString("nombreEquipo") + "-" + rs.getString("idCiudad") + "-" + rs.getString("nombreCiudad");
			}
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}
		return elementosCadena;
	}

	public boolean actualizacionEquipo(String nombre, String ciudad, String elementoSeleccionado) 
	{
		boolean modificacionCorrecta = true;

		if(nombre.contains("*") | nombre.contains("-"))
		{
			return false;
		}
		String sentencia = "UPDATE equipos SET nombreEquipo = '" + nombre + "', idCiudadFK = " + ciudad + " WHERE idEquipo = " + elementoSeleccionado + ";";
		try 
		{
			statement = connection.createStatement();
			int filasAfectadas = statement.executeUpdate(sentencia);
			if (filasAfectadas == 0) 
			{
				modificacionCorrecta = false;
			}
		} 
		catch(SQLException e) 
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
			modificacionCorrecta = false;
		}
		return modificacionCorrecta;
	}

	public boolean bajaEquipo(String elementoSeleccionado)
	{
		boolean errorSQL = false;
		String sentencia = "DELETE FROM equipos WHERE idEquipo = " + elementoSeleccionado;
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
		}
		catch(SQLException e)
		{
			errorSQL = true;
		}
		return errorSQL;
	}

	public String rellenarExcelEquipos()
	{
		String elementosCadena = "";
		String sentencia = "SELECT * FROM equipos JOIN ciudades ON equipos.idCiudadFK = ciudades.idCiudad";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);

			while(rs.next())
			{
				elementosCadena = elementosCadena + rs.getString("idEquipo") + "-" + rs.getString("nombreEquipo") + "-" + rs.getString("nombreCiudad") + "*";
			}
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}
		return elementosCadena;
	}

	public String dameJugadores()
	{
		String contenido = "";
		String sentencia = "SELECT * FROM jugadores;";

		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

			rs = statement.executeQuery(sentencia);

			while(rs.next())
			{
				contenido = contenido + "ID: " + rs.getInt("idJugador") + " | Nombre completo: " 

				+ rs.getString("nombreJugador") + " " 

				+ rs.getString("apellidosJugador") + " | Edad: " 

				+ rs.getInt("edadJugador") + " | Posición: " 

				+ rs.getString("posicionJugador") + " | Nacionalidad: " 

				+ rs.getString("nacionalidadJugador") + "\n";
			}
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL: " + e.toString());
		}
		return contenido;
	}

	public boolean altaJugador(String nombre, String apellidos, int edad, String posicion, String nacionalidad)
	{
		boolean altaCorrecta = true;

		if(nombre.contains("*") | apellidos.contains("*") | posicion.contains("*") | nacionalidad.contains("*") | nombre.contains("-") | apellidos.contains("-") | posicion.contains("-") | nacionalidad.contains("-"))
		{
			return false;
		}
		String sentencia = "INSERT INTO jugadores VALUES (null, '" + nombre + "', '" + apellidos + "', " + edad +", '" + posicion + "', '" + nacionalidad + "');";
		System.out.println(sentencia);
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
			altaCorrecta = false;
		}
		return altaCorrecta;
	}

	public String[] rellenarChoiceJugadores()
	{
		String elementosCadena = "Elija un jugador...*";
		String sentencia = "SELECT * FROM jugadores";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);

			while(rs.next())
			{
				elementosCadena = elementosCadena + rs.getString("idJugador") + "-" + rs.getString("nombreJugador") + " " + rs.getString("apellidosJugador") + "*";
			}
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}
		return elementosCadena.split("\\*");
	}

	public boolean bajaJugador(String elementoSeleccionado)
	{
		boolean errorSQL = false;
		String sentencia = "DELETE FROM jugadores WHERE idJugador = " + elementoSeleccionado;
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
		}
		catch(SQLException e)
		{
			errorSQL = true;
		}
		return errorSQL;
	}

	public String rellenarExcelJugadores()
	{
		String elementosCadena = "";
		String sentencia = "SELECT * FROM jugadores";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);

			while(rs.next())
			{
				elementosCadena = elementosCadena + rs.getString("idJugador") + "-" + rs.getString("nombreJugador") + "-" + rs.getString("apellidosJugador") + "-" + rs.getInt("edadJugador") + "-" + rs.getString("posicionJugador") + "-" + rs.getString("nacionalidadJugador") + "*";
			}
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}
		return elementosCadena;
	}

	public boolean actualizacionJugador(String nombre, String apellidos, int edad, String posicion, String nacionalidad, String elementoSeleccionado) 
	{
		boolean modificacionCorrecta = true;

		if(nombre.contains("*") | apellidos.contains("*") | posicion.contains("*") | nacionalidad.contains("*") | nombre.contains("-") | apellidos.contains("-") | posicion.contains("-") | nacionalidad.contains("-"))
		{
			return false;
		}
		String sentencia = "UPDATE jugadores SET nombreJugador = '" + nombre + "', apellidosJugador = '" + apellidos + "', edadJugador = " + edad + ", posicionJugador = '" + posicion + "', nacionalidadJugador = '" + nacionalidad + "' WHERE idJugador = " + elementoSeleccionado + ";";
		try 
		{
			statement = connection.createStatement();
			int filasAfectadas = statement.executeUpdate(sentencia);
			if (filasAfectadas == 0) 
			{
				modificacionCorrecta = false;
			}
		} 
		catch(SQLException e) 
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
			modificacionCorrecta = false;
		}
		return modificacionCorrecta;
	}

	public String seleccionModificacionJugadores(String elementoSeleccionado)
	{
		String elementosCadena = "";
		String sentencia = "SELECT * FROM jugadores WHERE idJugador = " + elementoSeleccionado + ";";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);

			while(rs.next())
			{
				elementosCadena = elementosCadena + rs.getString("nombreJugador") +  "-" + rs.getString("apellidosJugador") + "-" + rs.getInt("edadJugador") + "-" + rs.getString("posicionJugador") + "-" + rs.getString("nacionalidadJugador");
			}
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}
		return elementosCadena;
	}

	public String dameFichajes()
	{
		String contenido = "";
		String sentencia = "SELECT * FROM equipos INNER JOIN fichajes ON equipos.idEquipo = fichajes.idEquipoFK INNER JOIN jugadores ON fichajes.idJugadorFK = jugadores.idJugador;";

		try
		{
			String pattern = "dd/MM/YYYY";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

			rs = statement.executeQuery(sentencia);

			while(rs.next())
			{
				contenido = contenido + "ID: " + rs.getInt("idFichaje") + " | Fecha: " 

				+ simpleDateFormat.format(rs.getDate("fechaFichaje")) + " | Precio:  " 

				+ rs.getString("precioFichaje") + " | Equipo: " 

				+ rs.getString("nombreEquipo") + " | Jugador: " 

				+ rs.getString("nombreJugador") + " " + rs.getString("apellidosJugador") + "\n";
			}
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL: " + e.toString());
		}
		return contenido;
	}

	public boolean altaFichaje(String fecha, BigDecimal bd, String elementoSeleccionado1, String elementoSeleccionado2)
	{
		boolean altaCorrecta = true;
		String[] contenedor = null;
		for(int i = 0; i < 3; i++)
		{
			contenedor = fecha.split("/");
		}
		int dia = Integer.parseInt(contenedor[0]);
		int mes = Integer.parseInt(contenedor[1]);
		int anio = Integer.parseInt(contenedor[2]);
		String sentencia = "INSERT INTO fichajes VALUES (null, '" + anio + "-" + mes + "-" + dia + "', " + bd + ", " + elementoSeleccionado1 + ", " + elementoSeleccionado2 + ");";
		System.out.println(sentencia);
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
			altaCorrecta = false;
		}
		return altaCorrecta;
	}

	public String seleccionModificacionFichajes(String elementoSeleccionado)
	{
		String pattern = "dd/MM/YYYY";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String elementosCadena = "";
		String sentencia = "SELECT * FROM equipos JOIN fichajes ON equipos.idEquipo = fichajes.idEquipoFK INNER JOIN jugadores ON fichajes.idJugadorFK = jugadores.idJugador WHERE idFichaje = " + elementoSeleccionado + ";";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);

			while(rs.next())
			{
				elementosCadena = elementosCadena + simpleDateFormat.format(rs.getDate("fechaFichaje")) + "-" + rs.getString("precioFichaje") + "-" + rs.getString("idEquipo") + "-" + rs.getString("nombreEquipo") + "-" + rs.getString("idJugador") + "-" + rs.getString("nombreJugador") + " " + rs.getString("apellidosJugador");
			}
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}
		return elementosCadena;
	}

	public boolean actualizacionFichaje(String fecha, BigDecimal bd, String equipo, String jugador, String elementoSeleccionado) 
	{
		String fechaSQL = fecha.split("/")[2] + "-" + fecha.split("/")[1] + "-" + fecha.split("/")[0];
		boolean modificacionCorrecta = true;
		String sentencia = "UPDATE fichajes SET fechaFichaje = '" + fechaSQL + "', precioFichaje = " + bd + ", idEquipoFK = " + equipo + ", idJugadorFK = " + jugador + " WHERE idFichaje = " + elementoSeleccionado + ";";
		try 
		{
			statement = connection.createStatement();
			int filasAfectadas = statement.executeUpdate(sentencia);
			if (filasAfectadas == 0) 
			{
				modificacionCorrecta = false;
			}
		} 
		catch(SQLException e) 
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
			modificacionCorrecta = false;
		}
		return modificacionCorrecta;
	}

	public String[] rellenarChoiceFichajes()
	{
		String elementosCadena = "Elija un fichaje...*";
		String sentencia = "SELECT * FROM equipos INNER JOIN fichajes ON equipos.idEquipo = fichajes.idEquipoFK INNER JOIN jugadores ON fichajes.idJugadorFK = jugadores.idJugador";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);

			while(rs.next())
			{
				elementosCadena = elementosCadena + rs.getString("idFichaje") + "-" + rs.getString("nombreJugador") + " " + rs.getString("apellidosJugador") + " | " + rs.getString("nombreEquipo") + "*";
			}
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}
		return elementosCadena.split("\\*");
	}

	public void bajaFichaje(String elementoSeleccionado)
	{
		String sentencia = "DELETE FROM fichajes WHERE idFichaje = " + elementoSeleccionado;
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}
	}

	public String rellenarExcelFichajes()
	{
		String pattern = "dd/MM/YYYY";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		String elementosCadena = "";
		String sentencia = "SELECT * FROM jugadores INNER JOIN fichajes ON jugadores.idJugador = fichajes.idJugadorFK INNER JOIN equipos ON fichajes.idEquipoFK = equipos.idEquipo";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);

			while(rs.next())
			{
				elementosCadena = elementosCadena + rs.getString("idFichaje") + "-" + simpleDateFormat.format(rs.getDate("fechaFichaje")) + "-" + rs.getString("precioFichaje") + "-" + rs.getString("nombreJugador") + " " + rs.getString("apellidosJugador") + "-" + rs.getString("nombreEquipo") + "*";
			}
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}
		return elementosCadena;
	}

	public static boolean fechaCorrecta(int dia, int mes, int anio)
	{
		if(dia < 1 | dia > 31 | mes < 1 | mes > 12)
		{
			return false;
		}
		else if(dia > 30 & (mes == 4 | mes == 6 | mes == 9 | mes == 11))
		{
			return false;
		}
		else if(dia > 28 & mes == 2 & anio % 4 != 0 | dia > 29 & mes == 2 & anio % 4 == 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

}