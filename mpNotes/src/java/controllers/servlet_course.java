/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;

import models.CRUDcourse;
import models.ConexionBaseDeDatos;
import models.course;

/**
 *
 * @author horahenaripo
 */
@WebServlet (name="servlet_course", urlPatterns = {"/course"})
public class servlet_course extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        try {

            String accion = request.getParameter("request");

            if (accion.equals("insert")) {

                String userId = request.getParameter("user_id");
                String name = request.getParameter("name");
                String fullName = request.getParameter("full_name");
                String description = request.getParameter("description");
                String knowledgeArea = request.getParameter("knowledge_area");
                String career = request.getParameter("career");
                int credits = Integer.parseInt(request.getParameter("credits"));
                String thematicContent = request.getParameter("thematic_content");
                String semester = request.getParameter("semester");
                String professor = request.getParameter("professor");

                course nuevoCourse = new course(0, userId, name, fullName, description, knowledgeArea, career, credits, thematicContent, semester, professor);

                ConexionBaseDeDatos conexion = new ConexionBaseDeDatos();
                CRUDcourse crudCourse = new CRUDcourse(conexion);
                crudCourse.agregarCourse(nuevoCourse);

                response.sendRedirect("router.jsp?msg=Curso '" + name + "' Agregado al Sistema&redir_to=dashboard.jsp");

            } else if (accion.equals("update")) {

                int courseId = Integer.parseInt(request.getParameter("course_id"));
                String userId = request.getParameter("user_id");
                String name = request.getParameter("name");
                String fullName = request.getParameter("full_name");
                String description = request.getParameter("description");
                String knowledgeArea = request.getParameter("knowledge_area");
                String career = request.getParameter("career");
                int credits = Integer.parseInt(request.getParameter("credits"));
                String thematicContent = request.getParameter("thematic_content");
                String semester = request.getParameter("semester");
                String professor = request.getParameter("professor");

                course courseAModificar = new course(courseId, userId, name, fullName, description, knowledgeArea, career, credits, thematicContent, semester, professor);

                ConexionBaseDeDatos conexion = new ConexionBaseDeDatos();
                CRUDcourse crudCourse = new CRUDcourse(conexion);
                crudCourse.modificarCourse(courseAModificar);

                response.sendRedirect("router.jsp?msg=Curso con ID " + courseId + " Modificado en el Sistema&redir_to=dashboard.jsp");

            } else if (accion.equals("remove")) {

                int courseId = Integer.parseInt(request.getParameter("course_id"));

                ConexionBaseDeDatos conexion = new ConexionBaseDeDatos();
                CRUDcourse crudCourse = new CRUDcourse(conexion);
                crudCourse.eliminarCourse(courseId);

                response.sendRedirect("router.jsp?msg=Curso con ID " + courseId + " Eliminado del Sistema&redir_to=dashboard.jsp");

            } else if (accion.equals("list")) {

                course[] listado = CRUDcourse.listarTodosLosCourses();
                HttpSession session = request.getSession();
                session.setAttribute("course.listar", listado);

                response.sendRedirect("dashboard.jsp?req=listc");

            } else if (accion.equals("consult")) {

                int courseId = Integer.parseInt(request.getParameter("course_id"));

                ConexionBaseDeDatos conexion = new ConexionBaseDeDatos();
                CRUDcourse crudCourse = new CRUDcourse(conexion);
                course encontrado = crudCourse.consultarCourse(courseId);

                if (encontrado != null) {
                    
                    HttpSession session = request.getSession();
                    session.setAttribute("course.encontrado", encontrado);
                    response.sendRedirect("view_course.jsp");
                     
                } else {
                     
                    response.sendRedirect("router.jsp?msg=Curso con ID " + courseId + " no encontrado.&redir_to=dashboard.jsp");
                }
                 return;

            } else {
                
                response.sendRedirect("router.jsp?msg=La Accion Solicitada para Cursos no es Correcta.&redir_to=dashboard.jsp");
                
            }

        } catch (Exception error) {
            
            response.sendRedirect("router.jsp?msg="+error.getMessage()+"&redir_to=login.jsp");
            
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
