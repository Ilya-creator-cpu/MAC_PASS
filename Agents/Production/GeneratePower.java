package Agents.Production;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;
import java.util.Random;
@Slf4j
public class GeneratePower extends Behaviour {

    private double energy;

    private double minPrice,firstPrice;

    private Random random = new Random();

    private double B1 = 10.3;

    private double B2 = 8.6;



    @Override
    public void action() {

        MessageTemplate mt = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.INFORM),
                MessageTemplate.MatchProtocol("Запрашиваю энергию"));
        ACLMessage receive = getAgent().receive(mt);



        if (receive != null) {
            String [] content = receive.getContent().split(",");
            log.info("Поступил запрос на продажу энергии от " + receive.getSender().getLocalName());
            switch (getAgent().getLocalName()) {

                case "TermoStation":

                energy = 7.9;

                minPrice = 5.5;

                firstPrice = 11;

                TermalEnergy.setTermalEnergy(energy);
                break;
             case "SunStation":

                energy = Math.abs(random.nextGaussian() * B1 * B2) * 100;

                minPrice = energy / 100;
                firstPrice = minPrice * 2;
                SunEnergy.setSunEnergy(energy);
                break;
                case  "WindStation":
                double C0 = -77.405;
                double C1 = 19.907;
                double C2 = -1.292;
                double C3 = 0.024;
                log.info(content[2] + " Час в котором вырабатывается энергия");
                if (5 < Double.parseDouble(content[2]) && Double.parseDouble(content[2]) < 19) {
                    energy = C0 + C1 * Double.parseDouble(content[2]) + C2 * Double.parseDouble(content[2]) * Double.parseDouble(content[2])
                            + C3 * Double.parseDouble(content[2]) * Double.parseDouble(content[2]) * Double.parseDouble(content[2]);
                    minPrice = 1000 / energy;
                    firstPrice = minPrice * 2;

                } else {
                    energy = 1;
                    minPrice = 1000 / energy;
                    firstPrice = minPrice * 2;

                }
                WindEnergy.setWindEnergy(energy);
                break;
            }
            String topicName = content[3];
            getAgent().addBehaviour(new FSMProducer(energy,minPrice,firstPrice,topicName));
            receive = null;
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
