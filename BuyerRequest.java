package Bilet_1.Behaviours;

import Bilet_1.Heplers.DFhelper;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
@Slf4j
public class BuyerRequest extends Behaviour {
    private List<AID> agents;
    //    private int bookNum = (int) Math.random() * 4;
    private String book;
    private boolean sendRequest;


    public  BuyerRequest (String book){
        this.book = book;
    }

    @Override
    public void action() {
        agents =  DFhelper.DFHelper.findAgents(getAgent(),"Seller");
        log.info("Found {} sellers", agents.size());
        ACLMessage request = new ACLMessage( ACLMessage.REQUEST);
        for (AID agent: agents){
            request.addReceiver(agent);
        }
//        switch (bookNum){
//            case 0:
//                book = "War and peace";
//                break;
//            case 1:
//                book = "Oblomov";
//                break;
//            case 2:
//                book = "Green mile";
//                break;
//            case 3:
//                book = "Idiot";
//                break;
//            case 4:
//                book = "Captain's daughter";
//        }
        request.setContent(book);
        getAgent().send(request);
        sendRequest = true;
        log.info("Buyer send his request for book [{}]", book);

    }

    @Override
    public boolean done() {
        return sendRequest;
    }
}
