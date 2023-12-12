package back.tests;

import back.db.*;
import back.*;
import back.Module;

public class TestRequetes {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        Requetes req = new Requetes();

        // Intervenant

        Statut s = new Statut("vacataire", 120, 87, 2/3);
        System.out.println("\n--Intervenant--");
        req.insertStatut(s);
        req.insertIntervenant(Intervenant.creerIntervenant(1, "Thomas", "Colignon", s, 40.3f));
        System.out.println("insert: " + req.getIntervenants());
        req.updateIntervenant(Intervenant.creerIntervenant(1, "Thomas", "Colignon", new Statut("vacataire", 120, 87, 2/3), 150f));
        System.out.println("update: " + req.getIntervenants());
        req.deleteIntervenant(Intervenant.creerIntervenant(1, "Thomas", "Colignon", new Statut("vacataire", 120, 87, 2/3), 150f));
        System.out.println("delete: " + req.getIntervenants() + "\n\n");

        // Module
        System.out.println("\n--Module--");
        req.insertModule(Module.creerModule(1, "Stage/suivi", "s4", "Stage", "Stage", "S4.ST", 52, 2, 4, 52, 0));
        System.out.println("insert: " + req.getModules());
        req.updateModule(Module.creerModule(1, "Stage/suivi", "s6", "Stage", "Stage", "S4.ST", 52, 4, 4, 52, 10));
        System.out.println("update: " + req.getModules());
        req.deleteModule(Module.creerModule(1, "Stage/suivi", "s6", "Stage", "Stage", "S4.ST", 52, 4, 4, 52, 10));
        System.out.println("delete: " + req.getModules() + "\n\n");

        // Heure
        System.out.println("\n--Heure--");
        req.insertHeure(Heure.creerHeure(1, Module.creerModule(1, "Stage/suivi", "s4", "Stage", "Stage", "S4.ST", 52, 2, 4, 52, 0), new TypeHeure(1, "sae", 2f) , 10, "relou"));
        System.out.println("insert: " + req.getHeures());
        req.updateHeure(Heure.creerHeure(1, Module.creerModule(1, "Sae", "s3", "Sae3", "Sae3", "S3.SAE", 52, 2, 4, 52, 0), new TypeHeure(1, "sae", 2f) , 10, "relou"));
        System.out.println("update: " + req.getHeures());
        req.deleteHeure(Heure.creerHeure(1, Module.creerModule(1, "Sae", "s3", "Sae3", "Sae3", "S4.ST", 52, 2, 4, 52, 0) , new TypeHeure(1, "sae", 2f) , 10, "relou"));
        System.out.println("delete: " + req.getHeures() + "\n\n");

        // TypeHeure
        System.out.println("\n--TypeHeure--");
        req.insertTypeHeure(new TypeHeure(2, "sae", 2f));
        System.out.println("insert: " + req.getTypesHeures()); 
        req.updateTypeHeure(new TypeHeure(2, "td", 1f));
        System.out.println("insert: " + req.getTypesHeures());
        req.deleteTypeHeure(new TypeHeure(2, "td", 1f));
        System.out.println("insert: " + req.getTypesHeures());


    }
}
