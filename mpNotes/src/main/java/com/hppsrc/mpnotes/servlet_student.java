/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.hppsrc.mpnotes;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author horahenaripo
 */
@WebServlet (name="servlet_student", urlPatterns = {"/student"})
public class servlet_student extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        
        try {

            String accion = request.getParameter("request"); // CAPTURAR LA ACCION

                ConexionBaseDeDatos conexion = new ConexionBaseDeDatos();
                CRUDstudent crudEstudiante = new CRUDstudent(conexion);

                    if (accion.equals("insert")) {

                        String username = request.getParameter("username");
                        String password = request.getParameter("password");
                        String name = request.getParameter("name");
                        String email = request.getParameter("email");

                        student nuevoEstudiante = new student(username, password, name, email);
                        crudEstudiante.agregarStudent(nuevoEstudiante);

                        response.sendRedirect("web/dashboard.jsp?msg=Estudiante " + username + " Agregado al Sistema"); 

                    } else if (accion.equals("update")) {

                        String username = request.getParameter("username");
                        String password = request.getParameter("password");
                        String name = request.getParameter("name");
                        String email = request.getParameter("email");

                        student studentAModificar = new student(username, password, name, email);
                        crudEstudiante.modificarStudent(studentAModificar);

                        response.sendRedirect("web/dashboard.jsp?msg=Estudiante "+username+" Modificado en el Sistema"); 

                    } else if (accion.equals("remove")) {

                        String username = request.getParameter("username");
                        crudEstudiante.eliminarStudent(username);

                        response.sendRedirect("web/dashboard.jsp?msg=Estudiante "+username+" Eliminado del Sistema"); 

                    } else if (accion.equals("list")) {

                        student[] listado = CRUDstudent.listarTodosLosStudents();
                        HttpSession session = request.getSession();
                        session.setAttribute("student.listar", listado);

                        response.sendRedirect("web/dashboard.jsp?req=list");

                    } else if (accion.equals("login")) {

                        String username = request.getParameter("username");
                        String password = request.getParameter("password");
                        student encontrado = CRUDstudent.iniciarSesion(username, password);

                        HttpSession session = request.getSession();
                        session.setAttribute("student.login", encontrado);

                        response.sendRedirect("web/dashboard.jsp?msg=Bienvenido al Sistema");

                    } else if (accion.equals("exit")) {

                        HttpSession session = request.getSession();
                        session.setAttribute("student.login", null);
                        session.invalidate();

                        response.sendRedirect("web/index.jsp?msg=Sesion cerrada"); 

                    } else {

                        // TODO redireccionar a pagina anterior con mensaje de error
                        // tal vez hacer que esto lo haga otra cosa?
                        response.sendRedirect("web/router.jsp?msg=La Accion Solicitada no es Correcta&redir_back=true");

                    }

            } catch (Exception error) {

                // TODO redireccionar a pagina anterior con mensaje de error
                response.sendRedirect("web/router.jsp?mensaje="+error.getMessage()+"&redir_back=true");

            }
        }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
        
}
