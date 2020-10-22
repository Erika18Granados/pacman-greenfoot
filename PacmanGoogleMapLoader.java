/**
 * This class represents the main game map creation
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PacmanGoogleMapLoader implements StaticMapLoader 
{
    // Method from class MapLoader (interface: it has to be implemented)
    public Map loadMap()
    {
        // Create the object map
        Map map = new Map();
        
        // Add certain amount of walls to the map
        addWalls(map);

        // Add the small items to the map
        addSmallItems(map);

        addPowerItems(map);
        
        map.addObject(new PacMan(), 50, 150);
        map.addObject(new BluePhantom(), 100, 200);
        map.addObject(new OrangePhantom(), 450, 500);
        map.addObject(new PinkPhantom(), 400, 300);
        map.addObject(new RedPhantom(), 300, 350);

        return map;
    }

    void addWalls(Map map)
    {
        map.addObject(new Wall(), 50, 50);
        map.addObject(new Wall(), 100, 50);
        map.addObject(new Wall(), 150, 50);
        map.addObject(new Wall(), 200, 50);
        map.addObject(new Wall(), 250, 50);

        map.addObject(new Wall(), 300, 150);
        map.addObject(new Wall(), 300, 200);
        map.addObject(new Wall(), 300, 250);
        map.addObject(new Wall(), 300, 400);
        map.addObject(new Wall(), 300, 450);
        map.addObject(new Wall(), 300, 500);

        map.addObject(new Wall(), 450, 650);
        map.addObject(new Wall(), 500, 650);
        map.addObject(new Wall(), 550, 650);
        map.addObject(new Wall(), 600, 650);
        map.addObject(new Wall(), 650, 650);
    }

    void addSmallItems(Map map)
    {
        for(int x = 50; x < 600; x+=20) {
            map.addObject(new SmallPoint(), x, 320);
        }
    }

    void addPowerItems(Map map)
    {
        map.addObject(new PowerPoint(), 50, 350);
        map.addObject(new PowerPoint(), 100, 550);
        map.addObject(new PowerPoint(), 650, 350);
        map.addObject(new PowerPoint(), 650, 550);
        map.addObject(new PowerPoint(), 700, 100);

    }

}
