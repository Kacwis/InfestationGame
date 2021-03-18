package View;

import Objects.Country;
import Utilites.GlobalStates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class GraphicsOfWorld extends JComponent {

    ArrayList<JButton> listOfButtons = new ArrayList<>();
    int lengthOfArraysOfCoordinates = 70;
    int[] arrayOfX = new int[lengthOfArraysOfCoordinates];
    int[] arrayOfY = new int[lengthOfArraysOfCoordinates];

    public GraphicsOfWorld(){
        setBackground(Color.RED);
        setLayout(null);
        creatingButtonsOfCountries();
        setSize(GlobalStates.imageOfmapOfTheWorld.getWidth(this), GlobalStates.imageOfmapOfTheWorld.getHeight(this));
        Thread transportingLines = new Thread(new Runnable() {
            @Override
            public void run() {
                while(!GlobalStates.isItEnd){
                    for (int i = 0; i < lengthOfArraysOfCoordinates ; i++) {
                        int randomIndex = (int)(Math.random() * (listOfButtons.size() ));
                        arrayOfX[i] = listOfButtons.get(randomIndex).getX();
                        arrayOfY[i] = listOfButtons.get(randomIndex).getY();
                    }
                    repaint();
                    try {
                        Thread.sleep(GlobalStates.INSTANCE.getLengthOfDay());
                    }
                    catch (InterruptedException e){
                        continue;
                    }
                }
            }
        });
        transportingLines.start();

    }


    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphic = (Graphics2D) g;
        graphic.drawImage(GlobalStates.imageOfmapOfTheWorld, 0, 0, this);
        graphic.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
        for (int i = 0; i < lengthOfArraysOfCoordinates ; i++) {
            graphic.draw(new Line2D.Float(arrayOfX[i], arrayOfY[i] , arrayOfX[lengthOfArraysOfCoordinates - 1 - i], arrayOfY[lengthOfArraysOfCoordinates -  1 - i]));
        }
        super.paintComponent(g);
    }

    private void creatingButtonsOfCountries(){
        BigDecimal scaleForX = new BigDecimal(GlobalStates.imageOfmapOfTheWorld.getWidth(this));
        BigDecimal scaleForY = new BigDecimal(GlobalStates.imageOfmapOfTheWorld.getHeight(this));
        BigDecimal dividerForX = new BigDecimal(360);
        BigDecimal dividerForY = new BigDecimal(160);
        scaleForX = scaleForX.divide(dividerForX, 10, RoundingMode.HALF_UP);
        scaleForY = scaleForY.divide(dividerForY, 10, RoundingMode.HALF_UP);
        for(Country country : GlobalStates.INSTANCE.getSortedMapOfcountries().values()){
            BigDecimal x = new BigDecimal(country.getY() + 150);
            BigDecimal y = new BigDecimal((country.getX() * -1) + 80);
            BigDecimal resultX = x.multiply(scaleForX);
            BigDecimal resultY = y.multiply(scaleForY);
            JButton currentButton = new JButton(country.getIconOfFlag());
            currentButton.setBounds(resultX.intValue() - 40, resultY.intValue() + 10 , 30, 20);
            currentButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    FrameOfCountry frameOfCountry = new FrameOfCountry(country);
                    frameOfCountry.setLocationRelativeTo(null);
                    frameOfCountry.setVisible(true);
                }
            });
            add(currentButton);
            listOfButtons.add(currentButton);
        }
    }
}
