package View;

import Utilites.GlobalStates;

import javax.swing.*;
import java.awt.*;

public class MyJPanel extends JPanel {

    public MyJPanel(){
        setLayout(null);
        setSize(FrameOfGame.widthOfMainFrame, FrameOfGame.heightOfMainFrame);
        InfoUnderMap infoUnderMap = new InfoUnderMap();
        GraphicsOfWorld graphicsOfWorld = new GraphicsOfWorld();
        infoUnderMap.setBounds(graphicsOfWorld.getWidth(), 0, FrameOfGame.widthOfMainFrame - graphicsOfWorld.getWidth(), FrameOfGame.heightOfMainFrame );
        graphicsOfWorld.setBounds(0, 0, graphicsOfWorld.getWidth(), graphicsOfWorld.getHeight());
        add(graphicsOfWorld);
        add(infoUnderMap);
        setBackground(GlobalStates.menuColor);
    }
}
