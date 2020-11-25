import org.junit.Test;
import static org.junit.Assert.*;
public class HeroTest {
    @Test
    public void Hero_instantiatesCorrectly_true(){
        Hero myHero = new Hero("Benitha", 20, "Flying", "Fire");
        assertTrue(myHero instanceof Hero);
    }
    @Test
    public void Hero_instantiatesWithName_String(){
        Hero myHero = new Hero("Benitha", 20, "Flying", "Fire");
        assertEquals("Benitha", myHero.getName());
    }
    @Test
    public void Hero_instantiatesWithAge_int(){
        Hero myHero = new Hero("Benitha", 20, "Flying", "Fire");
        assertEquals(20, myHero.getAge());
    }
    @Test
    public void Hero_instantiatesWithSpecialPower_String(){
        Hero myHero = new Hero("Benitha", 20, "Flying", "Fire");
        assertEquals("Flying", myHero.getSpecialPower());
    }
    @Test
    public void Hero_instantiatesWithWeakness_String(){
        Hero myHero = new Hero("Benitha", 20, "Flying", "Fire");
        assertEquals("Fire", myHero.getWeakness());
    }
    @Test
    public void clear_clearsAllInstancesOfHero_int(){
        Hero myHero = new Hero("Benitha", 20, "Flying", "Fire");
        Hero.clear();
        assertEquals(0,Hero.all().size());
    }
    @Test
    public void all_returnsAllInstancesOfHero_true(){
        Hero.clear();
        Hero myHero = new Hero("Benitha", 20, "Flying", "Fire");
        assertTrue(Hero.all().contains(myHero));
    }
    @Test
    public void getId_instantiatesWithId_int(){
        Hero.clear();
        Hero myHero = new Hero("Benitha", 20, "Flying", "Fire");
        assertEquals(1, myHero.getId());
    }
    @Test
    public void find_returnsHeroWithParticularId_true(){
        Hero.clear();
        Hero myHero = new Hero("Benitha", 20, "Flying", "Fire");
        assertEquals(myHero, Hero.find(myHero.getId()));
    }
    @Test
    public void remove_removesHeroWithParticularId_false(){
        Hero.clear();
        Hero myHero = new Hero("Benitha", 20, "Flying", "Fire");
        Hero.remove(myHero.getId());
        assertFalse(Hero.all().contains(myHero));
    }

    @Test
    public void testIfInstanceIsUpdated() throws Exception {
        Hero myHero = new Hero("Benitha", 20, "Flying", "Fire");
        int formerID=myHero.getId();
        boolean formerOccupied=myHero.isOccupied();
        myHero.updateHero(true);
        assertEquals(formerID,myHero.getId());
        assertNotEquals(formerOccupied,myHero.isOccupied());
    }
}