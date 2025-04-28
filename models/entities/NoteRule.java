/*
	* mpNotes Source Code, created by Hppsrc.
	* Entity file
	* Build 2504281601

	* mpNotes is a free open source grade-taking app.
	* Source code on: https://github.com/hhenaor/DSW_Act2
	* This project is licensed under the terms of the GNU General Public License version 3.0 (GPLv3).
*/

import java.math.BigDecimal;

public class NoteRule {

    private final Integer noteRuleID;
    private Integer courseID;
    private Integer noteCount;
    private BigDecimal maxValue;

    // Constructor
    public NoteRule(
		Integer noteRuleID,
		Integer courseID,
		Integer noteCount,
		BigDecimal maxValue) {

        this.noteRuleID = noteRuleID;
        this.courseID = courseID;
        this.noteCount = noteCount;
        this.maxValue = maxValue;

    }

    // Getters
    public Integer getNoteRuleID() { return noteRuleID; }
    public Integer getCourseID() { return courseID; }
    public Integer getNoteCount() { return noteCount; }
    public BigDecimal getMaxValue() { return maxValue; }

    // Setters
    // ! Disabled noteRuleID setter to prevent overwriting the primary key
    // public void setNoteRuleID(Integer noteRuleID) { this.noteRuleID = noteRuleID; }
    public void setCourseID(Integer courseID) { this.courseID = courseID; }
    public void setNoteCount(Integer noteCount) { this.noteCount = noteCount; }
    public void setMaxValue(BigDecimal maxValue) { this.maxValue = maxValue; }

}
