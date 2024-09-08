package org.bedware_software.tools.mybang;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationScoped
@Path("bangs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BangsResource {

    @GET
    public List<Bang> order() {
        Bang bang = new Bang("GitHub", "gh", BangType.APP);
        return List.of(bang);
    }

    @POST
    public void createBang(Bang bang) {
        System.out.println("bang = " + bang);
    }

}
