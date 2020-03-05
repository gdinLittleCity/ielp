package com.gdinxiao.manager.domain;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.gdinxiao.common.util.Constant;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;


public class Category {
    private String cid;
    @NotBlank(message = "{category.cname.empty}")
    @Size(min = 3, max = 50, message = "{category.cname.length}")
    @Pattern(regexp = "^([\\u4E00-\u9FA5]|\\w)*$", message = "{regexp}")
    private String cname;
    private String desc;
    private Category parent;
    private List<Category> child = new ArrayList<Category>();
    private int isResource = Constant.CATEGORY_ISRESOURCE_DEFAULT_NOT;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public List<Category> getChild() {
        return child;
    }

    public void setChild(List<Category> child) {
        this.child = child;
    }

    public int getIsResource() {
        return isResource;
    }

    public void setIsResource(int isResource) {
        this.isResource = isResource;
    }


}
