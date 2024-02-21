package com.saew.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saew.dto.Student;

@RestController
@RequestMapping("/students")
public class StudentsController {

    private static List<Student> students = new ArrayList<>();
    static {
        students.add(new Student(1, "Juan", "juangg@gmail.com", 3.5f));
        students.add(new Student(2, "Maria", "mariawm@outlook.com", 3.8f));
        students.add(new Student(3, "Carlos", "carlosdea@hotmail.com", 3.2f));
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return students;
    }

    @GetMapping("/name/{nombre}")
    public List<Student> getStudentByName(@PathVariable String nombre) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getNombre().equalsIgnoreCase(nombre)) {
                result.add(student);
            }
        }
        return result;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return new ResponseEntity<>(student, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        students.add(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                Student student = students.get(i);
                student.setNombre(updatedStudent.getNombre());
                student.setCorreoElectronico(updatedStudent.getCorreoElectronico());
                student.setGPA(updatedStudent.getGPA());
                return new ResponseEntity<>(student, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                students.remove(i);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
