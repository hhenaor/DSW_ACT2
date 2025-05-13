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
import models.student;

@WebServlet (name="servlet_course", urlPatterns = {"/course"})
public class servlet_course extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);
        student usuarioLoggeado = null;
        String userId = null;

        if (session != null) {
            Object loginAttribute = session.getAttribute("login");
            if (loginAttribute instanceof student) {
                usuarioLoggeado = (student) loginAttribute;
                userId = usuarioLoggeado.getUsername();
            }
        }

        String accion = request.getParameter("request");

        if (("remove".equals(accion) || "update".equals(accion) || "insert".equals(accion) || "search".equals(accion) || "list".equals(accion)) && userId == null) {
             response.sendRedirect("router.jsp?msg=Debes iniciar sesion para realizar esta operacion&redir_to=login.jsp");
             return;
        }

        try {

            if (accion.equals("insert")) {

                ConexionBaseDeDatos conexionInsert = new ConexionBaseDeDatos();
                CRUDcourse crudCourseInsert = new CRUDcourse(conexionInsert);

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

                crudCourseInsert.agregarCourse(nuevoCourse);

                response.sendRedirect("router.jsp?msg=Curso '" + name + "' Agregado al Sistema&redir_to=dashboard.jsp");

            } else

            if (accion.equals("update")) {

                ConexionBaseDeDatos conexionUpdate = new ConexionBaseDeDatos();
                CRUDcourse crudCourseUpdate = new CRUDcourse(conexionUpdate);

                String courseIdParam = request.getParameter("course_id");

                if (courseIdParam == null || courseIdParam.isEmpty()) {
                     response.sendRedirect("router.jsp?msg=Datos incompletos para la actualizacion&redir_to=dashboard.jsp");
                     return;
                }

                int courseId = Integer.parseInt(courseIdParam);

                ConexionBaseDeDatos conexionConsulta = new ConexionBaseDeDatos();
                CRUDcourse crudConsulta = new CRUDcourse(conexionConsulta);

                course criteriaForUpdateCheck = new course(courseId, userId, null, null, null, null, null, 0, null, null, null);
                course[] cursosEncontrados = CRUDcourse.courseFinder(criteriaForUpdateCheck, userId);

                if (cursosEncontrados == null || cursosEncontrados.length == 0) {
                     response.sendRedirect("router.jsp?msg=Curso a modificar no encontrado o no te pertenece&redir_to=dashboard.jsp");
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

                crudCourseUpdate.modificarCourse(courseAModificar);

                response.sendRedirect("router.jsp?msg=Curso con ID " + courseId + " Modificado en el Sistema&redir_to=dashboard.jsp");

            } else

            if (accion.equals("remove")) {

                ConexionBaseDeDatos conexionRemove = new ConexionBaseDeDatos();
                CRUDcourse crudCourseRemove = new CRUDcourse(conexionRemove);

                String courseIdParam = request.getParameter("course_id");

                 if (courseIdParam == null || courseIdParam.isEmpty()) {
                     response.sendRedirect("router.jsp?msg=ID del curso a eliminar no proporcionado&redir_to=dashboard.jsp");
                     return;
                 }

                int courseId = Integer.parseInt(courseIdParam);

                ConexionBaseDeDatos conexionConsulta = new ConexionBaseDeDatos();
                CRUDcourse crudConsulta = new CRUDcourse(conexionConsulta);
                course criteriaForRemoveCheck = new course(courseId, userId, null, null, null, null, null, 0, null, null, null);
                course[] cursosEncontrados = CRUDcourse.courseFinder(criteriaForRemoveCheck, userId);

                if (cursosEncontrados == null || cursosEncontrados.length == 0) {
                     response.sendRedirect("router.jsp?msg=Curso a eliminar no encontrado o no te pertenece&redir_to=dashboard.jsp");
                     return;
                }

                crudCourseRemove.eliminarCourse(courseId);

                response.sendRedirect("router.jsp?msg=Curso con ID " + courseId + " Eliminado del Sistema&redir_to=dashboard.jsp");

            } else

            if (accion.equals("search")) {

                String courseIdParam = request.getParameter("course_id");
                String name = request.getParameter("name");
                String fullName = request.getParameter("full_name");
                String description = request.getParameter("description");
                String knowledgeArea = request.getParameter("knowledge_area");
                String career = request.getParameter("career");
                String creditsParam = request.getParameter("credits");
                String thematicContent = request.getParameter("thematic_content");
                String semester = request.getParameter("semester");
                String professor = request.getParameter("professor");

                int courseId = 0;
                if (courseIdParam != null && !courseIdParam.isEmpty()) {
                    try {
                        courseId = Integer.parseInt(courseIdParam);
                    } catch (NumberFormatException e) {
                        boolean onlyIdProvided = (name == null || name.isEmpty()) &&
                                                 (fullName == null || fullName.isEmpty()) &&
                                                 (description == null || description.isEmpty()) &&
                                                 (knowledgeArea == null || knowledgeArea.isEmpty()) &&
                                                 (career == null || career.isEmpty()) &&
                                                 (creditsParam == null || creditsParam.isEmpty()) &&
                                                 (thematicContent == null || thematicContent.isEmpty()) &&
                                                 (semester == null || semester.isEmpty()) &&
                                                 (professor == null || professor.isEmpty());

                        if (onlyIdProvided) {
                             response.sendRedirect("router.jsp?msg=Error: El ID del curso debe ser un numero valido.&redir_to=dash/search.jsp");
                             return;
                        }
                         System.err.println("Error al convertir course_id a numero: " + courseIdParam + ". Continuando con otros criterios si existen.");
                         courseId = 0;
                    }
                }

                 int credits = 0;
                 if (creditsParam != null && !creditsParam.isEmpty()) {
                    try {
                        credits = Integer.parseInt(creditsParam);
                    } catch (NumberFormatException e) {
                         boolean onlyCreditsProvided = (courseIdParam == null || courseIdParam.isEmpty()) &&
                                                       (name == null || name.isEmpty()) &&
                                                       (fullName == null || fullName.isEmpty()) &&
                                                       (description == null || description.isEmpty()) &&
                                                       (knowledgeArea == null || knowledgeArea.isEmpty()) &&
                                                       (career == null || career.isEmpty()) &&
                                                       (thematicContent == null || thematicContent.isEmpty()) &&
                                                       (semester == null || semester.isEmpty()) &&
                                                       (professor == null || professor.isEmpty());

                         if (onlyCreditsProvided) {
                              response.sendRedirect("router.jsp?msg=Error: El valor de creditos debe ser un numero valido.&redir_to=dash/search.jsp");
                              return;
                         }
                         System.err.println("Error al convertir credits a numero: " + creditsParam + ". Continuando con otros criterios si existen.");
                         credits = 0;
                    }
                 }

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

                boolean anyCriteriaProvided = searchCriteria.getCourse_id() > 0 ||
                                              (searchCriteria.getName() != null && !searchCriteria.getName().isEmpty()) ||
                                              (searchCriteria.getFull_name() != null && !searchCriteria.getFull_name().isEmpty()) ||
                                              (searchCriteria.getDescription() != null && !searchCriteria.getDescription().isEmpty()) ||
                                              (searchCriteria.getKnowledge_area() != null && !searchCriteria.getKnowledge_area().isEmpty()) ||
                                              (searchCriteria.getCareer() != null && !searchCriteria.getCareer().isEmpty()) ||
                                              searchCriteria.getCredits() > 0 ||
                                              (searchCriteria.getThematic_content() != null && !searchCriteria.getThematic_content().isEmpty()) ||
                                              (searchCriteria.getSemester() != null && !searchCriteria.getSemester().isEmpty()) ||
                                              (searchCriteria.getProfessor() != null && !searchCriteria.getProfessor().isEmpty());

                if (!anyCriteriaProvided) {
                    response.sendRedirect("router.jsp?msg=Por favor, ingresa al menos un criterio de busqueda.&redir_to=dash/search.jsp");
                    return;
                }

                course[] resultados = CRUDcourse.courseFinder(searchCriteria, userId);

                if (resultados == null || resultados.length == 0) {
                    response.sendRedirect("router.jsp?msg=No se encontraron cursos que coincidan con los criterios proporcionados para tu usuario.&redir_to=dash/search.jsp");
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

                    String msg = "Se encontro una coincidencia usando el atributo " + atributoEncontrado;
                    response.sendRedirect(request.getContextPath() + "/course?request=single&msg=" + msg + "&course_id=" + encontrado.getCourse_id());

                } else {
                    String msg = "Se encontraron " + resultados.length + " coincidencias. Por favor, refina tu busqueda";
                    response.sendRedirect("router.jsp?msg=" + msg + "&redir_to=dash/search.jsp");
                }

            } else
                
            if (accion.equals("single")) {
                 String courseIdParam = request.getParameter("course_id");
                 String msgParam = request.getParameter("msg");

                 int courseId = 0;
                 if (courseIdParam != null && !courseIdParam.isEmpty()) {
                     try {
                         courseId = Integer.parseInt(courseIdParam);
                         if (courseId <= 0) {
                              response.sendRedirect("router.jsp?msg=Error: El ID del curso debe ser un numero positivo valido.&redir_to=dash/search.jsp");
                              return;
                         }
                     } catch (NumberFormatException e) {
                         response.sendRedirect("router.jsp?msg=Error: El ID del curso no es un numero valido.&redir_to=dash/search.jsp");
                         return;
                     }
                 } else {
                      response.sendRedirect("router.jsp?msg=ID del curso no proporcionado para ver detalles.&redir_to=dash/search.jsp");
                      return;
                 }

                 course encontrado = CRUDcourse.consultarCourse(courseId);

                 if (encontrado != null && userId != null && userId.equals(encontrado.getUser_id())) {
                     request.setAttribute("singleCourse", encontrado);
                     if (msgParam != null) {
                         request.setAttribute("msg", msgParam);
                     } else {
                         request.setAttribute("msg", "Detalles del Curso:");
                     }
                     request.getRequestDispatcher("dash/lister.jsp").forward(request, response);
                 } else {
                     response.sendRedirect("router.jsp?msg=Curso no encontrado o no te pertenece.&redir_to=dash/search.jsp");
                 }

            }

            else {
                response.sendRedirect("router.jsp?msg=La Accion Solicitada para Cursos no es Correcta.&redir_to=dashboard.jsp");
            }

        } catch (NumberFormatException e) {
             try {
                 response.sendRedirect("router.jsp?msg=Error: Un valor numerico (ID o creditos) no es valido.&redir_to=search.jsp");
             } catch (IOException ioe) {
                  System.err.println("ERROR: Fallo sendRedirect en NumberFormatException catch");
                  ioe.printStackTrace();
             }
             e.printStackTrace();
        }
        catch (Exception error) {
            try {
                 response.sendRedirect("router.jsp?msg=Ocurrio un error procesando el curso: " + error.getMessage() + "&redir_to=dashboard.jsp");
            } catch (IOException ioe) {
                 System.err.println("ERROR FATAL: No se pudo redirigir a router.jsp en el catch del servlet de curso");
                 ioe.printStackTrace();
            }
            error.printStackTrace();
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