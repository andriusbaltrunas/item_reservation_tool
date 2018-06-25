package com.item.reservation.tool.utils;

public enum MessageType {
    ERROR("errorMsg"), SUCCESS("successMsg"), WARNING("warningMsg"), ALERT("alertMsg"), INFO_ALERT("infoAlertMsg");

    private String type;

    MessageType(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
