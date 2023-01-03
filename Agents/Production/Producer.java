package Agents.Production;

import Agents.CfgClass;
import Agents.DFhelper;
import Agents.Data;
import Agents.Production.DataPrise;
import Agents.Production.EnergyAbility;
import Agents.Production.GeneratePower;
import jade.core.Agent;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Producer extends Agent {

    Data data;

    private int price;

    private double ability;

    CfgClass cfg;

    DFhelper dFhelper = new DFhelper();
    @SneakyThrows
    protected void setup() {
        log.info(getLocalName() + " Producer");
        registration();
        if (getLocalName().equals("TermoStation")) {
        price = 120;
        ability = 7.9;}
        else if (getLocalName().equals("SunStation")) {
            price = 1000;
        ability = 4.3;}
        DataPrise dp = new DataPrise(price);
        dp.setPrices(this.getAID(),price);
        EnergyAbility ea = new EnergyAbility(ability);
        ea.setEnergyAbility(this.getAID(),ability);
        addBehaviour(new GeneratePower());
    }




    public void registration() {
        dFhelper.registerAgent(this,"Production");

    }
}
