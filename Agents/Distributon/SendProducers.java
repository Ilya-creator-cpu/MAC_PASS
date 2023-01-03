package Agents.Distributon;

import Agents.DFhelper;
import Agents.TopicHelper;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
@Slf4j
public class SendProducers extends Behaviour {
    private final String whichAgent;
    private final String maxPrice;
    private final String whichHour;
    private boolean stop = false;
    private final String neededPower;

    DFhelper dFhelper = new DFhelper();

    int cnt=0;

    public SendProducers(String whichAgent, String neededPower , String maxPrice, String whichHour) {
        this.whichAgent = whichAgent;
        this.neededPower = neededPower;
        this.maxPrice = maxPrice;
        this.whichHour = whichHour;
    }

    @Override
    public void action() {

        // возвращает лист List<AID> всех зареганных продюсеров
        List<AID> producers = dFhelper.findAgents(getAgent(), "Production");
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        AID topicAID = TopicHelper.createTopic(myAgent, getAgent().getLocalName()+"energy");  //topic
        msg.setContent(neededPower+","+maxPrice+","+whichHour+","+topicAID.getLocalName());
        msg.setProtocol("Запрашиваю энергию");


        for (AID producer : producers) {
            msg.addReceiver(producer);
            log.info("Я поставщик " + getAgent().getLocalName() +
                    " хочу купить для " + whichAgent + " " + "вот столько энергии - "+ neededPower + "кВт у " + producer.getLocalName() +" по макс цене - "+ maxPrice);

            cnt++;
        }
        getAgent().send(msg);
        stop = true;

    }

    @Override
    public boolean done() {
        return stop;
    }
}
