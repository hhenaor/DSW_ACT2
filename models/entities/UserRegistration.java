/*
	* mpNotes Source Code, created by Hppsrc.
	* Entity file
	* Build 2504281601

	* mpNotes is a free open source grade-taking app.
	* Source code on: https://github.com/hhenaor/DSW_Act2
	* This project is licensed under the terms of the GNU General Public License version 3.0 (GPLv3).
*/

public class UserRegistration {

	private final String userID;
	private final Integer courseID;

	// Constructor
	public UserRegistration(String userID, Integer courseID) {

		this.userID = userID;
		this.courseID = courseID;

	}

	// Getters
	public String getUserID() { return userID; }
	public Integer getCourseID() { return courseID; }

	// Setters
	// ! Disabled userID setter to prevent overwriting the primary key
	// public void setUserID(Integer userID) { this.userID = userID; }
	// ! Disabled courseID setter to prevent overwriting the primary key
	// public void setCourseID(Integer courseID) { this.courseID = courseID; }

}