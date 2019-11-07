package app.domain.models.binding;

import app.domain.entities.Class;

public class HeroCreateBindingModel {
    private String name;
    private String aclass;
    private Integer level;

    public HeroCreateBindingModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAclass() {
        return aclass;
    }

    public void setAclass(String aclass) {
        this.aclass = aclass;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
