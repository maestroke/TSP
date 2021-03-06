package View;

import Model.Order;
import Model.Artikel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Tekenpanel extends JPanel{
    private Order order;
    public static final int PWIDTH = 400;
    public static final int PHEIGHT = 400;
    
    private int xVak = 5;
    private int yVak = 5;
    private int boxWidth;
    private int boxHeight;
    private int padding = 10;
    private int lineHeight = 12;
    private String algoritme;
    
    public Tekenpanel(Order order, String algoritme){
        this.order = order;
        this.algoritme = algoritme;
        
        setPreferredSize(new Dimension(400,400));
        setMinimumSize(new Dimension(200,200));
        
        boxWidth = (PWIDTH - (2 * padding)) / xVak;
        boxHeight = (PHEIGHT - (2 * padding)) / yVak;
        
    }
    
    public void paintComponent (Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // hier wordt het magazijn getekend
        g.setColor(Color.BLACK);
        for(int x = 0; x < xVak; x++){
            for(int y = 0; y < yVak; y++){
                g.drawRect(x * boxWidth + padding, y * boxHeight + padding, boxWidth, boxHeight);
                int a1 = x * boxWidth + padding;
                int a2 = y * boxHeight + padding;
            }
        }
        // hier worden alle producten in het scherm getekend.
        ArrayList<Artikel> artikelsInOrder = order.getProductLijst();
        int countArtikelen = 0;
        while(countArtikelen<artikelsInOrder.size()){
            int artikel1X = artikelsInOrder.get(countArtikelen).getX()-1;
            int artikel1Y = artikelsInOrder.get(countArtikelen).getY()-1;
            
            g2.setColor(Color.BLACK);
            
            g2.fillOval((38 + (76*artikel1X)), (76*5) - (38+(76*artikel1Y)), 20, 20);
            
            countArtikelen++;
        }
        // hier wordt de route getekend als er een algoritme is gekozen.
        if(algoritme != null){
            ArrayList<Artikel> lijst = order.getAlgoritmeLijst();
            int grootte = lijst.size()-1;
            int count = 0;

            while(count<grootte){
                int artikel1X = lijst.get(count).getX()-1;
                int artikel1Y = lijst.get(count).getY()-1;
                int artikel2X = lijst.get(count+1).getX()-1;
                int artikel2Y = lijst.get(count+1).getY()-1;
                if(count == 0){
                    g2.setColor(Color.YELLOW);
                }else{
                    g2.setColor(Color.GREEN);
                }
                if(count == grootte - 1){
                    g2.setColor(Color.RED);
                }
                g2.setStroke(new BasicStroke(2));
                
                if(artikel1Y == artikel2Y){
                    double dubbel = (artikel1X + artikel2X);
                    double half = (dubbel / 2);
                    int half1 = (int) (10 + (38 + (76*half)));
                    int half2 = (int) (10 + (38 + (76*half)));
                    int half3 = (int)(10 + (38 + (76*half)));
                    g2.drawLine(10 + (38 + (76*artikel1X)), 2 + (76*5 - (38+(76*artikel1Y))), half1,  10 + (76*5) - (38+(76*artikel2Y)));
                    g2.drawLine(10 + (38 + (76*artikel1X)), 18 + (76*5 - (38+(76*artikel1Y))), half2,  10 + (76*5) - (38+(76*artikel2Y)));
                    g2.drawLine(half3, 10 + (76*5) - (38+(76*artikel1Y)), 10 + (38 + (76*artikel2X)), 10 + (76*5) - (38+(76*artikel2Y)));
                }else if(artikel1X == artikel2X){
                    double dubbel = (artikel1Y + artikel2Y);
                    double half = (dubbel / 2);
                    int half1 = (int)(10 + (76*5) - (38+(76*half)));
                    int half2 = (int)(10 + (76*5) - (38+(76*half)));
                    int half3 = (int) (10 + (76*5) - (38+(76*half)));
                    g2.drawLine(2 + (38 + (76*artikel1X)), 10 + (76*5 - (38+(76*artikel1Y))), 10 + (38 + (76*artikel2X)), half1);
                    g2.drawLine(18 + (38 + (76*artikel1X)), 10 + (76*5 - (38+(76*artikel1Y))), 10 + (38 + (76*artikel2X)), half2);
                    g2.drawLine(10 + (38 + (76*artikel1X)), half3, 10 + (38 + (76*artikel2X)), 10 + (76*5) - (38+(76*artikel2Y)));
                }else if((artikel1X != artikel2X) && (artikel1Y != artikel2Y)){
                    g2.drawLine(10 + (38 + (76*artikel1X)), 2 + (76*5 - (38+(76*artikel1Y))), 10 + (38 + (76*artikel2X)),  10 + (76*5) - (38+(76*artikel1Y)));
                    g2.drawLine(10 + (38 + (76*artikel1X)), 18 + (76*5 - (38+(76*artikel1Y))), 10 + (38 + (76*artikel2X)),  10 + (76*5) - (38+(76*artikel1Y)));
                    g2.drawLine( 10 + (38 + (76*artikel2X)),  10 + (76*5) - (38+(76*artikel1Y)), 10 + (38 + (76*artikel2X)), 10 + (76*5) - (38+(76*artikel2Y)));
                }
                
                count++;
            }
            if(count - 1 == order.getProductLijst().size()){
            g2.setColor(Color.YELLOW);
            int artikel1X = lijst.get(count-1).getX()-1;
            int artikel1Y = lijst.get(count-1).getY()-1;
            int artikel2X = lijst.get(count).getX()-1;
            int artikel2Y = lijst.get(count).getY()-1;
                
            if(artikel1Y == artikel2Y){
                    double dubbel = (artikel1X + artikel2X);
                    double half = (dubbel / 2);
                    int half1 = (int) (10 + (38 + (76*half)));
                    int half2 = (int) (10 + (38 + (76*half)));
                    int half3 = (int)(10 + (38 + (76*half)));
                    g2.drawLine(10 + (38 + (76*artikel1X)), 2 + (76*5 - (38+(76*artikel1Y))), half1,  10 + (76*5) - (38+(76*artikel2Y)));
                    g2.drawLine(10 + (38 + (76*artikel1X)), 18 + (76*5 - (38+(76*artikel1Y))), half2,  10 + (76*5) - (38+(76*artikel2Y)));
                    g2.drawLine(half3, 10 + (76*5) - (38+(76*artikel1Y)), 10 + (38 + (76*artikel2X)), 10 + (76*5) - (38+(76*artikel2Y)));
                }else if(artikel1X == artikel2X){
                    double dubbel = (artikel1Y + artikel2Y);
                    double half = (dubbel / 2);
                    int half1 = (int)(10 + (76*5) - (38+(76*half)));
                    int half2 = (int)(10 + (76*5) - (38+(76*half)));
                    int half3 = (int) (10 + (76*5) - (38+(76*half)));
                    g2.drawLine(2 + (38 + (76*artikel1X)), 10 + (76*5 - (38+(76*artikel1Y))), 10 + (38 + (76*artikel2X)), half1);
                    g2.drawLine(18 + (38 + (76*artikel1X)), 10 + (76*5 - (38+(76*artikel1Y))), 10 + (38 + (76*artikel2X)), half2);
                    g2.drawLine(10 + (38 + (76*artikel1X)), half3, 10 + (38 + (76*artikel2X)), 10 + (76*5) - (38+(76*artikel2Y)));
                }else if((artikel1X != artikel2X) && (artikel1Y != artikel2Y)){
                    g2.drawLine(10 + (38 + (76*artikel1X)), 2 + (76*5 - (38+(76*artikel1Y))), 10 + (38 + (76*artikel2X)),  10 + (76*5) - (38+(76*artikel1Y)));
                    g2.drawLine(10 + (38 + (76*artikel1X)), 18 + (76*5 - (38+(76*artikel1Y))), 10 + (38 + (76*artikel2X)),  10 + (76*5) - (38+(76*artikel1Y)));
                    g2.drawLine( 10 + (38 + (76*artikel2X)),  10 + (76*5) - (38+(76*artikel1Y)), 10 + (38 + (76*artikel2X)), 10 + (76*5) - (38+(76*artikel2Y)));
                }
            }
        }
    }
}
