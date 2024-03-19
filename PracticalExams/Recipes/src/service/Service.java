package service;
import domain.Meal;
import repository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Service {
    private Repository repo;

    public Service(Repository repo) {
        this.repo = repo;
    }

    public ArrayList<Meal> getAll()
    {
        return repo.getAll();
    }

    public List<Meal> getFilterMeals(int time, String ingredient){
        List<Meal> meals = repo.getAll();
        List<Meal> rez = meals.stream()
                .filter(meal -> meal.getTime() < time && meal.getIngredients().contains(ingredient))
                .collect(Collectors.toList());
        return rez;
    }

}
