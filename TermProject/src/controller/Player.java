package controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class Player {

    private SimpleStringProperty name;
    private SimpleStringProperty country;
    private SimpleIntegerProperty age;
    private SimpleDoubleProperty height;
    private SimpleStringProperty club;
    private SimpleStringProperty position;
    private SimpleIntegerProperty number;
    private SimpleDoubleProperty weeklySalary;
    private Button button;
    private final String IDLE_BUTTON_STYLE = "-fx-background-color: #6bd0ff;";
    private final String HOVERED_BUTTON_STYLE = "-fx-background-color: #3fa0ef; -fx-text-fill: #ffffff;";

    public Player(){

    }

    public Player(String name, String country, int age, double height, String club, String position, int number, double weeklySalary) {
        this.name = new SimpleStringProperty(name);
        this.country = new SimpleStringProperty(country);
        this.age = new SimpleIntegerProperty(age);
        this.height = new SimpleDoubleProperty(height);
        this.club = new SimpleStringProperty(club);
        this.position = new SimpleStringProperty(position);
        this.number = new SimpleIntegerProperty(number);
        this.weeklySalary = new SimpleDoubleProperty(weeklySalary);
        this.button= new Button("Click");
        button.setStyle(IDLE_BUTTON_STYLE);
        button.setOnMouseEntered(e -> button.setStyle(HOVERED_BUTTON_STYLE));
        button.setOnMouseExited(e -> button.setStyle(IDLE_BUTTON_STYLE));

        button.setOnAction( e -> {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Player Info");
                    a.setHeaderText(getName());
                    a.setContentText(getCountry() + "\n" + getAge() + "\n" + getHeight() + "\n" + getClub() + "\n" + getPosition() + "\n" + getNumber() + "\n" + getWeeklySalary());
                    a.showAndWait();
                }
        );
    }

    public String getName() {
        return name.get();
    }

    /*public void setName(String name) {
        this.name.set(name);
    }*/

    public String getCountry() {
        return country.get();
    }

    /*public void setCountry(String country) {
        this.country.set(country);
    }*/

    public Integer getAge() {
        return age.get();
    }

    /*public void setAge(int age) {
        this.age.set(age);
    }*/

    public Double getHeight() {
        return height.get();
    }

    /*public void setHeight(double height) {
        this.height.set(height);
    }*/

    public String getClub() {
        return club.get();
    }

    /*public void setClub(String club) {
        this.club.set(club);
    }*/

    public String getPosition() {
        return position.get();
    }

    /*public void setPosition(String position) {
        this.position.set(position);
    }*/

    public Integer getNumber() {
        return number.get();
    }

    /*public void setNumber(int number) {
        this.number.set(number);
    }*/

    public Double getWeeklySalary() {
        return weeklySalary.get();
    }

    /*public void setWeeklySalary(double weeklySalary) {
        this.weeklySalary.set(weeklySalary);
    }*/

    public String toString() {
        return getName() + ", " + getCountry() + ", " + getAge()+", "+getHeight()+", "+getClub()+", "+getPosition()+", "+getNumber()+", "+getWeeklySalary();
    }

    public Button getButton() {
        return button;
    }
}
