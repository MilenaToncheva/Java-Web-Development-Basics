package app.domain.models.view;

import app.domain.entities.Sector;

public class JobApplicationViewModel {
    private String id;
    private Sector sector;
    private String profession;

    public JobApplicationViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
