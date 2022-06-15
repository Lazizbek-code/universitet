package uz.pdp.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjparelationships.entity.Address;
import uz.pdp.appjparelationships.entity.Group;
import uz.pdp.appjparelationships.entity.Student;
import uz.pdp.appjparelationships.payload.StudentDto;
import uz.pdp.appjparelationships.repository.AddressRepository;
import uz.pdp.appjparelationships.repository.GroupRepository;
import uz.pdp.appjparelationships.repository.StudentRepository;

import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    GroupRepository groupRepository;

    //1. VAZIRLIK
    @GetMapping("/forMinistry")
    public Page<Student> getStudentListForMinistry(@RequestParam int page) {
        //1-1=0     2-1=1    3-1=2    4-1=3
        //select * from student limit 10 offset (0*10)
        //select * from student limit 10 offset (1*10)
        //select * from student limit 10 offset (2*10)
        //select * from student limit 10 offset (3*10)
        Pageable pageable = PageRequest.of(page, 10);
        Page<Student> studentPage = studentRepository.findAll(pageable);
        return studentPage;
    }

    //2. UNIVERSITY
    @GetMapping("/forUniversity/{universityId}")
    public Page<Student> getStudentListForUniversity(@PathVariable Integer universityId,
                                                     @RequestParam int page) {
        //1-1=0     2-1=1    3-1=2    4-1=3
        //select * from student limit 10 offset (0*10)
        //select * from student limit 10 offset (1*10)
        //select * from student limit 10 offset (2*10)
        //select * from student limit 10 offset (3*10)
        Pageable pageable = PageRequest.of(page, 10);
        Page<Student> studentPage = studentRepository.findAllByGroup_Faculty_UniversityId(universityId, pageable);
        return studentPage;
    }

    //3. FACULTY
    @GetMapping("/forFaculty/{facultyId}")
    public Page<Student> getStudentListForFaculty(@PathVariable Integer facultyId,
                                                  @RequestParam Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        return studentRepository.findAllByGroup_FacultyId(facultyId, pageable);
    }

    //4. GROUP OWNER
    @GetMapping(value = "/group/{groupId}")
    public Page<Student> getStudentListForGroup(@PathVariable Integer groupId,
                                                @RequestParam Integer page) {

        Pageable pageable = PageRequest.of(page, 10);
        return studentRepository.findAllByGroupId(groupId, pageable);
    }

    @PostMapping()
    public String addStudent(@RequestBody StudentDto studentDto) {

        Optional<Address> optionalAddress = addressRepository.findById(studentDto.getAddressId());
        Optional<Group> optionalGroup = groupRepository.findById(studentDto.getGroupId());

        if (optionalAddress.isPresent() && optionalGroup.isPresent()) {

            Student newStudent = new Student();
            newStudent.setFirstName(studentDto.getFirstName());
            newStudent.setLastName(studentDto.getLastName());
            newStudent.setAddress(optionalAddress.get());
            newStudent.setGroup(optionalGroup.get());
            newStudent.setSubjects(studentDto.getSubjects());

            return "New Student saved";
        }
        return "Address or Group  not found";
    }

    @PutMapping(value = "/{id}")
    public String editStudent(@PathVariable Integer id, @RequestBody StudentDto studentDto) {

        Optional<Student> studentOptional = studentRepository.findById(id);

        if (studentOptional.isPresent()) {

            Optional<Address> optionalAddress = addressRepository.findById(studentDto.getAddressId());
            Optional<Group> optionalGroup = groupRepository.findById(studentDto.getGroupId());

            if (optionalAddress.isPresent() && optionalGroup.isPresent()) {

                Student editStudent = studentOptional.get();
                editStudent.setFirstName(studentDto.getFirstName());
                editStudent.setLastName(studentDto.getLastName());
                editStudent.setAddress(optionalAddress.get());
                editStudent.setGroup(optionalGroup.get());
                editStudent.setSubjects(studentDto.getSubjects());
                return "Student edited";
            }
            return "Address or Group  not found";
        }
        return "Student not found";
    }

    @DeleteMapping(value = "/{id}")
    public String deleteStudent(@PathVariable Integer id) {

        Optional<Student> optionalStudent = studentRepository.findById(id);

        if (optionalStudent.isPresent()) {
            studentRepository.deleteById(id);
            return "Student deleted";
        }
        return "Student not found";
    }


}
