package com.getarrayz.securedoc.event;

import com.getarrayz.securedoc.entity.UserEntity;
import com.getarrayz.securedoc.enumeration.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@AllArgsConstructor
@Data
public class UserEvent {

    private UserEntity user;

    private EventType type;

    private Map<?, ?> data;

}
