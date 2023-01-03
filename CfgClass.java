import javax.swing.text.TabableView;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "cfg")

public class CfgClass {

    @XmlElement
    private int P;

    @XmlElement
    private int t;

    public void setP(int p) {
        P = p;
    }

    public void setT(int T){
        t = T;
    }
    public int getP() {
        return P;
    }
}
