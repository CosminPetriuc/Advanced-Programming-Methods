package gui;

import domain.Meal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import org.w3c.dom.events.MouseEvent;
import service.Service;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Controller {
    private Service service;

    public Controller(Service service) {
        this.service = service;
    }

    @FXML
    private TextField Ingredients1;

    @FXML
    private ListView<String> ListView;

    @FXML
    private TextField Name1;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private TextField functionField;

    @FXML
    private TextField time1;

    @FXML
    void searchText(ActionEvent event) {

    }




    protected void populateList() {
        try {
            ArrayList<Meal> genes = service.getAll();
            var sortedMeal = genes.stream().sorted(Comparator.comparing(report-> report.getName())).collect(Collectors.toList());
            for (Meal gene : sortedMeal) {
                ListView.getItems().add(gene.toString());
            }
        }catch (Exception exception) { exception.printStackTrace();}
    }

    @FXML
    protected void populateComboBox(){
        ArrayList<String> descriptions = new ArrayList<>();
        try {
            ArrayList<Meal> Meals = service.getAll();
            for(Meal Meal:Meals)
            {
                String desc = Meal.getName();
                if(!descriptions.contains(desc)) descriptions.add(desc);
            }
            comboBox.getItems().addAll(descriptions);
        }
        catch (Exception exception) {exception.printStackTrace();}
    }

    @FXML
    protected void Add(ActionEvent event) {
        String text = Ingredients1.getText();
        String time1Text = time1.getText();
        if(Objects.equals(text, "") && Objects.equals(time1Text, "")){
            populateList();
        }
        else if(Objects.equals(text, "")){
            int time = Integer.parseInt(time1Text);
            List<Meal> meals = service.getFilterMeals(time, text);
            ListView.getItems().clear();
            for(Meal meal:meals){
                ListView.getItems().add(meal.toString());
            }
        }
        else if(Objects.equals(time1Text, "")){
            List<Meal> meals = service.getFilterMeals(0, text);
            ListView.getItems().clear();
            for(Meal meal:meals){
                ListView.getItems().add(meal.toString());
            }
        }
        else{
            int time = Integer.parseInt(time1Text);
            List<Meal> meals = service.getFilterMeals(time, text);
            ListView.getItems().clear();
            for(Meal meal:meals){
                ListView.getItems().add(meal.toString());
            }
        }
    }





    @FXML
    public void initialize(){
        try {
//            service.createSchema();
//            service.AddingSchema();
            populateList();
            populateComboBox();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }





}
