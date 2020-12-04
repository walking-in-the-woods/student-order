package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.domain.*;
import edu.javacourse.studentorder.exception.DaoException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

public class StudentOrderDaoImplTest {

    private static final Logger logger = LoggerFactory.getLogger(StudentOrderDaoImplTest.class);

    @BeforeClass
    public static void startUp() throws Exception {
        DBinit.startUp();
    }

    @Test
    public void saveStudentOrder() throws DaoException {
        StudentOrder so = buildStudentOrder(10);
        Long id = new StudentOrderDaoImpl().saveStudentOrder(so);
    }

    @Test(expected = DaoException.class)
    public void saveStudentOrderError() throws DaoException {
            StudentOrder so = buildStudentOrder(10);
            so.getHusband().setSurName(null);
            Long id = new StudentOrderDaoImpl().saveStudentOrder(so);
    }

    @Test
    public void getStudentOrders() throws DaoException {
        List<StudentOrder> list = new StudentOrderDaoImpl().getStudentOrders();
    }

    public StudentOrder buildStudentOrder(long id) {
        StudentOrder so = new StudentOrder();
        so.setStudentOrderId(id);
        so.setMarriageCertificateId("" + (123456000 + id));
        so.setMarriageDate(LocalDate.of(2016, 7, 4));
        RegisterOffice ro = new RegisterOffice(1L, "", "");
        so.setMarriageOffice(ro);

        Street street = new Street(1L, "Сампсоньевский проспект");

        Address address = new Address("195000", street, "10", "2", "121");

        // Husband
        //Adult husband = new Adult("Васильев", "Павел", "Николаевич", LocalDate.of(1995, 3, 18));
        Adult husband = new Adult("Васильев", "Павел", "Николаевич", LocalDate.of(1995, 3, 18));
        husband.setPassportSeries("" + (1000 + id));
        husband.setPassportNumber("" + (100000 + id));
        husband.setIssueDate(LocalDate.of(2017, 9, 15));
        PassportOffice po1 = new PassportOffice(1L, "", "");
        husband.setIssueDepartment(po1);
        husband.setStudentId("" + (100000 + id));
        husband.setAddress(address);
        husband.setUniversity(new University(2L, ""));
        husband.setStudentId("HH12345");
        // Wife
        //Adult wife = new Adult("Васильева", "Ирина", "Петровна", LocalDate.of(1997, 8, 21));
        Adult wife = new Adult("Васильева", "Ирина", "Петровна", LocalDate.of(1997, 8, 21));
        wife.setPassportSeries("" + (2000 + id));
        wife.setPassportNumber("" + (200000 + id));
        wife.setIssueDate(LocalDate.of(2018, 4, 5));
        PassportOffice po2 = new PassportOffice(2L, "", "");
        wife.setIssueDepartment(po2);
        wife.setStudentId("" + (200000 + id));
        wife.setAddress(address);
        wife.setUniversity(new University(1L, ""));
        wife.setStudentId("WW12345");
        // Child
        //Child child1 = new Child("Васильева", "Евгения", "Павловна", LocalDate.of(2016, 1, 11));
        Child child1 = new Child("Васильева", "Евгения", "Павловна", LocalDate.of(2016, 1, 11));
        child1.setBirthCertificateNumber("" + (300000 + id));
        child1.setIssueDate(LocalDate.of(2018, 6, 11));
        RegisterOffice ro2 = new RegisterOffice(2L, "", "");
        child1.setIssueDepartment(ro2);
        child1.setAddress(address);
        // Child
        //Child child2 = new Child("Васильев", "Александр", "Павлович", LocalDate.of(2018, 10, 24));
        Child child2 = new Child("Васильев", "Александр", "Павлович", LocalDate.of(2018, 10, 24));
        child2.setBirthCertificateNumber("" + (400000 + id));
        child2.setIssueDate(LocalDate.of(2018, 7, 19));
        RegisterOffice ro3 = new RegisterOffice(3L, "", "");
        child2.setIssueDepartment(ro3);
        child2.setAddress(address);

        so.setHusband(husband);
        so.setWife(wife);
        so.addChild(child1);
        so.addChild(child2);

        return so;
    }
}