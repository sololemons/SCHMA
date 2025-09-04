package com.studentservice.student.utilis;

import com.shared.dtos.RequestRoleDto;

import com.shared.dtos.RoleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class CheckRoleController {
    private final CheckRoleService checkRoleService;
@PostMapping("/get/role")
    public RoleDto checkRole(@RequestBody RequestRoleDto requestRoleDto){

   return checkRoleService.checkRoleForUser(requestRoleDto);
    }
}
