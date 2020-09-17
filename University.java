//В университете есть несколько факультетов, в которых учатся студенты, объединенные в группы. У каждого студента есть несколько
//учебных предметов по которым он получает оценки. Необходимо реализовать иерархию студентов, групп и факультетов.​
//
//Посчитать средний балл по всем предметам студента;​
//
//Посчитать средний балл по конкретному предмету в конкретной группе и на конкретном факультете;​
//
//Посчитать средний балл по предмету для всего университета.​
//
//Реализовать следующие исключения:​
//
//Оценка ниже 0 или выше 10;​
//
//Отсутствие предметов у студента (должен быть хотя бы один);​
//
//Отсутствие студентов в группе;​
//
//Отсутствие групп на факультете;​
//
//Отсутствие факультетов в университете.​

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class University {

    public static void averageMarkOnSubjectInUniversity(List<Faculty> university, String subject) throws NullAmountException, MarkValueExceptions {
        double averageMarkOnSubject;
        double sum = 0.0;
        int amountOfMarks = 0;
        if (university.isEmpty()) {
            throw new NullAmountException("The University has no faculties");
        } else {
            for (Faculty faculty : university) {
                if (faculty.faculty.isEmpty()) {
                    throw new NullAmountException("Faculty " + faculty.facultyName + " has no groups");
                }

                for (int i = 0; i < faculty.getFaculty().size(); i++) {
                    for (int j = 0; j < faculty.getFaculty().get(i).group.size(); j++) {
                        if (faculty.getFaculty().get(i).group.get(j).getMarksAndSubjects().isEmpty()) {
                            throw new NullAmountException("Student " + faculty.getFaculty().get(i).group.get(j).getId() + " has no subjects");
                        }
                        if (faculty.getFaculty().get(i).group.get(j).getMarksAndSubjects().values().iterator().next() < 0.0 || faculty.getFaculty().get(i).group.get(j).getMarksAndSubjects().values().iterator().next() > 10.0) {
                            throw new MarkValueExceptions("Student average mark error.\nCheck student's " + faculty.getFaculty().get(i).group.get(j).getId() + " marks");
                        }

                        if (faculty.getFaculty().get(i).group.get(j).getMarksAndSubjects().containsKey(subject)) {
                            sum += faculty.getFaculty().get(i).group.get(j).getMarksAndSubjects().get(subject);
                            amountOfMarks++;
                        }
                    }
                }
            }

            if (amountOfMarks > 0) {
                averageMarkOnSubject = sum / amountOfMarks;
                System.out.println("\nAverage mark in University on " + subject + ": " + averageMarkOnSubject);
            } else {
                System.out.println("\nThere is no subject " + subject + " in the University");
            }
        }
    }
}


class Faculty extends University {
    String facultyName;
    List<Group> faculty;

    Faculty(List<Group> faculty, String facultyName) {
        this.faculty = faculty;
        this.facultyName = facultyName;
    }

    public List<Group> getFaculty() {
        return faculty;
    }

    public double averageMarkOnSubjectOnFaculty(List<Group> faculty, String subject) throws NullAmountException, MarkValueExceptions {
        double averageMarkOnSubject = 0.0;
        double sum = 0.0;
        int amountOfMarks = 0;
        if (faculty.isEmpty()) {
            throw new NullAmountException("Faculty " + facultyName + " has no groups");
        } else {
            for (Group group : faculty) {
                if (group.group.isEmpty()) {
                    throw new NullAmountException("Faculty " + facultyName + " contains empty group " + group.groupName);
                }
                for (int i = 0; i < group.group.size(); i++) {
                    if (group.group.get(i).getMarksAndSubjects().isEmpty()) {
                        throw new NullAmountException("Student " + group.group.get(i).getId() + " has no subjects");
                    } else {
                        if (group.group.get(i).getMarksAndSubjects().values().iterator().next() < 0.0 || group.group.get(i).getMarksAndSubjects().values().iterator().next() > 10.0) {
                            throw new MarkValueExceptions("Student average mark error.\nCheck student's " + group.group.get(i).getId() + " marks");
                        }

                        if (group.group.get(i).getMarksAndSubjects().containsKey(subject)) {
                            sum += group.group.get(i).getMarksAndSubjects().get(subject);
                            amountOfMarks++;
                        }
                    }
                }
            }

            if (amountOfMarks > 0) {
                averageMarkOnSubject = sum / amountOfMarks;
                System.out.println("\nAverage mark on " + subject + " on faculty " + facultyName + ": " + averageMarkOnSubject);
            } else {
                System.out.println("\nThere is no subject " + subject + " on faculty " + facultyName);
            }
            return averageMarkOnSubject;
        }
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "faculty=" + faculty +
                '}';
    }
}

class Group extends University {

