package dev.cloudfirst.demographics.eventsource;

import org.infinispan.protostream.GeneratedSchema;
import org.infinispan.protostream.annotations.AutoProtoSchemaBuilder;

@AutoProtoSchemaBuilder(includeClasses = { PersonState.class }, schemaPackageName = "demographics")
public interface PersonSchema extends GeneratedSchema {
    
}
