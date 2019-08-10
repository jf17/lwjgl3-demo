package ru.jf17.app;

public class DataClass {

    private    float right_view_rotx , left_view_rotx , up_view_roty ,down_view_roty ,view_rotz, PLUS ;
    private   float transparency; // прозрачность


    public float getTransparency() {
        return transparency;
    }

    public void setTransparency() {
        if(transparency==0.5f){transparency=1f;}else{transparency=0.5f;}
    }


    public DataClass() {
        right_view_rotx = 0.05f;
        left_view_rotx = -0.05f;
        up_view_roty = 0.05f;
        down_view_roty = -0.05f;
        view_rotz = 0.05f;
        PLUS = 0.06f;
        transparency = 0.5f;

    }
    public void setDefault(){
        right_view_rotx = 0.05f;
        left_view_rotx = -0.05f;
        up_view_roty = 0.05f;
        down_view_roty = -0.05f;
        view_rotz = 0.05f;
        transparency = 0.5f;
    }

    public float getPLUS() {
        return PLUS;
    }

    public float getRight_view_rotx() {
        return right_view_rotx;
    }

    public void setRight_view_rotx(float right_view_rotx) {
        this.right_view_rotx = right_view_rotx;
    }

    public float getLeft_view_rotx() {
        return left_view_rotx;
    }

    public void setLeft_view_rotx(float left_view_rotx) {
        this.left_view_rotx = left_view_rotx;
    }

    public float getUp_view_roty() {
        return up_view_roty;
    }

    public void setUp_view_roty(float up_view_roty) {
        this.up_view_roty = up_view_roty;
    }

    public float getDown_view_roty() {
        return down_view_roty;
    }

    public void setDown_view_roty(float down_view_roty) {
        this.down_view_roty = down_view_roty;
    }

    public float getView_rotz() {
        return view_rotz;
    }

    public void setView_rotz(float view_rotz) {
        this.view_rotz = view_rotz;
    }
}
