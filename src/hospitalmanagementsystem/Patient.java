package hospitalmanagementsystem;

public class Patient {
    private int id;
    private String name;
    private String surName;
    private String age;
    private  String contactInfo;
    private String gender;
    private String category;
    public Patient(int id, String name,String surName, String age, String contactInfo, String gender, String category){
        this.id=id;
        this.name=name;
        this.surName=surName;
        this.age=age;
        this.contactInfo=contactInfo;
        this.gender=gender;
        this.category=category;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public String getAge() {
        return age;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public String getGender() {
        return gender;
    }

    public String getCategory() {
        return category;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
