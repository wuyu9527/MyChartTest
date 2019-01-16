package com.example.gupo_android.test.Bean;

/**
 * @author : whx
 * @date : 2019/1/16 17:51
 */
public class MenuBean {

    private int id;
    private String text;
    private int color;
    private int image;

    public MenuBean(int id, String text, int color, int image) {
        this.id = id;
        this.text = text;
        this.color = color;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
