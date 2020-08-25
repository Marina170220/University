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

    public static void averageMarkOnSubjectInUniversity(List<Faculty> university, String subject) throws NullAmountException {
        double averageMarkOnSubject;
        double sum = 0.0;
        int amountOfMarks = 0;
        if (university.size() == 0) {
            throw new NullAmountException("The University has no faculties");
        } else {
            for (Faculty faculty : university) {
                for (int i = 0; i < faculty.getFaculty().size(); i++) {
                    for (int j = 0; j < faculty.getFaculty().get(i).size(); j++) {
                        if (faculty.getFaculty().get(i).get(j).getMarksAndSubjects().containsKey(subject)) {
                            sum += faculty.getFaculty().get(i).get(j).getMarksAndSubjects().get(subject);
                            amountOfMarks++;
                        }
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


    /*public static void findStudent(List<List<Student>> faculty) {    //метод не работает!!!
        int counter = 0;
        System.out.println("Enter student id");
        Scanner userScanner = new Scanner(System.in);
        for (List<Student> list : faculty) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getId().contains(userScanner.next())) {
                    System.out.println("\nStudent id:" + list.get(i).getId());
                    try {
                        list.get(i).averageMark(list.get(i).getMarksAndSubjects());
                    } catch (MarkValueExceptions ex) {
                        System.err.println(ex.getMessage());
                        System.err.println("Check student's " + list.get(i).getId() + " marks");
                    }
                    counter++;
                }
            }
        }
        if (counter == 0) {
            System.out.println("Student not found");
        }
    }*/
    }


    class Faculty extends University {
        String facultyName;
        List<List<Student>> faculty;

        Faculty(List<List<Student>> faculty, String facultyName) {
            this.faculty = faculty;
            this.facultyName = facultyName;
        }

        public List<List<Student>> getFaculty() {
            return faculty;
        }

        public double averageMarkOnSubjectOnFaculty(List<List<Student>> faculty, String subject) throws NullAmountException {
            double averageMarkOnSubject = 0.0;
            double sum = 0.0;
            int amountOfMarks = 0;
            if (faculty.isEmpty()) {
                throw new NullAmountException("Faculty " + facultyName + " has no groups");
            } else {
                for (List<Student> group : faculty) {
                    if (group.isEmpty()) {
                        throw new NullAmountException("Faculty " + facultyName + " contains empty group");
                    } else {
                        for (int i = 0; i < group.size(); i++) {
                            if (group.get(i).getMarksAndSubjects().isEmpty()) {
                                throw new NullAmountException("Student " + group.get(i).getId() + " has no subjects");
                            } else {
                                if (group.get(i).getMarksAndSubjects().containsKey(subject)) {
                                    sum += group.get(i).getMarksAndSubjects().get(subject);
                                    amountOfMarks++;
                                }
                            }
                        }
                    }
                }
                if (amountOfMarks == 0) {
                    System.out.println("\nThere is no subject " + subject + " on faculty " + facultyName);
                } else {
                    averageMarkOnSubject = sum / amountOfMarks;
                    System.out.println("\nAverage mark on " + subject + " on faculty " + facultyName + ": " + averageMarkOnSubject);
                }
            }
            return averageMarkOnSubject;
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

        Group(String groupName) {
            this.groupName = groupName;
        }

        @Override
        public String toString() {
            return "Group{" +
                    "group=" + group +
                    '}';
        }

        public List<Student> fillGroup(File groupDirectory, List<Student> group) throws NullAmountException {
            Scanner scanner = null;

            if (groupDirectory.isDirectory()) {
                if (groupDirectory.listFiles().length == 0) {
                    throw new NullAmountException("Group " + groupDirectory.getName() + " is empty");
                } else {
                    for (File studentFile : groupDirectory.listFiles()) {
                        try {
                            scanner = new Scanner(studentFile);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        Map<String, Double> marksAndSubjects = new HashMap<>();
                        while (scanner.hasNext()) {
                            marksAndSubjects.put(scanner.next(), scanner.nextDouble());
                            group.add(new Student(studentFile.getName().substring(0, 4), marksAndSubjects));
                        }
                    }
                }
            }
     
            //System.out.println("Group " + groupDirectory.getName() + ": " + group);
            return group;
        }

        public void studentAverageMark(List<Student> group, String id) throws NullAmountException {
            double averageMark = 0.0;
            int amountOfMarks = 0;
            Student findStudent = null;
            try {
                for (Student student : group) {
                    if (student.getMarksAndSubjects().size() == 0) {
                        throw new NullAmountException("Student " + student.getId() + " has no subjects");
                    } else {
                        if (student.getId().contains(id)) {
                            findStudent = student;
                            averageMark = findStudent.averageMark(findStudent.getMarksAndSubjects());
                        }
                        amountOfMarks++;
                    }
                }

                if (amountOfMarks > 0) {
                    System.out.println("\nStudent " + findStudent.getId() + " Average mark: " + averageMark);
                } else {
                    System.out.println("\nThere is no student " + id + "  in group " + groupName);
                }
            } catch (MarkValueExceptions ex) {
                System.err.println(ex.getMessage());
                System.err.println("Student average mark error. Check student's " + findStudent.getId() + " marks");
            }
        }

        public void averageMarkOnSubjectInGroup(List<Student> group, String subject) throws NullAmountException {
            double sum = 0.0;
            int amountOfMarks = 0;

            for (Student student : group) {
                if (student.getMarksAndSubjects().size() == 0) {
                    throw new NullAmountException("Student " + student.getId() + " has no subjects");
                } else {
                    if (student.getMarksAndSubjects().containsKey(subject)) {
                        sum += student.getMarksAndSubjects().get(subject);
                        amountOfMarks++;
                    }
                }
            }
            if (amountOfMarks == 0) {
                System.out.println("\nThere is no subject " + subject + " in group " + groupName);
            } else {
                double averageMarkOnSubject = sum / amountOfMarks;
                System.out.println("\nAverage mark on " + subject + " in group " + groupName + ": " + averageMarkOnSubject);
            }
        }
    }

    /*public void findStudentInGroup(List<Student> group) { //метод не работает!!!
        int counter = 0;
        System.out.println("Enter student id");
        Scanner userScanner = new Scanner(System.in);
        for (Student student : group) {
            if (student.getId().contains(userScanner.next().trim())) {
                System.out.println("\nStudent id:" + student.getId());
                try {
                    student.averageMark(student.getMarksAndSubjects());
                } catch (MarkValueExceptions ex) {
                    System.err.println(ex.getMessage());
                }
                counter++;
            } else {
                System.out.println("Student not found");
            }
        }
    }
}*/

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




