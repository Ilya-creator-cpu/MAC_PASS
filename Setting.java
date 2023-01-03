import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement (name = "Setting")

public class Setting {
    List<Load> load;

    public List<Load> getLoad(){
        return load;
    }

    public void setLoad(List<Load> new_load){
        load = new_load;
    }

    public String getLoadInString() {
        String str="";

        for(Load load: load) {
            str = str + load.getLoad() + " " + load.getTime() + "; ";
        }
        return str;
    }
}
