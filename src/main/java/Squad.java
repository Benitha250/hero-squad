import java.util.ArrayList;
import java.util.List;
public class Squad {
    private int maxSize;
    private String name;
    private String cause;
    private static ArrayList<Squad> squads = new ArrayList<>();
    private int id;
    private boolean occupied;
    private ArrayList<Hero> heroes;
    public Squad(String name, int max_size, String cause) {
        this.name = name;
        this.maxSize = max_size;
        this.cause = cause;
        squads.add(this);
        this.heroes = heroes;
        id = squads.size();
        heroes = new ArrayList<>();
    }
    public String getName(){
        return name;
    }
    public int getSize(){
        return maxSize;
    }
    public String getCause(){
        return cause;
    }
    public static ArrayList<Squad> all(){
        return squads;
    }
    public int getId(){
        return id;
    }
    public static Squad find(int id){
        return squads.get(id-1);
    }

    public boolean isOccupied() {
        return occupied;
    }

    public static void clear(){
        squads.clear();
    }
    public static void remove(int id){
        squads.remove(id-1);
        for (Squad squad:squads) {
            squad.id = squads.indexOf(squad)+1;
        }
    }
    public void setId(int id) {
        this.id = id;
    }
    public ArrayList<Hero> getHeroes(){
        return heroes;
    }
    public void addHero(Hero myHero){
        if (heroes.size()<maxSize){
            heroes.add(myHero);
        }
    }
    public void removeHero(Hero myHero){
        heroes.remove(myHero);
    }
    public void updateHero(boolean occupied){
        this.occupied=occupied;
    }
}