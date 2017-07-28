package tst.x.entities;

import javax.validation.constraints.NotNull;

import org.fuin.ddd4j.ddd.AggregateRootId;
import org.fuin.ddd4j.ddd.EntityType;
import org.fuin.ddd4j.ddd.StringBasedEntityType;
import org.fuin.objects4j.common.Contract;
import org.fuin.objects4j.vo.AbstractStringValueObject;
import org.fuin.objects4j.vo.ValueObject;

public final class AggregateXId extends AbstractStringValueObject implements
        AggregateRootId, ValueObject {

    private static final long serialVersionUID = 1000L;

    public static final EntityType TYPE = new StringBasedEntityType(
            "AggregateX");

    private String value;

    protected AggregateXId() {
        super();
    }

    public AggregateXId(@NotNull final String value) {
        super();
        Contract.requireArgNotNull("value", value);

        this.value = value;
    }

    public final String getValue() {
        return value;
    }

    @Override
    public final EntityType getType() {
        return TYPE;
    }

    @Override
    public final String asTypedString() {
        return TYPE + " " + asString();
    }

    @Override
    public final String asBaseType() {
        return getValue();
    }

}
