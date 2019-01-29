package edu.escuelaing.arem;

import static spark.Spark.*;

public class SparkWebApp {

    public static void main(String[] args) {
        port(getPort());
        get("/index", (req, res) -> "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<body>\n"
                + "\n"
                + "<h2>Calculadora de medias y desviaciones estandar</h2>\n"
                + "\n"
                + "<form action=\"/resp\">\n"
                + "  Lista de números<br>\n"
                + "  <input type=\"text\" name=\"numeros\" value=\"\">\n"
                + "  <br><br>\n"
                + "  <input type=\"submit\" value=\"Submit\">\n"
                + "</form> \n"
                + "\n"
                + "\n"
                + "</body>\n"
                + "</html>");
        get("/resp", (req, res) -> "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<body>\n"
                + "\n"
                + "<h2>Respuesta</h2>\n"
                + "\n"
                + "<form action=\"/index\">\n"
                + "  Media:<br>\n"
                + media(req.queryParams("numeros"))
                + "  <br>\n"
                + "  <br> Desviacion: <br>\n"
                + desv(req.queryParams("numeros"))
                + "  <br><br>\n"
                + "  <input type=\"submit\" value=\"Volver\">\n"
                + "</form> \n"
                + "\n"
                + "\n"
                + "</body>\n"
                + "</html>"
                + "\n");

    }

    static float media(String numeros) {
        float suma = 0;
        String[] num = numeros.split(" ");
        int n = num.length;
        for (int i = 0; i < n; i++) {
            suma += Float.parseFloat(num[i]);
        }
        return suma / n;
    }

    static float desv(String numeros) {
        float suma = 0;
        String[] num = numeros.split(" ");
        int n = num.length;
        float media = media(numeros);
        for (int i = 0; i < n; i++) {
            suma += Math.pow(Float.parseFloat(num[i]) - media, 2);
        }

        return (float) Math.sqrt(suma / (n - 1));
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; //returns default port if heroku-port isn't set

    }
}
