package Agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.messaging.TopicManagementHelper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class TopicHelper {
    @SneakyThrows
    public static AID createTopic(Agent agent, String TopicName) {
         TopicManagementHelper topicHelper = null;
         AID jadeTopic = null;

         topicHelper = (TopicManagementHelper) agent.getHelper(TopicManagementHelper.SERVICE_NAME);
         jadeTopic = topicHelper.createTopic(TopicName);
         topicHelper.register(jadeTopic);
         return jadeTopic;
    }
}
