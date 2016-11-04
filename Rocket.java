import greenfoot.*;
import java.lang.Object; 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.awt.Color; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Rocket here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rocket extends Actor
{
    double gravity = 0;
    double g = 0.2;
    int boostSpeed = -5;
    int X = 0;
    boolean mouseClick = false;
    /**
     * Act - do whatever the Rocket wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (getOneIntersectingObject(Pipe.class) != null) {
            displayGameOver();
        }
        mouseClick = false;
        movement();
        setRotation();
        if(getY() > getWorld().getHeight() || this.getY() < -60 || this.getX() > getWorld().getWidth()) {
            displayGameOver();
        }
        gravity = gravity + g;
    } 
    
    private void displayGameOver(){
        GameOver gameOver = new GameOver();
            getWorld().addObject(gameOver, getWorld().getWidth()/2, getWorld().getHeight()/2);
            int score = 0;
            try{
                if (new File("BestScore.txt").exists()) {
				BufferedReader reader = new BufferedReader(new FileReader("BestScore.txt"));
                score = Integer.parseInt(reader.readLine());
                reader.close();
				if(RocketWorld.score > score){
				score = RocketWorld.score;
                BufferedWriter writer = new BufferedWriter(new FileWriter("BestScore.txt"));
                writer.write("" + RocketWorld.score);
                writer.close();
            }}
            else{
                BufferedWriter writer = new BufferedWriter(new FileWriter("BestScore.txt"));
                writer.write("" + RocketWorld.score);
                score = RocketWorld.score;
                writer.close();
            }
        } catch(IOException e){
            e.printStackTrace();
        }
            if(score != 0){
                Score image = new Score();
                image.setImage(new GreenfootImage("Best Score:" + score, 60, Color.WHITE, new Color(50,50,50,125)));
                
                this.getWorld().addObject(image, this.getWorld().getWidth() / 2, this.getWorld().getHeight() / 2 - new GameOver().getImage().getHeight() / 2 - 30);
            }
            else{
                Score image = new Score();
                image.setImage(new GreenfootImage("Best Score:" + RocketWorld.score, 60, Color.WHITE, new Color(50,50,50,125)));
                this.getWorld().addObject(image, this.getWorld().getWidth() / 2, this.getWorld().getHeight() / 2 - new GameOver().getImage().getHeight() / 2 - 30);
            }
            RocketWorld.score = 0;
            Greenfoot.stop();
    }
    
    private void movement(){
        setLocation(getX(),(int)(getY()+gravity));
        if(Greenfoot.isKeyDown("up") == true) {
            gravity = boostSpeed;
        }
        mouseClick = Greenfoot.mouseClicked(null);
        if(mouseClick == true){
            gravity = boostSpeed;
        }
        if(Greenfoot.isKeyDown("right") == true){
            setLocation(getX()+3,getY());
        }
        else if (Greenfoot.isKeyDown("left") == true){
            setLocation(getX()-2,getY());
        }
    }
    
    private void setRotation(){
        if( gravity > -8 && gravity < 5 ){
            setRotation(-6);
        }
        else if( gravity >= 5 && gravity < 9 ){
            setRotation(6);
        }
        else if( gravity >= 9 ){
            setRotation(12);
        }
    }
}
