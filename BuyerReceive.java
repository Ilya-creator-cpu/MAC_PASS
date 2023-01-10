package Bilet_1.Behaviours;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
public class BuyerReceive extends Behaviour {
    private String book;
    private List<ACLMessage> answers = new ArrayList<>();

    private int minprice;

    protected HashMap<AID,Integer> agentprices = new HashMap<>();

    public List<Integer> prices = new ArrayList<>();
    private int receiversCount;

    public BuyerReceive( String book, int receiversCount) {
        this.book = book;

    }


    @Override
    public void action() {
        MessageTemplate mt = MessageTemplate.or(MessageTemplate.MatchPerformative(ACLMessage.AGREE), MessageTemplate.MatchPerformative(ACLMessage.FAILURE));
        ACLMessage receive = getAgent().receive(mt);
        if (receive != null){
          //  log.info("Message was received" + receiversCount);
            receiversCount++;
            if (Integer.parseInt(receive.getContent()) != 0) {
                prices.add(Integer.parseInt(receive.getContent()));
                //log.info("Агент " + receive.getSender().getLocalName() + "предлагает цену " + receive.getContent());
               // agentprices.put(receive.getSender(),Integer.parseInt(receive.getContent()));
                log.info(prices.toString());


                }
            if (receiversCount == 2) {
                minprice = prices.get(0);
                for (Integer price:prices) {
                    if (price<minprice)
                        minprice = price;
                }
                log.info("Итоговая цена " +minprice);






            }
        }
//        if (answers != null){
//            new ChooseWinner(answers);
//        }else {
//            log.warn("Buyer has no proposal");}

        }
 @Override
    public boolean done() {
        return false;
 }


}
