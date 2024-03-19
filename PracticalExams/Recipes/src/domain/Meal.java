package domain;

import java.util.Date;

public class Meal {
    private String name;

    private int time;

    private String ingredients;

    @Override
    public String toString(){
        return "Meal{" +
                "name=" + name +
                ",time=" + time+
                ",ingredients=" + ingredients +
                '}';
    }

    public String getName() {
        return name;
    }
    public String getIngredients(){
        return ingredients;

    }

   public int getTime(){
        return time;
    }

    public Meal(String name, int time, String ingredients){
        this.name = name;
        this.time = time;
        this.ingredients = ingredients;
    }
}
