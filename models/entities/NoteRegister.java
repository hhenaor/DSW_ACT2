/*
	* mpNotes Source Code, created by Hppsrc.
	* Entity file
	* Build 2504281601

	* mpNotes is a free open source grade-taking app.
	* Source code on: https://github.com/hhenaor/DSW_Act2
	* This project is licensed under the terms of the GNU General Public License version 3.0 (GPLv3).
*/

import java.math.BigDecimal;

public class NoteRegister {

	private final Integer noteID;
	private Integer noteRuleID;
	private BigDecimal noteValue;
	private String comment;

	public NoteRegister(
		Integer noteID,
		Integer noteRuleID,
		BigDecimal noteValue,
		String comment) {

		this.noteID = noteID;
		this.noteRuleID = noteRuleID;
		this.noteValue = noteValue;
		this.comment = comment;

	}

	// Getters
	public Integer getNoteID() { return noteID; }
	public Integer getNoteRuleID() { return noteRuleID; }
	public BigDecimal getNoteValue() { return noteValue; }
	public String getComment() { return comment; }

	// Setters
	// ! Disabled note_id setter to prevent overwriting the primary key
	// public void setNoteID(Integer noteID) { this.noteID = noteID; }
	public void setNoteRuleID(Integer noteRuleID) { this.noteRuleID = noteRuleID; }
	public void setNoteValue(BigDecimal noteValue) { this.noteValue = noteValue; }
	public void setComment(String comment) { this.comment = comment; }

}