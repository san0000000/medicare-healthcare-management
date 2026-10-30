package com.medicare.dto;

public class DoctorResponseDTO {

    private Long id;
    private String name;
    private String specialization;
    private Integer experience;

    public DoctorResponseDTO() {
    }

    public DoctorResponseDTO(
            Long id,
            String name,
            String specialization,
            Integer experience) {

        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.experience = experience;
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }
}