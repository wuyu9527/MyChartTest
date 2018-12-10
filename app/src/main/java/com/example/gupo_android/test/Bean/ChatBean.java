package com.example.gupo_android.test.Bean;

/**
 * 消息体
 *
 * @author : whx
 * @date : 2018/12/7 16:19
 */
public class ChatBean extends BaseBean {


    private int icon;
    private int type;
    private int msgType;

    private String text;
    private String msg;

    public ChatBean(int icon, int type, int msgType) {
        this.icon = icon;
        this.type = type;
        this.msgType = msgType;
    }

    public int getType() {
        return type;
    }

    @Override
    public void setType(int type) {
        this.type = type;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }


    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
