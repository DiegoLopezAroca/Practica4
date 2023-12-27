package practica;

import java.io.IOException;
import java.sql.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseDeDatosCartas {
	
	private static Connection con;
	private static Statement s;
	private static ResultSet rs;
	private static Logger logger;
	
	
	public static void main(String[] args) throws SQLException{
		
		
		try {
			logger = Logger.getLogger("BaseDeDatosCartas");
			logger.addHandler(new FileHandler("BaseDeDatosCartas.xml"));
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String comentarioSQL = "";
		
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:cartas.db");
			logger.log(Level.INFO, "Iniciando conexion" + con);
			s = con.createStatement();
			try {
				comentarioSQL = "create table carta (numero string, palo string)";
				logger.log(Level.INFO, "BD: " + comentarioSQL);
				s.executeUpdate(comentarioSQL);
			} catch (SQLException e) {
				logger.log(Level.INFO, "La tabla carta ya está creada");
			}
			try {
				comentarioSQL = "create table filtro (codigo integer not null, textoExplicativo string)";
				logger.log(Level.INFO, "BD: " + comentarioSQL);
				s.executeUpdate(comentarioSQL);;
			} catch (SQLException e) {
				// TODO: handle exception
				logger.log(Level.INFO, "La tabla ya esta creada");
			}
			try {
				comentarioSQL = "create table manoFiltro (filtroCodigo integer references filtro(codigo), id integer primary key autoincrement)";
				logger.log(Level.INFO, "BD: " + comentarioSQL);
				s.executeUpdate(comentarioSQL);;
			} catch (SQLException e) {
				// TODO: handle exception
				logger.log(Level.INFO, "La tabla ya esta creada");
			}
			try {
				comentarioSQL = "create table cartaMano (codigo integer, textoExplicativo string)";
				logger.log(Level.INFO, "BD: " + comentarioSQL);
				s.executeUpdate(comentarioSQL);
			} catch (SQLException e) {
				// TODO: handle exception
				logger.log(Level.INFO, "La tabla ya esta creada");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.log(Level.SEVERE, "No se ha podido iniciar la conexion");
			System.exit(0);
		}
	}

	public void insertarCarta(String numero, String palo) {
		// TODO Auto-generated method stub
		String comentarioSQL = "";
		try {
			comentarioSQL = "insert into carta values(" + numero + ", " + palo + ")";
			logger.log(Level.INFO, "Insercion de valores en tabla" + comentarioSQL);
			s.executeUpdate(comentarioSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertarFiltro(int codigo, String textoExplicativo) {
        String comentarioSQL = "";
        try {
            comentarioSQL = "insert into filtro VALUES(" + codigo + ", '" + textoExplicativo + "')";
            logger.log(Level.INFO, "Inserción de valores en tabla filtro: " + comentarioSQL);
            s.executeUpdate(comentarioSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public void insertarManoFiltro(int filtroCodigo) {
        String comentarioSQL = "";
        try {
            comentarioSQL = "insert into manoFiltro (filtroCodigo) values(" + filtroCodigo + ")";
            logger.log(Level.INFO, "Inserción de valores en tabla manoFiltro: " + comentarioSQL);
            s.executeUpdate(comentarioSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

