package mx.uv;

import static spark.Spark.*;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        List<Usuario> usuariosList = new ArrayList<>();
        System.out.println( "Hello World!" );

        //fuente:https://gist.github.com/saeidzebardast/e375b7d17be3e0f4dddf
        options("/*",(request,response)->{
        String accessControlRequestHeaders=request.headers("Access-Control-Request-Headers");
        if(accessControlRequestHeaders!=null){
        response.header("Access-Control-Allow-Headers",accessControlRequestHeaders);
        }
        String accessControlRequestMethod=request.headers("Access-Control-Request-Method");
        if(accessControlRequestMethod!=null){
        response.header("Access-Control-Allow-Methods",accessControlRequestMethod);
        }
        return "OK";
        });
        before((request,response)->response.header("Access-Control-Allow-Origin","*"));

        get("/", 
                (request, response) -> "<h1>Bienvenidx al Proyecto</h1>"
        );
        get("/hola", 
                (request, response) -> "<h1>Hola proyecto!</h1>"
        );
        get("/adios", 
                (request, response) -> "<h1>Adios proyecto!</h1>"
        );
        get("/consultar", (request, response) ->{
            JsonObject respuesta = new JsonObject();
            respuesta.addProperty("nombre", request.queryParams("nombre"));
            respuesta.addProperty("correo", request.queryParams("correo"));
            respuesta.addProperty("telefono", request.queryParams("telefono"));
            return respuesta;
    });
        post("/agregar", (request, response) ->{
            Gson gson = new Gson();
            JsonElement jElement = gson.fromJson(request.body(), JsonElement.class);
            JsonObject usuarios = jElement.getAsJsonObject();
            String nombre = usuarios.get("nombre").getAsString();
            String correo = usuarios.get("correo").getAsString();
            String telefono = usuarios.get("telefono").getAsString();
            Usuario nuevoUsuario = new Usuario(nombre, correo, telefono);
            usuariosList.add(nuevoUsuario);
            JsonObject objectRes = new JsonObject();
            for(Usuario i : usuariosList){
                objectRes.addProperty("nombre",i.getNombre());
                objectRes.addProperty("correo",i.getCorreo());
                objectRes.addProperty("telefono",i.getTelefono());
            } 
            response.type("application/json");
            response.status(200);
            return objectRes;
    });
        delete("/eliminar", (request, response) ->{
            String telefono = request.params(":telefono");
            for(Usuario u: usuariosList){
                if(u.getTelefono().equals(telefono)){
                    usuariosList.remove(u);
                    JsonObject oRespuesta = new JsonObject();
                    oRespuesta.addProperty("nombre", u.getNombre());
                    oRespuesta.addProperty("correo", u.getCorreo());
                    oRespuesta.addProperty("telefono", u.getTelefono());
                    response.type("application/json");
                    response.status(200);
                    return oRespuesta;
                }
            }
            response.status(404);
            return "Telefono no asignado a un Usuario";
    });
        put("/modificar", (request, response) ->{
            String requestTelefono = request.params(":telefono");
            Usuario removeUsuario = null;
            Usuario nuevoUsuario = null;
            for(Usuario u: usuariosList){
                if(u.getTelefono().equals(request)){
                    Gson gson = new Gson();
                    JsonElement jelem = gson.fromJson(request.body(), JsonElement.class);
                    JsonObject usuarios = jelem.getAsJsonObject();
                    String nombre = usuarios.get("nombre").getAsString();
                    String correo = usuarios.get("correo").getAsString();
                    String telefono = usuarios.get("telefono").getAsString();
                    nuevoUsuario = new Usuario(nombre,correo,telefono);
                    removeUsuario = u;
                }
            }
            if (removeUsuario != null) {
                usuariosList.remove(removeUsuario);
                usuariosList.add(nuevoUsuario);
                JsonObject oRespuesta = new JsonObject();
                oRespuesta.addProperty("nombre", nuevoUsuario.getNombre());
                oRespuesta.addProperty("correo", nuevoUsuario.getCorreo());
                oRespuesta.addProperty("telefono", nuevoUsuario.getTelefono());
                response.type("application/json");
                response.status(200);
                return oRespuesta;
            }
            response.status(404);
            return "Error el usuario no existe";
    });


    }

}
