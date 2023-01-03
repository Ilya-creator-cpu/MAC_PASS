package Agents.Production;

import jade.core.AID;

import java.util.HashMap;

public class EnergyAbility {
    HashMap<AID,Double> energyAbility = new HashMap<>();
    private double ability;
    public EnergyAbility(double ability) {
        this.ability = ability;
    }

    public void setEnergyAbility (AID agent, double ability) {
        energyAbility.put(agent,ability);
    }
    public double getAbility(AID agent) {
        return energyAbility.get(agent);

    }
}
