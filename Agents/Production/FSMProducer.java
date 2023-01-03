package Agents.Production;

import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class FSMProducer extends FSMBehaviour {
    public FSMProducer(double ability, double minPrice, double firstPrice, String topicName) {
        String SEND_FIRST_STAVKA = "send first stavka";
        String AUCTION_PRODUCER = "auction producer";
        String WAITING_CONTRACT = "waiting contract";
        String LAST_STATE = "last state";
        String PHARALLEL_WAITING_CONTRACT = " pharallel waiting contract";

        registerFirstState(new SendPrice(getAgent(),5000,topicName, ability, firstPrice,minPrice), SEND_FIRST_STAVKA);
//         registerState(new AuctionProducer(firstBindPower,minPricePower,firstPricePower,topicName), AUCTION_PRODUCER);
        registerState(new AuctionProducer(minPrice,topicName, firstPrice), AUCTION_PRODUCER);

        registerState(new WaitForContract(getAgent()), PHARALLEL_WAITING_CONTRACT);


        registerLastState(new OneShotBehaviour() {
            @SneakyThrows
            @Override
            public void action() {
                log.info("End of hour");
            }
        }, LAST_STATE);

        registerDefaultTransition(SEND_FIRST_STAVKA, AUCTION_PRODUCER);
        registerDefaultTransition(AUCTION_PRODUCER, PHARALLEL_WAITING_CONTRACT);
        registerDefaultTransition(PHARALLEL_WAITING_CONTRACT, LAST_STATE);
    }
}
