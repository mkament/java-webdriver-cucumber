package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import java.util.*;


public class JavaStepDefs {

    @And("I say {string}")
    public void iSay(String message) {
        System.out.println(message);
    }

    @And("I perform actions with {string} and {string}")
    public void iPerformActionsWithAnd(String str1, String str2) {
        System.out.println("str1: " + str1);
        System.out.println("str2: " + str2);
        System.out.println("str1 upp: " + str1.toUpperCase());
        System.out.println("str2 upp: " + str2.toUpperCase());
        System.out.println("str1 len: " + str1.length());
        System.out.println("str2 len: " + str2.length());
//        System.out.println("str1 == str2 ?: " + (str1 == str2));
        System.out.println("str1.equals(str2) ?: " + str1.equals(str2));
        System.out.println("str1.ignore case equals(str2) ?: " + str1.equalsIgnoreCase(str2));
        System.out.println("str1 contains str2?: " + str1.contains(str2));

        if (str1.equals(str2)) {
            // gets inside if true
            System.out.println("Strings equal each other!");
        } else {
            // gets inside if false
            System.out.println("Strings do not equal each other!");
        }
    }

    @Given("I say hello world")
    public void iSayHelloWorld() {
        String message = "Hello World!";

        System.out.println(message);
        System.out.println("Hey there! " + message);
        System.out.println(message.toUpperCase());
        System.out.println(message);

        message = message.toUpperCase();
        System.out.println(message);

        String result = String.format("My message: %s ", message);
        System.out.println(result);
    }

    @And("I calculate numbers")
    public void iCalculateNumbers() {
        System.out.println(10 / 3);
        System.out.println(21 % 3);

        int i = 5;
        Integer index = 5;

        double d = 5.0;
        Double doubleValue = 5.0;

        System.out.println(index == d);
        // below 2 are the same
        System.out.println(index != d);
        System.out.println(!(index == d));

        if (i > d) {
            System.out.println("i more than d!");
        } else if (i == d) {
            System.out.println("i equals d!");
        } else {
            System.out.println("i less than d!");
        }
    }

    @And("I work with arrays")
    public void iWorkWithArrays() {
        int[] nums = {5, 2, 5, 7, 10, 11, 1};
        nums[0] = 7;
        nums[6] = 17;
        System.out.println(nums);
        for (int i : nums) {
            System.out.print(i + " ");
        }

        System.out.println("First out of nums: " + nums[0]);

        System.out.println();
        String[] fruits = {"kiwi", "apple", "orange"};
        for (String fruit : fruits) {
            System.out.print(fruit + " ");
        }
        System.out.println();
        for (int i = 0; i < fruits.length; i++) {
            System.out.print(fruits[i] + " ");
        }
        System.out.println("\n\nFirst out of fruits: " + fruits[0]);

        String str1 = new String("value");
        Integer int1 = new Integer(5);

        List<Integer> listOfNums = new ArrayList<>();
        listOfNums.add(5);
        listOfNums.add(2);
        listOfNums.add(3);
        for (int i : listOfNums) {
            System.out.println(i);
        }
        for (int i = 0; i < listOfNums.size(); i++) {
            System.out.println(listOfNums.get(i));
        }

        List<String> listOfFruits = new ArrayList<>();
        listOfFruits.add("kiwi");
        listOfFruits.add("apple");
        listOfFruits.add("orange");
        for (String fruit : listOfFruits) {
            System.out.print(fruit + " ");
        }
    }

    @And("I print {int} day of week")
    public void iPrintDayOfWeek(int day) {
        String[] daysOfWeek = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        System.out.println(daysOfWeek[day - 1]);
    }

    @And("I print every {int} day of week")
    public void iPrintEveryRdDayOfWeek(int every) {
        final String[] daysOfWeek = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

        int i = 1;
        for (String day : daysOfWeek) {
            if (i % every == 0) {
                System.out.println(day);
            }
            i++;
        }

        System.out.println("----------");


        for (int j = 1; j <= daysOfWeek.length ; j++) {
            if (j % every == 0) {
                System.out.println(daysOfWeek[j-1]);
            }
        }

        System.out.println("----------");

        for (int k = every; k <= daysOfWeek.length ; k += every) {
            System.out.println(daysOfWeek[k-1]);
        }
        System.out.println("----------");

        for (int m = 0; m < daysOfWeek.length; m++) {
            System.out.println(daysOfWeek[m]);
        }
    }

    @And("I work with maps")
    public void iWorkWithMaps() {
        Map<String, String> user = new HashMap<>();
        user.put("username", "jdoe");
        user.put("email", "john@doe.example.com");
        user.put("password", "jdoe");
        for(String key : user.keySet()) {
            System.out.println(key + ": " + user.get(key));
        }

        Map<String, String> admin = new HashMap<>();
        admin.put("username", "admin");
        admin.put("email", "admin@admin.example.com");
        admin.put("password", "adminPass");


        for(String key : admin.keySet()) {
            System.out.println(key + ": " + admin.get(key));
        }


    }

    @And("I switch key and value in a map")
    public void iSwitchKeyAndValueInAMap() {
            Map<String, String> info = new LinkedHashMap<>();
            info.put("firstName", "John");
            info.put("middleName", "George");
            String temp = info.get("firstName");
            System.out.println(temp);
            info.put("firstName", info.get("middleName"));
            info.put("middleName", temp);
            System.out.println(info.toString());
            //Swap values of firstName and middleName in a Map, you  have map {firstName=George, middleName=John}
            //To get value from map, use info.get("key"), to set value in a map, use info.put("key", "value");
    }
}