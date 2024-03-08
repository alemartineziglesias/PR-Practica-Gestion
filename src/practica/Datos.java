package practica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	public String dameEquipos()
	{
		String contenido = "";
		String sentencia = "SELECT * FROM equipos;";
		
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			rs = statement.executeQuery(sentencia);
			
			while(rs.next())
			{
				contenido = contenido + "ID: " + rs.getInt("idEquipo") + " | Nombre: " 
			
				+ rs.getString("nombreEquipo") + " | Ciudad: " 
				
				+ rs.getInt("idCiudadFK") + "\n";
			}
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL: " + e.toString());
		}
		return contenido;
	}
	
	public boolean altaEquipo(String nombre)
	{
		boolean altaCorrecta = true;
		String sentencia = "INSERT INTO equipos VALUES (null, '" + nombre + "');";
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
	
	public String dameFichajes()
	{
		String contenido = "";
		String sentencia = "SELECT * FROM fichajes;";
		
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			rs = statement.executeQuery(sentencia);
			
			while(rs.next())
			{
				contenido = contenido + "ID: " + rs.getInt("idFichaje") + " | Fecha: " 
			
				+ rs.getString("fechaFichaje") + " | Precio:  " 
						
				+ rs.getString("precioFichaje") + " | Equipo: " 
			
				+ rs.getInt("idEquipoFK") + " | Jugador: " 
			
				+ rs.getInt("idJugadorFK") + "\n";
			}
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL: " + e.toString());
		}
		return contenido;
	}
	
	public boolean altaFichaje(String fecha, String precio)
	{
		boolean altaCorrecta = true;
		String sentencia = "INSERT INTO fichajes VALUES (null, '" + fecha + "', " + precio + ");";
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

}