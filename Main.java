import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Student> group11Students = new ArrayList<>();
        List<Student> group12Students = new ArrayList<>();
        List<Student> group13Students = new ArrayList<>();

        List<Student> group21Students = new ArrayList<>();
        List<Student> group22Students = new ArrayList<>();
        List<Student> group23Students = new ArrayList<>();
        List<Student> group24Students = new ArrayList<>();

        List<Student> group31Students = new ArrayList<>();
        List<Student> group32Students = new ArrayList<>();
        List<Student> group33Students = new ArrayList<>();
        List<Student> group34Students = new ArrayList<>();
        List<Student> group35Students = new ArrayList<>();

        Group group11 = new Group("Group11");
        Group group12 = new Group("Group12");
        Group group13 = new Group("Group13");

        Group group21 = new Group("Group21");
        Group group22 = new Group("Group22");
        Group group23 = new Group("Group23");
        Group group24 = new Group("Group24");

        Group group31 = new Group("Group31");
        Group group32 = new Group("Group32");
        Group group33 = new Group("Group33");
        Group group34 = new Group("Group34");
        Group group35 = new Group("Group35");

        List<List<Student>> faculty1Groups = new ArrayList<>();
        List<List<Student>> faculty2Groups = new ArrayList<>();
        List<List<Student>> faculty3Groups = new ArrayList<>();

        List<Faculty> university = new ArrayList<>();

        try {
            faculty1Groups.add(group11.fillGroup(new File("F:\\University\\F1\\Gr11"), group11Students));
            faculty1Groups.add(group12.fillGroup(new File("F:\\University\\F1\\Gr12"), group12Students));
            faculty1Groups.add(group13.fillGroup(new File("F:\\University\\F1\\Gr13"), group13Students));

            faculty2Groups.add(group21.fillGroup(new File("F:\\University\\F2\\Gr21"), group21Students));
            faculty2Groups.add(group22.fillGroup(new File("F:\\University\\F2\\Gr22"), group22Students));
            faculty2Groups.add(group23.fillGroup(new File("F:\\University\\F2\\Gr23"), group23Students));
            faculty2Groups.add(group24.fillGroup(new File("F:\\University\\F2\\Gr24"), group24Students));

            faculty3Groups.add(group31.fillGroup(new File("F:\\University\\F3\\Gr31"), group31Students));
            faculty3Groups.add(group32.fillGroup(new File("F:\\University\\F3\\Gr32"), group32Students));
            faculty3Groups.add(group33.fillGroup(new File("F:\\University\\F3\\Gr33"), group33Students));
            faculty3Groups.add(group34.fillGroup(new File("F:\\University\\F3\\Gr34"), group34Students));
            faculty3Groups.add(group35.fillGroup(new File("F:\\University\\F3\\Gr35"), group35Students));

        } catch (NullAmountException e) {
            System.err.println(e.getMessage());
        }

        Faculty faculty1 = new Faculty(faculty1Groups, "Faculty1");
        Faculty faculty2 = new Faculty(faculty2Groups, "Faculty2");
        Faculty faculty3 = new Faculty(faculty3Groups, "Faculty3");

        university.add(faculty1);
        university.add(faculty2);
        university.add(faculty3);


        try {
            group11.studentAverageMark(group11Students, "1103");
        } catch (NullAmountException e) {
            System.err.println(e.getMessage());
        }
        try {
            group11.averageMarkOnSubjectInGroup(group11Students, "Math");
        } catch (NullAmountException e) {
            System.err.println(e.getMessage());
        }
        try {
            faculty1.averageMarkOnSubjectOnFaculty(faculty1Groups, "History");
        } catch (NullAmountException e) {
            System.err.println(e.getMessage());
        }
        try {
            University.averageMarkOnSubjectInUniversity(university, "History");
        } catch (NullAmountException e) {
            System.err.println(e.getMessage());
        }
    }
}


