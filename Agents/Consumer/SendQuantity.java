package Agents.Consumer;

import Agents.DFhelper;
import Agents.Data;
import Agents.Distributon.Distributor;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j

public class SendQuantity extends TickerBehaviour {
    Distributor distributor = new Distributor();
    DFhelper dFhelper = new DFhelper();
    Data data;

    AID agent;

    int hour = 1;


    List<AID> agents = new ArrayList<>();

    public SendQuantity(Agent a, long timeout, Data data) {
        super(a, timeout);
        this.data = data;
    }
    @Override
    @SneakyThrows
    public void onTick() {
        log.info(Integer.toString(data.getkWt()));
        ACLMessage msg = new ACLMessage(ACLMessage.PROPOSE);
        String content = (String.valueOf(data.getPower(hour)) + "," + String.valueOf(hour));
        msg.setContent(content);
        msg.setProtocol(getAgent().getLocalName());
        agents = dFhelper.findAgents(getAgent(),"Distribution");
        for(AID agent:agents)
            if (agent.getName().contains(getAgent().getLocalName())){
                msg.addReceiver(agent);
        }

        getAgent().send(msg);
        log.info("Запрос отправлен");
        hour++;
        if (hour == 25)
            hour = 1;
    }
}
