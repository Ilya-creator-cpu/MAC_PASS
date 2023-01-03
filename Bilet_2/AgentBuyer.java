package Bilet_2;

import Agents.DFhelper;
import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class AgentBuyer extends Agent {

    DFhelper dFhelper = new DFhelper();

    protected void setup() {
        registration();
        addBehaviour(new BuyerBehaviour(this,10000));
        addBehaviour(new WaitForRequest(this));
    }

    public void registration() {
        dFhelper.registerAgent(this,"Buyer");

    }
}
