package com.sjdf.platform.common.vo;

/**
 * 2012-8-28 下午5:28:40
 * select 复选框
 *
 * @author frank
 */
public class SelectVo {

    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SelectVo(long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public SelectVo() {
        super();
    }

    @Override
    public String toString() {
        return "SelectVo [id=" + id + ", name=" + name + "]";
    }

}
