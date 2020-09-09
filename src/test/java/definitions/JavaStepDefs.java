package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.logging.log4j.core.util.JsonUtils;
import pages.*;

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


    @And("I code lesson ten assignments")
    public void iCodeLessonTenAssignments() {
        System.out.println("Assignment A: ");
        System.out.println("User inputs two numbers which are divided by 5. " +
                "If any resulting number is in a range of 1..10 - print it saying it is in the range of 1 to 10." +
                " If any number is in the range of 10..20 - print it saying it's in the range of 10..20.");
        printIfResultingNumbersInRange(40, 60);
        System.out.println("Assignment B: ");
        printAdditionOfTwoNumbers(9,19);
        System.out.println("To test if the function prints correct # I'd cast output to integer and subtract argument one and argument 2 from it.");
        System.out.println("Assignment C: ");
        // What challenge did you encounter in Selenium Automation? How did you solve it?
        // I resolve synchronization issues using explicit wait to make sure I wait for appearance of page elements test scripts interacts with.
        // I use java script and WebDriver's Actions class methods to click on elements I can't click on any other way.
        // I do drag and drop using Actions. I helped a team mate to accomplish coding a drag and drop test case he was stuck with for days.
        // I can switch from window to window to a pop-up window, and can access controls in an iFrame and an iframe inside another iframe.
        // I write reliable selectors. My locators withstand localization.
        // I write css locators for faster test scripts' suite execution.
        // I am able to reach and utilize DOM elements that are not inaccessible to beginners using special manipulations.
        System.out.println("Assignment D: Reverse every third character of a string. (Example: \"WebDriver\" => \"vDW\")");
        // Reverse every third character of a string. (Example: "WebDriver" => "vDW")
        System.out.println(reverseStringPrintEvery3rdChar("WebDriver"));
        System.out.println("Assignment E: ");
        System.out.println(speakLikeYoda("I am Automation Engineer"));


    }

    public String speakLikeYoda(String input) {
        String[] words = input.split(" ");
        String reversed = "";
        for (String item : words){
            reversed = item + " " + reversed;
        }
        return reversed;
    }
    public String reverseStringPrintEvery3rdChar (String input) {
        String temp = "";
        for (int i = input.length()-3; i>=0; i-=3){
            temp +=  input.charAt(i);
        }
        return temp;
    }
    public void printAdditionOfTwoNumbers(int i, int j){
        int num = i +j;
        System.out.println(num);
    }
    public void printIfResultingNumbersInRange (int i, int j){
        int x = i / 5;
        int y = j / 5;
        if ((x>=1&&x<=10)||(y>=1&&y<=10)) System.out.println("Number is in 1..10 range.");
        if ((x>10&&x<=20)||(y>10&&y<=20)) System.out.println("Number is in 10..20 range.");
    }

    @And("I code lesson Eleven assignments")
    public void iCodeLessonAssignments() {
        System.out.println("Assignment: Write a function that counts number of each character in a string");
        printStringCharsAndIndexes("WebDriver");
        System.out.println("Assignment: Write a function that finds if word is palindrome");
        System.out.println(isPalindrome("motto"));
        System.out.println(isPalindrome("refer"));
        System.out.println("Assignment: Write a function that finds if array contains duplicates");
        System.out.println(arrayContainsDuplicate(new int[]{2, 19, 37, 88, 99, 2, 999, -7, 99, 33, 16, 7}));
        System.out.println("Assignment: Write a function that find 2 max numbers in an array");
        System.out.println(findTwoLargestArrayElements(new int[]{2, 19, 37, 88, 99, 2, 999, -7, 99, 33, 16, 7}));
    }
    public void printStringCharsAndIndexes (String s){
        for (int i = 0; i<=s.length()-1; ){
            System.out.print(s.charAt(i) + " " + ++i + "; ");
        }
    }
    public boolean isPalindrome (String s){
        String temp = "";
        for (int i = s.length()-1; i>=0; i--){
            temp +=s.charAt(i);
        }
        if (temp.equals(s)) return true;
        else return false;
    }
    public boolean arrayContainsDuplicate (int[] arr) {
        for (int i= 0; i<arr.length; i++){
            for (int j= i+1; j<arr.length; j++){
                if (arr[i]==arr[j]) return true;
            }
        }
        return false;
    }
    public int [] findTwoLargestArrayElements (int[] nums){
        int [] result = {0,0}; //result[0] is the maximum num, result[1] is the 2nd largest num
        if (nums.length <2 ) {System.out.println("need to throw error as array's too short");}
        for (int i = 0; i<nums.length; i++){
            if (nums[i]>result[0]){
                result[1] = result[0];
                result[0] = nums[i];
            }
            else if (result[1] < nums[i]){
                result[1] = nums[i]; }
        }
        System.out.println(result[0]);
        System.out.println(result[1]);
        return result;
    }

    @Given("I work with classes")
    public void iWorkWithClasses() {
        Animal cat = new Cat("Tom");
        Mutt badBreedDog = new Mutt("Sparky");
        badBreedDog.lookHorrible();
        Animal mouse = new Mouse();
        mouse.setName("Mickey");
        System.out.println(cat.getName());
        cat.walk();
        cat.sleep();
        cat.speak();
        cat.eat("fish");

        Animal dog = new Dog();
        System.out.println(dog.getName());
        dog.walk();
        dog.sleep();
        dog.speak();
        dog.eat("fish");

        List<Animal> list = new ArrayList<>();
        list.add(cat);
        list.add(dog);
        list.add(mouse);
        list.add(badBreedDog);
        printAnimalNames(list);
    }

    public void printAnimalNames(List<Animal> animals) {
        System.out.println("print names method");
        for (Animal animal : animals) {
            animal.speak();
            System.out.println(animal.getName());
        }
    }
}








