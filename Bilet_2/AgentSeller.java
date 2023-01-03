package Bilet_2;

import Agents.DFhelper;
import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class AgentSeller extends Agent {
    DFhelper dFhelper = new DFhelper();

    protected void setup() {
        registration();
        addBehaviour(new SellerBehaviour());
    }
    public void registration() {
        dFhelper.registerAgent(this,"Seller");
        log.info("Seller was registered");
    }

}
