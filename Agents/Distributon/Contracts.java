package Agents.Distributon;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class Contracts extends Behaviour {
    Map<String, String > contractMap;
    int i =0;
    Double minPrice=999999.0;

    String winner;
    String neededPower2;

    Boolean finish = false;
    public Contracts(Agent a, Map<String, String> producerMap, String neededPower) {
        super(a);
        this.contractMap = producerMap;
        this.neededPower2 = neededPower;
    }


    @Override
    public void onStart() {
        log.info("Получил контракт");
        MyMap.myMapPut(contractMap);


        String str = MyMap.myMapGet().entrySet().stream().map(e -> e.getKey() + "," + e.getValue())
                .collect(Collectors.joining(","));



    }


    @Override
    public void action() {
        if(i <30) {
            String str = MyMap.myMapGet().entrySet().stream().map(e -> e.getKey() + "," + e.getValue())
                    .collect(Collectors.joining(","));
            i++;
        }
        String str = MyMap.myMapGet().entrySet().stream().map(e -> e.getKey() + "," + e.getValue())
                .collect(Collectors.joining(","));

        log.info(str);
        String[] str2 = str.split(",");


        for (int i = 1; i < str2.length; i=i+2) {
            if(Double.parseDouble(str2[i]) < minPrice){
                minPrice = Double.parseDouble(str2[i]);
                winner = str2[i-1];
            }
        }

            log.info("Победитель" + winner);

        ACLMessage msg = new ACLMessage(ACLMessage.CONFIRM);                                     //отправка  первого сообщ в тоПик
        msg.setContent(neededPower2+","+minPrice);
        msg.addReceiver(new AID(winner, true));
        getAgent().send(msg);
        log.info( getAgent().getLocalName() + " хочет заключить контракт с этим агентом" +
                winner);
       // log.info(msg.getContent());
        finish = true;
    }

    @Override
    public boolean done() {

        return finish;
    }
}
