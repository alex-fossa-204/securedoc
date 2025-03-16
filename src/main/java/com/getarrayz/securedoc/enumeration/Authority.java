package com.getarrayz.securedoc.enumeration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.getarrayz.securedoc.constant.Permissions.ADMIN_AUTHORITIES;
import static com.getarrayz.securedoc.constant.Permissions.MANAGER_AUTHORITIES;
import static com.getarrayz.securedoc.constant.Permissions.SUPER_ADMIN_AUTHORITIES;
import static com.getarrayz.securedoc.constant.Permissions.USER_AUTHORITIES;


//todo такое себе решение, эти данные следует вынести в отдельный компонент, данные о ролях должны храниться в базе данныех
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum Authority {

    USER(USER_AUTHORITIES),
    ADMIN(ADMIN_AUTHORITIES),
    SUPER_ADMIN(SUPER_ADMIN_AUTHORITIES),
    MANAGER(MANAGER_AUTHORITIES);

    private final String value;



}
