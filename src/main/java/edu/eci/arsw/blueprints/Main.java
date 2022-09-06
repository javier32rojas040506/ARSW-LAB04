package edu.eci.arsw.blueprints;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Set;

public class Main {

    public static void main(String a[]) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices  bps = ac.getBean(BlueprintsServices.class);
        bps.addNewBlueprint(new Blueprint("Pedro","casa#38"));
        bps.addNewBlueprint(new Blueprint("Pedro","casa#39"));
        bps.addNewBlueprint(new Blueprint("Juan","casa#12"));
        bps.addNewBlueprint(new Blueprint("Pedro","casa#8"));
        try {
            Set<Blueprint> authorBP = bps.getBlueprintsByAuthor("Pedro");
            System.out.println("Por Autor: pedro");
            for (Blueprint b: authorBP) {
                System.out.println(b);
            }
            System.out.println("Normal");
            System.out.println(bps.getBlueprint("Pedro", "casa#38"));
        } catch (BlueprintNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}