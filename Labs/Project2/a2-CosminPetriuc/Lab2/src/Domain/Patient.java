package Domain;
public class Patient implements Identifiable<Integer> {
    private int id;
    private String name;
    private int age;
    private String illness;

    public Patient(int Id, String name, int age, String illness) {
        this.id = Id;
        this.name = name;
        this.age = age;
        this.illness = illness;
    }

    @Override
    public int getID() {
        return id;
    }

    public void setId(int newId) {
        this.id = newId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int newAge) {
        this.age = newAge;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getIllness() {
        return illness;
    }

    public void setIllness(String newIllness) {
        this.illness = illness;
    }


    @Override
    public String toString() {
        return "Patient{" +
                "id='" + id + '\'' + " " +
                "name='" + name + '\'' + " " +
                "Age='" + age + '\'' + " " +
                "Illness='" + illness +
                '}';
    }
}

