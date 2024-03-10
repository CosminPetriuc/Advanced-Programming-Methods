import Domain.Patient;
import UI.Ui;
import Repository.Repository;
import Service.Service;

import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {
        Repository repo = new Repository();
        Service serv = new Service(repo);
        Ui ui = new Ui(serv);
        ui.run();


    }
}


