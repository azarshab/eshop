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
import java.math.BigInteger;
import java.security.SecureRandom;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.FileUploadEvent;

@ManagedBean
@SessionScoped
public class UploaderBB {

    public static final String PREFIX_PATH = "D:\\UPLOAD";

    public String handleFileUpload(FileUploadEvent event) {
        String outPath = "";
        try {
            File targetProject = new File(PREFIX_PATH);
            SecureRandom random = new SecureRandom();
            OutputStream out;
            outPath = new BigInteger(130, random).toString(32) + "_" + event.getFile().getFileName();
            try (InputStream inputStream = event.getFile().getInputstream()) {
                out = new FileOutputStream(new File(targetProject, outPath));
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
        return outPath;
    }

}
