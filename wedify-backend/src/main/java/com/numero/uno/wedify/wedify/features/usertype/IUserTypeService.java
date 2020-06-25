package com.numero.uno.wedify.wedify.features.usertype;

import java.util.List;

public interface IUserTypeService {

	List<UserType> getAllUserTypes();

	UserType getUserTypeById(Long id);

	UserType getUserTypeByType(UserTypeEnum type);

}
