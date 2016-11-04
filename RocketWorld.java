import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RocketWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RocketWorld extends World
{
    int pipeCounter = 0;
    int rocketCounter = 0;
    int pipeSpace = 150;
    public static int score = 0;
    int firstPipe = 240;
    Score scoreObj = null;
    /**
     * Constructor for objects of class RocketWorld.
     * 
     */
    public RocketWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1,false);
        setPaintOrder(Score.class, GameOver.class, Pipe.class, Rocket.class);
        Rocket rocket = new Rocket();
        addObject(rocket,100,200);
        scoreObj = new Score();

        scoreObj.setScore(0);
        addObject(scoreObj,getWidth()/2,0 + 25);
    }

    public void act() {
        pipeCounter++;
        if (pipeCounter % 100 == 0) {
            //Pipe pippy = new Pipe();
            //GreenfootImage image = pippy.getImage();
            //addObject(pippy,getWidth(),getHeight()/2 + image.getHeight() - random);
            createPipes();
        }
        if(pipeCounter >= firstPipe){
            if(rocketCounter % 100 == 0){
                score++;
                scoreObj.setScore(score);
            }
            rocketCounter++;
        }
    }

    private void createPipes(){
        int random = Greenfoot.getRandomNumber(150);
        Pipe topPipe = new Pipe();
        Pipe botPipe = new Pipe();
        GreenfootImage imageTop = topPipe.getImage();
        GreenfootImage imageBot = botPipe.getImage();
        int newRandom = random;
        addObject(botPipe, getWidth(), getHeight()/2 + imageBot.getHeight() + 50 - newRandom);
        addObject(topPipe, getWidth(), getHeight()/2 - newRandom - pipeSpace);
    }

    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     */
}
