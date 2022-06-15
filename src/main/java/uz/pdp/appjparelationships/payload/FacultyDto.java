package uz.pdp.appjparelationships.payload;


public class FacultyDto {
    private String name;
    private Integer universityId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Integer universityId) {
        this.universityId = universityId;
    }
}
