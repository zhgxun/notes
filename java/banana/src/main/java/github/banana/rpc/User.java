package github.banana.rpc;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int age;
    private String desc;
}
