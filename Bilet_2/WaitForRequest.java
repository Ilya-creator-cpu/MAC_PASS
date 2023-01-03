package Bilet_2;

import jade.core.Agent;
import jade.core.behaviours.ParallelBehaviour;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WaitForRequest extends ParallelBehaviour {
    public WaitForRequest(Agent a) {
        super(a, WHEN_ANY);
        addSubBehaviour(new Agree());
        addSubBehaviour(new Deny());
        addSubBehaviour(new Ignore());



    }
}
