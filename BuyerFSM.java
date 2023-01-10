package Bilet_1.Behaviours;

import Bilet_1.Agents.Book;
import jade.core.behaviours.FSMBehaviour;

public class BuyerFSM extends FSMBehaviour {

    private Book book;

    public BuyerFSM( String book, int numberOfAgents) {
       registerFirstState(new BuyerRequest(book),"SendAFirstRequest");
       registerLastState(new BuyerReceive(book,numberOfAgents),"WaitingForRequest");

       registerDefaultTransition("SendAFirstRequest","WaitingForRequest");
    }
}
