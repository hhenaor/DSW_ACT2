/*
	* mpNotes Source Code, created by Hppsrc.
	* Entity file
	* Build 2504281601

	* mpNotes is a free open source grade-taking app.
	* Source code on: https://github.com/hhenaor/DSW_Act2
	* This project is licensed under the terms of the GNU General Public License version 3.0 (GPLv3).
*/

public class UserStatus {

	private final String userID;
	private String status;

	public UserStatus(String userID, String status) {
		this.userID = userID;
		this.status = status;
	}

	// Getters
	public String getUserID() { return userID; }
	public String getStatus() { return status; }

	// Setters
	// ! Disabled user_id setter to prevent overwriting the primary key
	// public void setUserID(String userID) { this.userID= userID; }
	public void setStatus(String status) { this.status = status; }

}
