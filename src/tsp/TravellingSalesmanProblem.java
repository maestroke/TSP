
package tsp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


    public class TravellingSalesmanProblem {
    
    private Magazijn magazijn;
    private ArrayList<Artikel> routeData;
    private String Resultaat;
        
    
    public TravellingSalesmanProblem(Magazijn magazijn){
        this.magazijn = magazijn;
        routeData = new ArrayList<>();
    }

    public String setResultaat() {
        return Resultaat;
    }

    public void addArtikel(Artikel a){
        routeData.add(a);
    }

    static ArrayList permute(ArrayList<Integer> arr, int k){
        ArrayList<ArrayList> a = new ArrayList<>();
        for(int i = k; i < arr.size(); i++){
        if (k == arr.size() -1){
            System.out.println(Arrays.toString(arr.toArray()));
        }
        
            Collections.swap(arr, i, k);
            permute(arr, k+1);
            Collections.swap(arr, k, i);
            a.add(arr);
        }
        return a;
    }
    
    public ArrayList<Artikel> nearestNeighboor(ArrayList<Artikel> productLijst){
        ArrayList<Artikel> artikelLijst = new ArrayList<>();
        int counter = 0;
        while(counter < productLijst.size()){
            Artikel startArtikel = productLijst.get(counter);
            if(startArtikel.getX() == 1 &&startArtikel.getY() == 1){
                artikelLijst.add(startArtikel);
                productLijst.remove(startArtikel);
            }
            counter++;
        }
        counter = 0;
        
        if(artikelLijst.isEmpty()){
            Artikel A1 = new Artikel(1, 1, null, 1, 1, 1);
            artikelLijst.add(A1);
        }
        
        while(!productLijst.isEmpty()){
            int artikelToAdd = 0;
            int afstandTocheck = 100;
            counter = 0;
            while(counter < productLijst.size()){
                int afstand = artikelLijst.get(artikelLijst.size()-1).distanceTo(productLijst.get(counter));
                if(afstand <= afstandTocheck){
                    afstandTocheck = afstand;
                    artikelToAdd = counter;
                }
                counter++;
            }
            artikelLijst.add(productLijst.get(artikelToAdd));
            productLijst.remove(artikelToAdd);
        }
        
        if(artikelLijst.get(0).getNaam() == null){
            artikelLijst.remove(0);
        }
        
        return artikelLijst;
    }
}