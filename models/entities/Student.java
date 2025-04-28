/*
	* mpNotes Source Code, created by Hppsrc.
	* Entity file
	* Build 2504281601

	* mpNotes is a free open source grade-taking app.
	* Source code on: https://github.com/hhenaor/DSW_Act2
	* This project is licensed under the terms of the GNU General Public License version 3.0 (GPLv3).
*/

public class Student {

    private final String username;
    private String password;
    private String name;
    private String email;

    // Constructor
    public Student(String username, String password, String name, String email) {

        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;

    }

    // Getters
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    // Setters
    // ! Disabled setUsername setter to prevent overwriting the primary key
    // public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }

}
