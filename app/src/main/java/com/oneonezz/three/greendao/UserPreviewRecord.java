package com.oneonezz.three.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ${Justin} on 2019/8/5.
 */

@Entity
public class UserPreviewRecord {

    @Id(autoincrement = true)
    private Long id;

    private String uid;

    private String stream;
    
    private boolean tag;

    @Generated(hash = 1410440101)
    public UserPreviewRecord(Long id, String uid, String stream, boolean tag) {
        this.id = id;
        this.uid = uid;
        this.stream = stream;
        this.tag = tag;
    }

    @Generated(hash = 1407472908)
    public UserPreviewRecord() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getStream() {
        return this.stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public boolean getTag() {
        return this.tag;
    }

    public void setTag(boolean tag) {
        this.tag = tag;
    }


  


}
