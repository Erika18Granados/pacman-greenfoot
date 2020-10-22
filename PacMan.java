import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Main pac-man class
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PacMan extends Person
{
    /** CONSTANTS **/
    // Initial lifes of pac-man
    private static final int INITIAL_LIFES = 3;
    // Initial points of pac-man
    private static final int INITIAL_POINTS = 0;
    private static final int ITERATIONS_TO_CHANGE_SPRITE = 15;
    // Time that pacman is powered
    private static final int POWERED_TIME = 10000000;
    
    public enum PACMAN_STATE{
        NORMAL,
        POWERED
    }
   
    /** ATTRIBUTES **/
    // Remaining lifes
    private int lifes;
    // Accumulated points
    private int points;
    
    // State of pacman
    PACMAN_STATE pacmanState;
    // Time that pacman is powered
    private int poweredTime = 0;
    
    // Moving coordinates
    private int movementInX;
    private int movementInY;
    
    /** CONSTRUCTOR **/
    public PacMan()
    {
        /*
         * sprites attribute belongs to class Person
         */
        sprites = new GreenfootImage[2];
        // Assignation of the image files to each position of the GreenfootImages array
        sprites[0] = new GreenfootImage("images/pacman-open.png");
        sprites[1] = new GreenfootImage("images/pacman-close.png");
        
        // Assignation of initial lifes to counter of lifes
        lifes = INITIAL_LIFES;
        // Assignation of initial points to counter of points
        points = INITIAL_POINTS;
        // Assign the initial direction of the enum CharacterDirection to right (from class Person)
        direction = CharacterDirection.RIGHT;
        pacmanState = PACMAN_STATE.NORMAL;
    }
    /**
     * Act - do whatever the PacMan wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        /*
         * delaySprite is an attribute of Person
         * 
         */
        if(delaySprite >= ITERATIONS_TO_CHANGE_SPRITE)
        {
           /*
            * currentSprite was initialized in 0 in class Person
            * This set of instructions increment this attribute and based on the length of the images array
            * change the image.
            */
           currentSprite = (++currentSprite) % sprites.length; // Changes between 0 and 1
           setImage(sprites[currentSprite]);

           delaySprite = 0;
        }
        
        // Increment the delaySprite in 1
        delaySprite++;
        
        /*
         * Update the coordinates of PacMan
         * movementInX and movementInY are modified in movePacman method
         */
        setLocation(getX() + movementInX, getY() + movementInY);
        
        // Read the key from player (last pressed)
        String lastKeyPressed = Greenfoot.getKey();
        // If was not null
        if(lastKeyPressed != null)
        {
            
            // Show in world the lastKeyPressed
            getWorld().showText(lastKeyPressed, 100, 10);
            
            // Move PacMan based on the lastKeyPressed
            movePacman(lastKeyPressed);
        }
        
        getWorld().showText("POINTS:", 300, 10);
        getWorld().showText(String.format("%d", points), 400, 10);
        
        checkCollisions();
        
    }    
    
    /** Method to move the PacMan based on the last key pressed **/
    void movePacman(String keyPressed)
    {
        // Coordinates to change (increment) to the pacman
        movementInY = 0;
        movementInX = 0;
        // Switch of key
        switch(keyPressed)
        {
            // UP
            case "up":
                // Change y negative because 'UP'
                // If pacman is powered and the poweredTime is POWERED_TIME
                /*
                if(pacmanState == PACMAN_STATE.POWERED && poweredTime == POWERED_TIME) {
                    movementInY = -3;
                    poweredTime--;
                } else {
                    movementInY = -1;
                }*/
                movementInY = -1;
                // Set direction to 'UP'
                direction = CharacterDirection.UP;
                // Turn PacMan to actual x and y=0 coordinates
                turnTowards(getX(), 0);
            break;
            // DOWN
            case "down":
                // Change y by 1 because 'DOWN'
                movementInY = 1;
                // Set direction to 'DOWN'
                direction = CharacterDirection.DOWN;
                // Turn PacMan to actual x and limit of world in y
                turnTowards(getX(), getWorld().getHeight());
            break;
            // LEFT
            case "left":
                // Change direction to 'LEFT'
                direction = CharacterDirection.LEFT;
                // Turn PacMan to x=0 and actual y coordinates
                turnTowards(0, getY());
                // Change x by -1 because 'LEFT'
                movementInX = -1;
            break;
            // RIGHT
            case "right":
                // Change direction to 'RIGHT'
                direction = CharacterDirection.RIGHT;
                // Turn PacMan to limit of world in x and actual y
                turnTowards(getWorld().getWidth(), getY());
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
        SmallPoint smallPoint = null;
        PowerPoint powerPoint = null;
        
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
                smallPoint = (SmallPoint)getOneObjectAtOffset(0, -15, SmallPoint.class);
                powerPoint = (PowerPoint)getOneObjectAtOffset(0, -30, PowerPoint.class);
            break;
            case DOWN:
                wall = (Wall)getOneObjectAtOffset(0, 20, Wall.class);
                smallPoint = (SmallPoint)getOneObjectAtOffset(0, 15, SmallPoint.class);
                powerPoint = (PowerPoint)getOneObjectAtOffset(0, 30, PowerPoint.class);
            break;
            case RIGHT:
                wall = (Wall)getOneObjectAtOffset(20, 0, Wall.class);
                smallPoint = (SmallPoint)getOneObjectAtOffset(15, 0, SmallPoint.class);
                powerPoint = (PowerPoint)getOneObjectAtOffset(30, 0, PowerPoint.class);
            break;
            case LEFT:
                wall = (Wall)getOneObjectAtOffset(-20, 0, Wall.class);
                smallPoint = (SmallPoint)getOneObjectAtOffset(-15, 0, SmallPoint.class);
                powerPoint = (PowerPoint)getOneObjectAtOffset(-30, 0, PowerPoint.class);
            break;
        }
        
        if(wall != null)
        {
            movementInY = 0;
            movementInX = 0;
        }
        
        if(smallPoint != null)
        {
            movementInY = 0;
            movementInX = 0;
            getWorld().removeObject(smallPoint);
            points = points + smallPoint.getPoints();
            /*
            pacmanState = PACMAN_STATE.POWERED;
            poweredTime = POWERED_TIME;*/
        }
        
        if(powerPoint != null)
        {
            movementInY = 0;
            movementInX = 0;
            getWorld().removeObject(powerPoint);
            points = points + powerPoint.getPoints();
        }
    }
}
