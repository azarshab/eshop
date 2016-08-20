package ir.azarshab.controller;

/**
 *
 * @author java
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.FileUploadEvent;

@ManagedBean
@SessionScoped
public class UploaderBB {
    
    public static final String PREFIX_PATH="D:\\UPLOAD";

    public void handleFileUpload(FileUploadEvent event) {
        try {
            File targetProject = new File(PREFIX_PATH);
            OutputStream out;
            try (InputStream inputStream = event.getFile().getInputstream()) {
                out = new FileOutputStream(new File(targetProject,
                        event.getFile().getFileName()));
                int read = 0;
                byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
