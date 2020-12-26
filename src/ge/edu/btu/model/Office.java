package ge.edu.btu.model;

public class Office {
    private long id;
    private String structure;
    private String position;
    private String position_id;

    public Office(String structure, String position, String position_id) {
        this.structure = structure;
        this.position = position;
        this.position_id = position_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition_id() {
        return position_id;
    }

    public void setPosition_id(String position_id) {
        this.position_id = position_id;
    }
}
