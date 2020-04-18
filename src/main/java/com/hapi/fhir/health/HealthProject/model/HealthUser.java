package com.hapi.fhir.health.HealthProject.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "health_user")
@Data
public class HealthUser implements Serializable {
    public Integer getId() {
        return id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmp_password() {
        return emp_password;
    }

    @Override
    public String toString() {
        return "HealthUser{" +
                "id=" + id +
                ", emp_name='" + emp_name + '\'' +
                ", emp_password='" + emp_password + '\'' +
                '}';
    }

    public void setEmp_password(String emp_password) {
        this.emp_password = emp_password;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;
    @Column(name="emp_name")
    private String emp_name;
    @Column(name="emp_password")
    private String emp_password;

}
