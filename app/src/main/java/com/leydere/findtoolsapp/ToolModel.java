package com.leydere.findtoolsapp;

public class ToolModel {

    private int id;
    private String toolName;
    private String location;
    private String subLocation;
    private String imagePath;
    private boolean isCheckedOut;

    public ToolModel(int id, String toolName, String location, String subLocation, String imagePath, boolean isCheckedOut) {
        this.id = id;
        this.toolName = toolName;
        this.location = location;
        this.subLocation = subLocation;
        this.imagePath = imagePath;
        this.isCheckedOut = isCheckedOut;
    }

    // unlikely will need for this app, but part of youtube tutorial
    @Override
    public String toString() {
        return "ToolModel{" +
                "id=" + id +
                ", toolName='" + toolName + '\'' +
                ", location='" + location + '\'' +
                ", subLocation='" + subLocation + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", isCheckedOut=" + isCheckedOut +
                '}';
    }

    //Getters & Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSubLocation() {
        return subLocation;
    }

    public void setSubLocation(String subLocation) {
        this.subLocation = subLocation;
    }

    public boolean getIsCheckedOut() {
        return isCheckedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        isCheckedOut = checkedOut;
    }

    public String getImagePath() { return imagePath; }

    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
}
