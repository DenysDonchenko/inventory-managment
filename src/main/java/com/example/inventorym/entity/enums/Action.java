package com.example.inventorym.entity.enums;

import java.util.Arrays;
import java.util.UUID;

import static java.util.Objects.isNull;

public enum Action {

    SALE("4b50aa0c-4d04-11ec-81d3-0242ac130003"),
    RETURN("4757e40a-de19-48f3-8623-7e5fe542297b"),
    RECEPTION("70a1a095-d0d5-41b7-b177-b8884cd4bb8e"),
    UNKNOWN("47a5b04d-df53-486e-aaf5-1212ed3a103b");

    private final String actionId;

    Action(String statusId) {

        this.actionId = statusId;
    }

    public String getActionId() {
        return actionId;
    }

    public static Action getDocumentTypeFromUUID(UUID typeId) {
        if (isNull(typeId)) {
            return UNKNOWN;
        }

        return Arrays.stream(Action.values())
                .filter(parcelStatus -> parcelStatus.getActionId().equals(typeId.toString()))
                .findFirst()
                .orElse(UNKNOWN);
    }
}
