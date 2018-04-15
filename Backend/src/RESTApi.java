import Models.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

// The Java class will be hosted at the URI path "/helloworld"
@Path("/restapi")
public class RESTApi {

    @POST
    @Path("/venfroment")
    @Produces(MediaType.APPLICATION_JSON)
    public VentArray getAllVen(EntReq req){
        VentArray toreturn = new VentArray(Venture.SelectFromEnt(req.getId()));
        return toreturn;
    }

    @POST
    @Path("/ven")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Venture getIdVen(EntReq req){
        IndArray toreturn = new IndArray(Individual.Select(req.getId()));
        return null;
    }

    @GET
    @Path("/ind")
    @Produces(MediaType.APPLICATION_JSON)
    public IndArray getAllInt(){
        IndArray toreturn = new IndArray(Individual.Select());
        return toreturn;
    }

    @POST
    @Path("/ind")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public IndArray getIdInt(EntReq req){
        IndArray toreturn = new IndArray(Individual.Select(req.getId()));
        return toreturn;
    }


    @GET
    @Path("/ent")
    @Produces(MediaType.APPLICATION_JSON)
    public EntArray getAllEnt(){
        EntArray toreturn = new EntArray(Entrepreneur.Select());
        return toreturn;
    }

    @POST
    @Path("/ent")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public EntArray getIdEnt(EntReq req){
        EntArray toreturn = new EntArray(Entrepreneur.Select(req.getId()));
        return toreturn;
    }



}

