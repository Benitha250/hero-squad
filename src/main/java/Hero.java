import java.util.ArrayList;
import java.util.List;
public class Hero {

    private String name;
    private int age;
    private String powers;
    private String weakness;
    private static ArrayList<Hero> heroes = new ArrayList<>();
    private int id;
    private boolean occupied;

    public Hero(String name, int age, String powers,String weakness){
        this.name = name;
        this.age = age;
        this.powers = powers;
        this.weakness = weakness;
        heroes.add(this);
        id = heroes.size();
    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public String getSpecialPower(){
        return powers;
    }
    public String getWeakness(){
        return weakness;
    }
    public int getId(){
        return id;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public static ArrayList<Hero> all(){
        return heroes;
    }
    public void setId(int id) {
        this.id = id;
    }
    public static void clear(){
        heroes.clear();
    }
    public static Hero find(int id){
        return heroes.get(id-1);
    }
    public static void remove(int id){
        heroes.remove(id-1);
    }

    public void updateHero(boolean occupied){
        this.occupied=occupied;
    }
}
