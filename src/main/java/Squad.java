import java.util.ArrayList;
import java.util.List;
public class Squad {
    private int maxSize;
    private String name;
    private String cause;
    private static List<Squad> instances = new ArrayList<>();
    private int id;
    private List<Hero> heros;
    public Squad(String name, int max_size, String cause) {
        this.name = name;
        this.maxSize = max_size;
        this.cause = cause;
        instances.add(this);
        id = instances.size();
        heros = new ArrayList<>();
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
    public static List<Squad> all(){
        return instances;
    }
    public int getId(){
        return id;
    }
    public static Squad find(int id){
        return instances.get(id-1);
    }
    public static void clear(){
        instances.clear();
    }
    public static void remove(int id){
        instances.remove(id-1);
        for (Squad squad:instances) {
            squad.id = instances.indexOf(squad)+1;
        }
    }
    public List<Hero> getHeros(){
        return heros;
    }
    public void addHero(Hero myHero){
        if (heros.size()<maxSize){
            heros.add(myHero);
        }
    }
    public void removeHero(Hero myHero){
        heros.remove(myHero);
    }
}