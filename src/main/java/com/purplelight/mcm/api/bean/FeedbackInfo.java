package com.purplelight.mcm.api.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class FeedbackInfo implements Serializable {
	private static final long serialVersionUID = 4750374230068326857L;

	private int id;

    private String content;

    private String imagePath;

    private int inputUser;

    private Timestamp inputTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getInputUser() {
        return inputUser;
    }

    public void setInputUser(int inputUser) {
        this.inputUser = inputUser;
    }

    public Timestamp getInputTime() {
        return inputTime;
    }

    public void setInputTime(Timestamp inputTime) {
        this.inputTime = inputTime;
    }
}
