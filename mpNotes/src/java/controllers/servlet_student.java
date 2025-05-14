package controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;

import models.CRUDstudent;
import models.ConexionBaseDeDatos;
import models.student;

@WebServlet(name = "servlet_student", urlPatterns = {"/student"})
public class servlet_student extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession(false);
        student usuarioLoggeado;
        String userId = null;

        if (session != null) {
            
            Object loginAttribute = session.getAttribute("login");
            
            if (loginAttribute instanceof student) {
                usuarioLoggeado = (student) loginAttribute;
                userId = usuarioLoggeado.getUsername();
            }
            
        }

        String accion = request.getParameter("request");

        try {

            ConexionBaseDeDatos conexion = new ConexionBaseDeDatos();
            CRUDstudent crud = new CRUDstudent(conexion);

            switch (accion) {
                
                case "insert" -> {
                    
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    String name = request.getParameter("name");
                    String email = request.getParameter("email");
                    
                    student nuevoEstudiante = new student(username, password, name, email);
                    
                    crud.agregarStudent(nuevoEstudiante);
                    response.sendRedirect("router.jsp?msg=El estudiante \"" + username + "\" fue registrado&redir_to=login.jsp");
                    
                }
                
//                case "update" -> {
//                    
//                    // You can't update your user
//                    
//                }
                
                case "remove" -> {

                    if (userId == null) {
                        response.sendRedirect("router.jsp?msg=Debes iniciar sesion para realizar esta continuar&redir_to=login.jsp");
                        return;
                    }
                    
                    session = request.getSession();
                    
                    Object loginAttribute = session.getAttribute("login");
                    
                    crud.eliminarStudent(((student) loginAttribute).getUsername());
                    
                    session.setAttribute("login", null);
                    session.invalidate();

                    response.sendRedirect("router.jsp?msg=El estudiante fue eliminado y la sesion fue cerrada&redir_to=login.jsp");

                }
                
//                case "list" -> {
//                    
//                    // You can't list all users
//                    
//                }
                
                case "login" -> {
                    
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    
                    student encontrado = CRUDstudent.iniciarSesion(username, password);
                    
                    session = request.getSession();
                    session.setAttribute("login", encontrado);
                    
                    response.sendRedirect("dashboard.jsp");

                }
                
                case "exit" -> {

                    if (userId == null) {
                        response.sendRedirect("router.jsp?msg=Debes iniciar sesion para realizar esta continuar&redir_to=login.jsp");
                        return;
                    }
                    
                    session = request.getSession();
                    session.setAttribute("login", null);
                    session.invalidate();
                    
                    response.sendRedirect("router.jsp?msg=Se cerro la sesion correctamente&redir_to=login.jsp");
                    
                }
                
                default -> {
                    response.sendRedirect("router.jsp?msg=LRequest solicitada invalida. Regresando al Login&redir_to=login.jsp");
                }
                
            }

        } catch (Exception error) {

            response.sendRedirect("router.jsp?msg=Ocurrio un error al procesar el estudainte: " + error.getMessage() + "&redir_to=login.jsp");

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
