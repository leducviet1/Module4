package com.example.myfirststringmvc.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
@Entity
@Table(name = "students")
public class Student {

    @Id
    @NotBlank(message = "Không được để trống mã sinh viên")
    @Size(min=3,max=10,message = "Từ 3 đến 10 kí tự")
    String code;   //Đây là primary key

    @NotBlank(message = "Không được để trống tên sinh viên")
    String name;

    @NotNull(message = "Bắt buộc phải có GPA")
    @DecimalMin(value = "0.0" , inclusive = true, message = "GPA >= 0")
    @DecimalMax(value = "10.0" , inclusive = true, message = "GPA <=10")
    Float gpa;

    String avatar; //lưu đường dẫn file
    public Student() {}
    public Student(String code, String name, Float gpa) {
        this.code = code;
        this.name = name;
        this.gpa = gpa;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getGpa() {
        return gpa;
    }

    public void setGpa(Float gpa) {
        this.gpa = gpa;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRank(){
        if(gpa == null)return "NA";
        if (gpa >= 8.0) return "Giỏi";
        if (gpa >= 6.5) return "Khá";
        if (gpa >= 5.0) return "Trung bình";
        return "Yếu";
    }
}
