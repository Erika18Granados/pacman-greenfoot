import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StrowberryButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StrowberryButton extends Actor
{
    private int deltaX = 1;
    
    /**
     * Act - do whatever the StrowberryButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
       if(Greenfoot.mouseClicked(this))
       {
           MapLoader mapLoader = new PacmanGoogleMapLoader();
           
           Greenfoot.setWorld(mapLoader.loadMap());
       }
    }    
}
