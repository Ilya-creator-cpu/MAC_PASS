package Agents.Distributon;

import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;

public class FSMDistributor extends FSMBehaviour {
    public static final String OTPRAVKA_PRODUCERAM = "send request for power to producers";  //отправляет имя топика и инфу че надо
    public static final String RECEIVE_ANSWERS = "waiting confirmation from consumer"; //ПРИЕМ СТАВОК
    public static final String PHARALLEL_RECEIVE_ANSWERS = "pharallel receive answers";
    public static final String ZAGLUSHKA2 = "zaglushka2";
    public static final String LAST_STATEE = "last statee";
    public static final String PHARALLEL_CONTRACTS = "pharallel contracts";
    public static final String DIVISION = "division";
    public static final String DIVISION2 = "division2";
    public static final String DIVISION3 = "division3";
    private Map<String, String> map = new HashMap<>();
@SneakyThrows
    public FSMDistributor(String whichAgent, String neededPower, String whichHour, String maxPrice, Map<String, String> futureMap) {

        this.map = futureMap;

        registerFirstState(new SendProducers(whichAgent, neededPower, maxPrice, whichHour),OTPRAVKA_PRODUCERAM); //отправка запрсоа мощности производителям

        registerState(new PharallelReceiveAnswers(getAgent(), map), PHARALLEL_RECEIVE_ANSWERS);

        registerState(new PharallelContracts(getAgent(), map,neededPower), PHARALLEL_CONTRACTS);

        registerState(new DivisionFirst(neededPower, whichAgent), DIVISION);
        registerState(new DivisionSecond(neededPower), DIVISION2);
        registerState(new DivisionThird(whichAgent), DIVISION3);


        registerLastState(new OneShotBehaviour() {
            @Override
            public void action() {
            }
        }, LAST_STATEE);

        registerDefaultTransition(OTPRAVKA_PRODUCERAM, PHARALLEL_RECEIVE_ANSWERS);
        registerDefaultTransition(PHARALLEL_RECEIVE_ANSWERS, PHARALLEL_CONTRACTS);
        registerDefaultTransition(PHARALLEL_CONTRACTS, DIVISION);

        registerTransition(DIVISION, LAST_STATEE, 1);
        registerTransition(DIVISION, DIVISION2, 0);
        registerDefaultTransition(DIVISION2, DIVISION3);

        registerDefaultTransition(DIVISION3, LAST_STATEE);
    }
}
