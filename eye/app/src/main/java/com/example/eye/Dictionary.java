package com.example.eye;

public class Dictionary {

    private String LeftEye;
    private String RightEye;
    private String Lens;
    private String Locate;
    boolean selected;

    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getLeftEye() {
        return LeftEye;
    }

    public void setRightEye(String rightEye) {
        LeftEye = rightEye;
    }
    public String getRightEye() {
        return RightEye;
    }

    public void setLeftEye(String leftEye) {
        LeftEye = leftEye;
    }

    public String getLens() {
        return Lens;
    }

    public void setLens(String lens) {
        Lens = lens;
    }

    public Dictionary(String LeftEye, String RightEye, String Lens) {
        this.LeftEye = LeftEye;
        this.RightEye = RightEye;
        this.Lens = Lens;
    }
}