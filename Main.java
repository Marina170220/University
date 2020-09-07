import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws MarkValueExceptions, NullAmountException {

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

        try {
            Group group11 = new Group(Group.fillGroup((new File("F:\\University\\F1\\Gr11")), group11Students), "Group11");
            Group group12 = new Group(Group.fillGroup((new File("F:\\University\\F1\\Gr12")), group12Students), "Group12");
            Group group13 = new Group(Group.fillGroup((new File("F:\\University\\F1\\Gr13")), group13Students), "Group13");

            Group group21 = new Group(Group.fillGroup((new File("F:\\University\\F2\\Gr21")), group21Students), "Group21");
            Group group22 = new Group(Group.fillGroup((new File("F:\\University\\F2\\Gr22")), group22Students), "Group22");
            Group group23 = new Group(Group.fillGroup((new File("F:\\University\\F2\\Gr23")), group23Students), "Group23");
            Group group24 = new Group(Group.fillGroup((new File("F:\\University\\F2\\Gr24")), group24Students), "Group24");

            Group group31 = new Group(Group.fillGroup((new File("F:\\University\\F3\\Gr31")), group31Students), "Group31");
            Group group32 = new Group(Group.fillGroup((new File("F:\\University\\F3\\Gr32")), group32Students), "Group32");
            Group group33 = new Group(Group.fillGroup((new File("F:\\University\\F3\\Gr33")), group33Students), "Group33");
            Group group34 = new Group(Group.fillGroup((new File("F:\\University\\F3\\Gr34")), group34Students), "Group34");
            Group group35 = new Group(Group.fillGroup((new File("F:\\University\\F3\\Gr35")), group35Students), "Group35");


            List<Group> faculty1Groups = new ArrayList<>();
            List<Group> faculty2Groups = new ArrayList<>();
            List<Group> faculty3Groups = new ArrayList<>();

            List<Faculty> university = new ArrayList<>();

            faculty1Groups.add(group11);
            faculty1Groups.add(group12);
            faculty1Groups.add(group13);

            faculty2Groups.add(group21);
            faculty2Groups.add(group22);
            faculty2Groups.add(group23);
            faculty2Groups.add(group24);

            faculty3Groups.add(group31);
            faculty3Groups.add(group32);
            faculty3Groups.add(group33);
            faculty3Groups.add(group34);
            faculty3Groups.add(group35);

            Faculty faculty1 = new Faculty(faculty1Groups, "Faculty1");
            Faculty faculty2 = new Faculty(faculty2Groups, "Faculty2");
            Faculty faculty3 = new Faculty(faculty3Groups, "Faculty3");

            university.add(faculty1);
            university.add(faculty2);
            university.add(faculty3);

            group11.studentAverageMark(group11Students, "1101");
            group12.averageMarkOnSubjectInGroup(group12Students, "Math");
            faculty1.averageMarkOnSubjectOnFaculty(faculty1Groups, "History");
            University.averageMarkOnSubjectInUniversity(university, "History");
        } catch (MarkValueExceptions ex) {
            throw new MarkValueExceptions(ex.getMessage(), ex);

        } catch (NullAmountException e) {
            throw new NullAmountException(e.getMessage(), e);
        }
    }
}


