
package com.medicare.dto;

public class PatientResponseDTO {

    private Long id;
    private String name;
    private String email;
    private Integer age;
    private String phone;

    public PatientResponseDTO() {
    }

    public PatientResponseDTO(
            Long id,
            String name,
            String email,
            Integer age,
            String phone) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}