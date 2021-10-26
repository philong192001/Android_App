package com.example.appcovid.network.dto;

public class Total {
    public CommonTotal internal ;
    public CommonTotal world ;

    public CommonTotal getInternal() {
        return internal;
    }

    public void setInternal(CommonTotal internal) {
        this.internal = internal;
    }

    public CommonTotal getWorld() {
        return world;
    }

    public void setWorld(CommonTotal world) {
        this.world = world;
    }
}
