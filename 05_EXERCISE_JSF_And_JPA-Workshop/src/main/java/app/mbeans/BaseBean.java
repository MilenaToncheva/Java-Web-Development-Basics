package app.web.mbeans;

import javax.faces.context.FacesContext;
import java.io.IOException;

public abstract class BaseBean {

   protected void redirect(String url){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/view" + url + ".xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
