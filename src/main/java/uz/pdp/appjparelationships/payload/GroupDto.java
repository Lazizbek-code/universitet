package uz.pdp.appjparelationships.payload;


public class GroupDto {
    private String name;
    private Integer facultyId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Integer facultyId) {
        this.facultyId = facultyId;
    }
}
