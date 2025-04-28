/*
	* mpNotes Source Code, created by Hppsrc.
	* Interface file
	* Build 2504281601

	* mpNotes is a free open source grade-taking app.
	* Source code on: https://github.com/hhenaor/DSW_Act2
	* This project is licensed under the terms of the GNU General Public License version 3.0 (GPLv3).
*/

import java.util.List;

public interface ICrud <T> {

    List <T> queryByID(Integer ID);

    void selectAll();

    void insert(T object);

    void deleteByID(Integer ID);

    void update(T object);

    Integer total();

}
