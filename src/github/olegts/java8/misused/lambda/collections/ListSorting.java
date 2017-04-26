package github.olegts.java8.misused.lambda.collections;

import github.olegts.java8.misused.User;
import github.olegts.java8.misused.Annotations.Good;
import github.olegts.java8.misused.Annotations.Ugly;

import java.util.List;

import static java.util.Comparator.comparing;

public class ListSorting {
    @Ugly
    class UsingCustomComparator {
        public void sortUsersById(List<User> users) {
            users.sort((x, y) -> Long.compare(x.getId(), y.getId()));
        }
    }

    @Good
    class UsingExistingPredefinedComparator {
        public void sortUsersById(List<User> users) {
            users.sort(comparing(User::getId));
        }
    }
}
