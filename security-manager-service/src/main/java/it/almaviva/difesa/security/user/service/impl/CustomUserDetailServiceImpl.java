package it.almaviva.difesa.security.user.service.impl;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.almaviva.difesa.security.service.MSSipadService;
import it.almaviva.difesa.security.shared.util.StatusEnum;
import it.almaviva.difesa.security.user.service.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.almaviva.difesa.security.privilege.entity.Privilege;
import it.almaviva.difesa.security.privilege.repository.PrivilegeRepository;
import it.almaviva.difesa.security.role.entity.Role;
import it.almaviva.difesa.security.role.repository.RoleRepository;
import it.almaviva.difesa.security.shared.repository.UserRoleRelationalRepository;
import it.almaviva.difesa.security.user.repository.UserRepository;
import it.almaviva.difesa.shared.common.user.CustomUserDetail;
import lombok.extern.slf4j.Slf4j;
import javax.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
@Slf4j
public class CustomUserDetailServiceImpl implements CustomUserDetailsService {

	private UserRoleRelationalRepository userRoleRepository;
	private UserRepository userRepository;
	private PrivilegeRepository privilegeRepository;
	private RoleRepository roleRepository;
	private MSSipadService msSipadService;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String fiscalCode) throws UsernameNotFoundException {
		try {
			return loadUser(fiscalCode);
		} catch (URISyntaxException e) {
			log.error("Any employee found with this fiscal code:  " + fiscalCode);
			throw new EntityNotFoundException(StatusEnum.USER_NOT_FINDABLE_FISCAL_CODE.getNameMessage());
		}
	}

	/***
	 * Method to load info of saved user on SIPAD db
	 * @param fiscalCode fiscalCode of user to load
	 * @return
	 * @throws URISyntaxException
	 */
	private CustomUserDetail loadUser(String fiscalCode) throws URISyntaxException {
    	
		AtomicReference<CustomUserDetail> customUserDetail = new AtomicReference<>(new CustomUserDetail());
		var employeeDetail = msSipadService.findUserByCode(fiscalCode);
		var user = userRepository.findByUserId(employeeDetail.getAndipIdPk());
    	 if (user.isPresent()){
    	 	List<Long> rolesIds = userRoleRepository.findByUserId(user.get().getId());
    	 	List<String> roles = roleRepository.findAllById(rolesIds).stream().map(Role::getRoleCode).collect(Collectors.toList());
 			List<String> authorities = privilegeRepository.findPrivilegesByUserId(user.get().getId()).stream().map(Privilege::getName).collect(Collectors.toList());

		 	List<String> privileges = Stream.concat(roles.stream(), authorities.stream()).collect(Collectors.toList());

    	 	customUserDetail.set(
    	 			new CustomUserDetail(user.get().getId(),
							employeeDetail.getAndipIdPk(),
							employeeDetail.getAndipCodFsc(),
							employeeDetail.getAndipNome(),
							employeeDetail.getAndipCogn(),
							employeeDetail.getAndipCodFsc(),
							getAuthorities(privileges)
					)
			);
    	 } else {
			 log.error("Any employee found with this fiscal code:  " + fiscalCode);
			 throw new EntityNotFoundException( StatusEnum.USER_NOT_FINDABLE_FISCAL_CODE.getNameMessage());
    	 }
        return customUserDetail.get();
    }

	private Collection<? extends GrantedAuthority> getAuthorities(List<String> privileges) {
		List<GrantedAuthority> authorities = new ArrayList<>();

		privileges.stream().map(SimpleGrantedAuthority::new).forEach(authorities::add);

		return authorities;
	}

}
