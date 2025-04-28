/*
	* mpNotes Source Code, created by Hppsrc.
	* Interface file
	* Build 2504281601

	* mpNotes is a free open source grade-taking app.
	* Source code on: https://github.com/hhenaor/DSW_Act2
	* This project is licensed under the terms of the GNU General Public License version 3.0 (GPLv3).
*/

public interface IConnection {

    void disconnect();

    void query(String sqlQuery);

    void update(String sqlQuery, String type);

}
