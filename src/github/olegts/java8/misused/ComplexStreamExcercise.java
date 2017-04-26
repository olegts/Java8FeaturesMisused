package github.olegts.java8.misused;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static github.olegts.java8.misused.ComplexStreamExcercise.Tuple.tupleOf;

public class ComplexStreamExcercise {

    public static void main(String[] args) {

        List<User> users = Collections.emptyList();
        Set<String> groups = Collections.emptySet();
        List<String> spaces = Collections.emptyList();

        Map<User, Integer> usersReputation = users.stream()
                //.filter(u -> groups.contains(getGroup(u)));
                //.filter(Test::isMemberOfSpecificGroups)
                .filter(isMemberOfSpecificGroups(groups))
                .flatMap(u -> u.changes.stream().map(change -> tupleOf(u, change)))
                .filter(t -> isReputationChangeApplicable(t.e))
                .collect(Collectors.groupingBy(t -> t.t,
                        Collectors.summingInt(t -> t.e.value)));

        User hero = usersReputation.entrySet().stream()
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .get();
    }

    static boolean isReputationChangeApplicable(ReputationChange change){
        //Check for Date range
        //Check for applicable spaces
        return true;
    }

    static String getGroup(User user){
        return "";
    }

    static boolean isMemberOfSpecificGroups(User user){
        Set<String> groups = Collections.emptySet();
        return groups.contains(user.group);
    }

    static Predicate<User> isMemberOfSpecificGroups(Set<String> groups){
        return u -> groups.contains(u.group);
    }

    static class User{
        String group;
        List<ReputationChange> changes;
    }

    static class ReputationChange{
        Date date;
        List<String> spaces;
        int value;
    }

    static class Tuple<T,E>{

        static <T,E> Tuple<T,E> tupleOf(T t, E e){
            return new Tuple<>(t, e);
        }

        public Tuple(T t, E e) {
            this.t = t;
            this.e = e;
        }

        T t;
        E e;
    }
}
