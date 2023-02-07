package se.lexicon.springbootrest.service;

import se.lexicon.springbootrest.model.dto.RoleDto;

import java.util.List;

public interface RoleService {
    List<RoleDto> getAll();

    RoleDto findById(Integer roleId);

    RoleDto create(RoleDto roleDto);

    void update(RoleDto roleDto);

    void delete(Integer roleId);
}
