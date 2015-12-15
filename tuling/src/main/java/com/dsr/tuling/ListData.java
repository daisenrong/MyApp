package com.dsr.tuling;

/**
 * Created by Administrator on 2015/12/13.
 */
public class ListData {
    private String content;
    public static final int SEND=1;
    public static final int RECEIVER=2;
    private int flag;

    public ListData(String content,int flag) {
        setContent(content);
        setFlag(flag);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
