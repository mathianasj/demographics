package dev.cloudfirst.demographics.funqy.cloudevent;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import dev.cloudfirst.demographics.eventsource.NewPerson;
import dev.cloudfirst.demographics.eventsource.PersonState;

@Mapper(componentModel = "cdi")
public interface EventMapper {
    PersonCreated toPersonCreated(NewPerson newPerson, String id);

    EmailUpdated toEmailUpdated(NewPerson newPerson, String id);

    void mergePersonState(PersonCreated personCreated, @MappingTarget PersonState personState);

    void mergePersonState(EmailUpdated emailUpdated, @MappingTarget PersonState personState);
}
