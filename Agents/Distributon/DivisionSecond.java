package Agents.Distributon;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
@Slf4j

public class DivisionSecond extends Behaviour {
    String winner1;
    String winner2;
    Double minPrice1=999999.0;
    Double minPrice2=999999.0;
    String neededPower2;
    List<String> list = new ArrayList<>();
    Boolean finish = false;
    int deleteIndex;
    public DivisionSecond(String neededPower) {
        this.neededPower2 = neededPower;
    }


    @Override
    public void action() {
        String str = MyMap.myMapGet().entrySet().stream().map(e -> e.getKey() + "," + e.getValue())
                .collect(Collectors.joining(","));
        String[] str2 = str.split(",");


        list.addAll(List.of(str2));



        for (int i = 1; i < list.size(); i=i+2) {
            if(Double.parseDouble(list.get(i)) < minPrice1){
                minPrice1 = Double.parseDouble(str2[i]);
                winner1 = str2[i-1];
                deleteIndex = i;
            }
        }

        list.remove(deleteIndex);
        list.remove(deleteIndex-1);

        if(list.size()>2){
            for (int i = 1; i < list.size(); i=i+2) {
                if(Double.parseDouble(list.get(i)) < minPrice2){
                    minPrice2 = Double.parseDouble(list.get(i));
                    winner2 = list.get(i-1);
                }
            }
        }
        if(list.size()<=2){
            minPrice2 = Double.parseDouble(list.get(1));
            winner2 = list.get(0);
        }




        ACLMessage msg1 = new ACLMessage(ACLMessage.CONFIRM);                                     //отправка  первого сообщ в тоПик
        msg1.setContent((Double.parseDouble(neededPower2)/2 )+ ","+minPrice1);
        msg1.addReceiver(new AID(winner1, false));
        getAgent().send(msg1);
        log.info(getAgent().getLocalName() + " ему " +winner1 + " вот столько энергии " + (Double.parseDouble(neededPower2)/2));


        ACLMessage msg2 = new ACLMessage(ACLMessage.CONFIRM);                                     //отправка  первого сообщ в тоПик
        msg2.setContent((Double.parseDouble(neededPower2)/2 )+ ","+minPrice2);
        msg2.addReceiver(new AID(winner2, false));
        getAgent().send(msg2);
        log.info(getAgent().getLocalName() + " ему " +winner2 + " вот столько энергии " + (Double.parseDouble(neededPower2)/2));
        finish = true;
    }

    @Override
    public boolean done() {
        return finish;
    }
}
