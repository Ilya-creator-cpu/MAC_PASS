package Agents.Distributon;

import java.util.HashMap;
import java.util.Map;


    public class MyMap {
        static Map<String, String> myMap = new HashMap<>();


        public synchronized static void myMapPut(String  key, String value) {
            myMap.put(key, value);
        }

        public synchronized static String myMapGet(String  key) {
            return myMap.get(key);
        }

        public synchronized static void myMapPut(Map<String, String> contractMap) {
            myMap = contractMap;
        }

        public synchronized static Map<String, String> myMapGet() {
            return myMap;
        }


    }

