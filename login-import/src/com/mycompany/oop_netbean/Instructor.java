package com.mycompany.oop_netbean;

import java.sql.Date;
import java.sql.ResultSet;

public class Instructor {
        private int ID;
	private String Username, Name,UserType, Password, Email ;
	private int Gender;
	private Date Birthday;
        private String Phone;
	
        public Instructor(){
            
        }

    public Instructor(int ID, String Username, String Name, String UserType, String Password, String Email, int Gender, Date Birthday, String Phone) {
        this.ID = ID;
        this.Username = Username;
        this.Name = Name;
        this.UserType = UserType;
        this.Password = Password;
        this.Email = Email;
        this.Gender = Gender;
        this.Birthday = Birthday;
        this.Phone = Phone;
    }
        

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String UserType) {
        this.UserType = UserType;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int Gender) {
        this.Gender = Gender;
    }

    public Date getBirthday() {
        return Birthday;
    }

    public void setBirthday(Date Birthday) {
        this.Birthday = Birthday;
    }

    void parse(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
        
    
}