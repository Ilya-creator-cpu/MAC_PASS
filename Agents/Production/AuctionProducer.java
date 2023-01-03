package Agents.Production;

import Agents.TopicHelper;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class AuctionProducer extends Behaviour {
    private Double firstBindPower;
    private Double minPricePower3;
    private Double firstPricePower3;
    private String topicName = "energy";

    private boolean finish = false;
    public AuctionProducer(Double minPricePower3, String topicName, Double firstPricePower3) {
        this.minPricePower3 = minPricePower3;
        this.topicName = topicName;
        this.firstPricePower3 = firstPricePower3;
    }



    @Override
    public void action() {
        log.info("Аукцион начался");

        MessageTemplate mt = MessageTemplate.or(
                MessageTemplate.MatchPerformative(ACLMessage.PROPAGATE),
                MessageTemplate.MatchPerformative(ACLMessage.CANCEL));
        ACLMessage receive = getAgent().receive(mt);
        if(firstPricePower3 > 10000){
            finish = true;
        }

        if (receive != null) {

            if (receive.getPerformative() == ACLMessage.PROPAGATE && !receive.getSender().getLocalName().equals(getAgent().getLocalName())) {
                String[] opponentsMessage = receive.getContent().split(",");
                log.info("Агент " + getAgent().getLocalName() + " с ценой " + firstPricePower3 + " получил " + receive.getContent());
                double opponentPrice = Double.parseDouble(opponentsMessage[1]);
               // log.info(getAgent().getLocalName() + " моя цена " + firstPricePower3 + " моя мин цена " + minPricePower3 + " цена опонента " + opponentPrice);

                if (opponentPrice < firstPricePower3) {
                    //log.info(getAgent().getLocalName() + "готов снизить цену ");
                    //log.info("Старая цена " + firstPricePower3);
                    if(opponentPrice > minPricePower3){
                        firstPricePower3 = opponentPrice - 3;
                        //  log.info("Новая цена " + firstPricePower3);

                        ACLMessage msg = new ACLMessage(ACLMessage.PROPAGATE);
                        msg.setContent(getAgent().getLocalName() + "," + firstPricePower3);
                        msg.addReceiver(TopicHelper.createTopic(getAgent(), topicName));
                        getAgent().send(msg);
                    }
                    if(opponentPrice < minPricePower3){
                        firstPricePower3 = minPricePower3;
                        //log.info("Новая цена " + firstPricePower3);

                        ACLMessage msg = new ACLMessage(ACLMessage.PROPAGATE);
                        msg.setContent(getAgent().getLocalName() + "," + firstPricePower3);
                        msg.addReceiver(TopicHelper.createTopic(getAgent(), topicName));
                        getAgent().send(msg);
                    }



                }
            } else if (receive.getPerformative() == ACLMessage.CANCEL) {
               // log.info("Удален из топика");
                finish = true;

            }
        }





    }

    @Override
    public boolean done() {
        return finish;
    }
}
