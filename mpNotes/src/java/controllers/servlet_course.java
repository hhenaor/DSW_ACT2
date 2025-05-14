package controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;

import models.course;
import models.student;
import models.CRUDcourse;
import models.ConexionBaseDeDatos;

@WebServlet(name = "servlet_course", urlPatterns = {"/course"})
public class servlet_course extends HttpServlet {

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

        if (userId == null) {
            response.sendRedirect("router.jsp?msg=Debes iniciar sesion para realizar esta continuar&redir_to=login.jsp");
            return;
        }

        try {

            ConexionBaseDeDatos conexion = new ConexionBaseDeDatos();
            CRUDcourse crud = new CRUDcourse(conexion);

            switch (accion) {

                case "insert" -> {

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
                    crud.agregarCourse(nuevoCourse);

                    response.sendRedirect("router.jsp?msg=Curso \"" + fullName + "\" agregado al Sistema&redir_to=dashboard.jsp");

                }

                case "update" -> {

                    String courseIdParam = request.getParameter("course_id");
                    if (courseIdParam == null || courseIdParam.isEmpty()) {
                        response.sendRedirect("router.jsp?msg=Falta la ID del curso para actualizar&redir_to=dashboard.jsp");
                        return;
                    }

                    int courseId = Integer.parseInt(courseIdParam);
                    course criteriaForUpdateCheck = new course(courseId, userId, null, null, null, null, null, 0, null, null, null);
                    course[] cursosEncontrados = CRUDcourse.courseFinder(criteriaForUpdateCheck, userId);

                    if (cursosEncontrados == null || cursosEncontrados.length == 0) {
                        response.sendRedirect("router.jsp?msg=No se encontro un curso para modificar o no te pertenece&redir_to=dashboard.jsp");
                        return;
                    }

                    course cursoExistente = cursosEncontrados[0];
                    String name = request.getParameter("name");
                    String fullName = request.getParameter("full_name");
                    String description = request.getParameter("description");
                    String knowledgeArea = request.getParameter("knowledge_area");
                    String career = request.getParameter("career");
                    int credits = Integer.parseInt(request.getParameter("credits"));
                    String thematicContent = request.getParameter("thematic_content");
                    String semester = request.getParameter("semester");
                    String professor = request.getParameter("professor");

                    course courseAModificar = new course(courseId, cursoExistente.getUser_id(), name, fullName, description, knowledgeArea, career, credits, thematicContent, semester, professor);
                    crud.modificarCourse(courseAModificar);
                    
                    response.sendRedirect("router.jsp?msg=El curso \"" + fullName + "\" fue actualizado&redir_to=dashboard.jsp");

                }

                case "remove" -> {

                    String courseIdParam = request.getParameter("course_id");
                    if (courseIdParam == null || courseIdParam.isEmpty()) {
                        response.sendRedirect("router.jsp?msg=Falta la ID del curso para eliminar&redir_to=dashboard.jsp");
                        return;
                    }

                    int courseId = Integer.parseInt(courseIdParam);
                    course criteriaForRemoveCheck = new course(courseId, userId, null, null, null, null, null, 0, null, null, null);
                    course[] cursosEncontrados = CRUDcourse.courseFinder(criteriaForRemoveCheck, userId);

                    if (cursosEncontrados == null || cursosEncontrados.length == 0) {
                        response.sendRedirect("router.jsp?msg=No se encontro un curso para eliminar o no te pertenece&redir_to=dashboard.jsp");
                        return;
                    }

                    String fullName = cursosEncontrados[0].getFull_name();

                    crud.eliminarCourse(courseId);
                    
                    response.sendRedirect("router.jsp?msg=El curso \"" + fullName + "\" fue eliminado&redir_to=dashboard.jsp");

                }

                case "search" -> {
                    
                    String name = request.getParameter("name");
                    String fullName = request.getParameter("full_name");
                    String description = request.getParameter("description");
                    String knowledgeArea = request.getParameter("knowledge_area");
                    String career = request.getParameter("career");
                    String thematicContent = request.getParameter("thematic_content");
                    String semester = request.getParameter("semester");
                    String professor = request.getParameter("professor");

                    int courseId = 0;
                    int credits = 0;
                    
                    course searchCriteria = new course(
                            courseId,
                            null,
                            name,
                            fullName,
                            description,
                            knowledgeArea,
                            career,
                            credits,
                            thematicContent,
                            semester,
                            professor
                    );

                    boolean anyCriteriaProvided = 
                            searchCriteria.getCourse_id() >= 0
                            || (searchCriteria.getName() != null && !searchCriteria.getName().isEmpty())
                            || (searchCriteria.getFull_name() != null && !searchCriteria.getFull_name().isEmpty())
                            || (searchCriteria.getDescription() != null && !searchCriteria.getDescription().isEmpty())
                            || (searchCriteria.getKnowledge_area() != null && !searchCriteria.getKnowledge_area().isEmpty())
                            || (searchCriteria.getCareer() != null && !searchCriteria.getCareer().isEmpty())
                            || searchCriteria.getCredits() > 0
                            || (searchCriteria.getThematic_content() != null && !searchCriteria.getThematic_content().isEmpty())
                            || (searchCriteria.getSemester() != null && !searchCriteria.getSemester().isEmpty())
                            || (searchCriteria.getProfessor() != null && !searchCriteria.getProfessor().isEmpty());

                    if (!anyCriteriaProvided) {
                        response.sendRedirect("router.jsp?msg=Debes ingresar al menos un criterio de busqueda&redir_to=dash/search.jsp");
                        return;
                    }
                    
                    course[] resultados = CRUDcourse.courseFinder(searchCriteria, userId);
                    
                    if (resultados == null || resultados.length == 0) {
                        
                        response.sendRedirect("router.jsp?msg=No se encontraron cursos que coincidan con los criterios de busqueda o no te pertecene&redir_to=dash/search.jsp");
                        
                    } else if (resultados.length == 1) {
                        
                        course encontrado = resultados[0];
                        
                        String atributoEncontrado = "Criterio(s) proporcionado(s)";
                        
                        if (searchCriteria.getCourse_id() > 0 && encontrado.getCourse_id() == searchCriteria.getCourse_id()) {
                            atributoEncontrado = "ID";
                            
                        } else if (searchCriteria.getName() != null && !searchCriteria.getName().isEmpty() && encontrado.getName() != null && encontrado.getName().contains(searchCriteria.getName())) {
                            atributoEncontrado = "Nombre Corto";
                            
                        } else if (searchCriteria.getFull_name() != null && !searchCriteria.getFull_name().isEmpty() && encontrado.getFull_name() != null && encontrado.getFull_name().contains(searchCriteria.getFull_name())) {
                            atributoEncontrado = "Nombre Completo";
                            
                        } else if (searchCriteria.getDescription() != null && !searchCriteria.getDescription().isEmpty() && encontrado.getDescription() != null && encontrado.getDescription().contains(searchCriteria.getDescription())) {
                            atributoEncontrado = "Descripcion";
                            
                        } else if (searchCriteria.getKnowledge_area() != null && !searchCriteria.getKnowledge_area().isEmpty() && encontrado.getKnowledge_area() != null && encontrado.getKnowledge_area().contains(searchCriteria.getKnowledge_area())) {
                            atributoEncontrado = "Area de Conocimiento";
                            
                        } else if (searchCriteria.getCareer() != null && !searchCriteria.getCareer().isEmpty() && encontrado.getCareer() != null && encontrado.getCareer().contains(searchCriteria.getCareer())) {
                            atributoEncontrado = "Carrera";
                            
                        } else if (searchCriteria.getCredits() > 0 && encontrado.getCredits() == searchCriteria.getCredits()) {
                            atributoEncontrado = "Creditos";
                            
                        } else if (searchCriteria.getThematic_content() != null && !searchCriteria.getThematic_content().isEmpty() && encontrado.getThematic_content() != null && encontrado.getThematic_content().contains(searchCriteria.getThematic_content())) {
                            atributoEncontrado = "Contenido Tematico";
                            
                        } else if (searchCriteria.getSemester() != null && !searchCriteria.getSemester().isEmpty() && encontrado.getSemester() != null && encontrado.getSemester().contains(searchCriteria.getSemester())) {
                            atributoEncontrado = "Semestre";
                            
                        } else if (searchCriteria.getProfessor() != null && !searchCriteria.getProfessor().isEmpty() && encontrado.getProfessor() != null && encontrado.getProfessor().contains(searchCriteria.getProfessor())) {
                            atributoEncontrado = "Profesor";
                        }
                        
                        response.sendRedirect(request.getContextPath() + "/course?request=single&msg=Se encontro una coincidencia usando el atributo \"" + atributoEncontrado + "\"&course_id=" + encontrado.getCourse_id());

                    } else {
                        
                        response.sendRedirect("router.jsp?msg=Se encontraron " + resultados.length + " coincidencias. Por favor, refina tu busqueda&redir_to=dash/search.jsp");
                        
                    }

                }

                case "single" -> {
                    
                    String courseIdParam = request.getParameter("course_id");
                    String msgParam = request.getParameter("msg");
                    
                    int courseId;
                    
                    if (courseIdParam != null && !courseIdParam.isEmpty()) {
                        
                        try {
                            
                            courseId = Integer.parseInt(courseIdParam);
                            
                            if (courseId <= 0) {
                                response.sendRedirect("router.jsp?msg=La ID del curso en tu solicitud no es valida&redir_to=dash/search.jsp");
                                return;
                            }
                            
                        } catch (NumberFormatException e) {
                            response.sendRedirect("router.jsp?msg=La ID del curso en tu solicitud no es valida&redir_to=dash/search.jsp");
                            return;
                        }
                        
                    } else {
                        response.sendRedirect("router.jsp?msg=Falta la ID del curso para mostar&redir_to=dash/search.jsp");
                        return;
                    }
                    
                    course encontrado = CRUDcourse.consultarCourse(courseId);
                    
                    if (encontrado != null && userId.equals(encontrado.getUser_id())) {
                        
                        request.setAttribute("singleCourse", encontrado);
                        
                        if (msgParam != null) {
                            request.setAttribute("msg", msgParam);
                        } else {
                            request.setAttribute("msg", "Detalles del curso:");
                        }
                        
                        request.getRequestDispatcher("dash/lister.jsp").forward(request, response);
                        
                    } else {
                        response.sendRedirect("router.jsp?msg=Curso no encontrado o no te pertenece&redir_to=dash/search.jsp");
                    }
                }
                
                default -> {
                    response.sendRedirect("router.jsp?msg=Request solicitada invalida. Regresando al Dashboard&redir_to=dashboard.jsp");
                }
                
            }

        } catch (NumberFormatException e) {
            
            try {
                response.sendRedirect("router.jsp?msg=Ocurrio un error al procesar el ID del curso: " + e.getMessage() + "&redir_to=search.jsp");
            } catch (IOException ioe) {
                System.err.println("ERROR CATASTROFICO EN ROUTER: " + ioe.getMessage());
            }
            
        } catch (Exception error) {
            
            try {
                response.sendRedirect("router.jsp?msg=Ocurrio un error al procesar el curso: " + error.getMessage() + "&redir_to=dashboard.jsp");
            } catch (IOException ioe) {
                System.err.println("ERROR CATASTROFICO EN ROUTER: " + ioe.getMessage());
            }
            
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
