package Agents.Production;

public class SunEnergy {
    private static double energy;

    public synchronized static double getSunEnergy() {
        return energy;
    }

    public synchronized static void setSunEnergy(Double value) {
        energy = value;
    }
}
