package com.wang.model;

import java.io.Serializable;

public class PicObject implements Serializable {

    private static final long serialVersionUID = 1024768L;

    public byte[] pic;  //图片数据
    public String picMs; //图片描述
    public int picLx;  //图片类型

    public PicObject(byte[] pic,String picMs, int picLx)
    {
        this.pic=pic;
        this.picMs=picMs;
        this.picLx=picLx;
    }

}
