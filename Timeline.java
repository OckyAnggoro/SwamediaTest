import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Timeline {

    private static int systemAnalyst = 2;
    private static int dba = 1;
    private static int backEndDeveloper = 2;
    private static int frontEndDeveloper = 1;
    private static int softwareTester = 1;

    // uom per day
    private static int saTime = 15;
    private static int dbaTime = 23;
    private static int backEndTime = 45;
    private static int frontEndTime = 46;
    private static int softwareTestTime = 21;

    private static double additionalEmployee = 10; // %
    private static int addSystemAnalyst = 0;
    private static int addDba = 1;
    private static int addBackEndDev = 4;
    private static int addFrontEndDev = 3;
    private static int addSoftwareTester = 2;

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

    public static void main(String[] args){

        Employee systemAnalyst = new Employee("System Analyst", saTime, addSystemAnalyst);
        Employee dba = new Employee("Data Base Administrator", dbaTime, addDba);
        Employee backEnd = new Employee("Backend Developer", backEndTime, addBackEndDev);
        Employee frontEnd = new Employee("Frontend Developer", frontEndTime, addFrontEndDev);
        Employee softwareTester = new Employee("Software Tester", softwareTestTime, addSoftwareTester);

        List<Employee> employees = new ArrayList<>();
        employees.add(systemAnalyst);
        employees.add(dba);
        employees.add(backEnd);
        employees.add(frontEnd);
        employees.add(softwareTester);

        int projectTotalDay = 0;
        for(Employee employee : employees){
            projectTotalDay += calculateTimeline(employee);
        }

        System.out.println("Total processing time " + projectTotalDay + " day");
        String date = "10/07/2020";
        LocalDate localDate = LocalDate.parse(date, formatter);
        calculateDueDate(projectTotalDay, localDate);
    }

    private static int calculateTimeline(Employee employee) {
        if (employee.getEmployeeTime() == 0 ){
            return 0;
        }
        double dayTotal = (employee.getEmployeeTime() * additionalEmployee)/100 * employee.getEmployeeAddition();
        double dayFinal = employee.getEmployeeTime() - dayTotal;
        int dayRounded = (int) Math.round(dayFinal);

        System.out.println(employee.getEmployeeName() + " has added " + employee.getEmployeeAddition() + " employee and the time required to be " + dayRounded + " day");
        return dayRounded;
    }

    private static void calculateDueDate(int dayTotal, LocalDate localDate) {
        if(dayTotal < 1){
            System.out.println(localDate);
        }

        int addedDay = 1;
        LocalDate result = localDate;
        while (addedDay < dayTotal){
            result = result.plusDays(1);
            if(!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)){
                addedDay++;
            }
        }

        System.out.println("================= RESULT ===================");
        System.out.println(formatter.format(result));
    }

    public static class Employee {
        private String employeeName;
        private int employeeTime;
        private int employeeAddition;

        public Employee(String employeeName, int employeeTime, int employeeAddition) {
            this.employeeName = employeeName;
            this.employeeTime = employeeTime;
            this.employeeAddition = employeeAddition;
        }

        public String getEmployeeName() {
            return employeeName;
        }

        public void setEmployeeName(String employeeName) {
            this.employeeName = employeeName;
        }

        public int getEmployeeTime() {
            return employeeTime;
        }

        public void setEmployeeTime(int employeeTime) {
            this.employeeTime = employeeTime;
        }

        public int getEmployeeAddition() {
            return employeeAddition;
        }

        public void setEmployeeAddition(int employeeAddition) {
            this.employeeAddition = employeeAddition;
        }
    }
}

