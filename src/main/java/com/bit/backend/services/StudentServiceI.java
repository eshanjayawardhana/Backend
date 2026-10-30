package com.bit.backend.services;

import com.bit.backend.dtos.StudentDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentServiceI {
    StudentDto addStudent(StudentDto studentDto);
    List<StudentDto> getAllStudents();
    Page<StudentDto> getStudentsPage(int page, int size);
    StudentDto getStudentById(long id);
    StudentDto updateStudent(long id, StudentDto studentDto);
    StudentDto deleteStudent(long id);
}
