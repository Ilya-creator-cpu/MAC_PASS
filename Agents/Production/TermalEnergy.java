package Agents.Production;

public class TermalEnergy {
    private static double energy;

    public synchronized static double getTermalEnergy() {
        return energy;
    }

    public synchronized static void setTermalEnergy(Double value) {
        energy = value;
    }
}
