package Agents.Consumer;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;
import java.util.Objects;
@Slf4j
public class Report extends Behaviour {
    Boolean finish = false;
    private int onEndvalue = -1;

    Double max_price;

    public Report(Double max_price) {
        this.max_price = max_price;
    }

    @Override
    public void action() {

        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
        ACLMessage receive = getAgent().receive(mt);

        if (receive != null){
            log.info(getAgent().getLocalName() + " получил сообщение " + receive.getContent());
            String[] results = receive.getContent().split(",");
            if(Double.parseDouble(results[1])>max_price){
                onEndvalue = 0;
                finish=true;
            } else{
                onEndvalue = 1;
                finish = true;
            }
            if(Objects.equals(results[0], "halth")){
                onEndvalue = 2;
                finish=true;
            }

        } else {
            block();
        }
    }

    @Override
    public int onEnd() {
        System.out.println(onEndvalue);
        return onEndvalue; // для тестов
    }

    @Override
    public boolean done() {
        return finish;
    }
}
