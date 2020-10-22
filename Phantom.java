import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Phantom here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Phantom extends Person 
{
    private PhantomState phantomState;
    
    public Phantom()
    {        
        phantomState = PhantomState.NORMAL;
        direction = CharacterDirection.RIGHT;
    }
}
