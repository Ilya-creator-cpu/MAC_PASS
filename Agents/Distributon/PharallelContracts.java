package Agents.Distributon;

import jade.core.Agent;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.WakerBehaviour;

import java.util.Map;
import java.util.TreeMap;

public class PharallelContracts extends ParallelBehaviour {

    String neededPower;

    Map<String,String> producerMap2 = new TreeMap<>();

    public PharallelContracts(Agent a, Map<String,String> phMap, String neededPower ) {

        super(a,WHEN_ANY);
        this.producerMap2 = phMap;
        this.neededPower = neededPower;

        addSubBehaviour(new Contracts(myAgent,producerMap2,neededPower));
        addSubBehaviour(new WakerBehaviour(myAgent,15000) {
            @Override
            protected void onWake() {

            }
        });
    }


}
