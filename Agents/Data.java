package Agents;

import jade.core.AID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Data {

    public Data (long hour, int kWt) {
        this.hour = hour;
        this.kWt = kWt;
    }
    private AID topic;
    private long hour;
    private AgentPrice agentPrice;

    private AID winner;
    private int winPrice;
    private int kWt;
    private List<AgentPrice> agentPrices = new ArrayList<>();

    private  HashMap<AID,Integer> agentsprice = new HashMap<>();

    public AID getWinner() {
        return winner;

    }


    public void setagentsPrice(AID agent, int price) {
        agentsprice.put(agent,price);

    }

    public HashMap<AID,Integer> getAgentsprice() {
        return agentsprice;
    }

    public void setWinPrice(int winPrice) {
        this.winPrice = winPrice;
    }
    public int getWinPrice() {
        return winPrice;

    }

    public void setWinner(AID winner) {
        this.winner = winner;
    }

    public void setTopic(AID topic) {
        this.topic = topic;
    }
    public AID getTopic() {
        return topic;
    }

    public long getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setkWt(int kWt) {this.kWt = kWt;}

    public HashMap<Integer,Integer> power = new HashMap<>();

    public void setPower(int hour, int energy) {
        power.put(hour,energy);
    }
    public double getPower(int hour){
        return power.get(hour);
    }

    public int getkWt() {return kWt;}

    public void setAgentPrices() {
        agentPrices.add(agentPrice);
    }

    public List<AgentPrice> getAgentPrices() {
        return agentPrices;
    }

    public void setAgentPrices(List<AgentPrice> agentPrices) {
        this.agentPrices = agentPrices;
    }
}
