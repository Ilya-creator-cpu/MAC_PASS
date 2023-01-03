package Agents.Production;

public class WindEnergy {
    private static double energy;

    public synchronized static double getWindEnergy() {
        return energy;
    }

    public synchronized static void setWindEnergy(Double value) {
        energy = value;
    }
}
