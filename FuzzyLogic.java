package api; 

import javax.ws.rs.Consumes; 
import javax.ws.rs.GET; 
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces; 
import javax.ws.rs.core.MediaType; 
import javax.ws.rs.core.Response; 
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;

@Path("/api") 
@Produces(MediaType.APPLICATION_JSON) 
@Consumes(MediaType.APPLICATION_JSON) 
public class FuzzyLogic {               

    @GET  
    @Path("/fuzzyapi/{dificultadActual}/{tiempo}/{intentos}") 
    public Response doFuzzy (
    		@PathParam("dificultadActual") double dificultadActual,
    		@PathParam("tiempo") double tiempo,
    		@PathParam("intentos") double intentos) {
    	
    	 String fileName = "C:/fuzzy/nivel.fcl";
         FIS fis = FIS.load(fileName,true);

         // Error while loading?
         if( fis == null ) { 
             System.err.println("Can't load file: '" + fileName + "'");
             return null;
         }

       

         // Set inputs
         fis.setVariable("dificultadActual", dificultadActual);
         fis.setVariable("tiempo", tiempo);
         fis.setVariable("intentos", intentos);

         // Evaluate
         fis.evaluate();

        
    	
        return Response.ok(fis.getVariable("nuevaDificultad"),MediaType.APPLICATION_JSON)
        .build();  
    } 
}