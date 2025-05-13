/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author horahenaripo
 */
public class course {

    private final int course_id; 
    private String user_id; 
    private String name; 
    private String full_name; 
    private String description; 
    private String knowledge_area; 
    private String career; 
    private int credits; 
    private String thematic_content;
    private String semester; 
    private String professor; 

    // Constructor
    public course(int course_id, String user_id, String name, String full_name, String description, String knowledge_area, String career, int credits, String thematic_content, String semester, String professor) {
        this.course_id = course_id;
        this.user_id = user_id;
        this.name = name;
        this.full_name = full_name;
        this.description = description;
        this.knowledge_area = knowledge_area;
        this.career = career;
        this.credits = credits;
        this.thematic_content = thematic_content;
        this.semester = semester;
        this.professor = professor;
    }

    // Getters
    public int getCourse_id() { return course_id; }
    public String getUser_id() { return user_id; }
    public String getName() { return name; }
    public String getFull_name() { return full_name; }
    public String getDescription() { return description; }
    public String getKnowledge_area() { return knowledge_area; }
    public String getCareer() { return career; }
    public int getCredits() { return credits; }
    public String getThematic_content() { return thematic_content; }
    public String getSemester() { return semester; }
    public String getProfessor() { return professor; }

    // Setters
    // ! Disabled course_id setter to prevent overwriting the primary key (AUTO_INCREMENT)
    // public void setCourse_id(int course_id) { this.course_id = course_id; }
    public void setUser_id(String user_id) { this.user_id = user_id; }
    public void setName(String name) { this.name = name; }
    public void setFull_name(String full_name) { this.full_name = full_name; }
    public void setDescription(String description) { this.description = description; }
    public void setKnowledge_area(String knowledge_area) { this.knowledge_area = knowledge_area; }
    public void setCareer(String career) { this.career = career; }
    public void setCredits(int credits) { this.credits = credits; }
    public void setThematic_content(String thematic_content) { this.thematic_content = thematic_content; }
    public void setSemester(String semester) { this.semester = semester; }
    public void setProfessor(String professor) { this.professor = professor; }

}
