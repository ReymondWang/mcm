package com.purplelight.mcm.api.bean;

import java.io.Serializable;

public class PassportFile implements Serializable {
	private static final long serialVersionUID = 1200769344335403922L;

	private String fileName;

    private String filePath;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}