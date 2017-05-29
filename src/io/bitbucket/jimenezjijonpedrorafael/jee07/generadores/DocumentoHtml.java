package io.bitbucket.jimenezjijonpedrorafael.jee07.generadores;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public final class DocumentoHtml {

	public static String documentoHtml(String consultaSql, ResultSet conjuntoDeResultados) {
		StringBuffer documento = new StringBuffer();
		
		documento.append("<!DOCTYPE html>"); 
		documento.append("<html>");
		documento.append("<head>");
		documento.append("<meta charset=\"UTF-8\">");
		documento.append("<title>ServletConexionPostgreSQL</title>");
		documento.append("<link rel=\"stylesheet\" href=\"assets/css/normalize.css\">");
		documento.append("<link rel=\"stylesheet\" href=\"assets/css/main.css\">");
		documento.append("</head>");
		documento.append("<body>");
		documento.append("<div class=\"contenedor\">");
		documento.append("<div class=\"cabecera\">");
		documento.append("<h1>ServletConexionPostgreSQL</h1>");
		documento.append("</div>");
		documento.append("<div class=\"menu\">");
		documento.append("<ul>");
		documento.append("<li><a href=\".\">Inicio</a></li>");
		documento.append("</ul>");
		documento.append("</div>");
		documento.append("<div class=\"cuerpo\">");
		
		documento.append("<h1>Consulta SQL</h1>");
		documento.append("<p><code>" + consultaSql + "</code></p>");
		
		documento.append("<h2>Resultado</h2>");
		
		try {
			documento.append("<ul>");
			while (conjuntoDeResultados.next()) {
				
				int isbn = conjuntoDeResultados.getInt("isbn");
				String nombre= conjuntoDeResultados.getString("autor");
				String genero= conjuntoDeResultados.getString("genero");
				String descripcion= conjuntoDeResultados.getString("descripcion");
				String disponible = conjuntoDeResultados.getString("disponible");
				Double precio = conjuntoDeResultados.getDouble("precio");
				Date fechapublicacion= conjuntoDeResultados.getDate("fechapublicacion");
				

				documento.append("<li><strong>version_postgresql := </strong> <code>" + isbn+ "</code></li>");
				documento.append("<li><strong>fecha_sistema := </strong> <code>" + nombre + "</code></li>");
				documento.append("<li><strong>hora_sistema := </strong> <code>" + genero + "</code></li>");
				documento.append("<li><strong>version_postgresql := </strong> <code>" + descripcion+ "</code></li>");
				documento.append("<li><strong>fecha_sistema := </strong> <code>" + disponible + "</code></li>");
				documento.append("<li><strong>hora_sistema := </strong> <code>" + precio + "</code></li>");
				documento.append("<li><strong>hora_sistema := </strong> <code>" + fechapublicacion + "</code></li>");
				documento.append("<br><br><br><br><br>");
				
			}
			documento.append("</ul>");
		} catch (SQLException e) {
			documento.append("<strong>Â¡Error al tratar de leer el resultado!</strong>"+e);
		}
		
		documento.append("</div>");
		documento.append("</div>");
		documento.append("</body>");
		documento.append("</html>");
		
		return documento.toString();
	}
	
	public static void conResultadoDeConsultaSQL(HttpServletResponse response)
			throws IOException {

		Connection conexionAPostgreSql = null;
		Statement sentenciaDeConexion = null;
		ResultSet conjuntoDeResultados = null;
		
		/*
		 * Puedes encontrar mÃ¡s funciones de PostgreSQL en
		 *   https://www.postgresql.org/docs/9.6/static/functions-info.html
		 */
		/*
		String consultaSql = "select "
				+ "version() as version_postgresql, "
				+ "CURRENT_DATE as fecha_sistema, "
				+ "CURRENT_TIME as hora_sistema";
		*/
		String consultaSql = "select * from Libro";
		
		/*
		 * Investigar sobre: try-with-resources
		 */
		try {
			Context contexto = new InitialContext();
			DataSource fuenteDeDatos = (DataSource)contexto.lookup("java:comp/env/jdbc/PostgreSQL");
			conexionAPostgreSql = fuenteDeDatos.getConnection();
			sentenciaDeConexion = conexionAPostgreSql.createStatement();
			conjuntoDeResultados = sentenciaDeConexion.executeQuery(consultaSql);			
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			PrintWriter salida = response.getWriter();
			salida.println(documentoHtml(consultaSql, conjuntoDeResultados));
			
		} catch (Exception e) {
			System.err.println("Error durante la conexiÃ³n: " + e.getMessage());
		}
		finally {
			if (conjuntoDeResultados != null) {
				try {
					conjuntoDeResultados.close();
				} catch (Exception e) {
					System.err.println("Error cerrando el conjuntoDeResultados" + e.getMessage());
				}
			}
			if (sentenciaDeConexion != null) {
				try {
					sentenciaDeConexion.close();
				} catch (Exception e) {
					System.err.println("Error cerrando la sentenciaDeConexion" + e.getMessage());
				}
			}
			if (conexionAPostgreSql != null) {
				try {
					conexionAPostgreSql.close();
				} catch (Exception e) {
					System.err.println("Error cerrando la conexionAPostgreSQL" + e.getMessage());
				}
			}
		}
		
	}
}
