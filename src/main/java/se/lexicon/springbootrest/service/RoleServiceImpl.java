package se.lexicon.springbootrest.service;
// import the model mapper class
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.springbootrest.exception.DataDuplicateException;
import se.lexicon.springbootrest.exception.DataNotFoundException;
import se.lexicon.springbootrest.model.dto.RoleDto;
import se.lexicon.springbootrest.model.entity.Role;
import se.lexicon.springbootrest.repository.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
@Autowired
    RoleRepository roleRepository;
    @Autowired
    ModelMapper modelMapper;


    @Override
    public List<RoleDto> getAll() {
        List<Role> roleList = roleRepository.findAllByOrderByDesc();
        /*return roleList.stream()
                .map(role -> new RoleDto(role.getId(), role.getName()))
                .collect(Collectors.toList());*/

        return modelMapper.map(roleList, new TypeToken() {
        }.getType());
    }

    @Override
    public RoleDto findById(Integer roleId) {
        if (roleId == null) throw new IllegalArgumentException("role id was null");
        Optional<Role> optionalRole = roleRepository.findById(roleId);
        if (optionalRole.isPresent()) {
            Role entity = optionalRole.get();
            return modelMapper.map(entity, RoleDto.class);
        }
        return null;
    }

    @Override
    public RoleDto create(RoleDto roleDto) {
        if (roleDto == null) throw new IllegalArgumentException("role data was null");
        if (roleDto.getId() != 0) throw new IllegalArgumentException("role id should be null or zero");

        Role createdEntity = roleRepository.save(modelMapper.map(roleDto, Role.class));
        return modelMapper.map(createdEntity, RoleDto.class);
    }

    @Override
    public void update(RoleDto roleDto) {
        if (roleDto == null) throw new IllegalArgumentException("role data was null");
        if (roleDto.getId() == 0) throw new IllegalArgumentException("role id should not be zero");

        if (!roleRepository.findById(roleDto.getId()).isPresent())
            throw new DataNotFoundException("data not found error");

        if (roleRepository.findByName(roleDto.getName()).isPresent())
            throw new DataDuplicateException("duplicate error");
        roleRepository.save(modelMapper.map(roleDto, Role.class));

    }

    @Override
    public void delete(Integer roleId) {

        RoleDto roleDto = findById(roleId);
        if (roleDto == null) throw new DataNotFoundException("id was not valid");
        roleRepository.deleteById(roleId);
    }
}
