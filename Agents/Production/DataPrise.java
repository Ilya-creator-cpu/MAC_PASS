package Agents.Production;

import Agents.Data;
import jade.core.AID;

import java.util.HashMap;

public class DataPrise {

    private HashMap<AID,Integer> prices = new HashMap<>();

    private int price;

    public DataPrise(int price) {
        this.price = price;
    }
    public void setPrices(AID agent, int price) {
        prices.put(agent,price);

    }

    public Integer getPrices(AID agent) {
        return prices.get(agent);
    }
}
