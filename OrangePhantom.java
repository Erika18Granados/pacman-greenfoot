import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class OrangePhantom here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OrangePhantom extends Phantom
{
    private int movementInX;
    private int movementInY;
    
    public OrangePhantom() {
        /*
         * sprites attribute belongs to class Person
         */
        sprites = new GreenfootImage[1];
        // Assignation of the image files to each position of the GreenfootImages array
        sprites[0] = new GreenfootImage("images/orange-phantom-left.png");
    }
    
    /**
     * Act - do whatever the PacMan wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        setImage(sprites[0]);
        /*
         * Update the coordinates of PacMan
         * movementInX and movementInY are modified in movePacman method
         */
        setLocation(getX() + movementInX, getY() + movementInY);
        
        movePhantom();
        checkCollisions();
    }
    
    /** Method to move the Phantom **/
    void movePhantom()
    {
           // Coordinates to change (increment) to the pacman
            movementInY = 0;
            movementInX = 0;
            // Switch of key
           switch(direction)
            {
                // UP
                case UP:
                    // Change y negative because 'UP'
                    // If pacman is powered and the poweredTime is POWERED_TIME
                    movementInY = -1;
                    // Set direction to 'UP'
                    direction = CharacterDirection.UP;
                break;
                // DOWN
                case DOWN:
                    // Change y by 1 because 'DOWN'
                    movementInY = 1;
                    // Set direction to 'DOWN'
                    direction = CharacterDirection.DOWN;
                break;
                // LEFT
                case LEFT:
                    // Change direction to 'LEFT'
                    direction = CharacterDirection.LEFT;
                    // Change x by -1 because 'LEFT'
                    movementInX = -1;
                break;
                // RIGHT
                case RIGHT:
                    // Change direction to 'RIGHT'
                    direction = CharacterDirection.RIGHT;
                    // Change x by 1 because 'RIGHT'
                    movementInX = 1;
                break;
            }
    }
    
    /** Method to check if PacMan is collisioning with an object **/
    void checkCollisions()
    {
            // Set a Wall reference in null
            Wall wall = null;
        
            
            // Siwtch of direction
            switch(direction)
            {
                /*
                 * getOneObjectAtOffset -> returns one object that is located at the specified cell
                 * null -> If there was no object
                 * Object -> If an object was found
                 * (Wall) Cast is used to assign a Wall reference to wall
                 */
                // UP
                case UP:
                    wall = (Wall)getOneObjectAtOffset(0, -20, Wall.class);
                    if(wall != null || 0 == getY()) {
                        direction = CharacterDirection.DOWN;
                        turnTowards(getX(), getWorld().getHeight());
                        movementInY = 1;
                    }
                    
                break;
                case DOWN:
                    wall = (Wall)getOneObjectAtOffset(0, 20, Wall.class);
                    if(wall != null || getWorld().getHeight() == getY()) {
                        direction = CharacterDirection.UP;
                        turnTowards(getX(), 0);
                        movementInY = -1;
                    }           
                break;
                case RIGHT:
                    wall = (Wall)getOneObjectAtOffset(20, 0, Wall.class);
                    if(wall != null || getWorld().getWidth() == getX()) {
                        direction = CharacterDirection.LEFT;
                        turnTowards(0, getY());
                        movementInX = -1;
                    }
                break;
                case LEFT:
                    wall = (Wall)getOneObjectAtOffset(-20, 0, Wall.class);
                    if(wall != null || 0 == getX()) {
                        direction = CharacterDirection.RIGHT;
                        turnTowards(getWorld().getWidth(), getY());
                        movementInX = 1;
                    }
                break;
            }
    }
}

