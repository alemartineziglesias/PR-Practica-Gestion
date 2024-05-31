package practica;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ConsultaCiudad implements ActionListener, WindowListener
{
	Frame ventana = new Frame("Listado ciudades");
	TextArea listado = new TextArea(5, 40);
	Button btnVolver = new Button("Volver");
	Button btnExportar = new Button("Exportar");
	Dialog mensaje = new Dialog(ventana, "mensaje", true);
	Label lblMensaje = new Label("");
	Datos datos = new Datos();
	int tipoUsuario;
	
	ConsultaCiudad(int tipoUsuario)
	{
		ventana.setLayout(new FlowLayout());
		datos.conectar();
		listado.append(datos.dameCiudades());
		
		ventana.addWindowListener(this);
		ventana.add(listado);
		ventana.add(btnVolver);
		btnVolver.addActionListener(this);
		ventana.add(btnExportar);
		btnExportar.addActionListener(this);
		ventana.setSize(350, 200);
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
		if(e.getSource().equals(btnExportar))
		{
			String nombreArchivo="Ciudades.xlsx";
			String rutaArchivo= "C:\\Users\\studi\\Downloads\\" + nombreArchivo;
			String hoja = "Hoja1";
			XSSFWorkbook libro = new XSSFWorkbook();
			XSSFSheet hoja1 = libro.createSheet(hoja);
			// Cabeceras de la hoja de excel
			String [] header = new String[]{"ID", "Ciudades"};
			// Contenido de la hoja de excel
			String elementos = datos.rellenarExcelCiudades();
			String [] filas = elementos.split("\\*");
			String [][] document = new String[filas.length][];
			for (int i = 0; i < filas.length; i++) 
			{
	            String fila = filas[i];
	            String[] columnas = fila.split("-");
	            document[i] = new String[columnas.length];
	            for (int j = 0; j < columnas.length; j++) 
	            {
	            	document[i][j] = (columnas[j]);
	            }
			}
			//Poner en negrita la cabecera
			CellStyle style = libro.createCellStyle();
			XSSFFont font = libro.createFont();
			font.setBold(true);
			style.setFont(font);
			//Generar los datos para el fichero
			for (int i = 0; i <= document.length; i++)
			{
				XSSFRow row = hoja1.createRow(i); // Se crean las filas
				for (int j = 0; j < header.length; j++)
				{
					if (i == 0)
					{ // Para la cabecera
						XSSFCell cell= row.createCell(j); // Se crean las celdas para lacabecera, junto con la posición
						cell.setCellStyle(style); // Se añade el style creadoanteriormente
						cell.setCellValue(header[j]); // Se añade el contenido
					}
					else
					{ // Para el contenido
						XSSFCell cell = row.createCell(j); // Se crean las celdas para lacabecera, junto con la posición
						cell.setCellValue(document[i - 1][j]); // Se añade el contenido
					}
				}
			}
			File file;
			file = new File(rutaArchivo);
			try (FileOutputStream fileOuS = new FileOutputStream(file))
			{
				if (file.exists())
				{ // Si el archivo ya existe, se elimina
					file.delete();
				}
				//Se guarda la información en el fichero
				libro.write(fileOuS);
				fileOuS.flush();
				fileOuS.close();
				mensaje.setLayout(new FlowLayout());
				mensaje.addWindowListener(this);
				mensaje.setSize(250, 70);
				mensaje.setResizable(false);
				mensaje.setLocationRelativeTo(null);
				lblMensaje.setText("Archivo Creado");
				mensaje.add(lblMensaje);
				mensaje.setVisible(true);
				Utilidades.guardarLogExportacionCiudad(tipoUsuario);
				libro.close();
			}
			catch (FileNotFoundException fnfe)
			{
				mensaje.setLayout(new FlowLayout());
				mensaje.addWindowListener(this);
				mensaje.setSize(300, 70);
				mensaje.setResizable(false);
				mensaje.setLocationRelativeTo(null);
				lblMensaje.setText("El archivo no se encuentra o está en uso...");
				mensaje.add(lblMensaje);
				mensaje.setVisible(true);
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
		else if(e.getSource().equals(btnVolver))
		{
			ventana.setVisible(false);
			datos.desconectar();
		}
	}
}