package com.luv2code.springmvc;

import com.luv2code.springmvc.models.CollegeStudent;
import com.luv2code.springmvc.repository.StudentDao;
import com.luv2code.springmvc.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestPropertySource("/application.properties")
@SpringBootTest
public class StudentAndGradeServiceTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentDao studentDao;

    @Test
    public void createStudentService() {
        studentService.createStudent("Chad", "Darby", "850562933@qq.com");
        CollegeStudent student = studentDao.findByEmailAdress("850562933@qq.com");
        assertEquals("850562933@qq.com", student.getEmailAddress(), "find by email");
    }
}
