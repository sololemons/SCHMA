package com.staffservice.staffservice.controllers;

import com.staffservice.staffservice.dtos.AssignmentDto;
import com.staffservice.staffservice.dtos.AssignmentRequest;
import com.staffservice.staffservice.dtos.ClassDto;
import com.staffservice.staffservice.dtos.StaffDto;
import com.staffservice.staffservice.entities.Assignments;
import com.staffservice.staffservice.services.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffServices;



    @GetMapping("/all")
    public ResponseEntity<List<StaffDto>> getAllStaff() {

        return ResponseEntity.ok(staffServices.getAllStaffMembers());
    }
    @GetMapping("/filter")
    public ResponseEntity<Page<StaffDto>> getStaff(
            @RequestParam(value = "staffId", required = false) Long staffId,
            @RequestParam(value = "admissionYear", required = false) Long admissionYear,
            @RequestParam(value = "department", required = false) String department,
            Pageable pageable) {

        Page<StaffDto> staff = staffServices.getFilteredStaff(staffId, admissionYear, department, pageable);
        return ResponseEntity.ok(staff);
    }


    @GetMapping("/id/{staffId}")
    public ResponseEntity<StaffDto> getStaffById(@PathVariable Long staffId) {

        return staffServices.getStaffByStaffId(staffId);
    }


    @DeleteMapping("delete/{staffId}")
    public ResponseEntity<String> deleteStaffById(@PathVariable  Long staffId) {

        return ResponseEntity.ok(staffServices.deleteStaffByID(staffId));
    }
    @PostMapping("/create/assignment")
    public ResponseEntity<String> createAssignment(@RequestBody AssignmentRequest request) {

        return ResponseEntity.ok( staffServices.createAssignment(request));
    }
    @GetMapping("/get/classes")
    public ResponseEntity<List<ClassDto>> getAllClasses(){
        return ResponseEntity.ok(staffServices.getAllClasses());
    }
    @GetMapping("/view/assignments")
    public ResponseEntity<List<AssignmentDto>> getAssignments(@RequestParam String className){
        return ResponseEntity.ok(staffServices.getAllAssignments(className));
}



}
