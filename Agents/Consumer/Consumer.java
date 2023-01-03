package Agents.Consumer;

import Agents.*;
import jade.core.Agent;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Consumer extends Agent {
    Data data;

    private int hour = 4;



    private Map<Integer,Integer> hashMap = new HashMap<>();
    CfgClass power = new CfgClass();

    DFhelper dFhelper = new DFhelper();

    @SneakyThrows
    protected void setup(){
        log.info(getLocalName() + " Consumer");
        registration();
        data = new Data(1,1);

        CfgClass cfg = new CfgClass();
        power = WorkWithConfigFiles.unMarshalAny(CfgClass.class, "src/main/resources/"
                +getLocalName() + ".xml");

        for (int i = 1; i <24; i++) {
            data.setPower(i, power.getPowersInInteger().get(i));
        }
        TimeModel tm = new TimeModel();
       // log.info(Long.toString(hour));

        //log.info(hashMap.toString());

        addBehaviour(new SendQuantity(this,20000,data));
        addBehaviour(new Report(1500.0));

    }

    public void registration(){
        dFhelper.registerAgent(this,"Consumption");

    }

}
