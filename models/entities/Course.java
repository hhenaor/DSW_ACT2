/*
	* mpNotes Source Code, created by Hppsrc.
	* Entity file
	* Build 2504281601

	* mpNotes is a free open source grade-taking app.
	* Source code on: https://github.com/hhenaor/DSW_Act2
	* This project is licensed under the terms of the GNU General Public License version 3.0 (GPLv3).
*/

public class Course {

	private final Integer courseID;
	private String userID;
	private String name;
	private String fullName;
	private String description;
	private String knowledgeArea;
	private String career;
	private Integer credits;
	private String thematicContent;
	private String semester;
	private String professor;

	// Constructor
	public Course(
		Integer courseID,
		String userID,
		String name,
		String fullName,
		String description,
		String knowledgeArea,
		String career,
		Integer credits,
		String thematicContent,
		String semester,
		String professor) {

		this.courseID = courseID;
		this.userID = userID;
		this.name = name;
		this.fullName = fullName;
		this.description = description;
		this.knowledgeArea = knowledgeArea;
		this.career = career;
		this.credits = credits;
		this.thematicContent = thematicContent;
		this.semester = semester;
		this.professor = professor;

	}

	// Getters
	public Integer getCourseID() { return courseID; }
	public String getUserID() { return userID; }
	public String getName() { return name; }
	public String getFullName() { return fullName; }
	public String getDescription() { return description; }
	public String getKnowledgeArea() { return knowledgeArea; }
	public String getCareer() { return career; }
	public Integer getCredits() { return credits; }
	public String getThematicContent() { return thematicContent; }
	public String getSemester() { return semester; }
	public String getProfessor() { return professor; }

	// Setters
	// ! Disabled courseID setter to prevent overwriting the primary key
	// public void setCourseID(Integer courseID) { this.courseID = courseID; }
	public void setUserID(String userID) { this.userID = userID; }
	public void setName(String name) { this.name = name; }
	public void setFullName(String fullName) { this.fullName = fullName; }
	public void setDescription(String description) { this.description = description; }
	public void setKnowledgeArea(String knowledgeArea) { this.knowledgeArea = knowledgeArea; }
	public void setCareer(String career) { this.career = career; }
	public void setCredits(Integer credits) { this.credits = credits; }
	public void setThematicContent(String thematicContent) { this.thematicContent = thematicContent; }
	public void setSemester(String semester) { this.semester = semester; }
	public void setProfessor(String professor) { this.professor = professor; }

}