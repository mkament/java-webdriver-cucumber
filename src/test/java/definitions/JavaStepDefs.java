package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.logging.log4j.core.util.JsonUtils;

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


        for (int j = 1; j <= daysOfWeek.length; j++) {
            if (j % every == 0) {
                System.out.println(daysOfWeek[j - 1]);
            }
        }

        System.out.println("----------");

        for (int k = every; k <= daysOfWeek.length; k += every) {
            System.out.println(daysOfWeek[k - 1]);
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
        for (String key : user.keySet()) {
            System.out.println(key + ": " + user.get(key));
        }

        Map<String, String> admin = new HashMap<>();
        admin.put("username", "admin");
        admin.put("email", "admin@admin.example.com");
        admin.put("password", "adminPass");


        for (String key : admin.keySet()) {
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

    @And("I code lesson seven assignments")
    public void iCodeLessonSevenAssignments() {
        System.out.println("BELOW IS LESSON 7 HW PART 1:");
        System.out.println(Arrays.toString(switch3rdAnd5thElements(new int[]{5, 2, 9, 7, 3})));
        for (int element : switch3rdAnd5thElements(new int[]{5, 2, 9, 7, 3})) {
            System.out.print(element + ", ");
        }
        for (int i = 30; i > 0; --i) {
            System.out.print("-");
        }
        System.out.println("BELOW IS LESSON 7 HW PART 2:");
        System.out.println(isDivisible(11));
    }

    public String isDivisible(int i) {
        try {
            if (i % 3 == 0 && i % 4 == 0) {
                return "divisible by 3 and 4";
            } else if (i % 3 == 0) {
                return "divisible by 3";
            } else if (i % 4 == 0) {
                return "divisible by 4";
            }
        } catch (RuntimeException e) {
            System.out.println("Exception caught: This code works for Integers only.");
        }
        return "number is not divisible by 3 or 4 without a remainder";
    }

    public int[] switch3rdAnd5thElements(int[] arr) {
        try {
            int temp = arr[4];
            arr[4] = arr[2];
            arr[2] = temp;
            return arr;
        } catch (RuntimeException e) {
            System.out.println("Exception caught: input array of integers with lengh 5 or greater only.");
        }
        return arr;
    }

    @And("I code lesson eight assignments")
    public void iCodeLessonEightAssignments() {
        System.out.print("Assignment 1: ");
        printNumbers0toN(10);
        System.out.print("\nAssignment 2: ");
        printNumberRangeAtoZ(-4, 22);
        System.out.print("\nAssignment 3: ");
        printIntArray(new int[]{2, 19, 37, 88, 99, 2, -7, 999, 33, 16});
        System.out.print("\nAssignment 4: ");
        printEvenIntsArray(new int[]{2, 19, 37, 88, 99, 2, -7, 999, 33, 16});
        System.out.print("\nAssignment 5: ");
        System.out.println(isArrayEmpty(new int[]{2, 19, 37, 88, 99, 2, -7, 999, 33, 16}));
        System.out.print("\nAssignment 6: ");
        System.out.println(iCheckIfArrayHasIntElement(new int[]{2, 19, 37, 88, 99, 2, -7, 999, 33, 16}, 2));
        System.out.println(iCheckIfArrayHasStringElement(new String[]{"bamby", "bob", "fork"}, "bomby"));
        System.out.print("\nAssignment 7: ");
        printNumbersWithFizzBuzz(20);
    }


    //        1) Write a function that prints all numbers from 0 up to n
    public void printNumbers0toN(int z) {
        for (int i = 0; i <= z; i++) {
            System.out.print(i);
            if (i != z) System.out.print(", ");
        }
    }

    //        2) Write a function that supports also negative numbers
    public void printNumberRangeAtoZ(int a, int z) {
        for (int i = a; i <= z; i++) {
            System.out.print(i);
            if (i != z) System.out.print(", ");
        }
    }

    //        3) Write a function that prints all integer array
    public void printIntArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
            if (i != a.length - 1) System.out.print(", ");
        }
    }

    //        4) Write a function that prints all even numbers from integer array
    public void printEvenIntsArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] % 2 == 0) {
                System.out.print(a[i]);
                if (i != a.length - 1) System.out.print(", ");
            }
        }
    }

    //        5) Write a function that checks if array is empty
    public boolean isArrayEmpty(int[] a) {
        if (a.length == 0) return true;
        else return false;
    }

    //        6) Write a function that checks if array contains another element
    public boolean iCheckIfArrayHasIntElement(int input[], int value) {
        for (int i : input) {
            if (i == value) return true;
        }
        return false;
    }

    public boolean iCheckIfArrayHasStringElement(String[] input, String value) {
        for (String i : input) {
            if (i.equals(value))
                return true;
        }
        return false;
    }

    //        7) Write a function, accepts integer argument
//        It should print all the numbers up to the argument BUT:
//        if number is multiple of 3, it should print Fizz instead of number
//        if number is multiple of 5, it should print Buzz instead of number
//        If it is multiple of both 3 and 5, it should print FizzBuzz instead of number
//        Result for 20:  1 2 Fizz 4 Buzz Fizz 7 8 Fizz Buzz 11 Fizz 13 14 FizzBuzz 16 17 Fizz 19 Buzz
    public void printNumbersWithFizzBuzz(int z) {
        for (int i = 1; i <= z; i++) {
            if (i % 3 == 0 && i % 5 == 0) System.out.print("FizzBuzz ");
            else if (i % 3 == 0) System.out.print(("Fizz "));
            else if (i % 5 == 0) System.out.print(("Buzz "));
            else System.out.print(i + " ");
        }
    }


}








