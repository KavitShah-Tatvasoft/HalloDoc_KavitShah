package com.uninor.enumeration;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.NamedEntityGraph;

@Getter
public enum SimAccquireTypeEnum {

    EXISTING(1, "Existing"),
    PORT(2, "Port");

    private final int simAccquireTypeId;
    private final String simAccquireType;

    SimAccquireTypeEnum(int simAccquireTypeId, String simAccquireType) {
        this.simAccquireTypeId = simAccquireTypeId;
        this.simAccquireType = simAccquireType;
    }
}
