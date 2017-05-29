package io.bitbucket.jimenezjijonpedrorafael.jee07.servlets;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.bitbucket.jimenezjijonpedrorafael.jee07.generadores.DocumentoHtml;

@WebServlet(
	urlPatterns = {
		"/ServletConexionPostgreSQL",
		"/conexion-a-postgresql"
	}
)
public class ServletConexionPostgreSQL extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		DocumentoHtml.conResultadoDeConsultaSQL(response);
		
	}

}
