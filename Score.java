import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;

/**
 * Write a description of class Score here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Score extends Actor
{
    public Score() {
        GreenfootImage newImage = new GreenfootImage(100,50);
        
        setImage(newImage);
    }
    
    public void setScore(int score) {
        GreenfootImage newImage = getImage();
        newImage.clear();
        
        Color color = new Color(50,50,50,125);
        Font font = new Font("Comic Sans MS",0,50);
        
        newImage.setColor(color);
        newImage.fill();
        newImage.setColor(Color.white);
        newImage.setFont(font);
        
        newImage.drawString("" + score,20,42);
        setImage(newImage);
    }
}
