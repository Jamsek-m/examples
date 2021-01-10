package com.mjamsek.file.endpoints;

import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RequestScoped
@Path("/files")
@Produces(MediaType.APPLICATION_JSON)
public class FileResource {
    
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(
        @FormDataParam("file") List<FormDataBodyPart> body
    ) {
        for (FormDataBodyPart part : body) {
            ContentDisposition fileMetadata = part.getContentDisposition();
            InputStream fileContent = part.getEntityAs(InputStream.class);
            this.saveFile(fileContent, fileMetadata.getFileName());
        }
        return Response.ok().build();
    }
    
    private void saveFile(InputStream inputStream, String filename) {
        try {
            java.nio.file.Path destination = Paths.get("data-storage").resolve(filename);
            Files.copy(inputStream, destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
