package com.ram.leadboard.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class PairTestImp {

    public static void main(String[] args) {
        List<String> myList = new ArrayList<String>();
        myList.add("T1");
        myList.add("T2");

        myList.add("T3");
        myList.add("T4");

        // myList.add("T5"); myList.add("T6");
        // myList.add("T7"); myList.add("T8");

        if (myList.size() == 2) {
            Random random = new Random();
            int n = random.nextInt(2);
            System.out.println("Winner--" + myList.get(n));
        } else if (isListEven(myList)) {
            ArrayList<PairData<String, String>> result = (ArrayList<PairData<String, String>>) getPairData(myList);
            System.out.println("" + result);
            ArrayList<String> tempResult = getResultBySimulate(result);
            System.out.println("" + tempResult);
            if (isListEven(tempResult)) {
                ArrayList<PairData<String, String>> result2 = (ArrayList<PairData<String, String>>) getPairData(tempResult);
                System.out.println("" + result2);
            } else if (tempResult.size() == 2) {
                Random random = new Random();
                int n = random.nextInt(2);
                System.out.println("Winner--" + tempResult.get(n));
            } else {
                System.out.println("NO");
            }
        } else if (myList.size() == 2) {
            Random random = new Random();
            int n = random.nextInt(2);
            System.out.println("Winner--" + myList.get(n));
        } else {
            System.out.println("NO");
        }

    }

    public static List<PairData<String, String>> getPairData() {
        return null;
    }

    public static ArrayList<String> getResultBySimulate(ArrayList<PairData<String, String>> result) {
        ArrayList<String> winnerresult = new ArrayList<String>();

        // System.out.println(""+n);
        for (int i = 0; i < result.size(); i++) {
            Random random = new Random();
            int n = random.nextInt(2);
            PairData<String, String> pair = result.get(i);
            if (n == 0) {
                winnerresult.add(pair.first);
            } else if (n == 1) {
                winnerresult.add(pair.second);
            }

        }
        return winnerresult;
    }

    public static boolean isListEven(List<String> myList) {
        // System.out.println(""+myList.size());
        return powerOf2(myList.size());
    }

    private static boolean getReqursionForEnding(int listSize) {
        if (listSize == 1) {
            return false;
        } else if (listSize == 2) {
            return true;
        } else {
            int reminder = listSize / 2;

            return getReqursionForEnding(reminder);
        }

    }

    private static boolean powerOf2(int listSize) {
        if (listSize == 1)
            return true;
        else if (listSize % 2 != 0 || listSize == 0)
            return false;
        return powerOf2(listSize / 2);
    }

    public static List<PairData<String, String>> getPairData(List<String> array) {
        // TODO Auto-generated method stub
        ArrayList<PairData<String, String>> result = new ArrayList<PairData<String, String>>();
        HashMap<String, Integer> countList = new HashMap<String, Integer>();
        int count = 1;
        Collections.shuffle(array);
        for (int i = 0; i < array.size(); i++) {
            for (int j = 0; j < array.size(); j++) {
                if (array.get(i) != array.get(j)) {
                    // System.out.println("QQQQQQQQQQQQ--" + countList.equals(array.get(i)));
                    if (!countList.containsKey(array.get(i)) && !countList.containsKey(array.get(j))) {
                        countList.put(array.get(i), count);
                        countList.put(array.get(j), count);
                        PairData<String, String> p = PairData.of(array.get(i), array.get(j));
                        result.add(p);
                        count++;
                    }
                }
            }
        }

        return result;
    }

}
