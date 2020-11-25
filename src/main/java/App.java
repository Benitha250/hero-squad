import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        //start session
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
/*
            model.put("username", request.session().attribute("username"));
*/
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //hero form
        get("/heroes/delete",(request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Hero.clear();
            model.put("heroes",Hero.all());
            return new ModelAndView(model,"hero-detail.hbs");
        },new HandlebarsTemplateEngine());


        get("/create/hero",(request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "hero-form.hbs");
        },new HandlebarsTemplateEngine());

        post("/heroes/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            int age=Integer.parseInt(request.queryParams("age"));
            String powers=request.queryParams("powers");
            String weakness=request.queryParams("weakness");
            Hero newHero = new Hero(name,age,powers,weakness);
            model.put("heroes", newHero);
            return new ModelAndView(model, "new-hero-success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/hero", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Hero> heroes = Hero.all();
            model.put("heroes", heroes);
            return new ModelAndView(model, "hero-detail.hbs");
        }, new HandlebarsTemplateEngine());


        get("/heroes/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToFind = Integer.parseInt(request.params(":id")); //pull id
            Hero foundHero = Hero.find(idOfHeroToFind); //use it to find post
            model.put("hero", foundHero); //add it to model for template to display
            ArrayList<Hero> heroes = Hero.all();
            model.put("heroes", heroes);
            return new ModelAndView(model, "hero-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //squad
        get("/squads/delete",(request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Squad.clear();
            ArrayList<Hero> heroes=Hero.all();
            for (int i=0;i<heroes.size();i++){
                heroes.get(i).updateHero(false);
            }
            model.put("squads",Squad.all());
            return new ModelAndView(model,"squad-detail.hbs");

        },new HandlebarsTemplateEngine());

        get("/create/squad",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Hero> heroes=Hero.all();
            ArrayList<Hero> heroList=new ArrayList<>();
            for (int i=0;i<heroes.size();i++){
                if(heroes.get(i).isOccupied()==false){
                    heroList.add(heroes.get(i));
                }
            }
            model.put("heroes",Hero.all());
            return new ModelAndView(model,"squad-form.hbs");
        },new HandlebarsTemplateEngine());

        post("/squads/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            int maxSize=Integer.parseInt(request.queryParams("size"));
            String cause=request.queryParams("cause");
            ArrayList<Hero> heroes=new ArrayList<>();
            if(request.queryParamsValues("heroes")!=null){
                String[] heroesList=request.queryParamsValues("heroes");

                for(int i=0;i<heroesList.length;i++){
                    Hero addHero=Hero.find(Integer.parseInt(heroesList[i]));
                    if(heroes.size()<maxSize){
                        addHero.updateHero(true);
                        heroes.add(addHero);
                    }
                }
            }
            Squad newSquad= new Squad(name,maxSize,cause);
            model.put("heroes",Hero.all());
            return new ModelAndView(model, "new-squad-success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/squad",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("squads",Squad.all());
            return new ModelAndView(model,"squad-detail.hbs");
        },new HandlebarsTemplateEngine());

        get("/squads/:id",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToFind=Integer.parseInt(request.params(":id"));
            Squad foundSquad=Squad.find(idOfSquadToFind);
            model.put("squad",foundSquad);
            ArrayList<Squad> squads=Squad.all();
            model.put("squads",squads);
            return new ModelAndView(model,"squad-detail.hbs");
        },new HandlebarsTemplateEngine());


        //delete hero

        get("/hero/:id/delete",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToDelete=Integer.parseInt(request.params(":id"));
            Hero foundHero=Hero.find(idOfHeroToDelete);
            for (int i=idOfHeroToDelete;i<Hero.all().size();i++){
                Hero.all().get(i).setId(Hero.all().get(i).getId()-1);
            }
            foundHero.clear();
            ArrayList<Hero> heroes = Hero.all();
            model.put("heroes", heroes);
            return new ModelAndView(model,"hero-detail.hbs");
        },new HandlebarsTemplateEngine());

        get("/squad/:id/delete",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToDelete=Integer.parseInt(request.params(":id"));
            Squad foundSquad=Squad.find(idOfSquadToDelete);
            ArrayList<Squad> heroes=foundSquad.all();
            for(int i=0;i<heroes.size();i++){
                heroes.get(i).updateHero(false);
            }
            for (int i=idOfSquadToDelete;i<Squad.all().size();i++){
                Squad.all().get(i).setId(Squad.all().get(i).getId()-1);
            }
            foundSquad.all();

            ArrayList<Squad> squads = Squad.all();
            model.put("squads", squads);
            return new ModelAndView(model,"squad-detail.hbs");

        },new HandlebarsTemplateEngine());






    }
}