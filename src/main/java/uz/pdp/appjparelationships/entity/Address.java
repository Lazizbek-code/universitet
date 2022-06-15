package uz.pdp.appjparelationships.entity;

import javax.persistence.*;


@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//1

    @Column(nullable = false)
    private String city;//Toshkent

    @Column(nullable = false)
    private String district;//Mirobod

    @Column(nullable = false)
    private String street;//U.Nosir ko'chasi

    public Address() {
    }

    public Address(Integer id, String city, String district, String street) {
        this.id = id;
        this.city = city;
        this.district = district;
        this.street = street;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
