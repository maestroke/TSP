package tsp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


    public class TravellingSalesmanProblem {
    
    private Magazijn magazijn;
    private ArrayList<Artikel> routeData;
    private String Resultaat;
    private int nearestNeigboorAfstand;
    public static ArrayList<ArrayList<Vak>> switchedRoutes;
    public static ArrayList<Integer> afstanden;
    public static int kortsteDistance = 99;
    public static ArrayList<Vak> kortsteRoute;
        
    
    public TravellingSalesmanProblem(Magazijn magazijn){
        this.magazijn = magazijn;
        routeData = new ArrayList<>();
        switchedRoutes = new ArrayList<>();
        afstanden = new ArrayList<>();
    }

    public String setResultaat() {
        return Resultaat;
    }

    public void addArtikel(Artikel a){
        routeData.add(a);
    }
    
    public int getOrderAfstand(ArrayList<Artikel> productLijst){
        int afstand = 0;
        for(int i = 0;i+1<productLijst.size();i++){
            afstand = afstand + productLijst.get(i).distanceTo(productLijst.get(i+1));
        }
        return afstand;
    }
    
    public int getNearestNeighboorAfstand(){
        return nearestNeigboorAfstand;
    }
    
    static void permute(ArrayList<Vak> arrIn, int k) {
           ArrayList<Vak> arr = new ArrayList<>();

           for (Vak vak : arrIn) {
               arr.add(vak);
           }


           if (switchedRoutes == null) {
               switchedRoutes = new ArrayList<>();
               afstanden = new ArrayList<>();
           }

           for (int i = k; i < arr.size(); i++) {
               Collections.swap(arr, i, k);
               permute(arr, k + 1);
               Collections.swap(arr, k, i);
           }

           if (k == arr.size() - 1) {

               // Standaard startpunt
               Vak startPunt = new Vak(1, 1, 0);

               int totDistance = 0;
               int count = 1;
               totDistance += startPunt.distanceTo(arr.get(0));
               for (int j = 0; j < arr.size() - 1; j++) {

                   Vak vak = arr.get(j);
                   Vak vak2 = arr.get(j + 1);
                   count++;
                   totDistance += vak.distanceTo(vak2);

                   // Weer terug naar startpunt als alles is langsgeweest
                   if (count == arr.size()) {
                       totDistance += vak2.distanceTo(startPunt);
                   }

                   for(int l = 1; l < switchedRoutes.size();l++) {
                           if(afstanden.get(l) < kortsteDistance) {
                               kortsteDistance = afstanden.get(l);
                               kortsteRoute = switchedRoutes.get(l);
                           }
                       }
               }
               afstanden.add(totDistance);
               switchedRoutes.add(arr);
           }


       }

    
    public ArrayList<Artikel> volledigeEnumeratie(ArrayList<Artikel> productLijst){
        ArrayList<Artikel> artikelLijst = new ArrayList<>();
        
        // code
        
        return artikelLijst;
    }
    
    public ArrayList<Artikel> nearestNeighboor(ArrayList<Artikel> productLijst){
        ArrayList<Artikel> gegevenArtikelLijst = new ArrayList<>();
        for(int i = 0;i<productLijst.size();i++){
            gegevenArtikelLijst.add(productLijst.get(i));
        }
        ArrayList<Artikel> artikelLijst = new ArrayList<>();
        int counter = 0;
        int totaleAfstand = 0;
        while(counter < gegevenArtikelLijst.size()){
            Artikel startArtikel = gegevenArtikelLijst.get(counter);
            if(startArtikel.getX() == 1 &&startArtikel.getY() == 1){
                artikelLijst.add(startArtikel);
                gegevenArtikelLijst.remove(startArtikel);
            }
            counter++;
        }
        
        if(artikelLijst.isEmpty()){
            Artikel A1 = new Artikel(1, 1, null, 1, 1, 1);
            artikelLijst.add(A1);
        }
        
        while(!gegevenArtikelLijst.isEmpty()){
            int artikelToAdd = 0;
            int afstandTocheck = 100;
            counter = 0;
            while(counter < gegevenArtikelLijst.size()){
                int afstand = artikelLijst.get(artikelLijst.size()-1).distanceTo(gegevenArtikelLijst.get(counter));
                if(afstand <= afstandTocheck){
                    afstandTocheck = afstand;
                    artikelToAdd = counter;
                }
                counter++;
            }
            totaleAfstand = totaleAfstand+afstandTocheck;
            artikelLijst.add(gegevenArtikelLijst.get(artikelToAdd));
            gegevenArtikelLijst.remove(artikelToAdd);
        }
        
        if(artikelLijst.get(0).getNaam() == null){
            artikelLijst.remove(0);
        }
        
        nearestNeigboorAfstand = totaleAfstand;
        
        return artikelLijst;
    }
}