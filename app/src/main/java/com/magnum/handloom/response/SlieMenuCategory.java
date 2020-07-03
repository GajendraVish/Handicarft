
package com.magnum.handloom.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SlieMenuCategory {

    @SerializedName("error")
    @Expose
    private Integer error;

    @SerializedName("msg")
    @Expose
    private String msg;

    @SerializedName("category")
    @Expose
    private List<ArticalCategory> articalCategory = null;

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ArticalCategory> getArticalCategory() {
        return articalCategory;
    }

    public void setArticalCategory(List<ArticalCategory> articalCategory) {
        this.articalCategory = articalCategory;
    }

}
