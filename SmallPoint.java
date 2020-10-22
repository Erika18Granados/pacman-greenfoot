import greenfoot.*;

/**
 * Write a description of class SmallPoint here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SmallPoint extends Item
{
    private static final GreenfootImage image = new GreenfootImage("images/small-item.png");
    
    public SmallPoint(){
        setImage(image);
        setPoints(10);
    }
}
