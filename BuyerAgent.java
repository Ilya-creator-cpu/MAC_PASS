package Bilet_1.Agents;

import Bilet_1.Behaviours.BuyerFSM;
import Bilet_1.Behaviours.BuyerRequest;
import Bilet_1.Heplers.DFhelper;
import jade.core.AID;
import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;

@Slf4j
public class BuyerAgent extends Agent {

    private String neededBook;

    private List<AID> agents;
    private int bookNum = new Random().nextInt(5);
    private String book;

    @Override
    protected void setup() {
        log.info("Book number {}", bookNum);
        log.info("Buyer woke up");
        DFhelper.DFHelper.registerAgent(this, "Buyer");
        agents = DFhelper.DFHelper.findAgents(this,"Seller");
        switch (bookNum){
            case 0:
                book = "War and peace";
                break;
            case 1:
                book = "Oblomov";
                break;
            case 2:
                book = "Green mile";
                break;
            case 3:
                book = "Idiot";
                break;
            case 4:
                book = "Captain's daughter";
                break;
            case 5:
                book = "Shine";
        }
        log.info("Buyer looking for {}", book);
        addBehaviour(new BuyerFSM(book,agents.size()));
    }
}
