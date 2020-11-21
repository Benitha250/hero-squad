import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args){
        staticFileLocation("public");


        get("/", (Request request, Response response) -> {
            Map<String, Object> model = new HashMap<>();
            if(Squad.all().size()>0){
                model.put("squads",Squad.all());
            }
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/squad/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "new-squad-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/squad/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String newSquadName = request.queryParams("squad-name");
            int newSquadMaxSize = Integer.parseInt(request.queryParams("squad-size"));
            String newSquadCause = request.queryParams("squad-cause");
            Squad mySquad = new Squad(newSquadName,newSquadMaxSize,newSquadCause);
            request.session().attribute("squads", Squad.all());
            model.put("squad", mySquad);
            return new ModelAndView(model, "new-squad-success.hbs");
        }, new HandlebarsTemplateEngine());


        get("/squad/:id",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Squad chosenSquad = Squad.find(Integer.parseInt(request.params("id")));
            model.put("squad",chosenSquad);
            return new ModelAndView(model, "squad-details.hbs");
        }, new HandlebarsTemplateEngine());

        post("/squad/:id/addhero", (request, response) -> {
            Map<String, Object> model = new HashMap<>();

            Squad chosenSquad = Squad.find(Integer.parseInt(request.params("id")));
            if(chosenSquad.getHeros().size()==chosenSquad.getSize()){
                String message = "Could not add the hero to squad. It is already full. ";
                model.put("message", message);
            }else{
                String heroName = request.queryParams("hero-name");
                int heroAge = Integer.parseInt(request.queryParams("hero-age"));
                String heroPower =request.queryParams("special-power");
                String heroWeakness = request.queryParams("weakness");
                Hero myHero = new Hero(heroName,heroAge,heroPower,heroWeakness);
                request.session().attribute("heroes", Hero.all());
                chosenSquad.getHeros().add(myHero);
                String message = "The Hero was added successfully";
                model.put("message", message);
            }
            model.put("squad",chosenSquad);
            return new ModelAndView(model, "squad-details.hbs");
        },new HandlebarsTemplateEngine());

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        get("/hero/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "new-hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/hero/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String newheroName = request.queryParams("hero-name");
            int newheroAge = Integer.parseInt(request.queryParams("hero-age"));
            String newheroPower =request.queryParams("special-power");
            String newheroWeakness = request.queryParams("weakness");
            Hero myHero = new Hero(newheroName,newheroAge,newheroPower,newheroWeakness);
            request.session().attribute("heroes", Hero.all());
            model.put("hero", Hero);
            return new ModelAndView(model, "new-hero-success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/hero/:id",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Hero chosenHero = Hero.find(Integer.parseInt(request.params("id")));
            model.put("title", chosenHero.getName());
            model.put("hero", chosenHero);
            return new ModelAndView(model, "hero-details.hbs");
        },new HandlebarsTemplateEngine());

        get("/squads", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("squads", request.session().attribute("squads"));
            return new ModelAndView(model, "squads.hbs");
        }, new HandlebarsTemplateEngine());

        get("/heroes", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("heroes", request.session().attribute("heroes"));
            model.put("title", "Heroes");
            return new ModelAndView(model, "heroes.hbs");
        }, new HandlebarsTemplateEngine());

        get("/squad/delete/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Squad.remove(Integer.parseInt(request.params("id")));
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/hero/delete/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Hero.remove(Integer.parseInt(request.params("id")));
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

    }


}
