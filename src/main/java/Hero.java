import java.util.ArrayList;
import java.util.List;
public class Hero {

    private String hName;
    private int hAge;
    private String hSpecialPower;
    private String hWeakness;
    private static List<Hero> instances = new ArrayList<>();
    private int hId;
    public Hero(String name, int age, String special_power,String weakness){
        this.hName = name;
        hAge = age;
        this.hSpecialPower = special_power;
        this.hWeakness = weakness;
        instances.add(this);
        hId = instances.size();
    }
    public String getName(){
        return hName;
    }
    public int getAge(){
        return hAge;
    }
    public String getSpecialPower(){
        return hSpecialPower;
    }
    public String getWeakness(){
        return hWeakness;
    }
    public int getId(){
        return hId;
    }
    public static List<Hero> all(){
        return instances;
    }
    public static void clear(){
        instances.clear();
    }
    public static Hero find(int id){
        return instances.get(id-1);
    }
    public static void remove(int id){
        instances.remove(id-1);
    }
}
