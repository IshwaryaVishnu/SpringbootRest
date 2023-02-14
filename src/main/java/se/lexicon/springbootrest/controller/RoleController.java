package se.lexicon.springbootrest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.springbootrest.model.dto.RoleDto;
import se.lexicon.springbootrest.service.RoleService;

import java.util.List;

@RestController
    @RequestMapping("/api/v1/role")
    public class RoleController {

        @Autowired
        RoleService roleService;


        // http://localhost:8080/api/v1/role/
        @GetMapping("/") //fetch data read only method
        public ResponseEntity<List<RoleDto>> getAll() {
            //return ResponseEntity.ok(roleService.getAll()); // 200
            return ResponseEntity.status(HttpStatus.OK).body(roleService.getAll());
        }
    @Operation(summary = "Get a role by its id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Found the role", content = {@Content}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = {@Content})
    })
        @GetMapping("/{id}")
        public ResponseEntity<RoleDto> findById(@PathVariable("id") Integer id) {
            return ResponseEntity.ok(roleService.findById(id));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id){
            roleService.delete(id);
            //return ResponseEntity.noContent().build(); // 204
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    @Operation(summary = "Create a role")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Created the role",
                    content = {@Content(mediaType = "application/json", schema = @Schema(name = "Example", implementation = RoleDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request body", content = {@Content})
    })
        @PostMapping("/")
        public ResponseEntity<RoleDto> create(@RequestBody RoleDto dto){
            RoleDto createdROleDto = roleService.create(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdROleDto); // 201
        }


        @PutMapping("/") //update
        public ResponseEntity<Void> update(@RequestBody RoleDto dto){
            roleService.update(dto);
            return ResponseEntity.noContent().build();
        }
    }



