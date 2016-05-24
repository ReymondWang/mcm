package com.purplelight.mcm.api.result;

import java.util.ArrayList;
import java.util.List;

import com.purplelight.mcm.api.bean.PassportFile;

public class PassportFileResult extends Result {
	private List<PassportFile> fileList = new ArrayList<>();

    public List<PassportFile> getFileList() {
        return fileList;
    }

    public void setFileList(List<PassportFile> fileList) {
        this.fileList = fileList;
    }
}
