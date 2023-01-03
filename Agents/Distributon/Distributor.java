package Agents.Distributon;

import Agents.DFhelper;
import jade.core.Agent;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class Distributor extends Agent {
    DFhelper dFhelper = new DFhelper();
    private int hour;
    @SneakyThrows
    @Override
    public void setup() {
        registration();
        Thread.sleep(1000);
        addBehaviour(new TakeQuest());

    }
    public void registration(){
        dFhelper.registerAgent(this,"Distribution");
    }

}
