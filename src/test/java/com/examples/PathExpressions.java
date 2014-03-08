package com.examples;


import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.boon.Boon.*;
import static org.boon.Lists.*;
import static org.boon.Maps.map;
import static org.boon.Ok.okOrDie;
import static org.boon.core.reflection.BeanUtils.*;
import static org.boon.primitive.Chr.multiply;

/**
 * Created by Richard on 3/8/14.
 */
public class PathExpressions {

    @Test
    public void test() {
        PathExpressions.main();

    }

    public static class ContactInfo {
        String address;
        List<String> phoneNumbers;



    }

    public static class Employee {
        int id;
        int salary;
        String firstName;
        String lastName;

        ContactInfo contactInfo = new ContactInfo();

        public Employee() {
        }

        public Employee(int id, int salary, String firstName, String lastName,
                        String... phoneNumbers) {
            this.id = id;
            this.salary = salary;
            this.firstName = firstName;
            this.lastName = lastName;

            for (String phone : phoneNumbers) {
                contactInfo.phoneNumbers = lazyAdd(contactInfo.phoneNumbers, phone);
            }
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSalary() {
            return salary;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Employee employee = (Employee) o;

            if (id != employee.id) return false;
            if (salary != employee.salary) return false;
            if (firstName != null ? !firstName.equals(employee.firstName) : employee.firstName != null) return false;
            if (lastName != null ? !lastName.equals(employee.lastName) : employee.lastName != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = id;
            result = 31 * result + salary;
            result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
            result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "id=" + id +
                    ", salary=" + salary +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    "}";
        }
    }
    public static class Department {
        private String name;

        private List<Employee> employees;

        public Department() {
        }

        public Department(String name ) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Department add(Employee... employees) {
            this.employees = lazyAdd(this.employees, employees);
            return this;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Department that = (Department) o;

            if (employees != null ? !employees.equals(that.employees) : that.employees != null) return false;
            if (name != null ? !name.equals(that.name) : that.name != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (employees != null ? employees.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Department{" +
                    "name='" + name + '\'' +
                    ", employees=" + employees +
                    '}';
        }
    }


    public static void main(String... args) {

        //int id, int salary, String firstName, String lastName

        puts (multiply('_', 30), "From JAVA Objects", multiply('_', 30), "\n");

        List<Department> departments = list(
                new Department("Engineering").add(
                        new Employee(1, 100, "Rick", "Hightower", "555-555-1212"),
                        new Employee(2, 200, "John", "Smith", "555-555-1215", "555-555-1214", "555-555-1213"),
                        new Employee(3, 300, "Drew", "Donaldson", "555-555-1216"),
                        new Employee(4, 400, "Nick", "LaySacky", "555-555-1217")

                ),
                new Department("HR").add(
                        new Employee(1, 100, "Dianna", "Hightower", "555-555-1218"),
                        new Employee(2, 200, "Derek", "Smith", "555-555-1219"),
                        new Employee(3, 300, "Tonya", "Donaldson", "555-555-1220"),
                        new Employee(4, 400, "Sue", "LaySacky", "555-555-1221")

                )
        );


        showDepartmentInfoEmp(departments);



        puts (multiply('_', 30), "From LIST MAPS", multiply('_', 30), "\n");


        puts ("Now from lists and maps");

        List<?> list = list(
                    map(    "name", "Engineering",
                            "employees", list(
                               map("id", 1, "salary", 100, "firstName", "Rick", "lastName", "Hightower",
                                       "contactInfo", map("phoneNumbers",
                                                            list("555-555-1212")
                                                        )
                               ),
                               map("id", 2, "salary", 200, "firstName", "John", "lastName", "Smith",
                                       "contactInfo", map("phoneNumbers", list("555-555-1215",
                                                                    "555-555-1214", "555-555-1213"))),
                               map("id", 3, "salary", 300, "firstName", "Drew", "lastName", "Donaldson",
                                       "contactInfo", map("phoneNumbers", list("555-555-1216"))),
                               map("id", 4, "salary", 400, "firstName", "Nick", "lastName", "LaySacky",
                                       "contactInfo", map("phoneNumbers", list("555-555-1217")))

                            )
                    ),
                    map(    "name", "HR",
                        "employees", list(
                            map("id", 1, "salary", 100, "firstName", "Dianna", "lastName", "Hightower",
                                    "contactInfo",
                                        map("phoneNumbers", list("555-555-1218"))),
                            map("id", 2, "salary", 200, "firstName", "Derek", "lastName", "Smith",
                                    "contactInfo",
                                        map("phoneNumbers", list("555-555-1219"))),
                            map("id", 3, "salary", 300, "firstName", "Tonya", "lastName", "Donaldson",
                                    "contactInfo", map("phoneNumbers", list("555-555-1220"))),
                            map("id", 4, "salary", 400, "firstName", "Sue", "lastName", "LaySacky",
                                    "contactInfo", map("phoneNumbers", list("555-555-1221")))

                        )
                    )

        );


        showDepartmentInfo(list);



        puts (multiply('_', 30), "From JSON", multiply('_', 30), "\n");


        String json = toJson(list);
        puts (json);

        showDepartmentInfo((List) fromJson(json));


    }

    static boolean ok;


    private static void showDepartmentInfoEmp(List<?> departments) {
        puts ( "get the name of the first department",
                indexOf( departments, "[0].name") );

        okOrDie("indexOf [0].name is Engineering", indexOf( departments, "[0].name"));



        putl("get the employees from the second department",
                indexOf(departments, "[1].employees"));



        okOrDie("indexOf [1].employees returns list of employees",
                indexOf( departments, "[1].employees") instanceof List &&
                        iterator(indexOf(departments, "[1].employees")).next() instanceof Employee &&
                len(indexOf( departments, "[1].employees")) == 4
        );


        puts();


        putl("get all employees in all departments",
                indexOf(departments, "this.employees"));


        okOrDie("indexOf employees returns list of employees",
                indexOf( departments, "employees") instanceof List &&
                        iterator(indexOf(departments, "employees")).next() instanceof Employee &&
                        len(indexOf( departments, "employees")) == 8
        );

        puts();


        puts ( "Get the name of every department",
                indexOf(departments, "name") );

        puts();


        puts ( "get the first name of every employee",
                indexOf(departments, "employees.firstName") );



        okOrDie("indexOf employees.firstName returns list of employees' names",
                indexOf( departments, "employees.firstName") instanceof List &&
                        iterator(indexOf(departments, "employees.firstName")).next() instanceof String &&
                        len(indexOf( departments, "employees.firstName")) == 8
        );

        puts();


        putl("get the all of the phone numbers of all of the employees",
                indexOf(departments, "employees.contactInfo.phoneNumbers"));
    }


    private static void showDepartmentInfo(List<?> departments) {
        puts ( "get the name of the first department",
                indexOf( departments, "[0].name") );

        okOrDie("indexOf [0].name is Engineering", indexOf(departments, "[0].name"));



        putl("get the employees from the second department",
                indexOf(departments, "[1].employees"));



        okOrDie("indexOf [1].employees returns list of employees",
                indexOf( departments, "[1].employees") instanceof List &&
                        iterator(indexOf(departments, "[1].employees")).next() instanceof Map &&
                        len(indexOf( departments, "[1].employees")) == 4
        );






        puts();


        putl("get all employees in all departments",
                indexOf(departments, "this.employees"));



        okOrDie("indexOf employees returns list of employees",
                indexOf( departments, "employees") instanceof List &&
                        iterator(indexOf(departments, "employees")).next() instanceof Map &&
                        len(indexOf( departments, "employees")) == 8
        );

        puts();


        puts ( "Get the name of every department",
                indexOf(departments, "name") );

        puts();


        puts ( "get the first name of every employee",
                indexOf(departments, "employees.firstName") );


        okOrDie("indexOf employees.firstName returns list of employees' names",
                indexOf( departments, "employees.firstName") instanceof List &&
                        iterator(indexOf(departments, "employees.firstName")).next() instanceof String &&
                        len(indexOf( departments, "employees.firstName")) == 8
        );

        puts();


        putl("get the all of the phone numbers of all of the employees",
                indexOf(departments, "employees.contactInfo.phoneNumbers"));
    }
}