    String groupName;
    List<Student> group;

    Group(List<Student> group, String groupName) {
        this.group = group;
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "Group{" +
                "group=" + group +
                '}';
    }


    public static List<Student> fillGroup(File groupDirectory, List<Student> group) throws NullAmountException, MarkValueExceptions {
        Scanner scanner = null;

        if (groupDirectory.isDirectory()) {
            for (File studentFile : groupDirectory.listFiles()) {
                try {
                    scanner = new Scanner(studentFile);

                    Map<String, Double> marksAndSubjects = new HashMap<>();
                    while (scanner.hasNext()) {

                        marksAndSubjects.put(scanner.next(), scanner.nextDouble());
                    }
                    if (marksAndSubjects.isEmpty()) {
                        throw new NullAmountException("Student " + studentFile.getName().substring(0, 4) + " has no subjects");
                    }
                    for (Double mark : marksAndSubjects.values()) {
                        if (mark < 0.0 || mark > 10.0) {
                            throw new MarkValueExceptions("Invalid mark value in directory " + groupDirectory.getName());
                        }
                    }
                    group.add(new Student(studentFile.getName().substring(0, 4), marksAndSubjects));

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    scanner.close();
                }
            }
        }

        if (group.isEmpty()) {
            throw new NullAmountException("Group " + groupDirectory.getName() + " is empty");
        }
        System.out.println("Group " + groupDirectory.getName() + ": " + group);
        return group;
    }

    public void studentAverageMark(List<Student> group, String id) throws NullAmountException, MarkValueExceptions {
        double averageMark = 0.0;
        int amountOfMarks = 0;
        Student findStudent = null;
        if (group.isEmpty()) {
            throw new NullAmountException("Group " + groupName + " is empty");
        }
        try {
            for (Student student : group) {
                if (student.getMarksAndSubjects().isEmpty()) {
                    throw new NullAmountException("Student " + student.getId() + " has no subjects");
                }
                if (student.getId().contains(id)) {
                    findStudent = student;
                    averageMark = findStudent.averageMark(findStudent.getMarksAndSubjects());
                    amountOfMarks++;
                }
            }
        } catch (MarkValueExceptions ex) {
            System.err.println(ex.getMessage());
            System.err.println("Student average mark error. Check student's " + findStudent.getId() + " marks");
        }

        if (amountOfMarks > 0) {
            System.out.println("\nStudent " + findStudent.getId() + " Average mark: " + averageMark);
        } else {
            System.out.println("\nThere is no student " + id + "  in group " + groupName);
        }
    }


    public void averageMarkOnSubjectInGroup(List<Student> group, String subject) throws MarkValueExceptions, NullAmountException {
        double sum = 0.0;
        int amountOfMarks = 0;

        if (group.isEmpty()) {
            throw new NullAmountException("Group " + groupName + " is empty");
        }

        for (Student student : group) {
            boolean markValueIsNotValid = (student.getMarksAndSubjects().values().iterator().next() < 0.0 || student.getMarksAndSubjects().values().iterator().next() > 10.0);

            if (student.getMarksAndSubjects().isEmpty()) {
                throw new NullAmountException("Student " + student.getId() + " has no subjects");
            }
            if (markValueIsNotValid) {
                throw new MarkValueExceptions("Invalid mark value.\nCheck student's " + student.getId() + " marks");
            }
            if (student.getMarksAndSubjects().containsKey(subject)) {
                sum += student.getMarksAndSubjects().get(subject);
                amountOfMarks++;
            }
        }

        if (amountOfMarks > 0) {
            double averageMarkOnSubject = sum / amountOfMarks;
            System.out.println("\nAverage mark on " + subject + " in group " + groupName + ": " + averageMarkOnSubject);

        } else {
            System.out.println("\nThere is no subject " + subject + " in group " + groupName);
        }
    }
}


class Student extends University {

    String id;
    private Map<String, Double> marksAndSubjects;

    public Student(String id, Map<String, Double> marksAndSubjects) {
        this.id = id;
        this.marksAndSubjects = marksAndSubjects;
    }

    public String getId() {
        return id;
    }

    public Map<String, Double> getMarksAndSubjects() {
        return marksAndSubjects;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", marksAndSubjects=" + marksAndSubjects +
                '}';
    }

    public double averageMark(Map<String, Double> map) throws MarkValueExceptions, NullAmountException {
        double sum = 0.0;
        if (map.isEmpty()) {
            throw new NullAmountException("Student has no subjects");
        } else {
            for (String mark : map.keySet()) {
                if (map.get(mark) < 0.0 || map.get(mark) > 10.0) {
                    throw new MarkValueExceptions("Invalid mark value");
                } else {
                    sum += map.get(mark);
                }
            }
        }
        return (sum / map.size());
    }
}




