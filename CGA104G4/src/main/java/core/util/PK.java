package core.util;

import java.io.Serializable;
import java.util.Objects;

public class PK implements Serializable {
    public Integer admId;
    public Integer funcId;
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PK pk = (PK) o;
        return Objects.equals(admId, pk.admId)
                && Objects.equals(funcId, pk.funcId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(admId, funcId);
    }
}
