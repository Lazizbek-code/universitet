package uz.pdp.appjparelationships.payload;


public class UniversityDto {//MA'LUMOTLARNI TASHISH UCHUN XIZMAT QILADI
    private String name;
    private String city;
    private String district;
    private String street;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
