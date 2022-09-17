package com.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespModel implements Serializable {
    String compCode;
    String respStatus;
    String respMsg;
}
