import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ComparatorTest {
    public static void main(String[] args) {
        List<Student> students = buildStudent();
        List<Student> address = buildStudent();
        // sorted by student address
        Comparator<Address> byAddr = Comparator.comparing(Address::getAddress);
        Comparator<Student> byAddress = Comparator.comparing(Student::getAddress, byAddr);
        address = address.stream().sorted(byAddress).collect(Collectors.toList());
        // sorted by student id
        students = students.stream().sorted(Comparator.comparing(Student::getName, Comparator.naturalOrder())).collect(Collectors.toList());
        System.out.println(students);
        System.out.println(address);
    }

    private static List<Student> buildStudent() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, 20, "tony", new Address("A")));
        students.add(new Student(31, 16, "calvin", new Address("B")));
        students.add(new Student(37, 19, "david", new Address("C")));
        students.add(new Student(4, 18, "marry", new Address("D")));
        return students;
    }
}


