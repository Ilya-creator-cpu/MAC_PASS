package Agents;



import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement (name = "cfg")

public class CfgClass {


    List<Power> powers;

    public List<Power> getPowers(){return powers;}


    public void setPowers(List<Power> new_powers){
        powers = new_powers;
    }

    public List<Integer> getPowersInInteger() {
         List <Integer> l = new ArrayList<>();

        for(Power power: powers) {
            l.add(power.getPower());
        }
        return l;
    }


}