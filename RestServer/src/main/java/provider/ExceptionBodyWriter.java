package provider;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Produces("*/*")
public class ExceptionBodyWriter implements ExceptionMapper {


    @Override
    public Response toResponse(Throwable exception) {
        return Response.status(500).entity(exception.getMessage()).build();
    }
}
