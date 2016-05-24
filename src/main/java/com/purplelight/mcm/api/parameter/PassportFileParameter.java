package com.purplelight.mcm.api.parameter;

public class PassportFileParameter extends Parameter {
	private int systemId;

    private int itemId;

    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
