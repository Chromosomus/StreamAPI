

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.function.Predicate;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.*;



public class App {

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
    public static <T> Predicate<T> not(Predicate<T> t) {
        return t.negate();
    }

    public static void main(String[] args) {


        User user1 = new User("Boris", true, new ArrayList<>(List.of("dad", "ds", "da")), new ArrayList<>(List.of("dsa", "dsad")), 212.2,LocalDateTime.of(1998,12,31,4,20,2));
        ArrayList<User> users = new ArrayList<>();
        users.add(user1);
        User user2 = new User("Pietro", false, new ArrayList<>(List.of("dadads", "dsasdf", "dagdfgfd")), new ArrayList<>(List.of("dsdsfa", "dsadfds")), 212.3, LocalDateTime.of(1598,2,5,2,40,2));
        User user3 = new User("Bobby Mcgee", true, new ArrayList<>(List.of("dadafdsfas", "dsdhfgs", "dfsdgdfga")), new ArrayList<>(List.of("dsafsdfdf", "dsfdfdfad")), 103200, LocalDateTime.of(198,12,31,4,20,2));
        users.add(user2);
        users.add(user3);
        User user4 = new User("Boris", false, new ArrayList<>(List.of("dad", "ds", "da")), new ArrayList<>(List.of("dsa", "dsad")), 212.4, LocalDateTime.of(1998,12,1,4,20,2));
        users.add(user4);
        User user5 = new User("Boris", false, new ArrayList<>(List.of("dad", "ds", "da")), new ArrayList<>(List.of("dsa", "dsad")), 212.4, LocalDateTime.of(1998,12,31,4,20,21));
        users.add(user5);

        //Distinct users
        System.out.println("sorted list of distinct users \n");
        System.out.println(users.stream().filter(distinctByKey(User::getName)).sorted(Comparator.comparing(User::getName)).collect(Collectors.toList())+"\n");

        // min , max and average -- in that order
        System.out.println();


        //System.out.println(users.stream().min(Comparator.comparing(User::getBalance)).orElseThrow(NoSuchElementException::new)); //the output contains extra info like name and reg. date (probably because of my toString override), so I decided to use mapToDouble
        System.out.println(users.stream().mapToDouble(User::getBalance).min().orElseThrow(NoSuchElementException::new));


        //System.out.println(users.stream().max(Comparator.comparing(User::getBalance)).orElseThrow(NoSuchElementException::new)); // same here
        System.out.println(users.stream().mapToDouble(User::getBalance).max().orElseThrow(NoSuchElementException::new));

        System.out.println(users.stream().mapToDouble(User::getBalance).average().orElseThrow(NoSuchElementException::new));


        //get a list of emails
        System.out.println();
        System.out.println(users.stream().map(User::getEmails).collect(Collectors.toList()));

        //first balance
        System.out.println();
        System.out.println(users.stream().map(User::getBalance).filter(x -> x>=10000).findFirst().orElseThrow(NoSuchElementException::new));


        //inactive users
        System.out.println("Amount of inactive users:");
        System.out.println(users.stream().filter(not(User::isActive)).count());


        //get set
        System.out.println();
        System.out.println(users.stream().map(User::getBalance).collect(Collectors.toSet()));

        //sorted by reg.date
        System.out.println();
        System.out.println(users.stream().sorted(Comparator.comparing(User::getRegistrationDate)).collect(Collectors.toList()));


        //comma separated string
        System.out.println();
        System.out.println(users.stream().map(User::getName).collect(Collectors.joining(",")));

        //partitioning
        System.out.println();
        Map<Boolean, List<User>> partitionedUsers = users.stream().collect(Collectors.partitioningBy(User::isActive));
        partitionedUsers.forEach((Boolean k, List<User> u) -> System.out.println(k + " " + u));

        //grouping by roles
        /*System.out.println();
        Map<Role,List<User>> userRoles = users.stream().collect(Collectors.groupingBy(User::getRoles));*/
    }

}