import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
public class SquadTest {
    @After
    public void tearDown(){
        Squad.clear();
    }
    @Test
    // Tests if it gets all the entries
    public void Squad_instantiatesCorrectly_true(){
        Squad mySquad = new Squad("avatar", 2, "Bad guys");
        assertTrue(mySquad instanceof Squad);
    }
    @Test
    public void Squad_getName_instantiatesWithName_String(){
        Squad mySquad = new Squad("Avatar", 3, "Bad guys");
        assertEquals("Avatar", mySquad.getName());
    }
    @Test
    public void Squad_getName_instantiatesWithMaxSize_int(){
        Squad mySquad = new Squad("Avatar", 3, "Bad guys");
        assertEquals(3, mySquad.getSize());
    }
    @Test
    public void Squad_getName_instantiatesWithCause_String(){
        Squad mySquad = new Squad("Avatar", 3, "Bad guys");
        assertEquals("Bad guys", mySquad.getCause());
    }
    @Test
    public void Squad_all_returnsAllInstancesOfSquad_true(){
        Squad mySquad = new Squad("Avatar", 3, "Bad guys");
        assertTrue(Squad.all().contains(mySquad));
    }
    @Test
    public void Squad_getId_instantiatesWithId_int(){
        Squad.clear();
        Squad mySquad = new Squad("Avatar", 3, "Bad guys");
        assertEquals(1, mySquad.getId());
    }
    @Test
    public void Squad_find_returnsInstanceOfSquadOfParticularId_Squad(){
        Squad mySquad = new Squad("Avatar", 3, "Bad guys");
        assertEquals(mySquad, Squad.find(mySquad.getId()));
    }
    @Test
    public void Squad_clear_removesAllInstancesOfSquad_true(){
        Squad mySquad = new Squad("Avatar", 3, "Bad guys");
        Squad.clear();
        assertEquals(0,Squad.all().size());
    }
    @Test
    public void Squad_remove_removesAParticularSquad_false(){
        Squad firstSquad = new Squad("Avatar", 3, "Bad guys");
        Squad secondSquad = new Squad("Game changers",4,"World's safety");
        Squad.remove(secondSquad.getId());
        assertTrue(Squad.all().contains(firstSquad));
    }
    @Test
    public void Squad_getHeros_instantiatesWithEmptyHeroList_int(){
        Squad mySquad = new Squad("Avatar", 3, "Bad guys");
        assertEquals(0, mySquad.getHeroes().size());
    }
    @Test
    public void Squad_addHeros_addsHeroInstanceToSquad_true(){
        Hero myHero = new Hero("Benitha", 20, "Sexism","Sexual harrassment");
        Squad mySquad = new Squad("Avatar", 3, "Bad guys");
        mySquad.addHero(Hero.find(myHero.getId()));
        assertTrue(mySquad.getHeroes().contains(myHero));
    }
    @Test
    public void Squad_addHeros_cannotAddMoreThanItsMaxSize_true(){
        Squad mySquad = new Squad("Avatar", 3, "Bad guys");
        Hero myHero = new Hero("Benitha", 20, "Sexism","Sexual harrassment");
        Hero secondHero = new Hero("Benitha", 20, "Sexism","Sexual harrassment");
        Hero thirdHero = new Hero("Benitha", 20, "Sexism","Sexual harrassment");
        Hero fourthHero = new Hero("Benitha", 20, "Sexism","Sexual harrassment");
        mySquad.addHero(Hero.find(myHero.getId()));
        mySquad.addHero(Hero.find(secondHero.getId()));
        mySquad.addHero(Hero.find(thirdHero.getId()));
        assertTrue(mySquad.getHeroes().contains(thirdHero));
    }
    @Test
    public void Squad_addHeros_cannotAddMoreThanItsMaxSize_false(){
        Squad mySquad = new Squad("Avatar", 3, "Bad guys");
        Hero myHero = new Hero("Benitha", 20, "Sexism","Sexual harrassment");
        Hero secondHero = new Hero("Benitha", 20, "Sexism","Sexual harrassment");
        Hero thirdHero = new Hero("Benitha", 20, "Sexism","Sexual harrassment");
        Hero fourthHero = new Hero("Benitha", 20, "Sexism","Sexual harrassment");
        mySquad.addHero(Hero.find(myHero.getId()));
        mySquad.addHero(Hero.find(secondHero.getId()));
        mySquad.addHero(Hero.find(thirdHero.getId()));
        assertFalse(mySquad.getHeroes().contains(fourthHero));
    }
    @Test
    public void Squad_removeHero_removesHeroFromASquad_false(){
        Squad mySquad = new Squad("Avatar", 3, "Bad guys");
        Hero firstHero = new Hero("Benitha", 20, "Sexism","Sexual harrassment");
        Hero secondHero = new Hero("Benitha", 20, "Sexism","Sexual harrassment");
        mySquad.addHero(Hero.find(firstHero.getId()));
        mySquad.addHero(Hero.find(secondHero.getId()));
        mySquad.removeHero(Hero.find(secondHero.getId()));
        assertFalse(mySquad.getHeroes().contains(secondHero));
    }
    @Test
    public void Squad_removeHero_removesHeroFromASquad_true(){
        Squad mySquad = new Squad("Avatar", 3, "Bad guys");
        Hero firstHero = new Hero("Benitha", 20, "Sexism","Sexual harrassment");
        Hero secondHero = new Hero("Benitha", 20, "Sexism","Sexual harrassment");
        mySquad.addHero(Hero.find(firstHero.getId()));
        mySquad.addHero(Hero.find(secondHero.getId()));
        mySquad.removeHero(Hero.find(secondHero.getId()));
        assertTrue(mySquad.getHeroes().contains(firstHero));
    }

}