package edu.pucmm.practica2;

import edu.pucmm.practica2.Controladora.CrudControladora;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {

        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/publico");
            config.enableCorsForAllOrigins();
        }).start(getHerokuAssignedPort());

        new CrudControladora(app).aplicarRutas();
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 7000; //Retorna el puerto por defecto en caso de no estar en Heroku.
    }
}
